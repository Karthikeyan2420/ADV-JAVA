<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
<head>
    <title>User Details</title>
    <style>
        .container {
            display: flex;
            justify-content: space-between;
        }
        .profile {
            text-align: right;
        }
        .profile img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<% 
    session = request.getSession(false); // Get session, do not create new one
    if (session != null) {
        String username = (String) session.getAttribute("username");
        String photo = (String) session.getAttribute("photo");
%>
    <div class="container">
        <div>
            <h2>Welcome, <%= username %>!</h2>
            <p>Here are your details</p>
            <!-- Add other user details here -->
        </div>
        <div class="profile">
            <img src='images/<%= photo %>' alt="User Photo">
        </div>
    </div>
<% 
    } else { 
        // If no session exists, redirect to login page
        response.sendRedirect("login.jsp");
    } 
%>
</body>
</html>
