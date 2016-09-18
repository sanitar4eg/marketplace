<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <form:form class="form" method="post" modelAttribute="item">

        <h2>Добавить товар</h2>
        <label for="title">Название</label>
        <form:input type="text" id="title" path="title" class="form-control" placeholder="Название" required="true"
                    autofocus="true"/>
        <form:errors path="title"/>

        <label for="description">Описание</label>
        <form:input type="text" id="description" path="description" class="form-control" placeholder="Описание"
                    required="true"/>
        <form:errors path="description"/>

        <label for="startPrice">Стартовая цена</label>
        <form:input type="number" step="0.01" id="startPrice" path="startPrice" class="form-control"
                    cssStyle="margin-bottom: 10px;"
                    placeholder="Стартовая цена" required="true"/>
        <form:errors path="startPrice"/>

        <form:label for="bidIncrement" path="bidIncrement">Шаг ставки</form:label>
        <form:input type="number" step="0.01" id="bidIncrement" path="bidIncrement" class="form-control"
                    placeholder="Шаг ставки" required="true"/>
        <form:errors path="bidIncrement"/>

        <div class="checkbox">
            <label for="buyItNow" path="buyItNow">
                <form:checkbox id="buyItNow" path="buyItNow"/> Купить сейчас
            </label>
        </div>
        <form:errors path="buyItNow"/>

        <label for="timeLeft" path="timeLeft">Время на лот</label>
        <form:input type="time" id="timeLeft" path="timeLeft" class="form-control"
                    placeholder="Время на лот" required="true"/>
        <form:errors path="timeLeft"/>

        <form:button class="btn btn-lg btn-primary btn-block" type="submit">Добавить</form:button>
    </form:form>

</div> <!-- /container -->

</body>