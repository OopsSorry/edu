<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="STUDENT_TITLE" var="studentTitleText"/>
<fmt:message key="STUDENT_WELCOM" var="welcomText"/>
<fmt:message key="START_TEST" var="startTestText"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${studentTitleText}</title>
</head>
<body>
<t:menu preferredPage="student" mainPage="student" />
<p>${welcomText}</p>
	<c:forEach var="subject" items="${studentSubjects}">
			<h2>${subject.name}</h2>
			<table border=3 width="30%">
				<c:forEach var="test" items="${subject.tests}">
					<form action="startTest" method="POST">
						<input type="hidden" name="selectedTest" value="${test.idTest}">
						<tr>
							<th width="70%">${test.name}</th>
							<th><input type="submit" value="${startTestText}"></th>
						</tr>
					</form>
				</c:forEach>
			</table>
	</c:forEach>
</body>
</html>