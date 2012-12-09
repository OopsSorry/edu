package ua.kpi.testingsystem.logic;

import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.dao.factories.DAOFactoryManager;
import ua.kpi.testingsystem.dao.interfaces.UserDAO;
import ua.kpi.testingsystem.security.UserType;

/**
 * @version 1.0 5 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class LoginLogic {

	protected LoginLogic() {
	}
	
	/**
	 * 
	 * @param login
	 *            login of the user
	 * @param password
	 *            password of the user
	 * @return if user exist in datasource then method return him, else return
	 *         UNAUTHORIZED user
	 * @throws DAOException
	 */
	public static User getUser(final String login, final String password) {
		User user;
		if ((login == null) || (password == null) 
				|| (login.isEmpty()) || (password.isEmpty())) {
			return createAnonymousUser();
		}
		user = getUserFromDAO(login);
		if ((user == null) || (!password.equals(user.getPassword()))) {
			user = createAnonymousUser();
		}
		return user;
	}

	private static User getUserFromDAO(final String login) {
		User user;
		final UserDAO userDAO = DAOFactoryManager.getFactory().createUserDAO();
		user = userDAO.find(login);
		userDAO.close();
		return user;
	}

	private static User createAnonymousUser() {
		final User user = new User();
		user.setType(UserType.ANONYMOUS);
		return user;
	}

}
