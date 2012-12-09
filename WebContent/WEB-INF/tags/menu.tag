<%@ tag language="java" pageEncoding="utf-8"%>
<%@attribute name="preferredPage" required="false" type="java.lang.String"%>
<%@attribute name="mainPage" required="false" type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale  value="${sessionLanguage}"/>
<fmt:setBundle basename="ua.kpi.testingsystem.locales.language"/>
<fmt:message key="LOGOUT" var="logoutText"/>
<fmt:message key="MAIN_PAGE" var="mainPageText"/>

<div>
	<table width="100%" bgcolor="#87AEC5" >
		<tr>
			<td width="25%">
			<center>
				${user.name} ${user.surName}
			</center>
			</td>
			<td width="25%">
			<center>
				<a href="changeLanguage?paramLanguage=ua&preferredPage=${preferredPage}"><img src="media/img/ua.jpg" alt="Українська мова" ></a> &nbsp&nbsp&nbsp&nbsp&nbsp
				<a href="changeLanguage?paramLanguage=en&preferredPage=${preferredPage}"><img src="media/img/en.jpg" alt="English language" ></a>
			</center>
			</td>
			<td width="25%">
			<center>
				<a href ="${mainPage}" >${mainPageText}</a><br/>
			</center>
			</td>
			<td width="25%">
			<center>
				<a href ="logout" >${logoutText}</a><br/>
			</center>
			</td>
		</tr>
	</table>

</div>