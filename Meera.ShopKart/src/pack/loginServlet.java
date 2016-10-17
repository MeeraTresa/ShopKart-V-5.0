package pack;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String un = request.getParameter("uname");
		String pw = request.getParameter("pass");
		String nm = request.getParameter("name");
		
		if(Validate.checkUser(un, pw)){
			User loggedUser = new User();
			loggedUser.setUserId(un);
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
