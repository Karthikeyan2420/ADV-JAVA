<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %> <!-- No package import for User class -->

<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>User List</h2>

    <table border="1">
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Photo</th>
        </tr>

        <%
            List<User> users = (List<User>) request.getAttribute("userList");

            if (users != null) {
                for (User user : users) {
        %>
                    <tr>
                        <td><%= user.getSname() %></td>
                        <td><%= user.getAge() %></td>
                        <td><img src="<%= user.getPhoto() %>" width="100" height="100" alt="User Photo"></td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="3">No users found.</td>
                </tr>
        <%
            }
        %>
    </table>
</body>
</html>
