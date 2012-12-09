<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="DO_TEST_TITLE" var="doTestTitleText"/>
<fmt:message key="NEXT_QUESTION" var="nextQuestionText"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${doTestTitleText}</title>
</head>
<body>
<t:menu preferredPage="doTest" mainPage="student" />
<form action="doTest" method="POST">
	<br><br><br><br>
	<h2>${currentQuestion.text}</h2>
	<input type="hidden" name="currentQuestionId" value=${currentQuestion.idQuestion}>
	<table>
		<c:forEach var="answer" items="${currentQuestion.answers}">
			<tr>
				<td><input type="checkbox" name="selectedAnswers" value="${answer.idAnswer}"></td>
				<td>${answer.text}</td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="${nextQuestionText}">
</form>

</body>
</html>