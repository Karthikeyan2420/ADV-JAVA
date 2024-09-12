

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  // Set the content type to HTML
        response.setContentType("text/html");

        // Get the PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Write HTML content to the response
        out.println("<html>");
        out.println("<head><title>Hello World Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, World!</h1>");
        out.println("<p>Welcome to the Java Servlet!</p>");
        
     // Display a form for submitting data
        out.println("<form action='/check12/hello' method='post'>");
        out.println("Serial Number: <input type='text' name='serialNumber'><br>");
        out.println("Full Name: <input type='text' name='fullName'><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        
        
        out.println("<table border='1'><tr><th>Serial Number</th><th>Full Name</th></tr>");
        
    	 try {
    		
    		 Class.forName("oracle.jdbc.driver.OracleDriver");
    		 
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");
            
           
             PreparedStatement stmt = con.prepareStatement("select * from t3");
             ResultSet rs = stmt.executeQuery();
             
             
             while (rs.next()) {
            	 out.println("<tr><td>");
                 out.println(rs.getInt(1)+"</td>");
                 out.println("<td>"+rs.getString(2)+"</td></tr>");
                 
                 
             }
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
    	 out.println("</body>");
         out.println("</html>");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests by calling doGet
    	// Extract form data
        String serialNumber = request.getParameter("serialNumber");
        String fullName = request.getParameter("fullName");

        // Database connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");

            // SQL query to insert data
            String sql = "INSERT INTO t3 (id,name) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // Set parameters
            stmt.setInt(1, Integer.parseInt(serialNumber)); // Assuming Serial Number is an integer
            stmt.setString(2, fullName);

            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new record was inserted successfully!");
            }

            // Redirect back to doGet() to display the table with the new entry
            response.sendRedirect("/hello");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
