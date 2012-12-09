package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.kpi.testingsystem.beans.Subject;
import ua.kpi.testingsystem.dao.interfaces.SubjectDAO;

/**
 * @version 1.0 15 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class JDBCSubjectDAO extends JDBCAbstractDAO implements SubjectDAO {

	private static final String COLUMN_IDSUBJECT = "idsubjects";
	private static final String CILUMN_NAME = "name";
	private static final String COLUMN_DESCRIPTION = "description";

	private static final String TABLE = "subjects";
	private static final String INSERT = "INSERT INTO " + TABLE
			+ " (name, description) VALUES (?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE
			+ " WHERE idsubjects = ?";
	private static final String SELECT_ALL = " SELECT * FROM " + TABLE;
	private static final String DELETE_BY_ID = "DELETE FROM " + TABLE
			+ "WHERE idsubjects = ?";

	public JDBCSubjectDAO(Connection connection) {
		super(connection);
	}

	public void insert(Subject subject) {
		query.insert(INSERT, subject.getName(), subject.getDescription());
	}

	public Subject find(final int idSubject) {
		SubjectReader subjectReader = new SubjectReader();
		query.select(subjectReader, SELECT_BY_ID, idSubject);
		return subjectReader.get();
	}

	public List<Subject> findAll() {
		SubjectReader subjectReader = new SubjectReader();
		query.select(subjectReader, SELECT_ALL);
		return subjectReader.getAll();
	}

	public void delete(final Subject subject) {
		query.delete(DELETE_BY_ID, subject.getIdSubject());
	}
	
	private class SubjectReader implements Rowmapper {

		private List<Subject> subjects;

		public void read(ResultSet rs) throws SQLException {
			subjects = new LinkedList<Subject>();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setIdSubject(rs.getInt(COLUMN_IDSUBJECT));
				subject.setName(rs.getString(CILUMN_NAME));
				subject.setDescription(rs.getString(COLUMN_DESCRIPTION));
				subjects.add(subject);
			}
		}

		public Subject get() {
			if (subjects.isEmpty()) {
				return null;
			} else {
				return subjects.get(0);
			}
		}

		public List<Subject> getAll() {
			return subjects;
		}

	}

}
