<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="TEST_EDIT_TITLE" var="testEditTitleText"/>
<fmt:message key="CURRENT_TEST" var="currentTestText"/>
<fmt:message key="EXISTING_QUESTIONS" var="existingQuestionsText"/>
<fmt:message key="DELETE_QUESTION" var="deleteQuestionText"/>
<fmt:message key="ADD_QUESTION" var="addQuestionText"/>
<fmt:message key="ANSWER_COUNT" var="answerCountText"/>
<fmt:message key="CREATE_QUESTION" var="createQuestionText"/>
<fmt:message key="BACK_TO_TEST" var="backToTestText"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${testEditTitleText}</title>
</head>
<body>
<t:menu preferredPage="editTest" mainPage="tutor" />
	${currentTestText} - ${curTestName}<br><br>
	
	<h2>${existingQuestionsText}</h2>
		<table border=3 width="60%">
			<c:forEach var="question" items="${tutorQuestions}">
				<form action="deleteQuestion" method="POST">
					<input type="hidden" name="questionToDelete" value="${question.idQuestion}">
					<tr>
						<td width="70%">${question.text}</td>
						<th><input type="submit" value="${deleteQuestionText}"></th>
					</tr>
				</form>
			</c:forEach>
		</table>
	
	<br><br><br>
	
	<form action="addQuestion" method="POST">
		<h2>${addQuestionText}</h2>
		${answerCountText}
		<input type="text" name="answerCount"><br>
		<input type="submit" value="${createQuestionText}">
	</form>
	
	<br><br>
	<a href="tutor">&lt;&lt;&lt;  ${backToTestText}</a>
	
</body>
</html>