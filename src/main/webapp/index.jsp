<%@page import="com.solution.ib.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%! String msg= new String(); %>
<%
msg = request.getParameter("msg");

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
 if(session.getAttribute("data")!=null){
	 CustomerBean data = new CustomerBean();
	 data = (CustomerBean) session.getAttribute("data");
	  if(data.getStatus()==3){
		  response.sendRedirect("CustomerHome.jsp");
	  }
 }
%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="script/jquery.min.js"></script>
<script src="script/popper.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<link rel="stylesheet" href="style/style.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
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
<title>Great Indian Bank</title>
</head>
<body>
	<script type="text/javascript">
		
		function reg() {	
		Swal.fire({
			  icon: 'success',
			  title: 'Registered Successfully',
			  text: '!!!Login again to complete your Registration!!!',
			  footer: 'Thanks for Signing Up'
			})
		}
		function lout() {	
			Swal.fire({
				  icon: 'success',
				  title: 'You are logged out Successfully',
				  text: '!!!Thanks for using our Internet Banking!!!',
				  footer: ''
				})
			}
		function regfailed() {	
		Swal.fire({
			  icon: 'error',
			  title: 'Registeration Failed',
			  text: '!!!Opps Something Went Wrong!!!',
			  footer: 'please try again!'
			})
		}
		function review() {	
			Swal.fire({
				  icon: 'success',
				  title: 'Your Application is under Review',
				  text: '!!!We will inform you Once It completes!!!',
				  footer: 'We will do the verification at earliest'
				})
			}
		function invalid() {
			Swal.fire({
				  icon: 'error',
				  title: 'Invalid User Name and Password',
				  text: '!!!Please Check Your User Name and Password!!!',
				  footer: 'If the Problem persist Contact our Helpline'
				})
		}
		function registered() {
			Swal.fire({
				  icon: 'success',
				  title: 'Your Application is Submitted',
				  text: '!!!We will inform you Once It get Verified!!!',
				  footer: 'We Appreciate your Pateince'
				})
		}
		function Failed() {
			Swal.fire({
				  icon: 'error',
				  title: 'Your Application was not able to Submit',
				  text: 'Please Try Again!',
				  footer: 'We Appreciate your Pateince'
				})
		}
	</script>
	<%
if(msg==null){
	
}else{
	if(msg.equals("reg")){
		%>
	<script type="text/javascript">reg();</script>
	<%
	}
	if(msg.equals("regFailed")){
		%>
	<script type="text/javascript">regfailed();</script>
	<%
	}
	if(msg.equals("review")){
		%>
	<script type="text/javascript">
		review();
		</script>
	<%
	}
	if(msg.equals("invalid")){
		%>
	<script type="text/javascript">
		invalid();
		</script>
	<%
	}
	if(msg.equals("success")){
		%>
	<script type="text/javascript">
		lout();
		</script>
	<%
	}if(msg.equals("registered")){
		%>
	<script type="text/javascript">
		registered();
		</script>
	<%
	}
	if(msg.equals("Failed")){
		%>
	<script type="text/javascript">
		Failed();
		</script>
	<%
	}
}
%>
	<ul class="container1" style="outline: none; border: none;">
		<li class="li2"><img src="images\GIB.png" width="100px"
			height="100px" alt="GIB Logo"
			style="padding: 0px; margin-left: 20px;"></li>
		<li class="li1"><button type="button" class="login-btn"
				style="outline: none;"
				onclick="document.getElementById('id02').style.display='block'">
				<span>Register</span>
			</button></li>
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
				<h2 class="Headertext">Open Zero Balance Savings Account in few
					Minutes</h2>
				<h3
					style="font-family: sans-serif, serifcursive; font-size: 16px; outline: none; color: #FFFFFF; background: none; font-weight: bold; margin-top: 10px; margin-left: 65px;">Get
					Your Savings Account Instantly without Paper Work...</h3>
				<button class="header-btn" style="outline: none;">
					<span>Explore More</span>
				</button>

				<div id="id01" class="modal">
					<form class="modal-content animate" method="POST"
						action="CustomerLogin">
						<div class="container">
							<span
								onclick="document.getElementById('id01').style.display='none'"
								class="close" title="Close Modal">&times;</span> <img
								src="images\GIB.png" width="200px" height="200px" alt="solution"
								style="margin-left: 120px; padding: 10px;">
							<h1>Employee Login</h1>
							<label></label> <input type="text" id="uname1" name="uname1"
								placeholder="User Name"> <label></label> <input
								type="password" id="pass" name="pass" placeholder="Password">
							<label></label> <input type="submit" class="button1" id="login"
								name="login" value="Login" style="outline: none;"> <label></label>
							<input type="checkbox" id="rem" name="rem"> <label
								for="rem" class="label-rem">Remember me </label> <label></label><br>
							<button type="button" class="button-cancel"
								onclick="document.getElementById('id01').style.display='none'">Cancel</button>
							<button type="reset" class="button4">Reset</button>
							<br> <span class="reset-pass"><a href="#">Forget
									Password</a></span>
						</div>
					</form>
				</div>
				<div id="id02" class="modal">
					<form class="modal-content animate" action="UserRegistration1" method="POST">
						<div class="container">
							<span
								onclick="document.getElementById('id02').style.display='none'"
								class="close" title="Close Modal">&times;</span> <img
								src="images\GIB.png" width="200px" height="200px" alt="solution"
								style="margin-left: 120px; padding: 10px;">
							<h1>Open Account</h1>
							<label for="fname">Your First Name</label> <input type="text"
								id="fname" name="fname" placeholder="Your First Name">
							<label for="lname">Your Last Name</label> <input type="text"
								id="lname" name="lname" placeholder="Your Last Name"> <label
								for="mail">Your Email ID</label> <input type="email" id="mail"
								name="mail" placeholder="Your email"> <label
								for="uname">Create User ID</label> <input type="text" id="uname"
								name="uname" placeholder="User ID">
							<div id="chkuid"></div>
							<label></label> <label for="dob">Date of Birth</label> <input
								type="date" placeholder="Date Of Birth" id="dob" name="dob">
							<label for="passi">Create your Password</label> <input
								type="password" id="passi" name="passi"
								placeholder="Create Password"> <span id="passv"
								class="error">A Valid Password should be of atleast 8
								character long and must have the combination of
								uppercase,lowercase,numbers and special characters</span> <label
								for="passc">Confirm your Password</label> <input type="password"
								id="passc" name="passc" placeholder="Confirm Password">
							<label></label> <input type="submit" class="button1" id="reg"
								name="reg" value="Register" style="outline: none;"> <label></label>
							<input type="checkbox" id="agree" name="agree" value="Agreed">
							<label for="agree" class="label-rem">Click here to agree
								to our <a href="#">Terms and Condition</a>.
							</label> <label></label><br>
							<button type="button" class="button-cancel"
								onclick="document.getElementById('id02').style.display='none'">Cancel</button>
							<button type="reset" class="button3"
								style="float: right; width: 100px;">Reset</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
$(document).ready(function() {
	$("#form3").submit(function(e) {
		e.preventDefault();
		var form = $(this);
	    var url = form.attr('action');
	    var status_send = $("#status1").val();
		$.ajax({
	        url :url,
	        type:'POST',
	         dataType:'html',
	         timeout:100000,
	        data :{
	               'status':status_send
	        },
	        beforeSend:function(){
	        	$('#stat1').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
	        },
	        afterSend: function(){
	        	
	        },
	        success  :function(data){
	        	$('#stat1').html(data);
	        } 
	    });
	});
})

</script>
	<div class="track" style="display: block;">
		<h1 style="text-align: center;">Check Your Account Status</h1>
		<form action="Acc_Status" method="POST" onsubmit="" id="form3">
			<input type="text" id="status1" placeholder="Enter your User Name"
				style="width: 80%; margin-left: 8%;"> <input type="submit"
				class="" style="width: 75%; margin-left: 10%; margin-top: 30px;"
				id="statsubmit">
		</form>
	</div>
	<div id="stat1"></div>
	<div class="footer">
		<h1 class="footer-h1">&copy Copyright 2019</h1>
		<h1 class="footer-h1">Great Indian Bank</h1>
	</div>
	<script type="text/javascript">
$("#passv").hide(); 
$("#passi").keyup(function() {
	var pass = $("#passi").val();
	var regex =new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	if(regex.test(pass)){
		$("#passi").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
		 $("#passv").hide(); 
	}else{
		$("#passi").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
		 $("#passv").show(); 
	}
});
$("#passi").blur(function() {
	var pass = $("#passi").val();
	var regex =new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	if(regex.test(pass)){
		$("#passi").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
		 $("#passv").hide(); 
	}else{
		$("#passi").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
		 $("#passv").show(); 
	}
});
$("#passc").keyup(function() {
	var pass = $("#passi").val();
	if($("#passc").val()==pass){
		$("#passc").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
	}else{
		$("#passc").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
	}
});
$("#uname").blur(function() {
	var uname = $("#uname").val();
	$.ajax({
		url:'checkAvailability',
		type : 'POST',
		dataType:'html',
		data:{
			'uname': uname
		},beforeSend: function() {
			
		},success: function(data) {
			$("#chkuid").html(data);
		}
	})
});
</script>
	<script>
document.onkeypress = function (event) {
	event = (event || window.event);
	if (event.keyCode == 123) {
	//alert(‘No F-12’);
	return false;
	}
	}
	document.onmousedown = function (event) {
	event = (event || window.event);
	if (event.keyCode == 123) {
	//alert(‘No F-keys’);
	return false;
	}
	}
	document.onkeydown = function (event) {
	event = (event || window.event);
	if (event.keyCode == 123) {
	//alert(‘No F-keys’);
	return false;
	}
	}
	document.addEventListener('contextmenu', event => event.preventDefault());
window.onclick = function(event) {
    if (event.target == document.getElementById('id01') || event.target == document.getElementById('id02')) {
    	document.getElementById('id01').style.display = "none";
    	document.getElementById('id02').style.display = "none";
    }
}
</script>
</body>
</html>