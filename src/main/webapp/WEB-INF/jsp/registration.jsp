<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <form:form class="form" method="post" modelAttribute="userMP">

        <h2>Заполните следующие данные:</h2>
        <label for="inputEmail">Email</label>
        <form:input type="email" id="inputEmail" path="email" class="form-control" placeholder="Email"
                    required="true" autofocus="true"/>
        <form:errors path="email"/>
        <label for="password">Пароль</label>
        <form:input type="password" id="password" path="password" class="form-control" placeholder="Пароль"
                    required="true" pattern=".{6,}" title="6 символов минимум"/>
        <form:errors path="password"/>
        <label for="fullName">Имя</label>
        <form:input type="text" id="fullName" path="fullName" class="form-control" placeholder="Имя"
                    required="true"/>
        <form:errors path="fullName"/>
        <label for="billingAddress">Адресс доставки</label>
        <form:input type="text" id="billingAddress" path="billingAddress" class="form-control" placeholder="Адрес"
                    required="true"/>
        <form:errors path="billingAddress"/>
        <form:button class="btn btn-lg btn-primary btn-block" id="submitButton" type="submit">Сохранить</form:button>
        <div class="alert alert-danger" id="equalsFieldsError" hidden>Логин и имя не должны совпадать</div>
    </form:form>
</div> <!-- /container -->
</body>


<script type="text/javascript">
    (function () {
        function valuesCheck() {
            if (inputEmail.value == fullName.value) {
                submitButton.disabled = true;
                equalsFieldsError.hidden = false;
                return;
            }
            submitButton.disabled = false;
            equalsFieldsError.hidden = true;
        }

        var inputEmail = document.getElementById("inputEmail");
        var fullName = document.getElementById("fullName");
        var submitButton = document.getElementById("submitButton");
        var equalsFieldsError = document.getElementById('equalsFieldsError');

        inputEmail.addEventListener("change", valuesCheck, false);
        fullName.addEventListener("change", valuesCheck, false);

    })()
</script>
