<%@page import="com.solution.ib.bean.EmpBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%! EmpBean bean = new EmpBean();
	String empid = new String();
	String password = new String();
	String name = new String();
	String present = new String();
	String permanent = new String();
	String semail = new String();
	String pemail = new String();
	String aadhar = new String();
	String pancard = new String();
	String mob1 = new String();
	String mob2 = new String();
%>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	 if(session.getAttribute("data")!=null){
		bean = (EmpBean) session.getAttribute("data");
			 if(bean.getStatus()==3){
				 empid = bean.getEmpID();
				 password = bean.getPassword();
				 name = bean.getEmpfName()+" "+bean.getEmplName();
				 present = bean.getPriAddr();
				 permanent = bean.getSecAddr();
				 semail = bean.getSemail();
				 pemail = bean.getPemail();
				 aadhar = bean.getAadhar();
				 pancard = bean.getPanCard();
				 mob1 = bean.getPriMob();
				 mob2 = bean.getSecMob();
			 }else{
				 response.sendRedirect("Employee_login.jsp");
			 } 
		 
	}else{
		response.sendRedirect("Employee_login.jsp");
	} 
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="script/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="script/popper.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<link rel="stylesheet" href="style/style.css">
<script src="script/RegByAdmin.js"></script>
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<style type="text/css">
#imgcontainer {
	width: 100%;
	display: block;
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
	transition: 0.5s;
}
</style>

<script type="text/javascript">
  function home() {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='block';
		document.getElementById('viewdata').style.display='none';
	}
	function mydata() {
		document.getElementById('mydata').style.display='block';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='none';
	}
	function pendingrequest() {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'CustomerPending',
			type: 'POST',
			dataType:'html',
			data:{
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				$("#data_list").html(data);
			}
		})
	}
	function recyclebin() {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'RecycleCust',
			type: 'POST',
			dataType:'html',
			data:{
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				$("#data_list").html(data);
			}
		})
	}
	function allcustomers() {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'ViewAllCustomers',
			type: 'POST',
			dataType:'html',
			data:{
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				$("#data_list").html(data);
			}
		})
	}
	function allDetails(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'CustomerCompleteDetails',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				$("#data_list").html(data);
			}
		})
	}
	function approve(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'Approve',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				pendingrequest();
			}
		})
	}
	function reject(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'RejectCust',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				pendingrequest();
			}
		})
	}
	function restore(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'RestoreCust',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				recyclebin();
			}
		})
	}
	function del(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'DeleteCust',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				recyclebin();
			}
		})
	}
	function tempdel(custid) {
		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('viewdata').style.display='block';
		$.ajax({
			url: 'TempDelCust',
			type: 'POST',
			dataType:'html',
			data:{
				'custid':custid
			},beforeSend: function() {
				
			},afterSend: function() {
				
			},success: function(data) {
				allcustomers();
			}
		})
	}
	function logout() {
		$.ajax({
			url : 'logout',
			type:'POST',
			dataType: 'html',
			success: function() {
				window.location="Employee_login.jsp?msg=success";
			}
		});
	}
	function changepassword(){
		var pass = $("#pass").val();
		var passc=$("#passc").val();
		var oldpass=$("#oldpass").val();
		var empid =$("#empid").val();
		
	$.ajax({
		url:'ChangeEmployeePassword',
		type:'POST',
		dataType:'html',
		timeout:10000,
		data:{
			'oldpass':oldpass,
			'pass':pass,
			'passc':passc,
			'empid':empid
		},beforeSend: function() {
			
		},afterSend: function() {
			
		},error: function(data) {
			
		},success: function(data) {
			
		}
	})
	}
	$(document).ready(function() {
  		var password = "<%= password%>"
		$("#oldpass").css("box-shadow","0px 0px 8px red");
		$("#pass").css("box-shadow","0px 0px 8px red");
		 $("#oldpass").keyup(function() {
			 if($("#oldpass").val()==password){
					$("#oldpass").css("box-shadow","0px 0px 8px green");
				}else{
					$("#oldpass").css("box-shadow","0px 0px 8px red");
				}
		});
		 $("#pass").keyup(function(){
			 if($("#pass").val()==''){
					$("#pass").css("box-shadow","0px 0px 8px red");
				}else{
					$("#pass").css("box-shadow","0px 0px 8px green");
				}
		 });
		 $("#passc").keyup(function(){
			 if($("#pass").val()==$("#passc").val()){
					$("#passc").css("box-shadow","0px 0px 8px green");
				}else {
					$("#passc").css("box-shadow","0px 0px 8px red");
				} 
		 });
	})
  </script>
<script>
$(document).ready(function(){
	 $("#home").addClass("nav-button-active");
	 $("#pending,#viewall,#recycle").addClass("nav-button"); 
  $("#home").click(function(){
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button-active");
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button");
	  $("#home").addClass("nav-button-active");
	  $("#pending,#viewall,#recycle").addClass("nav-button");
  });
  $("#pending").click(function(){
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button-active");
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button");
	  $("#pending").addClass("nav-button-active");
	  $("#home,#viewall,#recycle").addClass("nav-button");
	  });
  $("#viewall").click(function(){
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button-active");
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button");
	  $("#viewall").addClass("nav-button-active");
	  $("#home,#pending,#recycle").addClass("nav-button");
	  });
  $("#recycle").click(function(){
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button-active");
	  $("#pending,#viewall,#recycle,#home").removeClass("nav-button");
	  $("#recycle").addClass("nav-button-active");
	  $("#viewall,#home,#pending,#recycle").addClass("nav-button");
	  });
});
</script>
<script type="text/javascript">
  function isKeyPressed(event) {
	  if (event.ctrlKey) {
		  return false;
	  }
	}
  </script>
<title>GIB Employee Login</title>
</head>
<body onkeypress="return isKeyPressed(event)"
	onkeydown="return isKeyPressed(event)">

	<ul class="container1" style="outline: none; border: none;">
		<li class="li2"><img src="images\GIB.png" width="100px"
			height="100px" alt="GIB Logo"
			style="padding: 0px; margin-left: 20px;"></li>
		<li class="li1" style="float: right; outline: none;"><input
			type="image" src="images/logout.png" id="logout" class="logout"
			style="outline: none; height: 40px; width: 40px; margin-top: 34px; margin-right: 40px;"
			onclick="logout()" alt = "logout"></li>
		<li class="li1" style="outline: none"><input type="image"
			src="images/profile.png"
			style="height: 50px; outline: none; width: 50px; margin-top: 30px; margin-right: 30px;"
			onclick="mydata()" alt="my data"></li>

		<!-- <li class="li1"><form action="logout" method="post">
 		<button type="submit" class="login-btn" style="outline: none;"><span>Log Out</span></button>
 	</form> -->
	</ul>
	<ul
		style="background-color: #FFFFFF; list-style-type: none; margin: 0; padding: 0; overflow: hidden;">
		<li class="li1">
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
				<li class="nav-item"><button type="button" id="home"
						onclick="home()">Home</button></li>
				<li class="nav-item">
					<button type="button" id="pending" onclick="pendingrequest()">Pending
						Requests</button>
				</li>
				<li class="nav-item">
					<button type="button" id="viewall" onclick="allcustomers()">Customers</button>
				</li>
				<li class="nav-item">
					<button type="button" id="recycle" onclick="recyclebin()">Recycle
						Bin</button>
				</li>
			</ul>
		</div>
	</nav>
	<div id="imgcontainer">
		<div id="overlay" style="background-color: rgba(0, 120, 255, 0.4)">
			<div class="headertextcontainer"
				style="width: 40%; background-color: rgba(255, 255, 255, 0.9);">
				<h2 class="Headertext" style="color: #929292; text-shadow: 0px;">
					Welcome
					<%=name %><br>
				</h2>
				<span style="margin-left: 60px; font-weight: bold;">From Here
					You can Manage Your Office Account</span>
				<button class="header-btn"
					style="outline: none; background-color: #B600FF;"
					onclick="mydata()">
					<span>My Data</span>
				</button>

			</div>
			<div class="headertextcontainer"
				style="width: 30%; background-color: rgba(255, 255, 255, 0.9); float: right;">
				<h2 class="Headertext" style="color: #0FAAA8;">
					MS Office<br>
				</h2>
				<button class="header-btn"
					style="outline: none; background-color: #B600FF;" onclick="">
					<span>Explore More</span>
				</button>
			</div>
		</div>
	</div>
	<div id="viewdata" class="viewdata">
		<div id="data_list"></div>
	</div>

	<div class="mydata" id="mydata">
		<img alt="proeffect" id="proeffect" class="MyProfile"
			src="images/profile.png"
			style="cursor: pointer; height: 16%; width: 16%; margin-left: 43%; margin-top: 5%;">
		<div class="profiledetail">
			<%=empid %></div>
		<div class="profiledetail">
			<%=name %></div>
		<div class="profiledetail">
			<%=pemail %></div>
		<div class="profiledetail">
			<%=aadhar %></div>
		<div class="profiledetail">
			<%=mob1 %></div>
		<div class="profiledetail">Permanent Address</div>
		<div class="profiledetail">
			<%=permanent %></div>
		<div class="profiledetail">Present Address</div>
		<div class="profiledetail">
			<%=present %></div>
		<div id="passchange" class="profiledetail"
			onclick="document.getElementById('pass1').style.display='block'">Change
			Your Password</div>
		<div class="profiledetail" id="pass1" style="display: none;">
			<form action="" method="POST" onsubmit="changepassword()">
				<input type="text" id="empid" name="empid" value="<%=empid%>"
					hidden=""> <input type="password" id="oldpass"
					name="oldpass" placeholder="Enter old Password"
					style="outline: none;"> <input type="password" id="pass"
					name="pass" placeholder="Enter New Password" style="outline: none;">
				<input type="password" id="passc" name="passc"
					placeholder="Confirm Your Password" style="outline: none;">
				<input type="submit" class="button1"
					onclick="document.getElementById('pass1').style.display='none'"
					style="margin-bottom: 15px; margin-top: 15px; outline: none;">
			</form>
			<div id="changestatus"></div>
		</div>
	</div>
	<div class="footer">
		<h1 class="footer-h1">&copy Copyright 2019</h1>
		<h1 class="footer-h1">Great Indian Bank</h1>
	</div>
	<script type="text/javascript">
document.addEventListener('contextmenu', event => event.preventDefault());
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
</script>
</body>
</html>