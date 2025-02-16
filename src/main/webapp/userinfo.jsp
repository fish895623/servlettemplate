<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="posts" class="java.util.ArrayList" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <t:bootstrap/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<main>
    <div class="container">
        <h1>Posts</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Created At</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${posts}">
                <jsp:useBean id="post" class="org.example.demo2.model.PostList" scope="request"/>
                <tr>
                    <th scope="row">${post.id}</th>
                    <td><a href="<%=request.getContextPath()%>/posts/${post.id}">${post.title}</a></td>
                    <td>${post.created_at}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
