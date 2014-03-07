package org.hmahout.dao;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String s){
		super(s);
	}
	
	public DaoException( Throwable e ){
		super(e);
	}
	
	public DaoException(String s, Throwable e) {
		super(s, e);
	}
}
