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

/**
 * Servlet implementation class AddtoCart
 */
@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("I'm in addtoCartServlet");
		List<Product> list = new ArrayList<Product>();
		int finalQty=0;
		try(Connection con = DBConnection.getConnect()){
			PreparedStatement ps =con.prepareStatement
                    ("select * from product");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				String prodId = rs.getString("prodid");
				String textbox = "text_"+ prodId;
				String checkbox = "check_"+ prodId;
				System.out.println(request.getParameter(textbox)+"The text box value !!!!");
				if((request.getParameter(checkbox) != null) && (request.getParameter(textbox) != "") && (request.getParameter(textbox) !="0") )
				{
					Product p = new Product();
					int qty = Integer.parseInt(request.getParameter(textbox));
					finalQty+=qty;
					//System.out.println(prodId+" "+rs.getString("prodname")+" "+rs.getInt("price")+" "+qty+" ");					
					p.setProdId(prodId);
					p.setProdName(rs.getString("prodname"));
					p.setCost(rs.getInt("price"));
					p.setInventory(qty);
					list.add(p);
					
				}
			}
			}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	if(finalQty !=0){
		HttpSession session = request.getSession();
		session.setAttribute("cart", list); 		
		RequestDispatcher rs= request.getRequestDispatcher("cart.jsp");
		rs.forward(request, response);
	}
	else{
		
		PrintWriter out = response.getWriter();
		//out.println("Please select the items !!!");
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Please Select items !!!');");
		out.println("location='Catalog.jsp';");
		out.println("</script>");
	}
	
	}
}
