package org.hmahout.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hmahout.annotation.Column;
import org.hmahout.annotation.NotPersistent;
import org.hmahout.annotation.Table;
import org.hmahout.db.DBException;
import org.hmahout.db.QueryHelper;
import org.hmahout.entity.Entity;
import org.hmahout.util.Constants;
import org.hmahout.util.Page;
import org.hmahout.util.StringUtils;

public class Dao {
	
	private final static Log log = LogFactory.getLog(Dao.class);
	
	/**
	 * 插入bean中的数据到数据库
	 * @param tableName 插入的数据表
	 * @param obj 插入的实体对象
	 * @return
	 */
	boolean insert(String tableName,Object obj) throws DaoException{
		return insert(tableName, obj, null);
	}
	
	boolean insert(String tableName, Object obj, String conditions,Object...conditionValues )throws DaoException{
		return execute(tableName, obj, false, conditions, conditionValues);
	}
	
	public boolean insert(Entity object) throws DaoException{
		String tableName = getTableName(object);
		
		if(tableName == null)
			throw new DaoException(object.getClass()+" tableName is null");
		
		return this.insert(tableName,object);
	}
	
	public boolean update(Entity object) throws DaoException{
		String tableName = getTableName(object);
		
		if(tableName == null)
			throw new DaoException(object.getClass()+" tableName is null");
		
		Integer id = null;
		try {
			id = (Integer)PropertyUtils.getProperty(object, "id");
		} catch (Exception e) {
			throw new DaoException("delete failed："+object.getClass()+" 获取主键值失败，", e);
		}
		
		if(id == null || id.intValue()==0)
			throw new DaoException("delete failed："+object.getClass()+" id is null");
		
		String condition = " id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		return update(tableName,object, condition, param.toArray());
	}
	
	public boolean delete(Entity obj) throws DaoException{
		
		String tableName = getTableName(obj);
		
		if(tableName == null)
			throw new DaoException("delete failed："+obj.getClass()+" tableName is null");
		
		Integer id = null;
		try {
			id = (Integer)PropertyUtils.getProperty(obj, "id");
		} catch (Exception e) {
			throw new DaoException("delete failed："+obj.getClass()+" 获取主键值失败，", e);
		}
		
		if(id == null || id.intValue()==0)
			throw new DaoException("delete failed："+obj.getClass()+" id is null");
		
		String condition = " id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		return this.delete(tableName, condition,param.toArray());
	}
	
	public <T> T getById(Class<T> cl,Integer id) throws DaoException{
		String tableName = null;
		List<Object> params = new ArrayList<Object>();
		if(cl.isAnnotationPresent(Table.class)){
			Table e = (Table)cl.getAnnotation(Table.class);
			tableName = e.name(); 
		}
		if(tableName == null)
			throw new DaoException("get failed："+cl+" tableName is null");
		
		String sql = "select * from "+tableName+" where id=?";
		params.add(id);
		
		T t = QueryHelper.read(cl, sql, params.toArray());
		if(null==t){
			return t;
		}
		
		Field[] fields = cl.getDeclaredFields();
		Integer fkvalue = null;
		Object keyvalue = null;
		for(Field f : fields){
			if(f.isAnnotationPresent(NotPersistent.class)){
				NotPersistent np = f.getAnnotation(NotPersistent.class);
				String fk = np.fk();
				String key = np.key();
				if(StringUtils.isBlank(fk))
					continue;
				
				Class c = f.getType();
				try {
					Object o = null;
					if(StringUtils.isBlank(key)){
						fkvalue = (Integer) PropertyUtils.getProperty(t, fk);
						o = this.getById(c, fkvalue);
					} else {
						keyvalue = PropertyUtils.getProperty(t, key);
						o = this.getByPro(c, fk, keyvalue);
					}
					PropertyUtils.setProperty(t, f.getName(), o);
				} catch (Exception e) {
					log.info("", e);
					e.printStackTrace();
				}
				
			}
		}
		
		return t;
	}
	
	/** 通过属性取值 (只支持单一属性,现在用于延迟加载)*/
	public <T> T getByPro(Class<T> cl, String paramName, Object param) throws DaoException {
		String tableName = null;
		if(cl.isAnnotationPresent(Table.class)){
			Table e = (Table)cl.getAnnotation(Table.class);
			tableName = e.name();
		}
		
		if(tableName == null)
			throw new DaoException("get failed："+cl+" tableName is null");
		
		String sql = "Select * from " + tableName + " where " + paramName + "=?";
		T t = QueryHelper.read(cl, sql, param);
		
		Field[] fields = cl.getDeclaredFields();
		Integer fkvalue = null;
		Object keyvalue = null;
		for(Field f : fields){
			if(f.isAnnotationPresent(NotPersistent.class)){
				NotPersistent np = f.getAnnotation(NotPersistent.class);
				String fk = np.fk();
				String key = np.key();
				if(StringUtils.isBlank(fk))
					continue;
				
				Class c = f.getType();
				try {
					Object o = null;
					if(StringUtils.isBlank(key)){ // 如果没有key，则说明是外键关联
						fkvalue = (Integer) PropertyUtils.getProperty(t, fk);
						o = this.getById(c, fkvalue);
					} else { // 如果有key，则说明是属性关联
						keyvalue = PropertyUtils.getProperty(t, key);
						o = this.getByPro(c, fk, keyvalue);
					}
					PropertyUtils.setProperty(t, f.getName(), o);
				} catch (Exception e) {
					log.info("", e);
					e.printStackTrace();
				}
				
			}
		}
		return t;
	}
	
	public <T> Page<T> queryForPage( Class<T> cls,String sql,Page page, Object[] args)throws DaoException {
		if(page.getPage() < 0 || page.getPer() < 0) 
			throw new DBException("Illegal parameter of 'page' or 'count', Must be positive.");
		Long totalCount = QueryHelper.read(Long.class,getSqlCount(sql),args);
		if(totalCount == null)
			totalCount = 0l;
		//int from = (page.getPage() - 1) * page.getPer();
		//List<T> resList = QueryHelper.query(cls,sql + " LIMIT ?,?",ArrayUtils.addAll(args, new Integer[]{from,page.getPer()}));
		List<T> resList = QueryHelper.querySlice(cls, sql, page.getPage(), page.getPer(), args);
		
		
		Field[] fields = cls.getDeclaredFields();
		Integer fkvalue = null;
		Object keyvalue = null;
		for(Field f : fields){
			if(f.isAnnotationPresent(NotPersistent.class)){
				NotPersistent np = f.getAnnotation(NotPersistent.class);
				String fk = np.fk();
				String key = np.key();
				if(StringUtils.isBlank(fk))
					continue;
				for(T t : resList){
					Class c = f.getType();
					try {
						Object o = null;
						if(StringUtils.isBlank(key)){
							fkvalue = (Integer) PropertyUtils.getProperty(t, fk);
							o = this.getById(c, fkvalue);
						} else {
							keyvalue = PropertyUtils.getProperty(t, key);
							o = this.getByPro(c, fk, keyvalue);
						}
						PropertyUtils.setProperty(t, f.getName(), o);
					} catch (Exception e) {
						log.info("", e);
						e.printStackTrace();
					}
				}
			}
		}
		
		page.setTotalRecords( totalCount.intValue() );
		page.setResults(resList);
		return page;
	}
	
	public <T> List<T> query(Class<T> cls,String tableName) throws DaoException {
		String sql = "select * from "+tableName;
		return query(cls, sql, new Object[0]);
	}
	
	public <T> List<T> query( Class<T> cls,String sql, Object[] args)throws DaoException {

		List<T> resList = QueryHelper.query(cls, sql, args);
		
		Field[] fields = cls.getDeclaredFields();
		Integer fkvalue = null;
		Object keyvalue = null;
		for(Field f : fields){
			if(f.isAnnotationPresent(NotPersistent.class)){
				NotPersistent np = f.getAnnotation(NotPersistent.class);
				String fk = np.fk();
				String key = np.key();
				if(StringUtils.isBlank(fk))
					continue;
				
				for(T t : resList){
					Class c = f.getType();
					try {
						Object o = null;
						if(StringUtils.isBlank(key)){
							fkvalue = (Integer) PropertyUtils.getProperty(t, fk);
							o = this.getById(c, fkvalue);
						} else {
							keyvalue = PropertyUtils.getProperty(t, key);
							o = this.getByPro(c, fk, keyvalue);
						}
						PropertyUtils.setProperty(t, f.getName(), o);
					} catch (Exception e) {
						log.info("", e);
						e.printStackTrace();
					}
				}
			}
		}
		
		return resList;
	}
	
	public <T> T query( Class<T> cls, T t)throws DaoException {
		Field[] fields = cls.getDeclaredFields();
		Integer fkvalue = null;
		Object keyvalue = null;
		for(Field f : fields){
			if(f.isAnnotationPresent(NotPersistent.class)){
				NotPersistent np = f.getAnnotation(NotPersistent.class);
				String fk = np.fk();
				String key = np.key();
				if(StringUtils.isBlank(fk))
					continue;
				
				Class c = f.getType();
				try {
					Object o = null;
					if(StringUtils.isBlank(key)){
						fkvalue = (Integer) PropertyUtils.getProperty(t, fk);
						o = this.getById(c, fkvalue);
					} else {
						keyvalue = PropertyUtils.getProperty(t, key);
						o = this.getByPro(c, fk, keyvalue);
					}
					PropertyUtils.setProperty(t, f.getName(), o);
				} catch (Exception e) {
					log.info("", e);
					e.printStackTrace();
				}
			}
		}
		return t;
	}

	private String getSqlCount(String sql){
		String sqlBak = sql.toLowerCase();
		String searchValue = " from ";
		String sqlCount = "select count(*) from "+sql.substring(sqlBak.indexOf(searchValue)+searchValue.length(),sqlBak.length());
		
		return sqlCount;
	}
	
	private String getTableName(Object object){
		if(object.getClass().isAnnotationPresent(Table.class)){
			Table e = (Table)object.getClass().getAnnotation(Table.class);
			return e.name(); 
		}
		return null;
	}
	
	/**
	 * 更新指定的bean 中的数据到数据库中,以bean 中的id值 作为更新条件
	 * @param tableName 更新的表
	 * @param obj 带更新数据的实体对象
	 * @return
	 */
	boolean update(String tableName,Object obj) throws DaoException{
		Object idValue = null;
		try{
			idValue = PropertyUtils.getProperty(obj, "id");
			if(idValue==null)
				log.warn("id's value is NULL where update to "+tableName);
				
		}catch (Exception e) {
			throw new DaoException("the bean with out id field when update to "+tableName, e);
		}
		
		return update(tableName, obj, "id = ?", idValue);
	}
	
	/**
	 * 更新bean中的值到表tableName中，要注意<b>条件中的问号与值一定对应
	 * @param tableName 数据表名
	 * @param obj 更新的实体对象
	 * @param conditions 更新条件，如："id=? and companyid = ?"
	 * @param conditionValues 更新条件值，如例子中的更新条件可以写 :Integer(1),Integer(15);
	 * @return
	 * @throws DaoException
	 */
	boolean update(String tableName,Object obj,String conditions,Object...conditionValues) throws DaoException{
		return execute(tableName, obj, true, conditions,conditionValues);
	}
	
	/**
	 * 执行更新，插入操作
	 * @param tableName 操作的数据表
	 * @param obj 实体bean
	 * @param isUpdate 是否是插入数据，true为更新
	 * @param conditions 更新条件，如"id=? and companyid = ?",注意：
	 * <b>仅更新时才有效果</b>
	 * @param conditionValues 更新条件的值对象，注意要与条件中和问号对应
	 * @return
	 * @throws DaoException
	 */
	private boolean execute(String tableName,Object obj,boolean isUpdate,String conditions,Object...conditionValues) throws DaoException{
		String executeMethod = "INSERT";
		if( isUpdate )
			executeMethod = "UPDATE";
		
		// 如果表名为空，则直接报错
		if( StringUtils.isBlank(tableName)) {
			throw new DaoException("table name is null where "+executeMethod+" with dao");
		}
		String sql = null;
		List<Object> params = new ArrayList<Object>();	
		
		try {
			// 生成插入或者更新语句
			StringBuffer sqlBuffer = new StringBuffer( executeMethod );
			sqlBuffer.append(" ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(" SET ");
			
			// 生成pro1=? ,pro2=? 的样式，同时设置他对应的参数
			keyAndParams(obj,sqlBuffer,params);
				
			sql = sqlBuffer.substring(0,sqlBuffer.length()-2);
			if( isUpdate ){
				// 如果是
				sql = sql +" WHERE "+ conditions;
				for(Object o : conditionValues){
					params.add(o);
				}				
			}
			
			if( params.size() > 0 ){		
				log.debug(executeMethod+" sql is:"+sql);
				log.debug(executeMethod+" values:"+ArrayUtils.toString(params));				
				return QueryHelper.update(sql, params.toArray())>0;
			}
			
		} catch (Exception e) {
			log.error(e,e);
			throw new DaoException("create "+executeMethod+" sql error",e);
		}	
		
		return false;
	}
	
	private void keyAndParams( Object obj, StringBuffer sqlBuffer, List<Object> params ) throws Exception{
		String field = null;
		Object value = null;
		Map map=BeanUtils.describe( obj );
		Iterator fields = map.keySet().iterator();
		
		while(fields.hasNext()){
			field = (String)fields.next();
			value = PropertyUtils.getProperty(obj, field);
			Class c = PropertyUtils.getPropertyType(obj, field);
			// 对null过滤， 但对Double类型的不进行过滤
			if( (value == null && (c != Double.class)) || "class".equalsIgnoreCase(field) )
				continue;
			Field f = null;
			try{
				 f = obj.getClass().getDeclaredField(field);
			}catch(Exception e){}
			if(f==null || f.isAnnotationPresent(NotPersistent.class)){
				continue;
				
			}else if(f.isAnnotationPresent(Column.class)){
				Column column = f.getAnnotation(Column.class);
				sqlBuffer.append(column.name());
				
			}else{
				sqlBuffer.append(field);
			}
			
			// 生成插入或者更新值，同时他的对应的值也要一一对应									
			sqlBuffer.append(" = ?, ");
			if(Constants.WEB_KEY_NULL.equals(value))
				params.add(null);
			else
				params.add(value);
		}
	}
	
	public boolean delete(String table,Integer id)throws DaoException{
		return delete(table,"id=?",id);
	}
	
	public boolean delete(String table,String conditions,Object...conditionValues) throws DaoException{
		
		if(StringUtils.isBlank(table)
				||StringUtils.isBlank(conditions))
			throw new DaoException("无法生成删除SQL语句");
		
		boolean f = false;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM "+table+" WHERE "+conditions); 
			List<Object> params = new ArrayList<Object>();
			for(Object o : conditionValues){
				params.add(o);
			}
			
			if( params.size() > 0 ){		
				log.debug("sql is:"+sql);
				log.debug("values:"+ArrayUtils.toString(params));

				f =  QueryHelper.update(sql.toString(), params.toArray())>0;
			}			
			
		}catch (Exception e) {
			throw new DaoException("delete FROM "+table+" sql error");
		}
		
		return f;
	}
}
