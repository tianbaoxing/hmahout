package org.hmahout.cache;

/**
 * Something went wrong in the cache
 */
public class CacheException extends RuntimeException {

	private static final long serialVersionUID = -3044257085401548929L;

	public CacheException(String s) {
		super(s);
	}

	public CacheException(String s, Throwable e) {
		super(s, e);
	}

	public CacheException(Throwable e) {
		super(e);
	}
	
}
