<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="bodystyle.css">
<title>Register</title>
<script type="text/javascript">
function fun1(){
	var x = document.getElementById("fp").value;
	var y = document.getElementById("cp").value;
	if(x != y)
		{		
		alert("Password mismatch!! Please re-type the details..");
		document.getElementById("fp").value='';
		document.getElementById("cp").value='';		
		document.getElementById("fp").focus();	
		return false;
		}
	return true;
	}
</script>

<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
</head>

<body onload="noBack();" 
	onpageshow="if (event.persisted) noBack();" onunload="">	



<div id="Back">  
    <button type="button" onclick="window.location.href='/Meera.ShopKart/index.html'" value="Back">Back</button>
</div>
<div >
<img src="images/logo.png"/>
</div>
<div style="margin:50px auto; width:34% ;background-color:#c2c2d6; border:8px #000066 solid;">
<h2 style="text-align:center">Register to Login</h2>


<form action="registerServlet" method="post" onsubmit="return fun1()" >


<table align="center">
	<tr>
	<td>Name: </td>
	<td> <input type="text" name="name"></td>
	</tr>
	<tr>
	<td>UserID: </td>
	<td> <input type="text" name="userid"/></td>
	</tr>
	<tr>
	<td>Address: </td>
	<td> <textarea name="addr" rows=4 cols=17></textarea></td>
	</tr>
	

	<tr>
	<td>Enter Password: </td>
	<td>  <input type="password" name="fpass" id="fp"/></td>
	</tr>
	<tr>
	<td>Confirm Password: </td>
	<td> <input type="password" name="cpass" id="cp"/></td>
	</tr>
	<tr><td colspan="2" align="center"><input type="submit" id="sub" value="Register" onclick="return fun1()"/></td></tr>
</table>



</form>
</div>

</body>
</html>