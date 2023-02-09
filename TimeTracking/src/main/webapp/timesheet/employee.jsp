<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="resources/style.css"/>
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

        <form action="employee" method="post">
            <div class="m-auto"><h1 class="text-center"><fmt:message key="employee"/></h1></div>

            <input type="hidden" min="1" class="form-control" name="id" id="id" placeholder="Id" readonly>

            <div class="form-group row">
                <label for="name" class="text-warning col-sm-5 "><fmt:message key="name"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="name" id="name" placeholder="<fmt:message key="name"/>">
                </div>
            </div>
            <div class="form-group row">
                <label for="age" class="text-warning col-sm-5"><fmt:message key="age"/></label>
                <div class="col-xl-12">
                    <input type="number" min="1" class="form-control" name="age" id="age" placeholder="<fmt:message key="age"/>">
                </div>
            </div>
            <div class="form-group row">
                <label for="phone" class="text-warning col-sm-5"><fmt:message key="phone-number"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="<fmt:message key="phone-number"/>">
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
        <form id="employeeForm" action="employee" method="post"></form>
    </div>

    <table class="table table-hover table-dark text-warning my-5" style="margin: auto; width: 98%;">
        <thead>
        <tr class="text-white">
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="name"/></th>
            <th scope="col"><fmt:message key="age"/></th>
            <th scope="col"><fmt:message key="phone-number"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.employees}" var="employee">
            <tr>
                <th scope="row">${employee.id}</th>
                <td>${employee.name}</td>
                <td>${employee.age}</td>
                <td>${employee.phoneNumber}</td>
                <td class="text-right">
                    <button type="submit" class="btn btn-outline-warning btn-sm mx-2" value="update" name="button"
                            onclick="updateFunction('${employee.id}','${employee.name}','${employee.age}','${employee.phoneNumber}')">
                        <fmt:message key="edit"/>
                    </button>
                    <button type="submit" form="employeeForm" class="btn btn-outline-danger btn-sm mx-2" value="delete"
                            name="button"
                            onclick="deleteFunction('${employee.id}')"><fmt:message key="delete"/>
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
        document.getElementById("phone").setAttribute("required", "");
        document.getElementById("age").setAttribute("required", "");
    }

    function updateFunction(id, name, age, number) {
        document.getElementById("createButton").setAttribute("disabled", "");
        document.getElementById("updateButton").removeAttribute("disabled");

        document.getElementById("id").value = id;
        document.getElementById("name").value = name;
        document.getElementById("phone").value = number;
        document.getElementById("age").value = age;

        document.getElementById("id").setAttribute("required", "");
        document.getElementById("name").setAttribute("required", "");
        document.getElementById("phone").setAttribute("required", "");
        document.getElementById("age").setAttribute("required", "");
        window.scrollTo(0, 0);
    }

    function deleteFunction(id) {
        let form = document.getElementById("employeeForm");
        let element = document.createElement("input");
        element.setAttribute("type", "hidden");
        element.setAttribute("name", "employeeID");
        element.setAttribute("value", id);
        form.appendChild(element);
    }
</script>
</body>
</html>
