<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test task UKEESS</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Registration form</h3>
                </div>
                <div class="panel-body">
                    <form role="form" method="post" action="/employee/add">
                        <div class="row">
                            <div class="col-xs-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" class="form-control input-sm"
                                           placeholder="Employee's name">
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-6">
                                <div class="form-group">
                                    <select name="department" class="form-control">
                                        <c:forEach var="department" items="${departments}">
                                            <c:catch>
                                                <option value="${department.id}">${department.name}</option>
                                            </c:catch>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input type="submit" name="register_operation" value="Register" class="btn btn-info btn-block">
                    </form>
                </div>
                <form name="searchForm" method="post" action="/employee/search">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input name="employeeName" type="text" class="  search-query form-control" placeholder="Search" />
                                <span class="input-group-btn">
                                    <button name="search_operation" class="btn btn-info" type="submit">
                                        <span>Search</span>
                                    </button>
                                </span>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="panel-heading"><span class="lead">Employees:</span></div>
<div class="tablecontainer">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Active</th>
            <th>Department</th>
            <th width="15%"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="employee" items="${employees}">
            <form id="rowForm${employee.id}" action="default" method="post">
                <tr>
                    <td><c:catch> <span>${employee.id}</span></c:catch></td>
                    <td><c:catch> <span>${employee.name}</span></c:catch></td>
                    <%--<td><c:catch> <input type="text" value="${employee.name}" disabled /></c:catch></td>--%>
                    <td><c:catch> <input type="checkbox" disabled
                                         <c:if test="${employee.active eq true}">checked=checked</c:if>
                                         value="${employee.active}"></c:catch></td>
                    <td><c:catch> <span>${employee.department.name}</span></c:catch></td>
                    <td>
                        <button type="submit" formaction="/employee/edit" onclick="changeRowData(this, ${employee.id})" name="edit_save" value="edit" class="btn btn-success custom-width">Edit</button>
                        <button type="submit" formaction="/employee/remove" onclick="getRowData(${employee.id})" name="remove_operation"
                                class="btn btn-danger custom-width">Remove</button>
                        <input type="hidden" name="employeeObject${employee.id}" value="{'id': ${employee.id}, 'name': '${employee.name}'}">
                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>



<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="<c:url value="/resources/js/dispatcher.js"/>"></script>
</body>
</html>
