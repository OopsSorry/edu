package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.kpi.testingsystem.beans.Answer;
import ua.kpi.testingsystem.beans.Question;
import ua.kpi.testingsystem.dao.interfaces.QuestionDAO;
import ua.kpi.testingsystem.exceptions.DAOException;

/**
 * @version 1.0 15 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCQuestionDAO extends JDBCAbstractDAO implements QuestionDAO {

	private static final String COLUMN_IDQUESTION = "idquestions";
	private static final String COLUMN_TESTS_IDTESTS = "tests_idtests";
	private static final String COLUMN_TEXT = "text";

	private static final String TABLE = "questions";
	private static final String INSERT = "INSERT INTO " + TABLE + "("
			+ COLUMN_TESTS_IDTESTS + ", " + COLUMN_TEXT + ") VALUES (?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_IDQUESTION + " = ?";
	private static final String SELECT_BY_IDTEST = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_TESTS_IDTESTS + " = ?";
	private static final String DELETE_BY_IDQUEST = "DELETE FROM " + TABLE
			+ " WHERE " + COLUMN_IDQUESTION + " = ? AND "
			+ COLUMN_TESTS_IDTESTS + " = ?";


	public JDBCQuestionDAO(final Connection connection) {
		super(connection);
	}

	public void insert(final Question quest) {
		query.insert(INSERT, quest.getIdTest(), quest.getText());
	}
	
	public void insertWithAnswers(final Question question) {
		try {
			final Connection connection = query.getConnection();
			try {
				int insertedId;
				connection.setAutoCommit(false);
				final PreparedStatement prst = connection.prepareStatement(INSERT, 
						PreparedStatement.RETURN_GENERATED_KEYS);
				prst.setInt(1, question.getIdTest());
				prst.setString(2, question.getText());
				prst.execute();
				final ResultSet rs = prst.getGeneratedKeys();
				rs.next();
				insertedId = rs.getInt(1);
				for (Answer answer : question.getAnswers()) {
					answer.setIdQuestion(insertedId);
				}
				final JDBCAnswerDAO answerDAO = new JDBCAnswerDAO(connection);
				answerDAO.insert(question.getAnswers());
				connection.commit();
			} catch (SQLException ex) {
				connection.rollback();
				throw new DAOException(ex);
			} catch (DAOException ex) {
				connection.rollback();
				throw ex;
			} finally {
					connection.setAutoCommit(true);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	public Question findById(final int id) {
		final QuestionReader questReader = new QuestionReader();
		query.select(questReader, SELECT_BY_ID, id);
		return questReader.get();
	}

	public List<Question> findByTestId(final int idTest) {
		final QuestionReader questReader = new QuestionReader();
		query.select(questReader, SELECT_BY_IDTEST, idTest);
		return questReader.getAll();
	}
	
	public void delete(final int idQuestion, final int idTest) {
		query.delete(DELETE_BY_IDQUEST, idQuestion, idTest);
	}
	
	private class QuestionReader implements Rowmapper {

		List<Question> questions;

		public void read(final ResultSet resultSet) throws SQLException {
			questions = new LinkedList<Question>();
			while (resultSet.next()) {
				final Question quest = new Question();
				quest.setIdQuestion(resultSet.getInt(COLUMN_IDQUESTION));
				quest.setIdTest(resultSet.getInt(COLUMN_TESTS_IDTESTS));
				quest.setText(resultSet.getString(COLUMN_TEXT));
				questions.add(quest);
			}
		}

		public Question get() {
			if (questions.isEmpty()) {
				return null;
			} else {
				return questions.get(0);
			}
		}

		public List<Question> getAll() {
			return questions;
		}

	}


}
