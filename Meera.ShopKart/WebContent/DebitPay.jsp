<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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


<script>
 
	  function isNumberKey(evt)
	  {
	     var charCode = (evt.which) ? evt.which : event.keyCode
	     if (charCode != 45  && charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;

	     return true;
	  } 
	
	
</script>
<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" 
	onpageshow="if (event.persisted) noBack();" onunload="">	 

<img src="images/logo.png"/>


<div align="center" style="height:200px; width:600px; margin:0 auto;">

<form action = "CartServlet" method = "post"> 

<table>

<tr>
	<th colspan="2"><strong>Debit Card Payment</strong></th>
</tr>

<tr><td colspan="2">Enter your Debit Card Details :</td></tr>

<tr><td>Card number:</td><td><input type='text' name='card'onkeypress="return isNumberKey(event)"/></td></tr>
<tr><td>CVV number:</td><td><input type='text' name='cvv'onkeypress="return isNumberKey(event)"/></tr>
<tr><td>Valid till:</td><td><input type='text' name='valid'/></td></tr>
<tr><td colspan="2"><input type = 'submit' name = 'submit' value = 'submit'/></td></tr>
</table>

</form>

<br>
<div align="center">
<form action="Payment.jsp"><input type = 'submit' name = 'paymethods' value = 'Choose Different Payment Mode'/></form>
</div>


</div>
</body>
</html>