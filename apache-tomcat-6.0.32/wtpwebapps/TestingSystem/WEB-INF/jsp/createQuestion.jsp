<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="CREATE_QUESTION_TITLE" var="createQuestTitleText"/>
<fmt:message key="QUESTION_TEXT" var="questionText"/>
<fmt:message key="ANSWERS" var="answersText"/>
<fmt:message key="IS_CORRECT" var="isCorrectText"/>
<fmt:message key="ANSWER_TEXT" var="answerText"/>
<fmt:message key="CREATE_QUETION2" var="createQuestionText"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${createQuestTitleText}</title>
</head>
<body>
	<t:menu preferredPage="editTest" mainPage="tutor" />
	<form method="POST" action="createQuestion" name="">
		<br>${questionText}<br>
		<textarea name="questionText" rows=5 cols=50></textarea><br>
		<br>${answersText}<br>
		<table>
			<tr>
				<td>№</td>
				<td>${isCorrectText}</td>
				<td>${answerText}</td>
			</tr>
			<c:forEach var="i" begin="${1}" end="${answerCount}">
				<tr>
					<td>${i}</td>
					<td><input type="checkbox" name="isCorrect${i}"></td>
					<td><input type="text" name="answer${i}" size="100"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="${createQuestionText}">
	</form>
</body>
</html>