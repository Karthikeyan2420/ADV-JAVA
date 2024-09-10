

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
        doGet(request, response);
    }
}
