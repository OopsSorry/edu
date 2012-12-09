package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.beans.Answer;
import ua.kpi.testingsystem.beans.Question;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.QuestionLogic;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

/**
 * @author Ivanov Yaroslav
 *
 */
public class CreateQuestionCommand implements Command {
	
	private static final int NUMBER_OF_FIRST_ANSWER = 1;
	private static final int MIN_COUNT_OF_ANSWERS = 2;

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		final User tutor = SessionLogic.getUser(request);
		Security.ensure(tutor, AccessList.EDIT_TEST);
		CommandResponse commandResponse;
		final Integer currentTest = (Integer) session.getAttribute(
				Pages.SESSION_CURRENT_TEST_ID);
		if (currentTest == null) {
			commandResponse = new RedirectResponse(PageManager.PAGE_URL_TUTOR);
		} else {
			final Question question = buildQuestionFromRequest(request);
			if (question == null) {
				commandResponse = new ForwardResponse(PageManager.PAGE_PATH_INVALID);
			} else {
				QuestionLogic.saveQuestion(question);
				commandResponse = new ForwardResponse(PageManager.PAGE_URL_ETID_TEST);
			}
		}
		return commandResponse;
	}
	
	protected Question buildQuestionFromRequest(final HttpServletRequest request) {
		Question question; 
		final String questionName = request.getParameter(Pages.QUESTION_TEXT);
		if ((questionName == null) || (questionName.isEmpty())) {
			question = null;
		} else {
			question = new Question();
			question.setIdTest((Integer)request.getSession().getAttribute(
					Pages.SESSION_CURRENT_TEST_ID));
			question.setText(questionName);
			Answer answer = null;
			int currentAnswer = NUMBER_OF_FIRST_ANSWER;
			do {
				answer = buildAnswer(request, currentAnswer);
				if (answer != null) {
					question.getAnswers().add(answer);
				}
				currentAnswer++;
			} while(answer != null);
			if (question.getAnswers().size() < MIN_COUNT_OF_ANSWERS) {
				question = null;
			}
		}
		return question;
	}
	
	protected Answer buildAnswer(final HttpServletRequest request, final int number) {
		Answer answer;
		final String answerText = request.getParameter(Pages.CREATED_ANSWER + number);
		final String isCorrect = request.getParameter(Pages.IS_CORRECT + number);
		if ((answerText == null) || (answerText.isEmpty())) {
			answer = null;
		} else {
			answer = new Answer();
			answer.setText(answerText);
			if((isCorrect == null) || (isCorrect.isEmpty())) {
				answer.setIsCorrect(false);
			} else {
				answer.setIsCorrect(true);
			}
		}
		return answer;
	}
	
}
