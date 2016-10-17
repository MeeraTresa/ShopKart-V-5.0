package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/orderHistServlet")
public class orderHistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		ArrayList<ordertable> listtemp1 = new ArrayList<ordertable>();
		HttpSession session2 = request.getSession(false);
		listtemp1 = (ArrayList<ordertable>)session2.getAttribute("orderselected");
		
		String counter=request.getParameter("counterhtml");
		
		int counterInt=Integer.parseInt(counter);
		ArrayList<String> proarray = new ArrayList<String>();

		int c1=0;
		String parray[] = null;
		String qarray[] = null;
		String carray[] = null;
		String oii1=null;
		String amountfinal=null;
		float tot=0f;
		
		out.println("<html>");
		
		out.println("<h2>YOUR PRODUCT AND PURCHASE DETAILS</h2></head>");
		out.println("<div align='right'><form action='LogoutServlet'><input type='submit' value='Logout' ></input></form></div>");
		out.println("<body>");
		out.println("<div id='Back'><button type='button' onclick='window.location.href= \"/Meera.ShopKart/Catalog.jsp\" ' value='Back'>Back</button></div>");
		try(Connection con = DBConnection.getConnect()){
			
			/*String query="select * from ordertable where userid=?";
		
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user);
			 ResultSet rsa = stmt.executeQuery();
			 while(rsa.next())
			 {
				 oii=rsa.getString(2);
				 String ui=rsa.getString(1);
				 String products=rsa.getString(3);
				 String dt=rsa.getString("datetime");
				 parray=products.split(",");
				*/
			out.println("<table border = 1 cellpadding='2' cellspacing='1'>");
			out.println("<tr><th>Products</th><th>Quantity</th><th>Cost</th></tr>");
			
			for(int z=0;z<listtemp1.size();z++)
			{
			oii1=listtemp1.get(z).getOrderid();
			if(z == counterInt)
			{
				
			String q1="select * from ordertable where orderid=?";
			String amountQuery="select amount from ordertable where orderid=?";
			PreparedStatement stmt2 = con.prepareStatement(q1);
			 stmt2.setString(1,oii1);
			 PreparedStatement stmt3 = con.prepareStatement(amountQuery);
			 stmt3.setString(1,oii1);
			 ResultSet amo=stmt3.executeQuery();
			 while(amo.next())
			 {
				 amountfinal=amo.getString("amount");
			 }
			 
			 ResultSet rsa1 = stmt2.executeQuery();
			 while(rsa1.next()){
				 String proid=rsa1.getString("products");
				 String quaa=rsa1.getString("inventory");
				 String costt=rsa1.getString("cost");
				 parray=proid.split(",");
				 qarray=quaa.split(",");
				 carray=costt.split(",");
				 for(int a=0;a<parray.length;a++)
				 {		String i=parray[a];	
					 String pquery="select prodname from product where prodid=?;";
					 
					 PreparedStatement stmt1 = con.prepareStatement(pquery);
					 stmt1.setString(1,i);
					 ResultSet rsa2 = stmt1.executeQuery();
					 while(rsa2.next()){
						 String pro=rsa2.getString("prodname");
						 //int q=rsa2.getInt("quantity");
						 //float a=rsa2.getFloat("price");     
						 //tot=(tot+(a*q));
						 proarray.add(pro);
						 c1++;
						 //out.println("<tr><td>"+pro+"</td></tr>");
					 }
				 }
			 }
	       			 
					for(int y=0;y<proarray.size();y++)
					{
						
						 out.println("<tr><td>"+proarray.get(y)+"</td>");
						 out.println("<td>"+qarray[y]+"</td>");
						 out.println("<td>"+carray[y]+"</td></tr>");
						 
					 }
					
				 
			 
			out.println("</table>");
		 
	
		 out.println("</body></html>");
			
			out.println("<div align='centre'>Total amount of Purchase = "+amountfinal+"</div>");
		}
			
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}   