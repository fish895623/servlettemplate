<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Registration</title>
    <t:bootstrap/>
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <div class="container">
        <h1>Registration</h1>
        <form method="post" action="registration">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                       name="email"
                       placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                    else.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                       name="password1">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword2">Retype Password</label>
                <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password"
                       name="password2">
            </div>
            <div class="form-group">
                <label for="exampleInputName">Name</label>
                <input type="text" class="form-control" id="exampleInputName" placeholder="Name" name="name">
            </div>
            <div class="form-group">
                <label for="privileges">PRIVILEGES</label>
                <input type="text" class="form-control" id="privileges" name="privileges" placeholder="Privileges">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</main>
</body>
</html>
