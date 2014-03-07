package org.hmahout.db;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hmahout.cache.CacheManager;

/**
 * 数据库查询助手
 */
@SuppressWarnings("unchecked")
public class QueryHelper {
	private final static Log log = LogFactory.getLog(QueryHelper.class);
	private final static QueryRunner queryRunner = new QueryRunner();
	private final static ColumnListHandler columnListHandler = new ColumnListHandler(){
		@Override
		protected Object handleRow(ResultSet rs) throws SQLException {
			Object obj = super.handleRow(rs);
			if(obj instanceof BigInteger)
				return ((BigInteger)obj).longValue();
			if(obj instanceof BigDecimal)
				return ((BigDecimal)obj).longValue();
			
			return obj;
		}
	};
	
	private final static ScalarHandler scaleHandler = new ScalarHandler(){
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			Object obj = super.handle(rs);
			if(obj instanceof BigInteger)
				return ((BigInteger)obj).longValue();
			
			if(obj instanceof BigDecimal)
				return ((BigDecimal)obj).longValue();
			return obj;
		}		
	};
	
	private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>(){{
		add(Long.class);
		add(Integer.class);
		add(String.class);
		add(java.util.Date.class);
		add(java.sql.Date.class);
		add(java.sql.Timestamp.class);
		add(Double.class);
	}};
	
	private final static boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PrimitiveClasses.contains(cls) ;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws DBException 
	 */
	public static Connection getConnection() throws DBException {
		try{
			return DBManager.getConnection();
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	public static void startTransaction() throws DBException {
		try {
			getConnection().setAutoCommit(false);
			log.debug("Transaction is start.");
		} catch (SQLException e) {
			throw new DBException("BeginTransaction Error", e);
		}
	}
	
	public static void endTransaction() throws DBException {
		try{
			Connection connection = getConnection();
			connection.commit();//提交JDBC事务   
			connection.setAutoCommit(true);// 恢复JDBC事务的默认提交方式  
			log.debug("Transaction is commited.");
		}catch (SQLException e) {
			throw new DBException("Commit Transaction Error",e);
		}		
	}
	
	public static void rollBackTransaction(){
		try {
			getConnection().rollback();
			log.debug("Commit is rollbacked.");
		} catch (Exception e) {
			log.error("Rollback Commit Error",e);
		}
	}

	/**
	 * 读取某个对象
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T read(Class<T> beanClass, String sql, Object...params) throws DBException {
		try{
			log.debug("query params:"+joinArray(params));
			return (T)queryRunner.query(getConnection(), sql, isPrimitive(beanClass)?scaleHandler:new BeanHandler(beanClass), params);
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	public static <T> T readCache(Class<T> beanClass, String cache, Serializable key, String sql, Object...params) throws DBException {
		T obj = (T)CacheManager.get(cache, key);
		if(obj == null){
			obj = read(beanClass, sql, params);
			CacheManager.set(cache, key, (Serializable)obj);
		}
		return obj;
	}
	
	/**
	 * 对象查询
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> query(Class<T> beanClass, String sql, Object...params) throws DBException {
		try{
			log.debug("query params:"+joinArray(params));
			return (List<T>)queryRunner.query(getConnection(), sql, isPrimitive(beanClass)?columnListHandler:new BeanListHandler(beanClass), params);
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	/**
	 * 对象查询,每行数据用Map包装
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String ,Object>> queryForMap(String sql, Object...params) throws DBException {
		try{
			log.debug("query params:"+joinArray(params));
			return (List<Map<String ,Object>>)queryRunner.query(getConnection(), sql,new MapListHandler() , params);
		}catch(SQLException e){
			throw new DBException(e);
		}
	}

	/**
	 * 支持缓存的对象查询
	 * @param <T>
	 * @param beanClass
	 * @param cache_region
	 * @param key
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static <T> List<T> queryCache(Class<T> beanClass, String cache_region, Serializable key, String sql, Object...params) throws DBException {		
		List<T> objs = (List<T>)CacheManager.get(cache_region, key);
		if(objs == null){
			objs = query(beanClass, sql, params);
			CacheManager.set(cache_region, key, (Serializable)objs);
		}
		return objs;
	}
	
	/**
	 * 分页查询
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param page 
	 * @param num 每页数据条数，如果为0，则取出所有，但不超过Integer.MAX_VALUE条记录
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static <T> List<T> querySlice(Class<T> beanClass, String sql, int page, int num, Object...params) throws DBException {
		if(page < 0 || num < 0) 
			throw new IllegalArgumentException("Illegal parameter of 'page' or 'count', Must be positive.");
		int from = (page - 1) * num;
		num = (num > 0) ? num : Integer.MAX_VALUE;
		return query(beanClass, sql + " LIMIT ?,?", ArrayUtils.addAll(params, new Integer[]{from,num}));		
	}
	
	/**
	 * 支持缓存的分页查询
	 * @param <T>
	 * @param beanClass
	 * @param cache
	 * @param cache_key
	 * @param cache_obj_count
	 * @param sql
	 * @param page
	 * @param count
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static <T> List<T> querySliceCache(Class<T> beanClass, String cache, Serializable cache_key, int cache_obj_count, String sql, int page, int count, Object...params) throws DBException {
		List<T> objs = (List<T>)CacheManager.get(cache, cache_key);
		if(objs == null) {
			objs = querySlice(beanClass, sql, 1, cache_obj_count, params);
			CacheManager.set(cache, cache_key, (Serializable)objs);
		}
		if(objs == null || objs.size()==0)
			return objs;
		int from = (page - 1) * count;
		if(from < 0)
			return null;
		if((from+count) > cache_obj_count)//超出缓存的范围
			return querySlice(beanClass, sql, page, count, params);
		int end = Math.min(from + count, objs.size());
		if(from >= end)
			return null;
		return objs.subList(from, end);
	}
	
	/**
	 * 执行统计查询语句，语句的执行结果必须只返回一个数值
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static Integer count(String sql, Object...params) throws DBException {
		try{
			log.debug("count params:"+joinArray(params));
			Number num = (Number)queryRunner.query(getConnection(), sql, scaleHandler, params);
			return (num!=null)?num.intValue():-1;
		}catch(SQLException e){
			throw new DBException(e);
		}
	}

	/**
	 * 执行统计查询语句，语句的执行结果必须只返回一个数值
	 * @param cache_region
	 * @param key
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static long countCache(String cache_region, Serializable key, String sql, Object...params) throws DBException {
		Number value = (Number)CacheManager.get(cache_region, key);
		if(value == null){
			value = count(sql, params);
			CacheManager.set(cache_region, key, value);
		}
		return value.longValue();
	}

	/**
	 * 执行INSERT/UPDATE/DELETE语句
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public static int update(String sql, Object...params) throws DBException {
		try{			
			log.debug("update params:"+joinArray(params));
			return queryRunner.update(getConnection(), sql, params);
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	/**
	 * 批量执行指定的SQL语句
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException 
	 */
	public static int[] batch(String sql, Object[][] params) throws DBException {
		try{
			return queryRunner.batch(getConnection(), sql, params);
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	private static String joinArray(Object...params){
		StringBuffer sb = new StringBuffer();
		if(params == null){
			return "";
		}
		for(Object o : params){
			sb.append(o+",");
		}
		return sb.toString();
	}
	
}
