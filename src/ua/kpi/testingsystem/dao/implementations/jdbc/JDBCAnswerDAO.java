package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.kpi.testingsystem.beans.Answer;
import ua.kpi.testingsystem.dao.interfaces.AnswerDAO;
import ua.kpi.testingsystem.exceptions.DAOException;

/**
 * @version 1.0 16 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCAnswerDAO extends JDBCAbstractDAO implements AnswerDAO {

	private static final String COLUMN_IDANSWER = "idanswers";
	private static final String COLUMN_IDQUESTIONS = "questions_idquestions";
	private static final String COLUMN_TEXT = "answer_text";
	private static final String COLUMN_CORRECT = "correct";

	private static final String TABLE = "answers";
	private static final String INSERT = "INSERT INTO " + TABLE + "("
			+ COLUMN_IDQUESTIONS + ", " + COLUMN_TEXT + ", "
			+ COLUMN_CORRECT + ") VALUES (?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_IDANSWER + " = ?";
	private static final String SELECT_BY_IDQUESTION = "SELECT * FROM "
			+ TABLE + " WHERE " + COLUMN_IDQUESTIONS + " = ?";
	private static final String DELETE = "DELETE FROM " + TABLE 
			+ "WHERE " + COLUMN_IDANSWER + " = ?";


	public JDBCAnswerDAO(Connection connection) {
		super(connection);
	}

	public void insert(Answer answer) {
		query.insert(INSERT, answer.getIdQuestion(), answer.getText(),
				answer.getIsCorrect());
	}
	
	public void insert(List<Answer> answers) {
		try {
			PreparedStatement prst = query.getConnection().prepareStatement(INSERT);
			for (Answer answer : answers) {
				prst.setInt(1, answer.getIdQuestion());
				prst.setString(2, answer.getText());
				prst.setBoolean(3, answer.getIsCorrect());
				prst.addBatch();
			}
			prst.executeBatch();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	public Answer findById(final int idAnswer) {
		AnswerReader answerReader = new AnswerReader();
			query.select(answerReader, SELECT_BY_ID, idAnswer);
			return answerReader.get();
	}

	public List<Answer> findByQuestionId(final int idQuestion) {
		AnswerReader answerReader = new AnswerReader();
		query.select(answerReader, SELECT_BY_IDQUESTION, idQuestion);
		return answerReader.getAll();
	}

	public void delete(final Answer answer) {
		query.delete(DELETE, answer.getIdAnswer());
	}

	private class AnswerReader implements Rowmapper {
		private List<Answer> answers;

		public void read(final ResultSet rs) throws SQLException {
			answers = new LinkedList<Answer>();
			while (rs.next()) {
				final Answer answer = new Answer();
				answer.setIdAnswer(rs.getInt(COLUMN_IDANSWER));
				answer.setIdQuestion(rs.getInt(COLUMN_IDQUESTIONS));
				answer.setText(rs.getString(COLUMN_TEXT));
				answer.setIsCorrect(rs.getBoolean(COLUMN_CORRECT));
				answers.add(answer);
			}
		}
		
		public Answer get() {
			if (answers.isEmpty()) {
				return null;
			} else {
				return answers.get(0);
			}
		}
		
		public List<Answer> getAll() {
			return answers;
		}
		
	}

}
