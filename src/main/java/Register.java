import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String fName = request.getParameter("Name");
		String lName = request.getParameter("LastName");
		String email = request.getParameter("Email");
		String pass = request.getParameter("Password");
		String address = request.getParameter("Address");
		String month = request.getParameter("b_month");
		String day = request.getParameter("b_day");
		String year = request.getParameter("b_year");
		String gender = request.getParameter("radiobutton");
		
		String dob = month+"/"+day+"/"+year;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginSignup","root","password");
			
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setString(5, address);
			ps.setString(6, dob);
			ps.setString(7, gender);
			
			int i = ps.executeUpdate();
			if (i > 0) {
				pw.print("You are Successfully Registered "+fName+". Thank You!!!");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			pw.print(e);
		}
		pw.close();
	}

}
