<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="bodystyle.css">
<style>
	table {
	    font-family: arial, sans-serif;
	    border-collapse: collapse;
	    width: 100%;
	}
	
	td, th {
	    border: 1px solid white;
	    text-align: center;
	    padding: 8px;
	}
	
	tr{
	    background-color: #dddddd;
	}
	
	table{
		border:8px #000066 solid;
	}
	
</style>
	
<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
</head>

<body onload="noBack();" 
	onpageshow="if (event.persisted) noBack();" onunload="">	
<div >
<img src="images/logo.png"/>

<div id="Back">  
    <button type="button" onclick="window.location.href='/Meera.ShopKart/EditOrder.jsp'" value="Back">Back</button>
</div>

<div align="right"><form action="LogoutServlet"><input type="submit" value="Logout" ></input></form></div>
</div>
<br>
<h2 align="center" style="color:white">Your Shopping Cart</h2>
	<table>
			<tr>
				<td style="color:#000066">${sessionScope.loggedUser.userId}<strong>${sessionScope.loggedUser.name}'s Shopping Cart</strong></td> 
			</tr>
	</table>
	
	<br>
	
	<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

	<table align="center" border="2px solid black" cellpadding="15px" height="50%" width="100%" >	
	<tr>
				<td>Product Id</td> 
				<td>Product Name</td> 
				<td>Price</td>
				<td>Quantity</td>
				<td>Amount</td>
			</tr>
	
	<c:forEach items = "${sessionScope.cart}" var ="e">
			<c:set var="amount" scope="session" value="${e.cost * e.inventory}"/>
			<c:set var="total"	scope="session" value="${total+amount}"/>
			<tr>
				<td>${e.prodId}</td> 
				<td>${e.prodName}</td> 
				<td>${e.cost}</td>
				<td>${e.inventory}</td>
				<td>${amount}</td>
			</tr>			
	</c:forEach>
			
		<tr>
				<td colspan="5">Total Amount ${total}</td>
		</tr>
		<c:set var="total"	scope="session" value="${0}"/>
	</table>
	<br>
	<div align="center">
	
	<form action="EditOrder.jsp" >
	<input type="submit" value="Edit Order" />
	</form>
	
	<br>
	
	<form action="Payment.jsp" >
	<input type="submit" value="Confirm order and Make payment" />
	</form>
	
	</div>
</body>
</html>