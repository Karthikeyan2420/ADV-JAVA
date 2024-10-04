<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.Cookie" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h2>Welcome</h2>
    <%
        // Retrieve cookies
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        if (username != null) {
    %>
            <p>Hello, <%= username %>! You are successfully logged in.</p>
    <%
        } else {
    %>
            <p>You are not logged in. Please <a href="login.jsp">log in</a>(or)<a href="Register.jsp">Register</a>.</p>
    <%
        }
    %>
    <a href="logout.jsp">Logout</a>
</body>
</html>
