<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
</head>
<body>
<div align="center">
    <h1><a href="/" style="text-decoration: none; color: black">Users List</a></h1>
    <h2><a href="/edit" style="text-decoration: none; color: black">Add User</a></h2>
</div>
<hr>
<form align="center" style="margin-right: 400px" method="post" action="search">
    <input type="text" name="search"/>
    <input type="submit" name="search" value="Search"/>
</form>

<div align="center" style="margin-top: 20px">
    <table border="1" cellpadding="8" cellspacing="0">
        <th>First Name</th>
        <th>Last Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Age</th>
        <th width="100">Edit</th>

        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.address}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.age}</td>
                <td>
                    <a href="/edit?id=${user.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/remove?id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${page > 1}">
        <a href="/?page=${page - 1}">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <a href="/?page=${i.index}" style="color: ${page == i.index ? "red" : "blue"}">${i.index}&nbsp;</a>
    </c:forEach>

    <c:if test="${page + 1 <= maxPages}">
        <a href="/?page=${page + 1}">Next</a>
    </c:if>

</div>
</body>
</html>