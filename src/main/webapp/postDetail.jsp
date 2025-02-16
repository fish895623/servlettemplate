<jsp:useBean id="post" scope="request" type="org.example.demo2.model.PostList"/>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.demo2.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <t:bootstrap/>
    <script>
        function submitButtonClicked(event) {
            event.preventDefault();
            const content = document.getElementById("content").value;
            fetch("<%=request.getContextPath()%>/comments", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    postId: ${post.id},
                    content: content
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log("Success: ", data);
                    if (data.status === 'ok') {
                        location.reload()
                    }
                })
                .catch((error) => {
                    console.error("Error: ", error);
                });
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1>${post.title}</h1>
        <p class="card-text">Write Data ${post.created_at}</p>
        <div class="card">
            <div class="card-body">
                <p class="card-text">${post.content}</p>
            </div>
        </div>

        <h3>Comments</h3>

        <% if (user == null || session.getAttribute("user").equals("false")) {%>
        <div class="alert alert-warning" role="alert">
            Please login to write a comment.
        </div>
        <% } else { %>
        <form style="height: 100%" onsubmit="submitButtonClicked(event)">
            <div class="form-group">
                <label for="content">Content</label>
                <textarea class="form-control" id="content" name="content"
                          required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <% } %>

        <%-- NOTE need to change axios to update values. Rerender all data is not good. --%>
        <jsp:useBean id="comments" scope="request" type="java.util.List<org.example.demo2.model.CommentDAO>"/>
        <c:forEach var="comment" items="${comments}">
            <div class="card mb-3 shadow-sm">
                <div class="card-body">
                    <h6 class="card-title">
                        <a href="<%=request.getContextPath()%>/userinfo/${comment.author_id}"
                           class="fw-bold text-decoration-none">
                                ${comment.authorName}
                        </a>
                    </h6>
                    <p class="text-muted small">${comment.createdAt}</p>
                    <p class="card-text">${comment.content}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
