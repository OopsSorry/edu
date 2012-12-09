<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="FINISH_TEST_TITLE" var="titleText"/>
<fmt:message key="FINISH_TEST" var="finishTextText"/>
<fmt:message key="BACK_TO_TEST_SELECT" var="backToTestText"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleText}</title>
</head>
<body>
<t:menu preferredPage="doTest" mainPage="student" />
${finishTextText}
<br>
<a href="student">${backToTestText}</a>

</body>
</html>