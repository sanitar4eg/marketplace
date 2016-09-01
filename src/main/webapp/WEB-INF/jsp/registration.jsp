<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" %>
<spring:url value="css/main.css" var="mainCss" />

<html>
<head>
    <link href="${mainCss}" rel="stylesheet" />
    <title>Registration Form</title>
</head>
<body>
<form:form method="Post">
    <table>
        <tr>
            <td class="error-text">User Name:</td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
