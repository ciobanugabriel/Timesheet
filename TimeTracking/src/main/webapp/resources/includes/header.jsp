<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:bundle basename="Timesheet">
    <nav class="navbar navbar-fixed-top  navbar-dark bg-dark fixed-top ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="home" class="navbar-brand">
                    <img alt="Brand" src="resources/images/sa-logo.png">
                </a>
                <c:choose>
                    <c:when test="${not empty sessionScope.loggedInAsAdmin && sessionScope.loggedInAsAdmin == true}">

                        <c:choose>
                            <c:when test="${not empty requestScope.onTimeTracking && requestScope.onTimeTracking == true}">
                                <a class="button-left btn btn-secondary" href="time-tracking" role="button">
                                    <fmt:message key="time-tracking"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="time-tracking" role="button">
                                    <fmt:message key="time-tracking"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onEmployee && requestScope.onEmployee == true}">
                                <a class="button-left btn btn-secondary" href="employee" role="button"><fmt:message
                                        key="employee"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="employee"
                                   role="button"><fmt:message key="employee"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onCustomer && requestScope.onCustomer == true}">
                                <a class="button-left btn btn-secondary" href="customer" role="button">
                                    <fmt:message key="customer"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="customer"
                                   role="button"><fmt:message key="customer"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onProject && requestScope.onProject == true}">
                                <a class="button-left btn btn-secondary" href="project" role="button"><fmt:message
                                        key="project"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="project" role="button"><fmt:message
                                        key="project"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onProjectOwner && requestScope.onProjectOwner == true}">
                                <a class="button-left btn btn-secondary" href="project-owner" role="button">
                                    <fmt:message key="project-owner"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="project-owner" role="button">
                                    <fmt:message key="project-owner"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onTask && requestScope.onTask == true}">
                                <a class="button-left btn btn-secondary" href="task" role="button"><fmt:message
                                        key="task"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="task" role="button"><fmt:message
                                        key="task"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onStatus && requestScope.onStatus == true}">
                                <a class="button-left btn btn-secondary" href="status" role="button"><fmt:message
                                        key="record-status"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="status" role="button"><fmt:message
                                        key="record-status"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty requestScope.onUser && requestScope.onUser == true}">
                                <a class="button-left btn btn-secondary" href="user" role="button"><fmt:message
                                        key="user"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="user" role="button"><fmt:message
                                        key="user"/></a>
                            </c:otherwise>
                        </c:choose>

                    </c:when>

                    <c:when test="${not empty sessionScope.loggedInAsAdmin && sessionScope.loggedInAsAdmin == false}">

                        <c:choose>
                            <c:when test="${not empty requestScope.onTimeTracking && requestScope.onTimeTracking == true}">
                                <a class="button-left btn btn-secondary" href="time-tracking" role="button">
                                    <fmt:message key="time-tracking"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="button-left btn btn-outline-warning" href="time-tracking" role="button">
                                    <fmt:message key="time-tracking"/></a>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                </c:choose>

            </div>
            <div class="row">
            <div class="mt-3 d-flex justify-content-end ">
                <form method="post" action="language">
                    <button type="submit" class="${sessionScope.roButton}" id="roButton" name="button" value="ro_RO"
                            style="display: none">
                        RO
                    </button>
                    <button type="submit" class="${sessionScope.enButton}" id="enButton" name="button" value="en_EN"
                            style="display: none">
                        EN
                    </button>
                    <button type="submit" class="${sessionScope.deButton}" id="deButton" name="button" value="de_DE"
                            style="display: none">
                        DE
                    </button>
                </form>
            </div>

            <c:if test="${not empty sessionScope.loggedInAsAdmin}">
                <div class="navbar-light navbar-right mt-3 ml-3">
                    <a class="btn btn-outline-danger btn-sm" href="logout" role="button"><fmt:message key="log-out"/></a>
                </div>
            </c:if>

            </div>
        </div>
    </nav>
</fmt:bundle>
