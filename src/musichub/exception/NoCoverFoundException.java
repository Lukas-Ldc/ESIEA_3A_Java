package musichub.exception;

import java.lang.Exception;

public class NoCoverFoundException extends Exception {
	
	public NoCoverFoundException () {}
	
	public NoCoverFoundException (String msg) {
		super(msg);
	}	
}