<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        <%@include file="resources/style.css" %>
    </style>
    <link rel="icon" type="image/png" href="resources/images/sa.ico"/>
</head>
<body>
<div>
    <%@include file="/resources/includes/header.jsp" %>
</div>
<br class="my-5">
<br class="my-5">
<br class="my-5">
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:bundle basename="Timesheet">

    <div class="bg-dark my-5 w-50 d-flex justify-content-center text-warning p-3"
         style="margin: auto;border-radius: 25px;">

        <form action="user" method="post">
            <div class="m-auto">
                <h1 class="text-center"><fmt:message key="user"/></h1>
            </div>
            <input type="hidden" min="1" class="form-control" name="id" id="id" value="" placeholder="Id" readonly>

            <c:if test="${not empty sessionScope.error_message_user}">
                <div class="alert alert-danger" role="alert">
                        ${sessionScope.error_message_user}
                </div>
                <c:remove var="error_message_user"/>
            </c:if>

            <div class="form-group row">
                <label for="name" class="text-warning col-sm-5 "><fmt:message key="username"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="name" id="name" value="" placeholder="<fmt:message key="username"/>">
                </div>
            </div>

            <div class="form-group row">
                <label for="password" class="text-warning col-sm-5 "><fmt:message key="password"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="password" id="password" value=""
                           placeholder="<fmt:message key="password"/>">
                </div>
            </div>

            <div class="form-group row">
                <label for="isAdmin" class="text-warning col-sm-5 "><fmt:message key="is-admin"/></label>
                <div class="col-xl-12">
                    <select type="text" class="col-xl-12 custom-select" name="isAdmin" id="isAdmin">
                        <option value="" selected><fmt:message key="choose"/>...</option>
                        <option value="true"><fmt:message key="yes"/></option>
                        <option value="false"><fmt:message key="no"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="employeeID" class="text-warning col-sm-5 "><fmt:message key="employee"/>ID</label>
                <div class="col-xl-12">
                    <select type="text" class="col-xl-12 custom-select" name="employeeID" id="employeeID">
                        <option value="" selected><fmt:message key="choose"/>...</option>
                        <c:forEach items="${requestScope.employees}" var="employee">
                            <option value="${employee.id}">${employee.id}.${employee.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="mt-5 d-flex justify-content-center">
                <button type="submit" class="btn btn-outline-success mx-5" value="create" name="button"
                        id="createButton"
                        onclick="createFunction()"><fmt:message key="create"/>
                </button>
                <button type="submit" class="btn btn-outline-warning mx-5" value="update" name="button"
                        id="updateButton"
                        disabled><fmt:message key="update"/>
                </button>
            </div>
        </form>
        <form id="userForm" action="user" method="post"></form>
    </div>

    <table class="table table-hover table-dark text-warning my-5" style="margin: auto; width: 98%;">
        <thead>
        <tr class="text-white">
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="name"/></th>
            <th scope="col"><fmt:message key="password"/></th>
            <th scope="col"><fmt:message key="is-admin"/></th>
            <th scope="col"><fmt:message key="employee"/>ID</th>
            <th scope="col"></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>

                <th scope="row">${user.id}</th>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.isAdmin()}</td>
                <td>${user.employeeID}</td>
                <td class="text-right">

                    <button type="submit" class="btn btn-outline-warning btn-sm mx-2" value="update" name="button"
                            onclick="updateFunction('${user.id}','${user.name}','${user.isAdmin()}','${user.employeeID}')">
                        <fmt:message key="edit"/>
                    </button>

                    <button type="submit" class="btn btn-outline-danger btn-sm mx-2" value="delete"
                            name="button"
                            onclick="deleteFunction('${user.id}')"><fmt:message key="delete"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fmt:bundle>

<div class="mx-auto" style="bottom: 0;position: fixed;opacity: 0.7;">
    <%@include file="/resources/includes/footer.jsp" %>
</div>
<script>
    document.getElementById('roButton').style.removeProperty("display");
    document.getElementById('enButton').style.removeProperty("display");
    document.getElementById('deButton').style.removeProperty("display");

    function createFunction() {
        document.getElementById("id").removeAttribute("required");

        document.getElementById("name").setAttribute("required", "");
        document.getElementById("password").setAttribute("required", "");
        document.getElementById("isAdmin").setAttribute("required", "");
        document.getElementById("employeeID").setAttribute("required", "");

    }

    function updateFunction(id, name, isAdmin, employeeID) {
        document.getElementById("createButton").setAttribute("disabled", "");
        document.getElementById("updateButton").removeAttribute("disabled");


        document.getElementById("isAdmin").value = isAdmin;
        document.getElementById("id").value = id;
        document.getElementById("employeeID").value = employeeID;
        document.getElementById("name").value = name;

        document.getElementById("id").setAttribute("required", "");
        document.getElementById("name").setAttribute("required", "");
        document.getElementById("password").setAttribute("required", "");
        document.getElementById("isAdmin").setAttribute("required", "");
        document.getElementById("employeeID").setAttribute("required", "");
        window.scrollTo(0, 0);
    }

    function deleteFunction(userID) {
        let form = document.getElementById("userForm");
        let element = document.createElement("input");
        element.setAttribute("type", "hidden");
        element.setAttribute("name", "userID");
        element.setAttribute("value", userID);
        form.appendChild(element);
    }
</script>
</body>
</html>
