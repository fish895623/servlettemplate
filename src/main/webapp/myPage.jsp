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
        function loadData() {
            const params = new URLSearchParams(window.location.search);
            const q = params.get('mode');

            const read = document.querySelector('.user-info-read');
            const write = document.querySelector('.user-info-write');
            const modify = document.querySelector('.btn-modify');
            const cancel = document.querySelector('.btn-cancel');

            if (q === 'w') {
                read.textContent = '';
                // make appear
                cancel.removeAttribute('hidden');
                // make hidden
                modify.setAttribute('hidden', "");

            }

            if (q === 'r' || q === null) {
                write.textContent = '';
                // make hidden
                cancel.setAttribute('hidden', "");
                // make appear
                modify.removeAttribute('hidden');
            }
        }

        window.onload = () => {
            loadData();
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
                        <td><input name="email" type="text" value="${user.email}"></td>
                        <td><input name="name" type="text" value="${user.name}"></td>
                        <td><input name="role" type="text" value="${user.role}"></td>
                    </tr>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary btn-modify"
                        onclick="location.href='<%=request.getContextPath()%>/mypage/${user.email}?mode=w'">Modify
                </button>
                <button type="button" class="btn btn-secondary btn-cancel"
                        onclick="location.href='<%=request.getContextPath()%>/mypage/${user.email}?mode=r'">Cancel
                </button>
                <%-- TODO: make btn that submit modified data --%>
            </div>
        </div>
    </div>
</main>
</body>
</html>