package org.hmahout.cache;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 缓存助手
 */
public class CacheManager {
	
	private final static Log log = LogFactory.getLog(CacheManager.class);
	private static CacheProvider provider;
	
	public static void init(String prv_name){
		try{
			CacheManager.provider = (CacheProvider)Class.forName(prv_name).newInstance();
			CacheManager.provider.start();
		}catch(Exception e){
			log.fatal("Unabled to initialize cache provider:" + prv_name + ", using ehcache default.", e);
			CacheManager.provider = new EhCacheProvider();
		}
	}

	private final static Cache _getCache(String cache_name) {
		if(provider == null){
			provider = new EhCacheProvider();
			provider.start();
		}
		return provider.buildCache(cache_name);
	}

	/**
	 * 获取缓存中的数据
	 * @param name 缓存名
	 * @param key  
	 * @return
	 */
	public final static Object get(String name, Serializable key){
		if(name!=null && key != null)
			return _getCache(name).get(key);
		return null;
	}
	
	/**
	 * 获取缓存中的数据
	 * @param <T>
	 * @param resultClass
	 * @param name
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T get(Class<T> resultClass, String name, Serializable key){
		if(name!=null && key != null)
			return (T)_getCache(name).get(key);
		return null;
	}
	
	/**
	 * 写入缓存
	 * @param name
	 * @param key
	 * @param value
	 */
	public final static void set(String name, Serializable key, Serializable value){
		if(name!=null && key != null && value!=null){
			synchronized (key) {
				_getCache(name).put(key, value);	
			}
		}
	}
	
	/**
	 * 清除缓冲中的某个数据
	 * @param name
	 * @param key
	 */
	public final static void evict(String name, Serializable key){
		if(name!=null && key != null)
			_getCache(name).remove(key);		
	}
	
}
