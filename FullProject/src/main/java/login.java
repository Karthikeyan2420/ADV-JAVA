

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");
		 try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");

	            // SQL query to insert data
	            String query = "SELECT * FROM user1 WHERE fn=? AND ps=?";
	            PreparedStatement statement = con.prepareStatement(query);
	            statement.setString(1, username);
	            statement.setString(2, password);
	            ResultSet resultSet = statement.executeQuery();
	            System.out.println("------------------------------------------------------");
	            System.out.println(username+" "+password);
	            boolean a=resultSet.next();
	            System.out.println(a+"just change");
	            if (a) {
	            	System.out.println("success");
	                // Create a cookie to store the username
	                Cookie userCookie = new Cookie("username", username);
	                userCookie.setMaxAge(60 * 60); // Set cookie expiry to 1 hour
	                response.addCookie(userCookie);

	                // Redirect to welcome page
	                response.sendRedirect("welcome.jsp");
	            } else {
	            	System.out.println("failed");
	                response.getWriter().println("Invalid username or password.");
	            }


	            // Close connections
	            resultSet.close();
	            statement.close();
	            con.close();
	        } catch (Exception e) {
	            response.getWriter().println("Error: " + e.getMessage());
	        }

	}

}
