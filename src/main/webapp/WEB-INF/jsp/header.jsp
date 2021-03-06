<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfToken"--%>
<head>

    <meta charset="UTF-8"/>
    <meta content="text/html;charset=UTF-8"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="css/main.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Marketplace</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/login">Авторизация</a></li>
                <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
                <li><a href="${pageContext.request.contextPath}/edit-item">Создать товар</a></li>
                <li><a href="${pageContext.request.contextPath}/show-items">Товары</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Выйти</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</body>
