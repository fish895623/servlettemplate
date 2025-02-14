<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <t:axios/>
    <t:bootstrap/>
    <script>
        function submitButtonClicked(event) {
            event.preventDefault();
            const id = 1;
            const title = document.getElementById("title").value;
            const content = document.getElementById("content").value;
            fetch("<%=request.getContextPath()%>/newpost", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    title: title,
                    content: content
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log("Success: ", data);
                })
                .catch((error) => {
                    console.error("Error: ", error);
                });
        }
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <div class="container">
        <h1>New Posts</h1>
        <form style="height: 100%" onsubmit="submitButtonClicked(event)">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
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
