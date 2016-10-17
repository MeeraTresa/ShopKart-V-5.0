package pack;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderHist")

public class OrderHist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession s=request.getSession(false);
		String user=((User)s.getAttribute("loggedUser")).getUserId();
		
		out.println("<html>");
		out.println("<head><h2>Welcome "+user+" </h2>");
		
		out.println("<div align='right'><form action='LogoutServlet'><input type='submit' value='Logout' ></input></form></div>");
		out.println("<h2>YOUR ORDER DETAILS</h2>");
		out.println("<body>");
		     
		String oii=null;
		int y=0;
		List<ordertable> listorder = new ArrayList<ordertable>();
		
		try(Connection con = DBConnection.getConnect()){
				
			String query="select * from ordertable where userid=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user);
			 ResultSet rsa = stmt.executeQuery();
			 while(rsa.next())
			 {
				 ordertable o=new ordertable();
				 oii=rsa.getString(2);
				 String ui=rsa.getString(1);
				 String products=rsa.getString(3);
				 String dt=rsa.getString("datetime");
				 String i=rsa.getString("inventory");
				 String c=rsa.getString("cost");
				 String a=rsa.getString("amount");
			   	 o.setOrderid(oii);
				 o.setUserid(ui);
				 o.setDatetime(dt);
				 o.setProducts(products);
				 o.setInventory(i);
				 o.setCost(c);
				 o.setAmount(a);
				 listorder.add(o);
				
				 out.println("<form action='orderHistServlet' method='post'>");
				 out.println("<table border=1>");
				 out.println("<tr><th><strong>Order Serial Number:<input type='text' name='counterhtml' value='"+y+"'/></input></th></tr>");
				 out.println("<tr><th><strong>Order ID of Purchase : "+oii+"</strong></th></tr>");
				 out.println("<tr><th><strong>Date and time of purchase: "+dt+"</strong></th><td>");
				out.println("<tr><td><div align='centre'><input type='submit' value='Click here for order details'/></div></td></tr>");
			    out.println("</table></form>");
			    y++;
			 }
			    
			    HttpSession order = request.getSession();
				order.setAttribute("orderselected", listorder);			     
			    RequestDispatcher rd1 = request.getRequestDispatcher("orderHistServlet"); 
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
