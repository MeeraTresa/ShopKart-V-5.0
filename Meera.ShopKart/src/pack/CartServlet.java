package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/*This servlet is the most important one in this entire ShopKart project 
 * Here's where it is updating the product table and also inserting values into the order table !!!
 */


@WebServlet("/CartServlet")

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AtomicLong idCounter = new AtomicLong();

	//The function which generates the order ids in a predefined format 
	public String createID() {
		 String s = "";
	        double d;
	        for (int i = 1; i <= 16; i++) {
	            d = Math.random() * 10;
	            s = s + ((int)d);
	            if (i % 4 == 0 && i != 16) {
	                s = s + "-";
	            }
	        }
	        return s;
	}

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		ArrayList<Product> cartproducts = new ArrayList<Product>();
		HttpSession session2 = request.getSession(false);
		cartproducts = (ArrayList<Product>)session2.getAttribute("cart");
		
		String oi=createID();	//order id
		
		float amount=0f;
		
		Date date=new Date();
	    String timeStamp= date.toString();	//date and time of the order 
	    
	  
	    //Passing order id and datetime to session
	  		HttpSession session = request.getSession();
	  		session.setAttribute("orderIdKey", oi); 		
	  		session.setAttribute("datetime", timeStamp);
	  	    
	    
	   //Getting Delivery details from thesession
	    String dname=(String)request.getSession().getAttribute("dname");
	    String dadd=(String)request.getSession().getAttribute("dadd");
	 
	
	    Connection con = DBConnection.getConnect();
	    try {
			con.setAutoCommit(false);
			System.out.println("Autocommit set to false !!!!");
		} catch (SQLException e2) {
			System.out.println("AutoCommit set to false error !!! ");
			e2.printStackTrace();
		}
	    
		try{
			
			System.out.println(con.getTransactionIsolation()+"This is the current transaction isolation level");
			con.setTransactionIsolation(1);
			System.out.println(con.getTransactionIsolation()+"This is the new Transaction Isolation !!!");
			
			//Preparing the ordertable entries
			
			String uid = ((User)session2.getAttribute("loggedUser")).getUserId();
			String strProd= new String("");
			String strqua=new String("");
			String strcost=new String("");
			for(int j=0;j<cartproducts.size();j++){
				String l1=Integer.toString((cartproducts.get(j).getInventory()));
				strqua=strqua.concat(l1+",");
				strProd = strProd.concat(cartproducts.get(j).getProdId()+",");
				amount=amount+((cartproducts.get(j).getInventory())*(cartproducts.get(j).getCost()));
				String l2=Float.toString(cartproducts.get(j).getCost());
				strcost=strcost.concat(l2+",");
			}
			
			String am=Float.toString(amount);
			
			
			PreparedStatement ps3 =con.prepareStatement
	                  ("INSERT INTO ordertable (`userid`,`orderid`,`products`,`datetime`,`inventory`,`cost`,`amount`) VALUES (?,?,?,?,?,?,?);");
			ps3.setString(1,uid);
			ps3.setString(2,oi);
			ps3.setString(3,strProd);
			ps3.setString(4,timeStamp);
			ps3.setString(5,strqua);
			ps3.setString(6,strcost);
			ps3.setString(7,am);
			
			ps3.executeUpdate();
			
			
			for(int i=0;i<cartproducts.size();i++){
				String tempid;
				int tempquan;
				tempid = cartproducts.get(i).getProdId();
				tempquan = cartproducts.get(i).getInventory();
				PreparedStatement ps2 =con.prepareStatement
		                  ("update product set quantity=quantity-? where prodid=?;");
				ps2.setInt(1, tempquan);
				ps2.setString(2, tempid);
				ps2.executeUpdate();
			}
			
			//Committing the transaction
			
			try{
				con.commit();
				 System.out.println("Committed changes !!!");
				 out.println("<div align=center><h2>Thank you for shopping with ShopKart </h2></div>");
		         out.println("<div align=center><strong>Your order was placed successfully at "+timeStamp+"</strong></div>");
		         out.println("<div align=center><strong>order ID :" +oi+"</div>");
		         out.println("<div align=center>Please note your Order ID to track your order at later Stage</div>");
		         out.println("<div align=center>Your package will be delivered to customer " +dname+ " \t to below address within 3-4 Working days</div>");
		         out.println("<div align=center>Delivery Address:  " +dadd+ "</div>" );
		         
		         out.println("<br><div align='center'><form action='PrintServlet' method = 'post'><input type='submit' value='Print Order Confirmation'></input></form></div>");
		         out.println("<br><div align=center><form action='CatalogServ' method='post'><input type='submit' value='Continue Shopping' /></form></div>");
		         out.println("<br><div align='center'><form action='LogoutServlet'><input type='submit' value='Logout' ></input></form></div>");
			}catch(SQLException e){
				System.out.println("Could not commit !!!");
			}
			
			
	}catch(Exception e){
		
		//Something went wrong in the transaction.
		
				try {
					
					con.rollback();
					out.println("<div align=center><h2>Oooopssss !!! Sorry Your order couldn't be palced <br> Your money will be refunded!!!<br> Redirecting....... </h2></div>");
					RequestDispatcher rs= request.getRequestDispatcher("EditOrder.jsp");
					rs.forward(request, response);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					System.out.println("\n\tError in rollback !!!");
					
				}
				e.printStackTrace();
		}
	
		
		
	}
	
	
}

