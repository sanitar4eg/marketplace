<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <form:form class="form-item" method="post" modelAttribute="item">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2>Добавить товар</h2>
        <label for="title" class="sr-only">Название</label>
        <form:input type="text" id="title" path="title" class="form-control" placeholder="Название" required="true"
                    autofocus="true"/>
        <form:errors path="title"/>

        <label for="description" class="sr-only">Описание</label>
        <form:input type="text" id="description" path="description" class="form-control" placeholder="Описание"
                    required="true"/>
        <form:errors path="description"/>

        <label for="startPrice" class="sr-only">Стартовая цена</label>
        <form:input type="number" step="0.01" id="startPrice" path="startPrice" class="form-control" required="true"/>
        <form:errors path="startPrice"/>

        <form:label for="bidIncrement" path="bidIncrement" class="sr-only">Шаг ставки</form:label>
        <form:input type="number" step="0.01" id="bidIncrement" path="bidIncrement" class="form-control"
                    required="true"/>
        <form:errors path="bidIncrement"/>

        <label for="bidIncrement">Время на лот</label>
        <form:input type="number" step="0.01" id="bidIncrement" path="bidIncrement" class="form-control"
                    required="true"/>
        <form:errors path="bidIncrement"/>

        <form:button class="btn btn-lg btn-primary btn-block" type="submit">Добавить</form:button>
    </form:form>

</div> <!-- /container -->

</body>