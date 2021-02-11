<%@page import="com.solution.ib.bean.CustomerBean"%>
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

String fname = new String();
String lname = new String();
CustomerBean data = new CustomerBean();
if(session.getAttribute("data")!=null){
	data = (CustomerBean)session.getAttribute("data");
	if(data!=null){
		fname = data.getCustfName();
		lname = data.getCustlName();
	}else{
		response.sendRedirect("index.jsp?msg=Failed");
	}
}else{
	response.sendRedirect("index.jsp?msg=Failed");
}
%>
<%-- <% 
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	/*  if(session.getAttribute("empId")==null){
		response.sendRedirect("Employee_login.jsp");
	}  */
%> --%>
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
.error {
	font-family: arial, sans-serif;
	color: red;
	margin: 5px;
	font-style: italic;
	font-size: 10px;
	font-weight: 200;
}
</style>
<script type="text/javascript" src="script/customerReg.js">
  	
  </script>
<title>Register Your Details</title>
</head>
<body>
	<ul class="container1" style="outline: none; border: none;">
		<li class="li2"><img src="images\GIB.png" width="100px"
			height="100px" alt="GIB Logo"
			style="padding: 0px; margin-left: 20px;"></li>
	</ul>
	<ul
		style="background-color: #FFFFFF; list-style-type: none; margin: 0; padding: 0; overflow: hidden;">
		<li class="li1">
	</ul>
	<div class="Regformcontainer" id="">
		<h3>Customer Registration</h3>
		<form action="UserRegistration2" method="POST" onsubmit="">
			<table>
				<tr>

					<td style="width: 20%;"><label for="fname"
						style="padding-left: 60px; position: relative;">Your First
							Name:</label></td>
					<td style="width: 30%;"><input type="text"
						style="border: 1px solid #dddddd;" class=""
						placeholder="First Name" name="fname" id="fname"
						value="<%=fname%>"><br> <span class="error"
						id="fnmalpha">*Only Alphabets are allowed</span> <span
						class="error" id="valfnm">*First Name cannot be less than 3
							Characters</span> <span class="error" id="fnmblank">*this field
							is mandatory </span></td>
					<td style="width: 20%;"><label for="mname"
						style="padding-left: 60px;">Your middle Name:</label></td>
					<td style="width: 30%;"><input type="text"
						style="border: 1px solid #dddddd;" placeholder="Middle Name"
						name="mname" id="mname"></td>
				</tr>
				<tr>
					<td><label for="lname" style="padding-left: 60px;">Your
							Last Name:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Last Name" name="lname" id="lname"
						value="<%=lname %>"><br> <span class="error"
						id="lnmalpha">*Only Alphabets are allowed</span> <span
						class="error" id="vallnm">*Last Name cannot be less than 3
							Characters</span> <span class="error" id="lnmblank">*this field
							is mandatory </span></td>
					<td><label for="father" style="padding-left: 60px;">Your
							Father's Name:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Father's Name" name="father" id="father"> <span
						class="error" id="fatherval1">*Only Alphabets are allowed</span> <span
						class="error" id="fatherval2">*Name cannot be less than 3
							Characters</span> <span class="error" id="fatherval3">*this field
							is mandatory </span></td>
				</tr>
				<tr>

					<td><label for="mother" style="padding-left: 60px;">Your
							Mother's Name:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Mother's Name" name="mother" id="mother"> <span
						class="error" id="motherval1">*Only Alphabets are allowed</span> <span
						class="error" id="motherval2">*Name cannot be less than 3
							Characters</span> <span class="error" id="motherval3">*this field
							is mandatory </span></td>
					<td><label for="spouse" style="padding-left: 60px;">Your
							Spouse's Name:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Spouse's Name" name="spouse" id="spouse"></td>
				</tr>
				<tr>
					<td><label for="aadhar" style="padding-left: 60px;">Aadhar
							Number:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="aadhar's Number" name="aadhar" id="aadhar"> <span
						class="error" id="aval">*Please Provide Valid AADHAR Number</span>
					</td>
					<td><label for="pancard" style="padding-left: 60px;">Pan
							Card:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Pan Card Number" name="pancard" id="pancard">
						<span class="error" id="pval">*Please Provide Valid PAN
							Number</span></td>
				</tr>
				<tr>
					<td><label for="passport" style="padding-left: 60px;">Passport
							Number:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Passport Number" name="passport" id="passport">
						<span class="error" id="passportv">*Please Provide Valid
							PASSPORT Number</span></td>
					<td><label for="passport" style="padding-left: 60px;">Blood
							Group:</label></td>
					<td><input type="text" placeholder="Blood Group" id="bgroup"
						name="bgroup" style="border: 1px solid #dddddd;"></td>
				</tr>
				<tr>
					<td><label for="gender" style="padding-left: 60px;">Your
							Gender:</label></td>
					<td><select id="gender"
						style="border: 1px solid #dddddd; width: 100%; outline: none; color: #757575; font-weight: normal; font-size: 18px; padding: 12px 20px; border: none; border-bottom: 1px solid #CBCBCB; text-align-last: center; text-align: center; align-content: center; position: relative;"
						class="">
							<option value="0" id="" style="text-align: right;">
								Select Your Gender</option>
							<option value="Male" id="">Male</option>
							<option value="Female" id="">Female</option>
					</select> <span class="error" id="genderv">*please select your gender</span>
					</td>
					<td><label for="bstate" style="padding-left: 60px;">Branch
							State:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Branch State" id="bstate" name="bstate"
						list="branch_states"> <span id="br_state"></span> <span
						class="error" id="bstatev">*please provide Branch State</span></td>
				</tr>
				<tr>
					<td><label for="mail" style="padding-left: 60px;">Primary
							Email:</label></td>
					<td><input type="email" style="border: 1px solid #dddddd;"
						placeholder="Primary email" id="mail" name="mail"> <span
						class="error" id="mailv">*please provide valid email ID</span></td>
					<td><label for="bname" style="padding-left: 60px;">Opening
							Branch:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Branch Name" id="bname" name="bname" value=""
						list="branch_names"> <span id="branch_list"></span> <span
						class="error" id="brv">*please provide Branch Name</span></td>
				</tr>
				<tr>
					<td><label for="mob" style="padding-left: 60px;">Primary
							Mobile Number:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Primary Mobile Number" id="mob" name="mob"> <span
						class="error" id="mobv">*please provide valid Mobile Number</span>
					</td>
					<td><label for="mob2" style="padding-left: 60px;">Mobile
							Number:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Secondary Mobile Number" id="mob2" name="mob2">
						<span class="error" id="mob2v">*please provide valid Mobile
							Number</span></td>
				</tr>
				<tr>
					<th>|</th>
					<th>Permanent Address</th>
					<th>|</th>
					<th>Present Address</th>
				</tr>
				<tr>
					<td><label for="al1" style="padding-left: 60px;">Address
							Line 1:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="House Name/House Number" id="al1" name="al1">
						<span class="error" id="al1v">*This field can't be empty</span></td>
					<td><label for="al2" style="padding-left: 60px;">Address
							Line 1:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="House Name/House Number" id="al2" name="al2">
						<span class="error" id="al2v">*This field can't be empty</span></td>
				</tr>
				<tr>
					<td><label for="al3" style="padding-left: 60px;">Address
							Line 2:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Street Number/Street Name" id="al3" name="al3">
						<span class="error" id="al3v">*This field can't be empty</span></td>
					<td><label for="al4" style="padding-left: 60px;">Address
							Line 2:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Street Number/Street Name" id="al4" name="al4">
						<span class="error" id="al4v">*This field can't be empty</span></td>
				</tr>
				<tr>
					<td><label for="country1" style="padding-left: 60px;">Country:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Country" id="country1" name="country1"
						list="country_names"> <span id="country_list1"></span> <span
						class="error" id="country1v">*This field can't be empty</span></td>
					<td><label for="country2" style="padding-left: 60px;">Country:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Country" id="country2" name="country2"
						list="country_names2">
						<div id="country_list2"></div> <span class="error" id="country2v">*This
							field can't be empty</span></td>
				</tr>
				<tr>
					<td><label for="state1" style="padding-left: 60px;">State:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="State" id="state1" name="state1" list="statenames1">
						<span id="state_list1"></span> <span class="error" id="state1v">*This
							field can't be empty</span></td>
					<td><label for="state2" style="padding-left: 60px;">State:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="State" id="state2" name="state2" list="statenames2">
						<span id="state_list2"></span> <span class="error" id="state2v">*This
							field can't be empty</span></td>
				</tr>
				<tr>
					<td><label for="city1" style="padding-left: 60px;">City:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="City" id="city1" name="city1" list="city_names">
						<span id="city_list"></span> <span class="error" id="city1v">*This
							field can't be empty</span></td>
					<td><label for="city2" style="padding-left: 60px;">City:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="City" id="city2" name="city2" list="city_names2">
						<div id="city_list2"></div> <span class="error" id="city2v">*This
							field can't be empty</span></td>
				</tr>
				<tr>
					<td><label for="pin1" style="padding-left: 60px;">Pin
							Code:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Pin Code" id="pin1" name="pin1"> <span
						class="error" id="pin1v">*This field can't be empty</span></td>
					<td><label for="pin2" style="padding-left: 60px;">Pin
							Code:</label></td>
					<td><input type="text" style="border: 1px solid #dddddd;"
						placeholder="Pin Code" id="pin2" name="pin2"> <span
						class="error" id="pin2v">*This field can't be empty</span></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><input type="checkbox" id="agree" name="agree" value="1"
						style="margin-bottom: 0px; margin-top: 5px; margin-left: 30px;"><span
						style="margin-left: 10px; padding-top: 70px; position: relative; margin-top: 20px;">
							Do you agree to our<a href="#"> terms and conditions</a>
					</span></td>
				</tr>
			</table>
			<input type="submit" value="Submit" class="button1"
				style="width: 60%; margin-left: 17%; margin-top: 20px; margin-right: 10px; outline: none;"
				onclick="">
		</form>
	</div>
	<script type="text/javascript">
  	$(document).ready(function () {
  		var check=0;
		$("#fnmalpha").hide();
		$("#valfnm").hide();
		$("#fnmblank").hide();
		$("#lnmalpha").hide();
		$("#vallnm").hide();
		$("#lnmblank").hide();
		$("#fatherval1").hide();
		$("#fatherval2").hide();
		$("#fatherval3").hide();
		$("#motherval1").hide();
		$("#motherval2").hide();
		$("#motherval3").hide();
		$("#aval").hide();
		$("#pval").hide();
		$("#passportv").hide();
		$("#genderv").hide();
		$("#mailv").hide();
		$("#smailv").hide();
		$("#mobv").hide();
		$("#mob2v").hide();
		$("#passv").hide(); 
		$("#cpassv").hide();
		$("#al1v").hide();
		$("#al2v").hide();
		$("#al3v").hide();
		$("#al4v").hide();
		$("#country1v").hide();
		$("#country2v").hide();
		$("#state1v").hide();
		$("#state2v").hide();
		$("#city1v").hide();
		$("#city2v").hide();
		$("#pin1v").hide();
		$("#pin2v").hide();
		$("#bstatev").hide();
		$("#brv").hide();
		 $("#fname").keyup(function () {
			var name= $("#fname").val();
			var len = $("#fname").val().length;
			if(name.length<3){
				$("#valfnm").show();
			}else{
				$("#valfnm").hide();
			}
			if((name.charAt(len-1) >= "A" && name.charAt(len-1) <= "Z") || (name.charAt(len-1) >="a" && name.charAt(len-1) <="z")){
				$("#fnmalpha").hide();
			}else{
				$("#fnmalpha").show();
			}
		}); 
		 $("#fname").blur(function() {
			 if($("#fname").val()==''){
				$("#fnmblank").show();
				$("#fname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#fnmblank").hide();
				$("#fname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#lname").keyup(function () {
			var name= $("#lname").val();
			var len = $("#lname").val().length;
			if(name.length<3){
				$("#vallnm").show();
			}else{
				$("#vallnm").hide();
			}
			if((name.charAt(len-1) >= "A" && name.charAt(len-1) <= "Z") || (name.charAt(len-1) >="a" && name.charAt(len-1) <="z")){
				$("#lnmalpha").hide();
			}else{
				$("#lnmalpha").show();
			}
		});
		$("#lname").blur(function() {
			 if($("#lname").val()==''){
				$("#lnmblank").show();
				$("#lname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#lnmblank").hide();
				$("#lname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#father").keyup(function () {
			var name= $("#father").val();
			var len = $("#father").val().length;
			if(name.length<3){
				$("#fatherval2").show();
			}else{
				$("#fatherval2").hide();
			}
			if((name.charAt(len-1) >= "A" && name.charAt(len-1) <= "Z") || (name.charAt(len-1) >="a" && name.charAt(len-1) <="z") || name.charAt(len-1)==" "){
				$("#fatherval1").hide();
			}else{
				$("#fatherval1").show();
			}
		});
		$("#father").blur(function() {
			 if($("#father").val()==''){
				$("#fatherval3").show();
				$("#father").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#fatherval3").hide();
				$("#father").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#mother").keyup(function () {
			var name= $("#mother").val();
			var len = $("#mother").val().length;
			if(name.length<3){
				$("#motherval2").show();
			}else{
				$("#motherval2").hide();
			}
			if((name.charAt(len-1) >= "A" && name.charAt(len-1) <= "Z") || (name.charAt(len-1) >="a" && name.charAt(len-1) <="z") || name.charAt(len-1)==" "){
				$("#motherval1").hide();
			}else{
				$("#motherval1").show();
			}
		});
		$("#mother").blur(function() {
			 if($("#mother").val()==''){
				$("#motherval3").show();
				$("#mother").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#motherval3").hide();
				$("#mother").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#aadhar").keyup(function () {
			var name= $("#aadhar").val();
			var len = $("#aadhar").val().length;
			if(name.charAt(len-1) >= "0" && name.charAt(len-1) <= "9"){
				$("#aval").hide();
			}else{
				$("#aval").show();
			}
		});
		$("#aadhar").blur(function() {
			 if($("#aadhar").val().length!=12){
				$("#aval").show();
				$("#aadhar").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#aval").hide();
				$("#aadhar").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#pancard").blur(function() {
			 if($("#pancard").val().length!=10){
				$("#pval").show();
				$("#pancard").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#pval").hide();
				$("#pancard").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#passport").blur(function() {
			 if($("#passport").val().length==0){
				$("#passportv").show();
				$("#passport").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#passportv").hide();
				$("#passport").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#gender").blur(function() {
			 if($("#gender").val()==0){
				$("#genderv").show();
				$("#gender").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#genderv").hide();
				$("#gender").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#mail").keyup(function() {
			var email = $("#mail").val();
			var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if(regex.test(email)){
				$("#mail").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#mailv").hide();
			}else{
				$("#mail").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#mailv").show();
			}
		});
		$("#bstate").blur(function() {
			var bstate = $("#bstate").val().length;
			if(bstate==0){
				$("#bstate").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#bstatev").show();
			}else{
				$("#bstate").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#bstatev").hide();
			}
		});
		$("#bname").blur(function() {
			var bname = $("#bname").val().length;
			if(bname==0){
				$("#bname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#brv").show();
			}else{
				$("#bname").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#brv").hide();
			}
		});
		$("#mob").keyup(function() {
			if($("#mob").val().length!=10){
				$("#mob").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#mobv").show();
			}else{
				$("#mob").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#mobv").hide();
			}
		});
		$("#mob").blur(function() {
			if($("#mob").val().length!=10){
				$("#mob").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#mobv").show();
			}else{
				$("#mob").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#mobv").hide();
			}
		});
		$("#mob2").keyup(function() {
			if($("#mob2").val().length==10 || $("#mob2").val().length==0){
				$("#mob2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				$("#mob2v").hide();
			}else{
				$("#mob2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				$("#mob2v").show();
			}
		});
		$("#pass").keyup(function() {
			var pass = $("#pass").val();
			var regex =new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
			if(regex.test(pass)){
				$("#pass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				 $("#passv").hide(); 
			}else{
				$("#pass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				 $("#passv").show(); 
			}
		});
		$("#pass").blur(function() {
			var pass = $("#pass").val();
			var regex =new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
			if(regex.test(pass)){
				$("#pass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				 $("#passv").hide(); 
			}else{
				$("#pass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				 $("#passv").show(); 
			}
		});
		$("#cpass").keyup(function() {
			var pass = $("#pass").val();
			if($("#cpass").val()==pass){
				$("#cpass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
				 $("#cpassv").hide(); 
			}else{
				$("#cpass").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
				 $("#cpassv").show(); 
			}
		});
		$("#al1").blur(function() {
			 if($("#al1").val()==''){
				$("#al1v").show();
				$("#al1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#al1v").hide();
				$("#al1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#al2").blur(function() {
			 if($("#al2").val()==''){
				$("#al2v").show();
				$("#al2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#al2v").hide();
				$("#al2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#al4").blur(function() {
			 if($("#al4").val()==''){
				$("#al4v").show();
				$("#al4").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#al4v").hide();
				$("#al4").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#al3").blur(function() {
			 if($("#al3").val()==''){
				$("#al3v").show();
				$("#al3").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#al3v").hide();
				$("#al3").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#country1").blur(function() {
			 if($("#country1").val()==''){
				$("#country1v").show();
				$("#country1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#country1v").hide();
				$("#country1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#country2").blur(function() {
			 if($("#country2").val()==''){
				$("#country2v").show();
				$("#country2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#country2v").hide();
				$("#country2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#state1").blur(function() {
			 if($("#state1").val()==''){
				$("#state1v").show();
				$("#state1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#state1v").hide();
				$("#state1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#state2").blur(function() {
			 if($("#state2").val()==''){
				$("#state2v").show();
				$("#state2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#state2v").hide();
				$("#state2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#city1").blur(function() {
			 if($("#city1").val()==''){
				$("#city1v").show();
				$("#city1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#city1v").hide();
				$("#city1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#city2").blur(function() {
			 if($("#city2").val()==''){
				$("#city2v").show();
				$("#city2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#city2v").hide();
				$("#city2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#pin1").blur(function() {
			 if($("#pin1").val()==''){
				$("#pin1v").show();
				$("#pin1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#pin1v").hide();
				$("#pin1").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#pin2").blur(function() {
			 if($("#pin2").val()==''){
				$("#pin2v").show();
				$("#pin2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px red"});
			}else{
				$("#pin2v").hide();
				$("#pin2").css({"outline":"0px solid red","box-shadow":"0px 0px 8px green"});
			} 
		}) ;
		$("#bstate").keyup(function() {
			var bstate = $("#bstate").val();
			$.ajax({
				url: 'FetchBranchState',
				type: 'POST',
				dataType: 'html',
				data: {
					'bstate': bstate
				},beforeSend: function() {
					
				},success: function(data) {
					$("#br_state").html(data);
				}
			})
		});
		$("#bname").keyup(function() {
			var bname = $("#bname").val();
			var bstate = $("#bstate").val();
			$.ajax({
				url: 'FetchBranch',
				type: 'POST',
				dataType: 'html',
				data: {
					'bname': bname,
					'bstate':bstate
				},beforeSend: function() {
					
				},success: function(data) {
					$("#branch_list").html(data);
				}
			})
		});
		$("#city1").keyup(function() {
			var city1 = $("#city1").val();
			var state1 = $("#state1").val();
			$.ajax({
				url:'FetchCity',
				type: 'POST',
				dataType: 'html',
				data:{'city1':city1,
					  'state1': state1
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#city_list").html(data);
				}
			})
		});
		$("#city2").keyup(function() {
			var city2 = $("#city2").val();
			var state2 = $("#state2").val();
			$.ajax({
				url:'FetchCity2',
				type: 'POST',
				dataType: 'html',
				data:{'city2':city2,
					  'state2':state2	
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#city_list2").html(data);
				}
			})
		});
		$("#country1").keyup(function() {
			var country1 = $("#country1").val();
			$.ajax({
				url:'FetchCountry1',
				type: 'POST',
				dataType: 'html',
				data:{'country1':country1
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#country_list1").html(data);
				}
			})
		});
		$("#country2").keyup(function() {
			var country2 = $("#country2").val();
			$.ajax({
				url:'FetchCountry2',
				type: 'POST',
				dataType: 'html',
				data:{'country2':country2
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#country_list2").html(data);
				}
			})
		});
		$("#state1").keyup(function() {
			var state1 = $("#state1").val();
			var country1 = $("#country1").val();
			$.ajax({
				url:'FetchState1',
				type: 'POST',
				dataType: 'html',
				data:{'state1':state1,
					  'country1':country1
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#state_list1").html(data);
				}
			})
		});
		$("#state2").keyup(function() {
			var state2 = $("#state2").val();
			var country2 = $("#country2").val();
			$.ajax({
				url:'FetchState2',
				type: 'POST',
				dataType: 'html',
				data:{'state2':state2,
					  'country2':country2
				},
				beforeSend: function() {
					
				},
				success: function(data) {
					$("#state_list2").html(data);
				}
			})
		});
	})
  </script>
</body>
</html>