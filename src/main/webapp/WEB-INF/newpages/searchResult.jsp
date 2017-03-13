<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Search result</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Users</h1>
            <p>All users after search</p>
            <a href=" <spring:url value="/adduser" /> "	class="btn btn-primary">
                <span class="glyphicon-info-sign glyphicon"/></span> ADD USER
            </a>
            <a href=" <spring:url value="/list" /> "	class="btn btn-primary">
                <span class="glyphicon-info-sign glyphicon"/></span> ALL USERS
            </a>
        </div>

    </div>

</section>

<section class="container">
    <div class="row">
        <%----%><c:forEach items="${user}" var="user">
        <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
            <div class="thumbnail">
                <div class="caption">
                    <h3>${user.id}</h3>
                    <p>${user.name}</p>
                    <p>${user.age} </p>
                    <p>${user.admin} </p>
                    <p>${user.timeStamp} </p>
                </div>
            </div>
            <div class="caption">
                <p>
                    <a href=" <spring:url value="/delete?id=${user.id}" /> "	class="btn btn-primary">
                        <span class="glyphicon-info-sign glyphicon"/></span> DELETE USER
                    </a>
                </p>
            </div>
            <div class="caption">
                <p>
                    <a href=" <spring:url value="/update?id=${user.id}" /> "	class="btn btn-primary">
                        <span class="glyphicon-info-sign glyphicon"/></span> UPDATE USER
                    </a>
                </p>
            </div>
        </div>
            <%----%></c:forEach>
    </div>
</section>
</body>
</html>
