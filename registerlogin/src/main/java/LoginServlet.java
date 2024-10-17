

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 // Get the username and date of birth from the form
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

}
