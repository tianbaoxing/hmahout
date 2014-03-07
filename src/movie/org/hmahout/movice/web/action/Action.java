package org.hmahout.movice.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport implements SessionAware,
		ServletRequestAware {
	
	private static final long serialVersionUID = 1984109682612393075L;

	protected Map session = null;

	protected HttpServletRequest request = null;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

}
