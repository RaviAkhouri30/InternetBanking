<%@page import="com.solution.ib.bean.EmpBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	String empid = new String();
	String password = new String();
	 if(session.getAttribute("data")==null){
		response.sendRedirect("Employee_login.jsp");
	}else{
		 EmpBean emp = (EmpBean)session.getAttribute("data");
		 if(emp==null){
			 response.sendRedirect("Employee_login.jsp");
		 }else{
			 if(emp.getEmpID().equals("admin")){
				 password= emp.getPassword();
				 empid = emp.getEmpID();
				 empid= empid.toUpperCase();
			 }else{
				 response.sendRedirect("Employee_login.jsp");
			 }
		 }
	} 
%>
<script type="text/javascript">
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
	function viewcompletedetails(empid) {
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='block'; 
		$.ajax({
			url:'ViewEmployeeCompleteDetails',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid':empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#empdata').html(data);
			}
		});
	}
		function edit(empid,fname,lname,doj,dob,branch,email) {
			myWindow = window.open("updateEmpDetailByAdmin.jsp?empid="+empid+"&fname="+fname+"&lname="+lname+"&doj="+doj+"&dob="+dob+"&branch="+branch+"&email="+email, "Update Data", "width=1200,height=1000");
	}	
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="script/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="script/popper.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css">
<link rel="stylesheet" href="style/style.css">
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<script src="script/RegByAdmin.js"></script>
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
  function isKeyPressed(event) {
	  if (event.ctrlKey || event.shiftKey) {
		  return false;
	  }
	}
  </script>
<script type="text/javascript">
    function newBranch() {
    	var prev;
    	$.ajax({
			url:'NewBranch',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'branch': $('#branchname').val(),
				'city': $('#city').val(),
				'state': $('#state').val(),
				'country': $('#country').val()
			},beforeSend: function() {
				prev = $('#pagecontent1').html();
				$('#pagecontent1').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#pagecontent1').html(prev);
				viewBranch();
			},complete: function(data) {
				$('#pagecontent1').html(prev);
				viewBranch()
			}
		});
	}
    function branchdel(IFSC) {
    	$.ajax({
			url:'BranchDelete',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'IFSC':IFSC
			},beforeSend: function() {
				$('#viewbranch').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				viewBranch()
			},complete: function() {
				viewBranch()
			}
		});
	}
	function viewBranch() {
		$.ajax({
			url:'ViewBranch',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				
			},beforeSend: function() {
				$('#viewbranch').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#viewbranch').html(data);
			},complete: function() {
				$('#viewbranch').html(data);
			}
		});
	}
    $( document ).ready(function() {
    	viewBranch();	
    });
    
  	function tempdelete(empid) {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none'
		$.ajax({
			url:'EmpDataTemporaryDelete',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid': empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				view();
			},complete: function() {
				view();
			}
		});
	}
  	function Permanentdelete(empid) {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'EmpDataPermanentDelete',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid': empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				viewRecycleBin();
			},complete: function() {
				viewRecycleBin();
			}
		});
	}
  	function view() {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'EmpView',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#empdata').html(data);
			}
		});
	}
  	function viewRecycleBin() {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'EmpRecycleBin',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#empdata').html(data);
			}
		});
	}
  	function pending() {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'PendingRequest',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				$('#empdata').html(data);
			}
		});
	}
  	function approve(empid) {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'approveemp',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid': empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				pending();
			}
		});
	}
  	function empRestore(empid) {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'empRestore',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid': empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				viewRecycleBin();
			}
		});
	}
  	function reject(empid) {
  		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		$.ajax({
			url:'empreject',
			type:'POST',
			dataType:'html',
			timeout:10000,
			data:{
				'empid': empid
			},beforeSend: function() {
				$('#empdata').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},afterSend: function() {
				
			},success: function(data) {
				pending();
			}
		});
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
  	function regform() {
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='block';
		document.getElementById('view1').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
	}
  	function home() {
  		document.getElementById('mydata').style.display='none';
		document.getElementById('regform').style.display='none';
		document.getElementById('imgcontainer').style.display='block';
		document.getElementById('view1').style.display='none';
		document.getElementById('pagecontent1').style.display='block';
	}
  	function mydata() {
  		document.getElementById('mydata').style.display='block';
  		document.getElementById('regform').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('view1').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
	}
  </script>
<script>
$(document).ready(function(){
	document.getElementById('pass1').style.display='none';
	$("#home").addClass("nav-button-active");
	$("#addemp,#upemp,#view,#recyclebin").addClass("nav-button"); 
  $("#home").click(function(){
	$("#addemp,#upemp,#view,#recyclebin").removeClass("nav-button");
	$("#addemp,#upemp,#view,#recyclebin").removeClass("nav-button-active");
    $("#addemp,#upemp,#view,#recyclebin").addClass("nav-button");
    $("#home").removeClass("nav-button-active");
    $("#home").addClass("nav-button-active");
  });
  $("#addemp").click(function(){
		$("#home,#upemp,#view,#recyclebin").removeClass("nav-button-active");
		$("#home,#upemp,#view,#recyclebin").removeClass("nav-button");
	    $("#home,#upemp,#view,#recyclebin").addClass("nav-button");
	    $("#addemp").removeClass("nav-button");
	    $("#addemp").addClass("nav-button-active");
	  });
  $("#upemp").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button");
	    $("#home,#addemp,#view,#recyclebin").addClass("nav-button");
	    $("#upemp").addClass("nav-button-active");
	  });
  $("#view").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button");
	    $("#home,#addemp,#upemp,#recyclebin").addClass("nav-button");
	    $("#view").addClass("nav-button-active");
	  });
  $("#recyclebin").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin").removeClass("nav-button");
	    $("#home,#addemp,#upemp,#view").addClass("nav-button");
	    $("#recyclebin").addClass("nav-button-active");
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
			onclick="logout()" alt="logout"></li>
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
					<button type="button" id="addemp" onclick="regform()">Add
						Employee</button>
				</li>
				<li class="nav-item">
					<button type="button" id="upemp" onclick="pending()">Pending
						Approval</button>
				</li>
				<li class="nav-item">
					<button type="button" id="view" onclick="view()">View
						Employee Details</button>
				</li>
				<li class="nav-item">
					<button type="button" id="recyclebin" onclick="viewRecycleBin()">Recycle
						Bin</button>
				</li>
			</ul>
		</div>
	</nav>
	<div id="imgcontainer">
		<div id="overlay" style="background-color: rgba(0, 120, 255, 0.4)">
			<div class="headertextcontainer"
				style="width: 30%; background-color: rgba(255, 255, 255, 0.9);">
				<h2 class="Headertext" style="color: #929292; text-shadow: 0px;">
					Welcome
					<%=empid %><br>
				</h2>

				<button class="header-btn"
					style="outline: none; background-color: #B600FF;"
					onclick="mydata()">
					<span>My Data</span>
				</button>

			</div>
			<div class="headertextcontainer"
				style="width: 30%; background-color: rgba(255, 255, 255, 0.9);">
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
	<div class="pagecontent" id="pagecontent1">
		<h3
			style="margin: 0px; width: 100%; text-align: center; padding: 0px;">Add
			Branch Details</h3>
		<form action="" method="POST" onsubmit="newBranch()">
			<label for="branchname"
				style="position: absolute; font-weight: normal; left: 1%; cursor: pointer;">Enter
				Name of Branch</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Branch Name" name="branchname" id="branchname">
			<label for="city"
				style="position: absolute; left: 32%; font-weight: normal; cursor: pointer;">Enter
				the Name of City</label> <input type="text" name="city" id="city"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;">
			<label for="state"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Enter
				the Name of State</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Name of State" name="state" id="state"><br>
			<label for="country"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Enter
				the Name of Country</label> <input type="text"
				style="width: 90%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Name of Country" name="country" id="country"> <input
				type="submit" value="Submit" class="button1"
				style="width: 60%; margin-left: 17%; margin-top: 20px; margin-right: 10px; outline: none;"
				onclick="">
		</form>
		<div id="viewbranch"
			style="margin-top: 10px; padding-top: 10px; width: 90%; margin-right: 50px; position: relative; display: inline-block;"></div>
	</div>
	<div class="formcontainer" id="regform">
		<h3>Add Employee Details</h3>
		<form action="" method="POST" onsubmit="save_data()">
			<label for="fname"
				style="position: absolute; font-weight: normal; left: 1%; cursor: pointer;">Enter
				First Name</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="First Name" name="fname" id="fname"> <label
				for="doj"
				style="position: absolute; left: 32%; font-weight: normal; cursor: pointer;">Enter
				Date of Joining</label> <input type="date" name="doj1" id="doj1"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;">
			<label for="lname"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Enter
				Last Name</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Last Name" name="lname" id="lname"> <label
				for="bname"
				style="position: absolute; font-weight: normal; left: 1%; top: 180px; cursor: pointer;">Enter
				Branch Name</label> <input type="text" placeholder="Branch Name" id="bname"
				name="bname"
				style="width: 30%; margin-top: 50px; margin-bottom: 20px; margin-right: 10px;"
				list="branch_names"> <span id="branch_list"></span> <label
				for="mail"
				style="position: absolute; font-weight: normal; left: 32%; top: 180px; cursor: pointer;">Enter
				Email</label> <input type="email" placeholder="email" id="mail" name="mail"
				style="width: 30%; margin-top: 17px; margin-bottom: 20px; margin-left: 5px; margin-right: 10px;">
			<label for="dob"
				style="position: absolute; font-weight: normal; left: 62%; top: 180px; cursor: pointer;">Enter
				DOB</label> <input type="date" name="dob" id="dob"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;">
			<input type="submit" value="Submit" class="button1"
				style="width: 60%; margin-left: 17%; margin-top: 20px; margin-right: 10px; outline: none;"
				onclick="">
		</form>
	</div>
	<script type="text/javascript">
 $(document).ready(function() {
	 $("#bname").keyup(function() {
			var bname = $("#bname").val();
			$.ajax({
				url: 'FetchBranchOnly',
				type: 'POST',
				dataType: 'html',
				data: {
					'bname': bname
				},beforeSend: function() {
					
				},success: function(data) {
					$("#branch_list").html(data);
				}
			})
		});
})
</script>
	<div class="mydata" id="mydata">
		<img alt="proeffect" id="proeffect" class="MyProfile"
			src="images/profile.png"
			style="cursor: pointer; height: 16%; width: 16%; margin-left: 43%; margin-top: 5%;">
		<div class="profiledetail">
			<%=empid %></div>
		<div id="passchange" class="profiledetail"
			onclick="document.getElementById('pass1').style.display='block'">Change
			Your Password</div>
		<div class="profiledetail" id="pass1">
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
	<div id="view1" class="view">
		<span id="empdata"></span>
	</div>
	<div class="" id="Success"></div>
	<script type="text/javascript">
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
</script>
	<div class="footer">
		<h1 class="footer-h1">&copy Copyright 2019</h1>
		<h1 class="footer-h1">Great Indian Bank</h1>
	</div>
</body>
</html>