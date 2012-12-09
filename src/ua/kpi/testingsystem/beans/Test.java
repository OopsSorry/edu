package ua.kpi.testingsystem.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0 14 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class Test implements Serializable {

	private static final long serialVersionUID = -4341738965531100106L;

	private int idTest;
	private int idUser;
	private int idSubject;
	private String name;
	private boolean enable;
	private List<Test> tests = new LinkedList<Test>();
	
	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + idSubject;
		result = prime * result + idTest;
		result = prime * result + idUser;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tests == null) ? 0 : tests.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (enable != other.enable)
			return false;
		if (idSubject != other.idSubject)
			return false;
		if (idTest != other.idTest)
			return false;
		if (idUser != other.idUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [idTest=");
		builder.append(idTest);
		builder.append(", idUser=");
		builder.append(idUser);
		builder.append(", idSubject=");
		builder.append(idSubject);
		builder.append(", name=");
		builder.append(name);
		builder.append(", enable=");
		builder.append(enable);
		builder.append(", tests=");
		builder.append(tests);
		builder.append("]");
		return builder.toString();
	}

	
	

}
