<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<body>
<div class="container">

    <h2>Список товаров</h2>

    <table class="table table-bordered" width="100%" id="itemsTable">
        <thead>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Стартовая цена</th>
            <th>Шаг ставки</th>
            <th>Купить сейчас</th>
            <th>Время на лот</th>
        </tr>
        </thead>
        <tbody>
        <core:forEach var="item" items="${items}">
            <tr>
                <td>${item.title}</td>
                <td>${item.description}</td>
                <td>${item.startPrice}</td>
                <td>${item.bidIncrement}</td>
                <td>${item.buyItNow}</td>
                <td>${item.timeLeft}</td>
            </tr>
        </core:forEach>
        </tbody>
    </table>

</div> <!-- /container -->
</body>


<script type="text/javascript">
    (function () {

    })()
</script>
