package ua.kpi.testingsystem.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0 15 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class Subject implements Serializable {

	private static final long serialVersionUID = 3116109689446481698L;

	private int idSubject;
	private String name;
	private String description;
	private List<Test> tests = new LinkedList<Test>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + idSubject;
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
		Subject other = (Subject) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idSubject != other.idSubject)
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
		builder.append(getClass().getSimpleName() + " [idSubject=");
		builder.append(idSubject);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tests=");
		builder.append(tests);
		builder.append("]");
		return builder.toString();
	}

	
	

}
