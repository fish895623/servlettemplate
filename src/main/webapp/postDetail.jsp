<jsp:useBean id="post" scope="request" type="org.example.demo2.model.PostList"/>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                    if (data.success === 'ok') {
                        location.reload();
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

        <form style="height: 100%" onsubmit="submitButtonClicked(event)">
            <div class="form-group">
                <label for="content">Content</label>
                <textarea class="form-control" id="content" name="content"
                          required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </div>
</main>
</body>
</html>
