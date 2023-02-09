<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/style.css"/>
    <link rel="icon" type="image/png" href="resources/images/sa.ico"/>
    <title>Login Page</title>
</head>
<body>
<div>
    <%@include file="/resources/includes/header.jsp" %>
</div>
<br class="my-5">
<br class="my-5">
<br class="my-5">

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:bundle basename="Timesheet"/>
<div>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-75">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-dark text-white" style="border-radius: 25px;">
                        <div class="card-body p-4 text-center">
                            <form action="login" method="post">
                                <div class="mb-md-5 mt-md-4 pb-4">

                                    <c:if test="${not empty requestScope.error_message}">
                                        <div class="alert alert-danger" role="alert">
                                                ${requestScope.error_message}
                                        </div>
                                    </c:if>

                                    <h2 class="fw-bold mb-2 text-warning pb-4">Log In</h2>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text text-dark" id="username">Username</span>
                                        </div>
                                        <input type="text" class="form-control" name="username" aria-label="username"
                                               aria-describedby="basic-addon1" required>
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text text-dark" id="password">Password </span>
                                        </div>
                                        <input type="password" class="form-control" name="password"
                                               aria-label="password" aria-describedby="basic-addon1" id="passwordInput"
                                               required>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="showPassword"
                                               onclick="myFunction()">
                                        <label class="form-check-label text-warning" for="showPassword">
                                            Show Password
                                        </label>
                                    </div>

                                    <button class="btn btn-outline-success btn-lg px-5 mt-5" type="submit">Log In
                                    </button>
                                    <div class="mt-5">
                                        <img alt="Brand" src="resources/images/sa-logo.png">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>


<div class="mx-auto" style="bottom: 0;position: fixed;opacity: 0.7;">
    <%@include file="/resources/includes/footer.jsp" %>
</div>
<script>
    function myFunction() {
        const x = document.getElementById("passwordInput");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }

</script>
</body>