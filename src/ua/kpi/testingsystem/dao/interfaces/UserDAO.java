package ua.kpi.testingsystem.dao.interfaces;

import ua.kpi.testingsystem.beans.User;

/**
 * @version 1.0 20 квіт. 2011
 * @author Ivanov Yaroslav
 *
 */
public interface UserDAO {

	/**
	 * @param login - login of the user
	 * return password
	 */
	public User find(String login);
	
	public void close();
	
}
