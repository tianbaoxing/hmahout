package org.hmahout.db;

public class DBException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DBException(String s) {
		super(s);
	}

	public DBException(String s, Throwable e) {
		super(s, e);
	}

	public DBException(Throwable e) {
		super(e);
	}

}
