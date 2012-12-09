package ua.kpi.testingsystem.logic;

import java.util.List;
import ua.kpi.testingsystem.beans.Question;
import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.dao.factories.DAOFactoryManager;
import ua.kpi.testingsystem.dao.interfaces.AnswerDAO;
import ua.kpi.testingsystem.dao.interfaces.QuestionDAO;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class TestState {

	protected Test currentTest;
	protected List<Question> questions;
	protected int currentQuestion;

	public TestState(final Test test) {
		this.currentTest = test;
		final QuestionDAO questionDAO = DAOFactoryManager.getFactory()
				.createQuestionDAO();
		questions = questionDAO.findByTestId(test.getIdTest());
		questionDAO.close();
		currentQuestion = 0;
	}

	public boolean hasMoreQuestions() {
		return (currentQuestion < questions.size() - 1);
	}
	
	public int getQuestionId() {
		return questions.get(currentQuestion).getIdQuestion();
	}
	
	public Question getCurrentQuestion() {
		return getQuestion(currentQuestion);
	}

	public Question getNextQuestion() {
		currentQuestion++;
		return getQuestion(currentQuestion);
	}
	
	protected Question getQuestion(final int questionNumber) {
		Question question = null;
		if ((questionNumber >= 0) && (questionNumber < questions.size())) {
			question = questions.get(questionNumber);
			final AnswerDAO answerDAO = DAOFactoryManager.getFactory().createAnswerDAO();
			question.setAnswers(answerDAO.findByQuestionId(question.getIdQuestion()));
			answerDAO.close();
		}
		return question;
	}
	
	public boolean hasQuestions() {
		return (!questions.isEmpty());
	}

}
