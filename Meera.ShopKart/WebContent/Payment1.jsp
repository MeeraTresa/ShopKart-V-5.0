<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>

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

<script>

function test(){
	
//To check valid entries in delivery details
var name1 = document.getElementById("dname").value;
var name2 = document.getElementById("dadd").value;
var name3 = document.getElementById("dphone").value;
if (name1 == "" || name2 == "" ||  name3 == ""){
	
	alert ("Blank Fields !!! Blank Fields !!!!");
	if(name1== ""){
		document.getElementById("dname").focus();
	}
	else if(name2==""){
		document.getElementById("dadd").focus();
	}
	else{
		document.getElementById("dphone").focus();
	}
	return false;
}

//To check radiobuttons

var r = document.getElementsByName("pay")
var c = -1

for(var i=0; i < r.length; i++){
   if(r[i].checked) {
      c = i; 
   }
}


if (c == -1){
	alert("Please select a payment option !!!");
	return false
}

}  
</script>
</head>
<body>


<div >

<img src="images/logo.png"/>

<div align="center">
<form action="cart.jsp">
<input type="submit" value="Review order/cart"/>
</form>
</div>



<form action="paymentServlet" method="post" onsubmit="return test()">

<table>
<tr><td>Name : <input type = "text" id="dname" name = "dname" ></td></tr>
<tr><td>Delivery address : <textarea id="dadd"  name ="dadd" rows=4 cols=17></textarea></td></tr>
<tr><td>Contact Number :<input type="text" id="dphone" onkeypress="return isNumberKey(event)"></td></tr>
</table>


<table>
<tr><td>Choose your payment gateway</td></tr>
<tr><td><input type="radio" name="pay" value="paytm">Use paytm wallet</td></tr>
<tr><td><input type="radio" name="pay"value="debit">Debit Card</td></tr>
<tr><td><input type="radio" name="pay"value="net">Net Banking</td></tr>
<tr><td><input type="radio" name="pay"value="cod">Cash On Delivery</td></tr>


<tr><td><input type="submit" value="Make payment"></td></tr>
</table>
</form>
</div>
</body>
</html>