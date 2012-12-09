package ua.kpi.testingsystem.exceptions;

/**
 * @author Ivanov Yaroslav
 *
 */
public class BuildException extends RuntimeException {

	private static final long serialVersionUID = -2766218601088729955L;

	public BuildException(String message) {
		super(message);
	}
	
	public BuildException(String message, Throwable cause){
		super(message, cause);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
	
}
