<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <t:bootstrap/>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1>Login Failed</h1>
        <p>Invalid email or password</p>
        <button type="button" class="btn btn-secondary"
                onclick="location.href='<%=request.getContextPath()+"/login"%>'">
            Back to Login
        </button>
    </div>
</main>
</body>
</html>
