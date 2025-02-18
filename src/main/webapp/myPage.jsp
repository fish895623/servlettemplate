<%--
  Created by IntelliJ IDEA.
  User: ben13
  Date: 2025-02-17
  Time: 오후 2:25
  To change this template use File | Settings | File Templates.
--%>

<jsp:useBean id="user" scope="request" type="org.example.demo2.model.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.demo2.model.User" %>
<%@ page import="java.util.Objects" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
    <t:bootstrap/>
    <style>
        .sidebar {
            width: 200px;
        }

        .content {
            margin-left: 220px;
        }
    </style>
    <script>
        function test() {
            const roleSelect = document.getElementById('role-select');
            console.log(roleSelect.value);
        }

        function validateForm() {
            const roleSelect = document.getElementById('role-select');
            if (roleSelect.value === 'Choose') {
                alert('Please select a valid role.');
                return false;
            }
            return true;
        }

        function loadData() {
            const params = new URLSearchParams(window.location.search);
            const q = params.get('mode');

            const read = document.querySelector('.user-info-read');
            const write = document.querySelector('.user-info-write');
            const modify = document.querySelector('.btn-modify');
            const cancel = document.querySelector('.btn-cancel');
            const submit = document.querySelector('.btn-submit');

            if (q === 'w') {
                read.textContent = '';
                // make appear
                cancel.removeAttribute('hidden');
                // make hidden
                modify.setAttribute('hidden', "");
                // make appear
                submit.removeAttribute('hidden');

            }

            if (q === 'r' || q === null) {
                write.textContent = '';
                // make hidden
                cancel.setAttribute('hidden', "");
                // make appear
                modify.removeAttribute('hidden');
                // make hidden
                submit.setAttribute('hidden', "");
            }
        }

        window.onload = () => {
            loadData();
            test();
        }
    </script>
</head>
<body>
<jsp:include page="/header.jsp"/>
<main>
    <div class="container">
        <div class="row">

            <div class="col-md-9 content user-info">
                <h1 class="content-title">Info</h1>
                <form method="post" action="${pageContext.request.contextPath}/updateUser" onsubmit="return validateForm()"
                      onchange="test()">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Email</th>
                            <th scope="col">Name</th>
                            <th scope="col">Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="user-info-read">
                            <th scope="row">${user.id}</th>
                            <td>${user.email}</td>
                            <td>${user.name}</td>
                            <td>${user.role}</td>
                        </tr>
                        <tr class="user-info-write">
                            <th scope="row">${user.id}</th>
                            <td><label>
                                <input readonly name="email" type="text" value="${user.email}">
                            </label></td>
                            <td><label>
                                <input name="name" type="text" value="${user.name}">
                            </label></td>
                            <td><label for="role-select"></label><select name="role" id="role-select">

                                <c:choose>

                                    <c:when test="${user.role == 'ADMIN'}">
                                        <option value="ADMIN" selected>ADMIN</option>
                                        <option value="USER">USER</option>
                                    </c:when>

                                    <c:when test="${user.role == 'USER'}">
                                        <option value="ADMIN">ADMIN</option>
                                        <option value="USER" selected>USER</option>
                                    </c:when>

                                    <c:otherwise>
                                        <option value="Choose" selected disabled>Choose</option>
                                        <option value="ADMIN">ADMIN</option>
                                        <option value="USER">USER</option>
                                    </c:otherwise>

                                </c:choose>
                            </select></td>

                        </tr>
                        </tbody>
                    </table>
                    <button type="button" class="btn btn-primary btn-modify"
                            onclick="location.href='<%=request.getContextPath()%>/mypage/${user.email}?mode=w'">Modify
                    </button>
                    <button type="button" class="btn btn-secondary btn-cancel"
                            onclick="location.href='<%=request.getContextPath()%>/mypage/${user.email}?mode=r'">Cancel
                    </button>
                    <button type="submit" class="btn btn-primary btn-submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>