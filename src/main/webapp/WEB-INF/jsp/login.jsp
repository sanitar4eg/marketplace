<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <form:form class="form" method="post" modelAttribute="userMP">

        <h2>Пожалуйста авторизуйтесь</h2>
        <label for="inputEmail">Email</label>
        <form:input type="email" id="inputEmail" path="email" class="form-control" placeholder="Email" required="true"
                    autofocus="true"/>
        <form:errors path="email"/>
        <label for="inputPassword">Пароль</label>
        <form:input type="password" id="inputPassword" path="password" class="form-control" placeholder="Пароль"
                    required="true"/>
        <form:errors path="password"/>
        <form:button class="btn btn-lg btn-primary btn-block" type="submit">Войти</form:button>
    </form:form>

</div> <!-- /container -->

</body>