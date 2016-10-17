package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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



//import com.sun.xml.internal.ws.resources.HttpserverMessages;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AtomicLong idCounter = new AtomicLong();

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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		ArrayList<Product> listtemp = new ArrayList<Product>();
		HttpSession session2 = request.getSession(false);
		listtemp = (ArrayList<Product>)session2.getAttribute("cart");
		String oi=createID();
		float amount=0f;
		
		Date date=new Date();
	    String timeStamp= date.toString();
	    
	  //pass oi to the session
	  		HttpSession session = request.getSession();
	  		session.setAttribute("orderIdKey", oi); 		
	  		session.setAttribute("datetime", timeStamp);
	  	    
	    
	    //Change !!! Get it from paynment.jsp
	    String dname=(String)request.getSession().getAttribute("dname");
	    String dadd=(String)request.getSession().getAttribute("dadd");
	    //String dphone=request.getParameter("dphone");
	    
		
		try(Connection con = DBConnection.getConnect()){
			String uid = ((User)session2.getAttribute("loggedUser")).getUserId();
			
			String strProd= new String("");
			String strqua=new String("");
			String strcost=new String("");
			for(int j=0;j<listtemp.size();j++){
				String l1=Integer.toString((listtemp.get(j).getInventory()));
				strqua=strqua.concat(l1+",");
				strProd = strProd.concat(listtemp.get(j).getProdId()+",");
				amount=amount+((listtemp.get(j).getInventory())*(listtemp.get(j).getCost()));
				String l2=Float.toString(listtemp.get(j).getCost());
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
			
			
			for(int i=0;i<listtemp.size();i++){
				String tempid;
				int tempquan;
				tempid = listtemp.get(i).getProdId();
				tempquan = listtemp.get(i).getInventory();
				PreparedStatement ps2 =con.prepareStatement
		                  ("update product set quantity=quantity-? where prodid=?;");
				ps2.setInt(1, tempquan);
				ps2.setString(2, tempid);
				ps2.executeUpdate();
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	    out.println("<div align=center><h2>Thank you for shopping with ShopKart </h2></div>");
         out.println("<div align=center><strong>Your order was placed successfully at "+timeStamp+"</strong></div>");
         out.println("<div align=center><strong>order ID :" +oi+"</div>");
         out.println("<div align=center>Please note your Order ID to track your order at later Stage</div>");
         out.println("<div align=center>Your package will be delivered to customer " +dname+ " \t to below address within 3-4 Working days</div>");
         out.println("<div align=center>Delivery Address:  " +dadd+ "</div>" );
         
         out.println("<br><div align='center'><form action='PrintServlet' method = 'post'><input type='submit' value='Print Order Confirmation'></input></form></div>");
         out.println("<br><div align=center><form action='CatalogServ' method='post'><input type='submit' value='Continue Shopping' /></form></div>");
         out.println("<br><div align='center'><form action='LogoutServlet'><input type='submit' value='Logout' ></input></form></div>");
	}	
}

