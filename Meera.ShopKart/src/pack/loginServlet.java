package pack;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ui = request.getParameter("uname");
		String pw = request.getParameter("pass");
		String nm = null;
		
		
		if(Validate.checkUser(ui, pw)){
			User loggedUser = new User();
			
			
			try(Connection con=DBConnection.getConnect()){
					
			PreparedStatement ps1 =con.prepareStatement
                    ("select name from user where userid=?");
			ps1.setString(1, ui);
			ResultSet rs1 = ps1.executeQuery();
			
			
			
			while(rs1.next())
			{
				nm = (rs1.getString(1));
			}
				
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
			loggedUser.setUserId(ui);
			loggedUser.setPass(pw);
			loggedUser.setName(nm);
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedUser", loggedUser);
			
			RequestDispatcher rs= request.getRequestDispatcher("CatalogServ");
			rs.forward(request, response);
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.println("<div align=center>Enter valid Username and Password!!</div>");
	        RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
	        rs.include(request, response);
	        
		}	
	}

}
