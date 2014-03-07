package org.hmahout.db;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class DBInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1466563316533521207L;
	private static final Logger log = Logger.getLogger(DBInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		long start = System.currentTimeMillis();   
		//执行目标方法 (调用下一个拦截器, 或执行Action)    
		String res = null;
		try {
			res = invocation.invoke();
		} finally {
			DBManager.closeConnection();
			log.debug("Action Exec Time:"+(System.currentTimeMillis()-start));
		}
		    
		return res;    
	}

}
