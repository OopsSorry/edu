package ua.kpi.testingsystem.web.commands.helpers;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class Pages {

	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_INVALID_USER = "invalidUser";
	public static final String PARAM_ERROR_MESSAGE = "errorMessage";
	
	public static final String SESSION_LANGUAGE = "sessionLanguage";
	public static final String SESSION_USER = "user";
	public static final String SESSION_CURRENT_TEST_ID = "sessionCurTest";
	public static final String SESSION_TEST_STATE = "testState";
	
	public static final String PARAM_CHANGE_LANGUAGE = "paramLanguage";
	public static final String PARAM_PREFERRED_PAGE = "preferredPage";

	// Tutor page
	public static final String EDIT_TEST_ACTION = "editTestAction";
	public static final String TUTOR_TESTS = "tutorTests";
	public static final String TESTS_COUNT = "testsCount";
	public static final String SELECTED_TEST_ID = "selectedTest";
	
	public static final String CREATE_TEST_ACTION = "createTestAction";
	public static final String SELECTED_SUBJECT_ID = "selectedSubjectID";
	public static final String TEST_CREATE_NAME ="testCreateName";
	public static final String SUBJECTS ="subjects";
	
	// EditTest page 
	public static final String TUTOR_QUESTIONS = "tutorQuestions";
	public static final String ANSWERS_COUNT = "answerCount";
	public static final String QUESTIONS_COUNT = "questionsCount";
	public static final String QUESTION_TO_DELETE = "questionToDelete";
	public static final String CURRENT_TEST_NAME = "curTestName";
	
	// CreateQuestion page
	public static final String QUESTION_TEXT = "questionText";
	public static final String IS_CORRECT = "isCorrect";
	public static final String CREATED_ANSWER = "answer";
	
	// Student page
	public static final String STUDENT_SUBJECTS = "studentSubjects";
	
	// doTest page
	public static final String CURRENT_QUESTION = "currentQuestion";
	public static final String CURRENT_QUESTION_ID = "currentQuestionId";
	public static final String SELECTED_ANSWERS = "selectedAnswers";
	
}
