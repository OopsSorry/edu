package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.dao.interfaces.UserDAO;
import ua.kpi.testingsystem.exceptions.DAOException;

/**
 * @version 1.0 20 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCUserDAO extends JDBCAbstractDAO implements UserDAO {

	private static final String COLUMN_IDUSER = "idusers";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_LOGIN = "login";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_SURNAME = "surname";

	private static final String TABLE = "users";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_LOGIN + " = ?";


	public JDBCUserDAO(final Connection connection) {
		super(connection);
	}

	public User find(String login) throws DAOException {
		UserReader userReader = new UserReader();
		query.select(userReader, SELECT_BY_ID, login);
		return userReader.getUser();
	}
	
	private class UserReader implements Rowmapper {

		private List<User> users;

		public void read(ResultSet rs) throws SQLException {
			users = new LinkedList<User>();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt(COLUMN_IDUSER));
				user.setType(rs.getString(COLUMN_TYPE));
				user.setLogin(rs.getString(COLUMN_LOGIN));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setName(rs.getString(COLUMN_NAME));
				user.setSurName(rs.getString(COLUMN_SURNAME));
				users.add(user);
			}
		}

		public User getUser() {
			if (users.isEmpty()) {
				return null;
			} else {
				return users.get(0);
			}
		}

	}

}
