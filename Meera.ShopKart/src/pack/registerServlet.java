package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Inside Get method of registerServlet !!!");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String ui = request.getParameter("userid");
		String pw = request.getParameter("fpass");
		String cw=request.getParameter("cpass");
		String un = request.getParameter("name");
		
		
		try(Connection con = DBConnection.getConnect()){
			String q1="select userid from user;";
			Statement psmt1 = con.createStatement();
			ResultSet rs1 = psmt1.executeQuery(q1);
			while(rs1.next())
			{
				String user1=rs1.getString("userid");
				if(user1.equals(ui))
				{
					throw new DuplicateEntry();
				}
			}
			String query="insert into user values('"+ui+"','"+pw+"','"+un+"');";
			PreparedStatement stmt = con.prepareStatement(query);
			 int i = stmt.executeUpdate();
			 if(i != 0)
			 {
				 out.println(i+" User is added with ID: "+ui);
			 }
			 out.println("<br><div align=left><form action='login.jsp' method='post'><input type='submit' value='Login' /></form></div>");
			
		
		}
		catch(DuplicateEntry e){
			
			
			out.println(e);
			out.println("Login with same userID or register as new User.");
			out.println("<br><div align=left><form action='login.jsp' method='post'><input type='submit' value='Login' /></form></div>");
			
		}
		//ideally SQL handling
		/*try {
		    ps.executeUpdate("INSERT INTO ...");
		} catch (SQLException e) {
		    if (e instanceof SQLIntegrityConstraintViolationException) {
		        // Duplicate entry
		    } else {
		        // Other SQL Exception
		    }
		}*/
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
}
}