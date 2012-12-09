package ua.kpi.testingsystem.dao.factories;

import ua.kpi.testingsystem.dao.connection.ConnectionManager;
import ua.kpi.testingsystem.dao.implementations.jdbc.JDBCAnswerDAO;
import ua.kpi.testingsystem.dao.implementations.jdbc.JDBCQuestionDAO;
import ua.kpi.testingsystem.dao.implementations.jdbc.JDBCSubjectDAO;
import ua.kpi.testingsystem.dao.implementations.jdbc.JDBCTestDAO;
import ua.kpi.testingsystem.dao.implementations.jdbc.JDBCUserDAO;
import ua.kpi.testingsystem.dao.interfaces.AnswerDAO;
import ua.kpi.testingsystem.dao.interfaces.QuestionDAO;
import ua.kpi.testingsystem.dao.interfaces.SubjectDAO;
import ua.kpi.testingsystem.dao.interfaces.TestDAO;
import ua.kpi.testingsystem.dao.interfaces.UserDAO;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCDAOFactory extends DAOFactory {

	@Override
	public TestDAO createTestDAO() {
		return new JDBCTestDAO(
				ConnectionManager.getInstance().getConnection());
	}

	@Override
	public QuestionDAO createQuestionDAO() {
		return new JDBCQuestionDAO(
				ConnectionManager.getInstance().getConnection());
	}

	@Override
	public AnswerDAO createAnswerDAO() {
		return new JDBCAnswerDAO(
				ConnectionManager.getInstance().getConnection());
	}

	@Override
	public UserDAO createUserDAO() {
		return new JDBCUserDAO(
				ConnectionManager.getInstance().getConnection());
	}

	@Override
	public SubjectDAO createSubjectDAO() {
		return new JDBCSubjectDAO(
				ConnectionManager.getInstance().getConnection());
	}
}
