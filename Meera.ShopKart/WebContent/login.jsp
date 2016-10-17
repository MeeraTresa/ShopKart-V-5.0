<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="loginstyle.css">
<link rel="stylesheet" type="text/css" href="bodystyle.css">


<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
</head>

<body onload="noBack();" 
	onpageshow="if (event.persisted) noBack();" onunload="">	
<div >
<img src="images/logo.png"/>
</div>

<div id="Back">  
    <button type="button" onclick="window.location.href='/Meera.ShopKart/index.html'" value="Back">Back</button>
</div>


<div class="container">
  <div class="login" style="background-color:#63639c">
    <h1>Login</h1>
    <form action="loginServlet" method="post">
      <p><input type="text" name="uname" placeholder="UserId"></p>
      <p><input type="password" name="pass" placeholder="Password"></p>
      <p class="submit"><input type="submit" value="Login"></p>
    </form>
  </div>
</div>

</body>
</html>