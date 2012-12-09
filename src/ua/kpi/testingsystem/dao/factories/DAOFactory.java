package ua.kpi.testingsystem.dao.factories;

import ua.kpi.testingsystem.dao.interfaces.AnswerDAO;
import ua.kpi.testingsystem.dao.interfaces.QuestionDAO;
import ua.kpi.testingsystem.dao.interfaces.SubjectDAO;
import ua.kpi.testingsystem.dao.interfaces.TestDAO;
import ua.kpi.testingsystem.dao.interfaces.UserDAO;

/**
 * @version 1.0 17 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public abstract class DAOFactory {

	public abstract TestDAO createTestDAO();

	public abstract QuestionDAO createQuestionDAO();

	public abstract AnswerDAO createAnswerDAO();

	public abstract UserDAO createUserDAO();

	public abstract SubjectDAO createSubjectDAO();

}
