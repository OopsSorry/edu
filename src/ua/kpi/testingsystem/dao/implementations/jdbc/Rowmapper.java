package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.0 27 квіт. 2011
 * @author Ivanov Yaroslav
 *
 */
public interface Rowmapper {
	
	void read(ResultSet rs) throws SQLException;

}
