<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Print Preview</title>
<script type='text/javascript' src='pdf_js.js'></script>

<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
</head>

<body onload="noBack();" 
	onpageshow="if (event.persisted) noBack();" onunload="">	
<form id="form1" runat="server">
    <div align="center">
    <div align="center" id="printablediv" style="width: 70%; height: 400px">
       
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
       <table border="2px solid black">
       <tr><th colspan="5"><strong>Bill</strong></th></tr>
       <tr><th colspan="5"><strong>Personal Details</strong></tr>
       <tr><td colspan="2">User Name</td><td colspan="3">${sessionScope.u_name}</td></tr>
       <tr><td colspan="2">User Id</td><td colspan="3">${sessionScope.loggedUser.userId}</td></tr>
       <tr><td colspan="2">Order Id</td><td colspan="3">${sessionScope.orderIdKey}</td></tr>
       <tr><td colspan="2">Date and Time of Order </td><td colspan="3">${sessionScope.datetime }</td></tr>       
       <tr><th colspan="5"><Strong>Order Summary</Strong></th></tr>
       <tr>
			<th>Product ID </th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Cost</th>
		</tr>
       <c:forEach items = "${sessionScope.cart}" var ="e">
			<c:set var="amount" scope="session" value="${e.cost * e.inventory}"/>
			<c:set var="total"	scope="session" value="${total+amount}"/>
			<tr>
				<td>${e.prodId}</td> 
				<td>${e.prodName}</td> 
				<td>${e.cost}</td>
				<td>${e.inventory}</td>
				<td align="right">${amount}</td>
			</tr>			
	</c:forEach> 
		<tr>
			<td colspan="4" align="center"><strong>Total Amount</strong></td>
			<td align="right"><strong>${total}</strong></td>
		</tr>  
       </table>
    </div>
    <input type="button" value="Save as pdf" onclick="javascript:printDiv('printablediv')" align="middle"/>
    </div>
</form>

<br>
    <div align="center"><form action="CatalogServ" method = "post"><input type="submit" value="Continue Shopping"></input></form></div>
    </div>


<br>
    <div align="center"><form action="LogoutServlet"><input type="submit" value="Logout" ></input></form></div>
    </div> 
</body>
</html>