<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.demo2.model.User" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    User user = (User) session.getAttribute("user");

    String pageParam = request.getParameter("page");
    Long currentPage = pageParam == null ? 1 : Long.parseLong(pageParam);
    Long endOfPage = (Long) request.getAttribute("endOfPage");
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <t:bootstrap/>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1>Posts</h1>
        <% if (user == null || session.getAttribute("user").equals("false")) {%>
        <% } else { %>
        <button class="btn btn-primary" onclick="window.location.href='newpost'">New Post</button>
        <% } %>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Author</th>
                <th scope="col">Title</th>
                <th scope="col">Created At</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="posts" scope="request"
                         type="java.util.List<org.example.demo2.model.PostList>"/>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <th scope="row">${post.id}</th>
                    <td><a href="userinfo/${post.authorID}">${post.authorName}</a></td>
                    <td><a href="posts/${post.id}">${post.title}</a></td>
                    <td>${post.created_at}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <% if (currentPage > 1) { %>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/posts"><<</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/posts?page=<%=currentPage - 1%>"><</a>
                    </li>
                    <% } %>
                    <li class="page-item">
                        <a class="page-link"
                           href="<%=request.getContextPath()%>/posts?page=<%=currentPage%>"><%=currentPage%>
                        </a>
                    </li>
                    <% if (currentPage < endOfPage) { %>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/posts?page=<%=currentPage + 1%>">></a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="<%=request.getContextPath()%>/posts?page=<%=endOfPage%>">>></a>
                    </li>
                    <% } %>
                </ul>
            </nav>
        </div>
    </div>
</main>
</body>
</html>
