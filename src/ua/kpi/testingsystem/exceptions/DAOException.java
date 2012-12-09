package ua.kpi.testingsystem.exceptions;

/**
 * @author Ivanov Yaroslav
 *
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -5255783641770689260L;
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOException (Throwable cause) {
		super(cause);
	}

}
