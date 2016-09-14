<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <form:form class="form-email" method="post" modelAttribute="userMP">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2>Заполните следующие данные:</h2>
        <label for="inputEmail" class="sr-only">Email</label>
        <form:input type="email" id="inputEmail" path="email" class="form-control" placeholder="Email" required="true"
                    autofocus="true"/>
        <form:errors path="email"/>
        <label for="password" class="sr-only">Пароль</label>
        <form:input type="password" id="password" path="password" class="form-control" placeholder="Пароль"
                    required="true" pattern=".{6,}" title="6 символов минимум"/>
        <form:errors path="password"/>
        <label for="fullName" class="sr-only">Имя</label>
        <form:input type="text" id="fullName" path="fullName" class="form-control" placeholder="Имя" required="true"/>
        <form:errors path="fullName"/>
        <label for="billingAddress" class="sr-only">Адресс доставки</label>
        <form:input type="text" id="billingAddress" path="billingAddress" class="form-control" placeholder="Адрес"
                    required="true"/>
        <form:errors path="billingAddress"/>
        <form:button class="btn btn-lg btn-primary btn-block" type="submit" id="email">Сохранить</form:button>
    </form:form>
</div> <!-- /container -->
</body>