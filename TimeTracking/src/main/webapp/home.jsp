<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="icon" type="image/png" href="resources/images/sa.ico"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/style.css"/>
</head>
<body>
<div>
    <%@include file="/resources/includes/header.jsp" %>
</div>
<br class="my-5">
<br class="my-5">

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:bundle basename="Timesheet">
    <div class="mt-5 text-warning d-flex justify-content-center" style="margin-top: 10%;">
        <h1 style="font-size:4em;"><fmt:message key="welcome-timesheet"/></h1>
    </div>
    <div class="mt-5 d-flex justify-content-center" style="margin-top: 10%;">
        <img width=400 height=400 alt="clock"
             src="${pageContext.request.contextPath}/resources/images/white-clock2.png">
    </div>
</fmt:bundle>

<div class="mx-auto" style="bottom: 0;position: fixed;opacity: 0.7;">
    <%@include file="/resources/includes/footer.jsp" %>
</div>
<script>
    document.getElementById('roButton').style.removeProperty("display");
    document.getElementById('enButton').style.removeProperty("display");
    document.getElementById('deButton').style.removeProperty("display");
</script>
</body>
</html>
