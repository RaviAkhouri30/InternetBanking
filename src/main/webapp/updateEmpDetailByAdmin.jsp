<%@page import="com.solution.ib.bean.EmpBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function edit() {
	$.ajax({
		url:'UpdateEmpByAdmin',
		type:'POST',
		dataType:'html',
		timeout:10000,
		data:{
			'empid': $('#empid').val(),
			'fname': $('#fname').val(),
			'lname': $('#lname').val(),
			'doj': $('#doj').val(),
			'dob': $('#dob').val(),
			'branch': $('#bname').val(),
			'email': $('#mail').val()
		},beforeSend: function() {
			$('#regform').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
		},afterSend: function() {
			
		},success: function(data) {
			Swal.fire({
				  icon: 'success',
				  title: 'Great',
				  text: 'Your Work is Saved!!!',
				  footer: '<a href>Why do I have this issue?</a>'
				})
			
		},error: function(data) {
			Swal.fire({
				  icon: 'error',
				  title: 'OOPS',
				  text: 'Sorry There is Some Problem!!!',
				  footer: '<a href>Why do I have this issue?</a>'
				})
				
		},complete: function(data) {
			setTimeout(function(){ window.close(); }, 1500);
		}
	});
}
</script>
<% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	String empid = new String();
	String fname = new String();
	String lname = new String();
	String doj = new String();
	String dob = new String();
	String branch = new String();
	String email = new String();
	 if(session.getAttribute("data")==null){
		response.sendRedirect("Employee_login.jsp");
	}else{
		 EmpBean emp = (EmpBean)session.getAttribute("data");
		 if(emp==null){
			 response.sendRedirect("Employee_login.jsp");
		 }else{
			 if(emp.getEmpID().equals("admin")){
				 empid = request.getParameter("empid");
				 fname = request.getParameter("fname");
				 lname = request.getParameter("lname");
				 doj = request.getParameter("doj");
				 dob = request.getParameter("dob");
				 branch = request.getParameter("branch");
				 email = request.getParameter("email");
			 }else{
				 response.sendRedirect("Employee_login.jsp");
			 }
		 }
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
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<title>Insert title here</title>
</head>
<body>
	<div class="formcontainer" id="regform" style="display: block;">
		<h3>Update Employee Details</h3>
		<form action="" method="POST" onsubmit="edit()">
			<input type="text" value="<%=empid%>" hidden="" id="empid"> <label
				for="fname"
				style="position: absolute; font-weight: normal; left: 1%; cursor: pointer;">Enter
				First Name</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="First Name" name="fname" id="fname" value="<%=fname%>">
			<label for="doj"
				style="position: absolute; left: 32%; font-weight: normal; cursor: pointer;">Enter
				Date of Joining</label> <input type="date" name="doj" id="doj"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;"
				value="<%=doj%>"> <label for="lname"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Enter
				Last Name</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Last Name" name="lname" id="lname" value="<%=lname%>">
			<label for="bname"
				style="position: absolute; font-weight: normal; left: 1%; top: 180px; cursor: pointer;">Enter
				Branch Name</label> <input type="text" placeholder="Branch Name" id="bname"
				name="bname"
				style="width: 30%; margin-top: 50px; margin-bottom: 20px; margin-right: 10px;"
				value="<%=branch%>"> <label for="mail"
				style="position: absolute; font-weight: normal; left: 32%; top: 180px; cursor: pointer;">Enter
				Email</label> <input type="email" placeholder="email" id="mail" name="mail"
				style="width: 30%; margin-top: 17px; margin-bottom: 20px; margin-left: 5px; margin-right: 10px;"
				value="<%=email%>"> <label for="dob"
				style="position: absolute; font-weight: normal; left: 62%; top: 180px; cursor: pointer;">Enter
				DOB</label> <input type="date" name="dob" id="dob"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;"
				value="<%=dob%>"> <input type="submit" value="Submit"
				class="button1"
				style="width: 60%; margin-left: 17%; margin-top: 20px; margin-right: 10px; outline: none;"
				onclick="">
		</form>
	</div>
	<script type="text/javascript">
document.addEventListener('contextmenu', event => event.preventDefault());
</script>
</body>
</html>