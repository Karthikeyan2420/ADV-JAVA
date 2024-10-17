


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class Lists
 */
@WebServlet("/Lists")
public class Lists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*  response.setContentType("text/html");

	      
	        // Query to fetch user data
	        String sql = "SELECT SNAME, AGE, PHOTO FROM user001";

	        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            ResultSet resultSet = statement.executeQuery();

	            // Create HTML table for displaying users
	            response.getWriter().println("<html><body>");
	            response.getWriter().println("<h2>User List</h2>");
	            response.getWriter().println("<table border='1'>");
	            response.getWriter().println("<tr><th>Name</th><th>Age</th><th>Photo</th></tr>");

	            // Loop through the result set and display user data
	            while (resultSet.next()) {
	                String name = resultSet.getString("SNAME");
	                int age = resultSet.getInt("AGE");
	                String photoPath = resultSet.getString("PHOTO");

	                response.getWriter().println("<tr>");
	                response.getWriter().println("<td>" + name + "</td>");
	                response.getWriter().println("<td>" + age + "</td>");
	                // Display image from saved path
	                response.getWriter().println("<td><img src='images/" + photoPath + "' width='100' height='100'></td>");
	                response.getWriter().println("</tr>");
	            }

	            response.getWriter().println("</table>");
	            response.getWriter().println("</body></html>");

	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.getWriter().println("Error retrieving users.");
	        }*/
		String username = request.getParameter("username");
        String dob = request.getParameter("dob");

        // Database connection details
     
        // SQL query to verify username and date of birth
        String sql = "SELECT SNAME, PHOTO FROM user001 WHERE SNAME = ? AND DOB = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setDate(2, Date.valueOf(dob)); // Convert string to SQL Date

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // If user is found, set session attributes
                String name = resultSet.getString("SNAME");
                String photo = resultSet.getString("PHOTO");

                HttpSession session = request.getSession();
                session.setAttribute("username", name);
                session.setAttribute("photo", photo);

                // Redirect to user details page
                response.sendRedirect("userDetails.jsp");

            } else {
                // If login fails, show an error message
                response.getWriter().println("Invalid username or date of birth.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error connecting to the database.");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
