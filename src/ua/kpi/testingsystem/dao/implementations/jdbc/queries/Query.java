package ua.kpi.testingsystem.dao.implementations.jdbc.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.dao.connection.ConnectionManager;
import ua.kpi.testingsystem.dao.implementations.jdbc.Rowmapper;
import ua.kpi.testingsystem.exceptions.DAOException;

/**
 * @version 1.0 14 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class Query {
	private static final Logger LOGGER = Logger.getLogger(Query.class);

	private final Connection connection;

	public Query(final Connection connection) {
		this.connection = connection;
	}
	
	public Connection getConnection() {
		return connection;
	}

	private PreparedStatement prepare(final String SQL,
			final Object[] parameters) throws SQLException {
		final PreparedStatement prstTemp = connection.prepareStatement(SQL);
		for (int i = 0; i < parameters.length; i++) {
			prstTemp.setObject(i + 1, parameters[i]);
		}
		return prstTemp;
	}

	public void insert(final String SQL, final Object... parameters) {
		PreparedStatement prst = null;
		try {
			prst = prepare(SQL, parameters);
			prst.execute();
		} catch (SQLException ex) {
			close();
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			closeResources(prst);
		}
	}
	
	public void select(final Rowmapper rowmapper, final String SQL,
			final Object... parameters) {
		PreparedStatement prst = null;
		try {
			prst = prepare(SQL, parameters);
			rowmapper.read(prst.executeQuery());
		} catch (SQLException ex) {
			close();
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			closeResources(prst);
		}
	}

	public void update(final String SQL, final Object... parameters) {
		PreparedStatement prst = null;
		try {
			prst = prepare(SQL, parameters);
			prst.execute();
		} catch (SQLException ex) {
			close();
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			closeResources(prst);
		}
	}

	public void delete(final String SQL, final Object... parameters) {
		PreparedStatement prst = null;
		try {
			prst = prepare(SQL, parameters);
			prst.execute();
		} catch (SQLException ex) {
			close();
			throw new DAOException(ex.getMessage(), ex);
		} finally {
			closeResources(prst);
		}
	}

	private void closeResources(final PreparedStatement prst) {
		try {
			if (prst != null) {
				prst.close();
			}
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	public void close() {
		ConnectionManager.getInstance().freeConnection(connection);
	}

}
