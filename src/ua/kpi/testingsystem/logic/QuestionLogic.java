package ua.kpi.testingsystem.logic;

import java.util.List;

import ua.kpi.testingsystem.beans.Question;
import ua.kpi.testingsystem.dao.factories.DAOFactoryManager;
import ua.kpi.testingsystem.dao.interfaces.QuestionDAO;

/**
 * @version 1.0 15 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class QuestionLogic {
	
	protected QuestionLogic() {
		
	}

	public static List<Question> getQuestions(final int testId) {
		final QuestionDAO questionDAO = DAOFactoryManager.getFactory()
				.createQuestionDAO();
		final List<Question> questions = questionDAO.findByTestId(testId);
		questionDAO.close();
		return questions;
	}

	public static void saveQuestion(final Question question) {
		QuestionDAO questionDAO = DAOFactoryManager.getFactory()
				.createQuestionDAO();
		questionDAO.insertWithAnswers(question);
		questionDAO.close();
	}

	public static void deleteQuestion(final int idQuestion, final int idTest) {
		QuestionDAO questionDAO = DAOFactoryManager.getFactory().createQuestionDAO();
		questionDAO.delete(idQuestion, idTest);
		questionDAO.close();
	}

}
