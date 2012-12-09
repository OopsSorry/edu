<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="TUTOR_TITLE" var="tutorTitleText"/>
<fmt:message key="TUTOR_WELCOM" var="tutorWelcomText"/>
<fmt:message key="ENABLE_TEST" var="enableTestText"/>
<fmt:message key="DISABLE_TEST" var="disableTestText"/>
<fmt:message key="EDIT_TEST" var="editTestText"/>
<fmt:message key="DELETE_TEST" var="deleteTestText"/>
<fmt:message key="TEST_CREATE" var="createTestText"/>
<fmt:message key="SELECT_SUBJECT" var="selectSubjectText"/>
<fmt:message key="TEST_NAME" var="testNameText"/>
<fmt:message key="CREATE_TEST_BUTTON" var="createTestButtonText"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${tutorTitleText}</title>
</head>
<body>
<t:menu preferredPage="tutor" mainPage="tutor" />

	<p>${tutorWelcomText}</p>

	<c:forEach var="subject" items="${subjects}">
		<h2>${subject.name}</h2>
		<table border=3 width="60%">
			<c:forEach var="test" items="${subject.tests}">
					<tr>
						<th width="60%">${test.name}</th>
						
						<c:choose>
							<c:when test="${test.enable}" >
								<form action="disableTest" method="post" >
									<input type="hidden" name="selectedTest" value="${test.idTest}">
									<th><input type="submit" value="${disableTestText}"></th>
								</form>
							</c:when>
							<c:otherwise>
								<form action="enableTest" method="post" >
									<input type="hidden" name="selectedTest" value="${test.idTest}">
									<th><input type="submit" value="${enableTestText}"></th>
								</form>
							</c:otherwise>
							
						</c:choose>
						<form action="${editTestAction}" method="POST">
							<input type="hidden" name="selectedTest" value="${test.idTest}">
							<th><input type="submit" value="${editTestText}"></th>
						</form>
						<form action="deleteTest" method="POST">
							<input type="hidden" name="selectedTest" value="${test.idTest}">
							<th><input type="submit" value="${deleteTestText}"></th>
						</form>
					</tr>
			</c:forEach>
		</table>
	</c:forEach>
	
	<br><br><br>
	<h2>${createTestText}</h2>
	<form action="${createTestAction}" method="POST">
		${selectSubjectText}
		<SELECT name="selectedSubjectID" STYLE="Width: 30%" SIZE="1">
			<c:forEach var="subject" items="${subjects}">
				<OPTION Value=${subject.idSubject}><c:out value="${subject.name}"/>
			</c:forEach>
		</SELECT>
		<br>${testNameText}
		<input type="text" name="testCreateName">
		<p><input type="submit" value="${createTestButtonText}">
	</form>
</body>
