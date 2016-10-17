package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PrintServlet
 */
@WebServlet("/PrintServlet")
public class PrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con = DBConnection.getConnect()){
			
			HttpSession session = request.getSession();
			String oi = (String)session.getAttribute("orderIdKey");
			User ui = (User)session.getAttribute("loggedUser");
			PreparedStatement ps1 =con.prepareStatement
                    ("select name from user where userid=?");
			ps1.setString(1, ui.getUserId());
			ResultSet rs1 = ps1.executeQuery();
			String nm = null;
			while(rs1.next())
			{
				nm = (rs1.getString(1));
			}
			session.setAttribute("u_name",nm);			
			}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		RequestDispatcher rs= request.getRequestDispatcher("print_preview.jsp");
		rs.forward(request, response);
	}

}
