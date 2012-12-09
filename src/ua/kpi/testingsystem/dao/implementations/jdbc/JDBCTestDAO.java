package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.dao.interfaces.TestDAO;

/**
 * @version 1.0 14 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCTestDAO extends JDBCAbstractDAO implements TestDAO {

	private static final String COLUMN_IDTEST = "idtests";
	private static final String COLUMN_USERS_IDUSERS = "users_idusers";
	private static final String COLUMN_SUBJECTS_IDSUBJECTS = "subjects_idsubjects";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_ENABLE = "enable";

	private static final String TABLE = "tests";
	private static final String INSERT = "INSERT INTO " + TABLE + "("
			+ COLUMN_USERS_IDUSERS + ", " + COLUMN_SUBJECTS_IDSUBJECTS + ", "
			+ COLUMN_NAME + ", " + COLUMN_ENABLE  + ") VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM " + TABLE;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE
			+ " WHERE  " + COLUMN_IDTEST + " = ?";
	private static final String SELECT_BY_IDTUTOR = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_USERS_IDUSERS + " = ?";
	private static final String SELECT_BY_SUBJ_AND_TUTOR = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_SUBJECTS_IDSUBJECTS + " = ? AND "
			+ COLUMN_USERS_IDUSERS + " = ?";
	private static final String SELECT_BY_ID_AND_TUTOR = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_IDTEST + " = ? AND " 
			+ COLUMN_USERS_IDUSERS + " = ?";
	private static final String SELECT_BY_IDSUBJECT = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_SUBJECTS_IDSUBJECTS + " = ?";
	private static final String SELECT_AVAILABLE = "SELECT * FROM " + TABLE
			+ " WHERE " + COLUMN_SUBJECTS_IDSUBJECTS + " = ? AND "
			+ COLUMN_ENABLE + " = ?";
	private static final String DELETE_TEST = "DELETE FROM " + TABLE
			+ " WHERE " + COLUMN_IDTEST + " = ? AND " 
			+ COLUMN_USERS_IDUSERS + " = ?";
	private static final String UPDATE_ENABLE = "UPDATE " + TABLE + " SET " 
			+ COLUMN_ENABLE + " = ? WHERE " + COLUMN_IDTEST + " = ? AND "
			+ COLUMN_USERS_IDUSERS + " = ?";


	public JDBCTestDAO(Connection connection) {
		super(connection);
	}

	public void insert(Test test) {
		query.insert(INSERT, test.getIdUser(), test.getIdSubject(),
				test.getName(), test.isEnable());
	}

	public Test findById(final int id) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_BY_ID, id);
		return testReader.get();
	}

	public List<Test> findByTutorId(final int idTutor) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_BY_IDTUTOR, idTutor);
		return testReader.getAll();
	}

	public List<Test> findBySubjectId(final int subjectId) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_BY_IDSUBJECT, subjectId);
		return testReader.getAll();
	}
	
	public Test findByIdAndTutor(int id, int tutorId) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_BY_ID_AND_TUTOR, id, tutorId);
		return testReader.get();
	}
	
	public List<Test> findBySubjAndTutor(int subjectId, int tutorId) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_BY_SUBJ_AND_TUTOR, subjectId, tutorId);
		return testReader.getAll();
	}
	
	public List<Test> findAvailable(int subjectId) {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_AVAILABLE, subjectId, true);
		return testReader.getAll();
	}

	public List<Test> findAll() {
		TestReader testReader = new TestReader();
		query.select(testReader, SELECT_ALL);
		return testReader.getAll();
	}

	public void delete(final int idTest, final int idUser) {
		query.delete(DELETE_TEST, idTest, idUser);
	}
	
	public void setEnable(int idTest, int idUser, boolean state) {
		query.update(UPDATE_ENABLE, state, idTest, idUser);
	}
	
	private class TestReader implements Rowmapper {

		private List<Test> tests;

		/**
		 * Creates test using values that represented in ResultSet
		 * @param rs ResultSet that contains Test to create
		 **/
		public void read(final ResultSet rs) throws SQLException {
			tests = new LinkedList<Test>();
			while (rs.next()) {
				Test test = new Test();
				test.setIdTest(rs.getInt(COLUMN_IDTEST));
				test.setIdUser(rs.getInt(COLUMN_USERS_IDUSERS));
				test.setIdSubject(rs.getInt(COLUMN_SUBJECTS_IDSUBJECTS));
				test.setName(rs.getString(COLUMN_NAME));
				test.setEnable(rs.getBoolean(COLUMN_ENABLE));
				tests.add(test);
			}
		}

		public Test get() {
			if (tests.isEmpty()) {
				return null;
			} else {
				return tests.get(0);
			}
		}

		public List<Test> getAll() {
			return tests;
		}

	}

}
