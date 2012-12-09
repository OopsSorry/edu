package ua.kpi.testingsystem.logic;

import java.util.List;
import ua.kpi.testingsystem.beans.Subject;
import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.dao.factories.DAOFactoryManager;
import ua.kpi.testingsystem.dao.interfaces.SubjectDAO;
import ua.kpi.testingsystem.dao.interfaces.TestDAO;

/**
 * @version 1.0 14 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class TestLogic {
	
	protected TestLogic() {
	}

	public static List<Subject> getAvailableTests() {
		final SubjectDAO subjectDAO = DAOFactoryManager.getFactory().createSubjectDAO();
		final List<Subject> subjects = subjectDAO.findAll();
		subjectDAO.close();
		final TestDAO testDAO = DAOFactoryManager.getFactory().createTestDAO();
		for(Subject subject : subjects) {
			subject.setTests(testDAO.findAvailable(subject.getIdSubject()));
		}
		testDAO.close();
		return subjects;
	}

	public static List<Subject> getTutorTests(final int tutorId) {
		final SubjectDAO subjectDAO = DAOFactoryManager.getFactory().createSubjectDAO();
		final List<Subject> subjects = subjectDAO.findAll();
		subjectDAO.close();
		final TestDAO testDAO = DAOFactoryManager.getFactory().createTestDAO();
		for(Subject subject : subjects) {
			subject.setTests(testDAO.findBySubjAndTutor(
					subject.getIdSubject(), 
					tutorId));
		}
		testDAO.close();
		return subjects;
	}
	
	public static Test getTest(final int testId) {
		final TestDAO testDAO = DAOFactoryManager.getFactory().createTestDAO();
		final Test test = testDAO.findById(testId);
		testDAO.close();
		return test;
	}
	
	public static void deleteTest(final int idTest, final int idUser) {
		final TestDAO testDAO = DAOFactoryManager.getFactory().createTestDAO();
		testDAO.delete(idTest, idUser);
		testDAO.close();
	}
	
	public static void setTestState(final int idTest, final int idUser, 
			final boolean state) {
		final TestDAO testDAO = DAOFactoryManager.getFactory().createTestDAO();
		testDAO.setEnable(idTest, idUser, state);
		testDAO.close();
	}

}
