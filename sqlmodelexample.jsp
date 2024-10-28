<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    
</head>
<body>
    <div class="container">
        <h2>User List</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Database connection and data fetching
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");

                        String sql = "SELECT userid, username, emailid, image FROM storeuser";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        ResultSet resultSet = statement.executeQuery();

                        while (resultSet.next()) {
                            int userid = resultSet.getInt("userid");
                            String username = resultSet.getString("username");
                            String email = resultSet.getString("emailid");
                            String image = resultSet.getString("image");
                %>
                <tr>
                    <td><%= username %></td>
                    <td><%= email %></td>
                    <td>
                        <!-- Button to trigger the modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" 
                            data-target="#userModal<%= userid %>">
                            View Details
                        </button>
                    </td>
                </tr>

                <!-- Modal for each user -->
                <div class="modal fade" id="userModal<%= userid %>" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="userModalLabel">User Details: <%= username %></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <img src="images/<%= image %>" alt="Profile Image" class="img-thumbnail" style="width: 150px; height: 150px;"><br><br>
                                <p><strong>Name:</strong> <%= username %></p>
                                <p><strong>Email:</strong> <%= email %></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap and jQuery JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
