package ua.kpi.testingsystem.dao.interfaces;

import java.util.List;
import ua.kpi.testingsystem.beans.Answer;

/**
 * @version 1.0 16 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public interface AnswerDAO {

	public void insert(Answer answer);

	public Answer findById(int idAnswer);

	public List<Answer> findByQuestionId(int idQuestion);

	public void delete(Answer answer);
	
	public void close();

}
