package ua.kpi.testingsystem.beans;

import java.io.Serializable;

/**
 * @version 1.0 15 April 2011
 * @author Ivanov Yaroslav
 * 
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = 9029054055278895865L;

	private int idAnswer;
	private int idQuestion;
	private String text;
	private boolean isCorrect;

	public int getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAnswer;
		result = prime * result + idQuestion;
		result = prime * result + (isCorrect ? 1231 : 1237);
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
		Answer other = (Answer) obj;
		if (idAnswer != other.idAnswer)
			return false;
		if (idQuestion != other.idQuestion)
			return false;
		if (isCorrect != other.isCorrect)
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
		builder.append(getClass().getSimpleName() + " [idAnswer=");
		builder.append(idAnswer);
		builder.append(", idQuestion=");
		builder.append(idQuestion);
		builder.append(", text=");
		builder.append(text);
		builder.append(", isCorrect=");
		builder.append(isCorrect);
		builder.append("]");
		return builder.toString();
	}

}
