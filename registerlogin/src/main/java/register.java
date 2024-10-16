

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
/**
 * Servlet implementation class register
 */
@WebServlet("/register")
@MultipartConfig 
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		  String sname = request.getParameter("sname");
	        int age = Integer.parseInt(request.getParameter("age"));
	        Part filePart = request.getPart("photo"); // file upload
	        String dobString = request.getParameter("dob");

	        // Save the file to a specific directory
	        String fileName = getFileName(filePart);
	        String uploadPath = "C:\\Users\\kkpr2\\eclipse-workspace\\registerlogin\\src\\main\\webapp\\images\\" + fileName;
	        filePart.write(uploadPath);  // write the file to the path

	        // Convert dob to Date object
	        Date dob = null;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            dob = sdf.parse(dobString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        // Database connection
	      

	        String sql = "INSERT INTO user001 (SNAME, AGE, PHOTO, DOB) VALUES (?, ?, ?, ?)";

	        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "oracle");
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, sname);
	            statement.setInt(2, age);
	            statement.setString(3, fileName); // save the file path
	            statement.setDate(4, new java.sql.Date(dob.getTime()));

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                response.getWriter().println("User registered successfully!");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Helper method to get the file name
	    private String getFileName(Part part) {
	        String contentDisposition = part.getHeader("content-disposition");
	        for (String token : contentDisposition.split(";")) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf('=') + 2, token.length() - 1);
	            }
	        }
	        return null;
	    }
	}


