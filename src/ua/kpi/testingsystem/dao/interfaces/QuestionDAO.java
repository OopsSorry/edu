package ua.kpi.testingsystem.dao.interfaces;

import java.util.List;
import ua.kpi.testingsystem.beans.Question;

/**
 * @version 1.0 14 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public interface QuestionDAO {

	public void insert(Question quest);
	
	public void insertWithAnswers(Question quest);

	public Question findById(final int id);

	public List<Question> findByTestId(final int testId);

	public void delete(int idQuestion, int idTest);
	
	public void close();
	
}
