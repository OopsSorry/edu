<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale  value="${sessionLanguage}" scope="page"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="LOGIN" var="loginText"/>
<fmt:message key="PASSWORD" var="passwordText"/>
<fmt:message key="ENTER" var="enterText"/>
<fmt:message key="LOGOUT" var="logoutText"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login page</title>
</head>
<body>
<center>
	<a href="changeLanguage?paramLanguage=ua"><img src="media/img/ua.jpg" alt="Українська мова" ></a> &nbsp&nbsp&nbsp&nbsp&nbsp
	<a href="changeLanguage?paramLanguage=en"><img src="media/img/en.jpg" alt="English language" ></a><br/>
<br/><br/><br/><br/>
<form method="POST" action="login">
	${loginText}<br/>
	<input type="text" name="login"><br>
	${passwordText}<br/>
	<input type="password" name="password"><br>
	<input type="submit" value="${enterText}">
</form>
<p>${invalidUser}
</center>
</body>
</html>