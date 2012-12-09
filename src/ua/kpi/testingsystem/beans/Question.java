package ua.kpi.testingsystem.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0 15 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class Question implements Serializable {

	private static final long serialVersionUID = -2004877635049001658L;

	private int idQuestion;
	private int idTest;
	private String text;
	private List<Answer> answers = new LinkedList<Answer>();
	
	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + idQuestion;
		result = prime * result + idTest;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (idQuestion != other.idQuestion)
			return false;
		if (idTest != other.idTest)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [idQuestion=");
		builder.append(idQuestion);
		builder.append(", idTest=");
		builder.append(idTest);
		builder.append(", text=");
		builder.append(text);
		builder.append(", answers=");
		builder.append(answers);
		builder.append("]");
		return builder.toString();
	}

	
}
