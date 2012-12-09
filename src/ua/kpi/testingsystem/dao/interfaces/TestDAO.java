package ua.kpi.testingsystem.dao.interfaces;

import java.util.List;
import ua.kpi.testingsystem.beans.Test;

/**
 * @version 1.0 14 April 2011
 * @author Ivanov Yaroslav
 */
public interface TestDAO {

	public void insert(Test test);

	public Test findById(int id);

	public Test findByIdAndTutor(int id, int tutorId);

	public List<Test> findByTutorId(int idTutor);

	public List<Test> findBySubjectId(int subjectId);

	public List<Test> findBySubjAndTutor(int subjectId, int tutorId);
	
	public List<Test> findAvailable(int subjectId);

	public List<Test> findAll();

	public void setEnable(int idTest, int idUser, boolean state);
	
	public void delete(int idTest, int idUser);

	public void close();

}
