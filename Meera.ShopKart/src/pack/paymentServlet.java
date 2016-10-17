package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/paymentServlet")
public class paymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		String dname = request.getParameter("dname");
		String dadd = request.getParameter("dadd");
		//System.out.println(dname+" "+dadd+"  Hahahhah");
		request.getSession().setAttribute("dname", dname);
		request.getSession().setAttribute("dadd", dadd);
		
		try{
			out.println("<head><h2>Delivery Details</h2></head>");
			out.println("<body>");
			out.println("<form name='deliveryform' action='CartServlet' method='post'>");	
			
			
		String payment=request.getParameter("pay");
		if(payment.equals("cod"))
		{
			
			out.println("Payment method chosen as Cash on Delivery");
			out.println("Additional charge os Rs.50 will be recovered as COD charge");
			
			
			
		}
		if(payment.equals("debit"))
		{
			
			RequestDispatcher rs= request.getRequestDispatcher("DebitPay.jsp");
			rs.forward(request, response);
			
		}
		
		if(payment.equals("net"))
		{
			out.println("You will be redirected to the secure RSA payment....");
			
		}
		if(payment.equals("paytm"))
		{
			out.println("Amount will be recovered from PAYTM wallet");
			
			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println("<head><h2>Action required</h2></head>");
			out.println("<body>");			
			out.println("<form name='error' action='Payment.jsp'>");	
			out.println("Oops !!! Something went wrong !!!");
			out.println("</form name='error'>");
			out.println("</body>");
			
		}
		out.println("<input type='submit' name = 'c' value='continue' ");
		out.println("</form name='deliveryform'>");
		out.println("</body>");
		out.println("</html>");
		
		//RequestDispatcher rs= request.getRequestDispatcher("CartServlet");
		//rs.forward(request, response);
		
		
	
	}

}