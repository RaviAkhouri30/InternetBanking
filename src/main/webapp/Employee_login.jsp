<%@page import="com.solution.ib.bean.EmpBean"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<% 
	EmpBean bean = new EmpBean();
	bean = (EmpBean) session.getAttribute("data");
	if(session.getAttribute("data")!=null){
		if(bean.getStatus()==9){
			response.sendRedirect("EmployeeHome.jsp");
		}
		if(bean.getStatus()==3){
			response.sendRedirect("Employee.jsp");
		}
		try{
			String st = request.getParameter("Loginstatus");
		}catch(NullPointerException e){
			e.printStackTrace();
		}
}
%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="script/jquery.min.js"></script>
<script src="script/popper.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<link rel="stylesheet" href="style/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script type="text/javascript" src="script/FormValidation.js"></script>
<script type="text/javascript" src="script/formval.js"> </script>
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<style type="text/css">
#imgcontainer {
	width: 100%;
	height: 400px;
	position: relative;
	padding: 0px;
	top: 0px;
	right: 0px;
	left: 0px;
	bottom: 0px;
	background-image: url("images/bank.jpg");
	background-color: black;
	overflow: hidden;
	background-position: center;
}
</style>
<title>GIB Employee Login</title>
</head>
<body>
	<script type="text/javascript">
	function logoutmsg() {
		Swal.fire({
			  icon: 'success',
			  title: 'Great',
			  text: 'You are Logged out Successfully',
			  footer: '<a href>Why do I have this issue?</a>'
			})
	}
	function deactive() {
		Swal.fire({
			  icon: 'error',
			  title: 'Sorry',
			  text: 'Your Account is not Active yet',
			  footer: 'Talk to your Admin'
			})
	}
	</script>
	<% 
	String msg = request.getParameter("msg");
if(msg==null){
	
}else{
	if(msg.equalsIgnoreCase("success")){
%>
	<script type="text/javascript">
 logoutmsg(); 
</script>
	<%

 msg=null;
	}else {}}
%>

	<script type="text/javascript">
	function loginfailed() {
		Swal.fire({
			  icon: 'error',
			  title: 'Invalid User Name and Password',
			  text: 'Please Try Again',
			  footer: '<a href>Why do I have this issue?</a>'
			})
	}
	</script>
	<% 
	String msg1 = request.getParameter("msg");
if(msg1==null){
	
}else{
	if(msg1.equalsIgnoreCase("failed")){
%>
	<script type="text/javascript">
 loginfailed(); 
</script>
	<%
	}
	if(msg1.equalsIgnoreCase("deactive")){
		%>
	<script type="text/javascript">
		deactive(); 
		</script>
	<%
			}
} %>

	<ul class="container1" style="outline: none; border: none;">
		<li class="li2"><img src="images\GIB.png" width="100px"
			height="100px" alt="GIB Logo"
			style="padding: 0px; margin-left: 20px;"></li>
		<li class="li1"><button type="button" class="login-btn"
				style="outline: none;"
				onclick="document.getElementById('id01').style.display='block'">
				<span>Login</span>
			</button>
	</ul>
	<ul
		style="background-color: #FFFFFF; list-style-type: none; margin: 0; padding: 0; overflow: hidden;">
		<li class="li1">
	</ul>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark "
		style="padding: 0px;">
		<img class="navbar-brand" src="images\GIBLogo.png" width="50px"
			height="50px" alt="GIB Logo" style="padding: 0px; margin-left: 10px;">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><button type="button"
						class="nav-button-active">Home</button></li>
			</ul>
		</div>
	</nav>
	<div id="imgcontainer">
		<div id="overlay">
			<div class="headertextcontainer">
				<h2 class="Headertext">From Here You can manage Your Employee
					Account</h2>

				<button class="header-btn" style="outline: none;">
					<span>Explore More</span>
				</button>

				<div id="id01" class="modal">
					<form class="modal-content animate" method="POST" action="EmpLogin"
						name="login" onsubmit="return validateLogin()">
						<div class="container">
							<span
								onclick="document.getElementById('id01').style.display='none'"
								class="close" title="Close Modal">&times;</span> <img
								src="images\GIB.png" width="200px" height="200px" alt="solution"
								style="margin-left: 120px; padding: 10px;">
							<h1>Employee Login</h1>
							<span id="unameval">Please enter your user name</span> <label></label>
							<input type="text" id="uname" name="uname"
								placeholder="User Name" onkeypress=""> <span
								id="passval">Please enter your Password</span> <label></label> <input
								type="password" id="pass" name="pass" placeholder="Password">
							<label></label> <input type="submit" class="button1" id="login"
								name="login" value="Login" style="outline: none;"> <label></label>
							<input type="checkbox" id="rem" name="rem"> <label
								for="rem" class="label-rem" id="label-rem">Remember me </label>
							<label></label><br>
							<button type="button" class="button-cancel"
								onclick="document.getElementById('id01').style.display='none'"
								style="outline: none;">Cancel</button>
							<button type="reset" class="button4" style="outline: none;">Reset</button>
							<br> <span class="reset-pass"><a href="#">Forget
									Password</a></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<span class="Headertext">From Here You can manage Your Employee
		Account</span>

	<script>
document.addEventListener('contextmenu', event => event.preventDefault());
window.onclick = function(event) {
    if (event.target == document.getElementById('id01') || event.target == document.getElementById('id02')) {
    	document.getElementById('id01').style.display = "none";
    }
}
</script>

</body>
</html>