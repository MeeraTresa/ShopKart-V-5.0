package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchProdServlet
 */
@WebServlet("/SearchProdServlet")
public class SearchProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n = request.getParameter("p");
		System.out.println(n+"Searching......");
		if(n==null || n.equals("")){
			request.getRequestDispatcher("Catalog.jsp").forward(request, response);
			//request.getSession().removeAttribute("product");
		}
		else 
		{
			try(Connection con = DBConnection.getConnect()){
				
				String query = "select * from product where prodname like '%"+n+"%'";
				PreparedStatement pstmt = con.prepareStatement(query);
				//pstmt.setString(1,n);
				ResultSet rs = pstmt.executeQuery();
				List<Product> list = new ArrayList<Product>();
				while(rs.next()) {
					Product e = new Product();
					e.setProdId(rs.getString(1));
					e.setProdName(rs.getString(2));
					e.setCost(rs.getFloat(3));
					e.setInventory(rs.getInt(4));
					list.add(e);
				}
				rs.close();
				pstmt.close();
				con.close();
				System.out.println("List :"+list);
				HttpSession s = request.getSession();
				request.getSession().removeAttribute("product");
				s.setAttribute("product", list);
				
				System.out.println("I'm in Serach servlet");
				System.out.println("I changed product list :"+list);
				System.out.println("Proucts searched and retrieved successfully !!!!");
				request.getRequestDispatcher("Catalog.jsp").forward(request, response);

				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}


}
