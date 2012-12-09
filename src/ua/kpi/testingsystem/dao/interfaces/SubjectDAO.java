package ua.kpi.testingsystem.dao.interfaces;

import java.util.List;
import ua.kpi.testingsystem.beans.Subject;

public interface SubjectDAO {

	public void insert(Subject subject);

	public Subject find(int idSubject);

	public List<Subject> findAll();
	
	public void delete(Subject subject);
	
	public void close();

}
