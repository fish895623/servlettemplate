<%@ page import="org.example.demo2.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
    <% User user = (User) session.getAttribute("user"); %>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="posts">Posts</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Dropdown link
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="posts">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <% if (user != null) { %>
                        <a class="nav-link" aria-current="page">
                            <%=user.getEmail()%>
                        </a>
                        <% } %>
                    </li>
                    <li class="nav-item">
                        <% if (user == null || session.getAttribute("user").equals("false")) {%>
                        <div>
                            <a class="nav-link" href="<%=request.getContextPath()%>/login">Login</a>
                        </div>
                        <% } else { %>
                        <div>
                            <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
                        </div>
                        <% } %>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
