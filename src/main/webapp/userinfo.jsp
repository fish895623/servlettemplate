<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.demo2.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <t:bootstrap/>
    <style>
        .sidebar {
            width: 200px;
            float: left;
            margin-right: 20px;
        }

        .content {
            margin-left: 220px;
        }

        th {
            text-transform: capitalize;
        }
    </style>
    <script>
        function loadData() {
            const params = new URLSearchParams(window.location.search);
            const q = params.get('q');

            const posts = document.querySelector('.posts');
            const comments = document.querySelector('.comments');

            if (q === 'comments') {
                posts.textContent = '';
            }

            if (q === 'post' || q === null) {
                comments.textContent = '';
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
            <div class="sidebar">
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="<%=request.getContextPath()%>/userinfo/<%=user.getId()%>?q=post">
                            Posts
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="<%=request.getContextPath()%>/userinfo/<%=user.getId()%>?q=comments">
                            Comments
                        </a>
                    </li>
                </ul>
            </div>
            <div class="content posts">
                <h1 class="content-title">Posts</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Created At</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="posts" scope="request" type="java.util.List<org.example.demo2.model.PostList>"/>
                    <c:forEach var="post" items="${posts}">
                        <tr>
                            <th scope="row">${post.id}</th>
                            <td><a href="<%=request.getContextPath()%>/posts/${post.id}">${post.title}</a></td>
                            <td>${post.created_at}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="content comments">
                <h1 class="content-title">Comments</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">content</th>
                        <th scope="col">created at</th>
                        <th scope="col">post title</th>
                        <th scope="col">post created at</th>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:useBean id="comments" scope="request"
                                 type="java.util.List<org.example.demo2.model.CommentDAO>"/>
                    <c:forEach var="comment" items="${comments}">
                        <tr>
                            <th scope="row">${comment.id}</th>
                            <th scope="row">
                                <a href="<%=request.getContextPath()%>/posts/${comment.postId}">
                                        ${comment.postTitle}
                                </a>
                            </th>
                            <th scope="row">${comment.postCreatedAt}</th>

                            <th scope="row">${comment.content}</th>
                            <th scope="row">${comment.createdAt}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
</html>
