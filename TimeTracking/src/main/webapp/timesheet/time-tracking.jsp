<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Time Tracking</title>
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

        <form action="time-tracking" method="post">
            <div class="m-auto"><h1 class="text-center"><fmt:message key="time-tracking"/></h1></div>

            <input type="hidden" min="1" class="form-control" name="id" id="id" placeholder="Id" readonly>

            <div class="form-group row">
                <label for="employeeID" class="text-warning col-sm-5 "><fmt:message key="employee"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="employeeID" id="employeeID"
                    <c:forEach items="${requestScope.employees}" var="employee">
                    <c:if test="${employee.id == sessionScope.userLogged.employeeID}">
                           value="${employee.name}"
                    </c:if>
                    </c:forEach> readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="projectID" class="text-warning col-sm-5 col-form-label"><fmt:message key="project"/></label>
                <div class="col-xl-12">
                    <select type="text" name="selectedProjectID" class="col-xl-12 custom-select" id="projectID">
                        <option value="" selected><fmt:message key="project"/>...</option>
                        <c:forEach items="${requestScope.projects}" var="project">
                            <option value="${project.id}">${project.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="workedHours" class="text-warning col-sm-5 col-form-label"><fmt:message key="worked-hours"/></label>
                <div class="col-xl-12">
                    <input type="number" min="1" class="form-control" name="workedHours" id="workedHours"
                           placeholder="<fmt:message key="worked-hours"/>">
                </div>
            </div>


            <div class="form-group row">
                <label for="projectOwnerID" class="text-warning col-sm-5 col-form-label"><fmt:message key="project-owner"/></label>
                <div class="col-xl-12">
                    <select type="text" name="selectedProjectOwnerID" class="col-xl-12 custom-select"
                            id="projectOwnerID">
                        <option value="" selected><fmt:message key="project-owner"/>...</option>
                        <c:forEach items="${requestScope.projectOwners}" var="projectOwner">
                            <option value="${projectOwner.id}">${projectOwner.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="taskID" class="text-warning col-sm-5 col-form-label"><fmt:message key="task"/></label>
                <div class="col-xl-12">
                    <select type="text" name="selectedTaskID" class="col-xl-12 custom-select" id="taskID">
                        <option value="" selected><fmt:message key="task"/>...</option>
                        <c:forEach items="${requestScope.tasks}" var="task">
                            <option value="${task.id}">${task.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row mt-5">
                <div class="mx-auto">
                    <label for="start"><fmt:message key="start"/>:</label>
                    <input type="date" id="start" name="start" min="2000-01-01">
                </div>
                <div class="mx-auto">
                    <label for="end"><fmt:message key="end"/>:</label>
                    <input type="date" id="end" name="end" min="2000-01-01">
                </div>
            </div>

            <div class="form-group row">
                <label for="customerID" class="text-warning col-sm-5 col-form-label"><fmt:message key="customer"/></label>
                <div class="col-xl-12">
                    <select type="text" name="selectedCustomerID" class="col-xl-12 custom-select" id="customerID">
                        <option value="" selected><fmt:message key="customer"/>...</option>
                        <c:forEach items="${requestScope.customers}" var="customer">
                            <option value="${customer.id}">${customer.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="recordID" class="text-warning col-sm-5 col-form-label"><fmt:message key="record-status"/></label>
                <div class="col-xl-12">
                    <select type="text" name="selectedRecordID" class="col-xl-12 custom-select" id="recordID">
                        <option value="" selected><fmt:message key="status"/>...</option>
                        <c:forEach items="${requestScope.records}" var="record">
                            <option value="${record.id}">${record.status} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="comment" class="text-warning col-sm-5 "><fmt:message key="comment"/></label>
                <div class="col-xl-12">
                    <input type="text" class="form-control" name="comment" id="comment" placeholder="<fmt:message key="comment"/>">
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
        <form id="timeForm" action="time-tracking" method="post"></form>
    </div>
    <table class="table table-hover table-dark text-warning my-5" style="margin: auto; width: 98%;">
        <thead>
        <tr class="text-white">
            <th scope="col">ID</th>
            <th scope="col"><fmt:message key="employee"/></th>
            <th scope="col"><fmt:message key="project"/></th>
            <th scope="col"><fmt:message key="worked-hours"/></th>
            <th scope="col"><fmt:message key="project-owner"/></th>
            <th scope="col"><fmt:message key="task"/></th>
            <th scope="col"><fmt:message key="start"/></th>
            <th scope="col"><fmt:message key="end"/></th>
            <th scope="col"><fmt:message key="customer"/></th>
            <th scope="col"><fmt:message key="status"/></th>
            <th scope="col"><fmt:message key="comment"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.timeTrackingList}" var="timeTracking">
            <c:choose>
                <c:when test="${not empty sessionScope.loggedInAsAdmin && sessionScope.loggedInAsAdmin}">
                    <tr>
                        <th scope="row">${timeTracking.id}</th>
                        <td>${timeTracking.getEmployee().getName()}</td>
                        <td>${timeTracking.getProject().getName()}</td>
                        <td>${timeTracking.workedHours}</td>
                        <td>${timeTracking.getProjectOwner().getName()}</td>
                        <td>${timeTracking.getTask().getName()}</td>
                        <td>${timeTracking.start}</td>
                        <td>${timeTracking.end}</td>
                        <td>${timeTracking.getCustomer().getName()}</td>
                        <td>${timeTracking.getRecordStatus().getStatus()}</td>
                        <td>${timeTracking.comment}</td>
                        <td class="text-center">
                            <c:if test="${timeTracking.getEmployee().getId() == sessionScope.userLogged.employeeID}">
                                <button type="submit" class="btn btn-outline-warning btn-sm mx-2" value="update" name="button"
                                        onclick="updateFunction('${timeTracking.id}','${timeTracking.getEmployee().getName()}',
                                                '${timeTracking.getProject().getId()}','${timeTracking.workedHours}','${timeTracking.getProjectOwner().getId()}',
                                                '${timeTracking.getTask().getId()}','${timeTracking.start}','${timeTracking.end}','${timeTracking.getCustomer().getId()}',
                                                '${timeTracking.getRecordStatus().getId()}','${timeTracking.comment}')">
                                    <fmt:message key="edit"/>
                                </button>
                                <button type="submit" class="btn btn-outline-danger btn-sm mx-2" value="delete" name="button"
                                        onclick="deleteFunction('${timeTracking.id}')"><fmt:message key="delete"/>
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${not empty sessionScope.loggedInAsAdmin && sessionScope.loggedInAsAdmin != true
            && timeTracking.getEmployee().getId() == sessionScope.userLogged.employeeID}">
                    <tr>
                        <th scope="row">${timeTracking.id}</th>
                        <td>${timeTracking.getEmployee().getName()}</td>
                        <td>${timeTracking.getProject().getName()}</td>
                        <td>${timeTracking.workedHours}</td>
                        <td>${timeTracking.getProjectOwner().getName()}</td>
                        <td>${timeTracking.getTask().getName()}</td>
                        <td>${timeTracking.start}</td>
                        <td>${timeTracking.end}</td>
                        <td>${timeTracking.getCustomer().getName()}</td>
                        <td>${timeTracking.getRecordStatus().getStatus()}</td>
                        <td>${timeTracking.comment}</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-outline-warning btn-sm mx-2" value="update" name="button"
                                    onclick="updateFunction('${timeTracking.id}','${timeTracking.getEmployee().getName()}',
                                            '${timeTracking.getProject().getId()}','${timeTracking.workedHours}','${timeTracking.getProjectOwner().getId()}',
                                            '${timeTracking.getTask().getId()}','${timeTracking.start}','${timeTracking.end}','${timeTracking.getCustomer().getId()}',
                                            '${timeTracking.getRecordStatus().getId()}','${timeTracking.comment}')">
                                <fmt:message key="edit"/>
                            </button>
                            <button type="submit" class="btn btn-outline-danger btn-sm mx-2" value="delete" name="button"
                                    onclick="deleteFunction('${timeTracking.id}')"><fmt:message key="delete"/>
                            </button>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
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

        document.getElementById("employeeID").setAttribute("required", "");
        document.getElementById("projectID").setAttribute("required", "");
        document.getElementById("workedHours").setAttribute("required", "");
        document.getElementById("projectOwnerID").setAttribute("required", "");
        document.getElementById("taskID").setAttribute("required", "");
        document.getElementById("start").setAttribute("required", "");
        document.getElementById("end").setAttribute("required", "");
        document.getElementById("customerID").setAttribute("required", "");
        document.getElementById("recordID").setAttribute("required", "");
        document.getElementById("comment").setAttribute("required", "");
    }

    function updateFunction(id, employeeID, projectID, workedHours, projectOwnerID, taskID, start, end, customerID, recordID, comment) {
        document.getElementById("createButton").setAttribute("disabled", "");
        document.getElementById("updateButton").removeAttribute("disabled");

        document.getElementById("id").value = id;
        document.getElementById("employeeID").value = employeeID;
        document.getElementById("projectID").value = projectID;
        document.getElementById("workedHours").value = workedHours;
        document.getElementById("projectOwnerID").value = projectOwnerID;
        document.getElementById("taskID").value = taskID;
        document.getElementById("start").value = start;
        document.getElementById("end").value = end;
        document.getElementById("customerID").value = customerID;
        document.getElementById("recordID").value = recordID;
        document.getElementById("comment").value = comment;

        document.getElementById("id").setAttribute("required", "");
        document.getElementById("employeeID").setAttribute("required", "");
        document.getElementById("projectID").setAttribute("required", "");
        document.getElementById("workedHours").setAttribute("required", "");
        document.getElementById("projectOwnerID").setAttribute("required", "");
        document.getElementById("taskID").setAttribute("required", "");
        document.getElementById("start").setAttribute("required", "");
        document.getElementById("end").setAttribute("required", "");
        document.getElementById("customerID").setAttribute("required", "");
        document.getElementById("recordID").setAttribute("required", "");
        document.getElementById("comment").setAttribute("required", "");
        window.scrollTo(0, 0);
    }

    function deleteFunction(id) {
        let form = document.getElementById("timeForm");
        let element = document.createElement("input");
        element.setAttribute("type", "hidden");
        element.setAttribute("name", "timeTrackingID");
        element.setAttribute("value", id);
        form.appendChild(element);
    }
</script>
</body>
</html>
