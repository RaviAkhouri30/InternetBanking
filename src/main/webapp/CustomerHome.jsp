<%@page import="java.util.Date"%>
<%@page import="com.solution.ib.bean.CardBean"%>
<%@page import="com.solution.ib.bean.AccDetails"%>
<%@page import="com.solution.ib.bean.CustomerBean"%>
<%@page import="com.solution.ib.bean.EmpBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%!String name = new String();
   String password = new String();
   String cardno = new String();
   String acc = new String();
   String bal = new String();
   String cvv = new String();
   String exp = new String();
   String pemail = new String();
   String semail = new String();
   String maddr = new String();
   String peraddr = new String();
   String Father = new String();
   String mother = new String();
   String branch = new String();
   String aadhaar = new String();
   String panCard = new String();
   String ifsc = new String();
   String id = new String();
   Date openingdate = new Date();
   String mob = new String();
   Date dob = new Date();
   String branchval = new String();
   String msg = new String();
%>
<% 
	msg = request.getParameter("msg");
	CustomerBean bean = new CustomerBean();
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	if(session.getAttribute("data")!=null && session.getAttribute("acc_det")!=null && session.getAttribute("card")!=null){
		 bean = (CustomerBean) session.getAttribute("data");
		 AccDetails det = new AccDetails();
		 det = (AccDetails)session.getAttribute("acc_det");
		 CardBean card = new CardBean();
		 card = (CardBean) session.getAttribute("card");
		 if(bean!=null && bean.getStatus()==3){
			 name = bean.getCustfName()+" "+bean.getCustlName();
			 acc= det.getAcc_No();
			 bal= det.getBalance();
			 cardno = card.getCardNo();
			 exp = card.getExp();
			 cvv = card.getCvv();
			 id = bean.getCustID();
			 pemail = bean.getPemail();
			 semail = bean.getSemail();
			 mob = bean.getPriMob();
			 Father = bean.getFather();
			 mother = bean.getMother();
			 maddr = bean.getSecAddr();
			 peraddr = bean.getPriAddr();
			 openingdate= bean.getDOJ();
			 dob = bean.getDOB();
			 password= bean.getPassword();
		 }
	}else{
		response.sendRedirect("index.jsp");
	}
%>
<script type="text/javascript">
	function logout() {
		$.ajax({
			url : 'logout',
			type:'POST',
			dataType: 'html',
			success: function() {
				window.location="index.jsp?msg=success";
			}
		});
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
<script src="script/RegByAdmin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="icon" type="image/ico" href="images/GIBLogo.png" />
<style type="text/css">
#imgcontainer {
	width: 100%;
	display: block;
	min-height: 370px;
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
  function addBenifeiceiry() {
	  var dat;
	  $.ajax({
          url :'AddBenifeiceiry',
          type:'POST',
           dataType:'html',
           timeout:10000,
          data :{
                'accno': $("#accno").val(),
                'name' : $("#toaccname").val(),
                'bank' : $("#bankname").val(),
                'ifsc': $("#ifsc").val(),
                'acc_type':$("#acc_type").val(),
                'branch':$("#branch").val()
          },
          
          beforeSend:function(){
          	prev=$('#pagecontent1').html();
          	$('#pagecontent1').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
          },
          afterSend: function(){
          	
          },
          success  :function(data){
          	Swal.fire({
          		  position: 'top-end',
          		  icon: 'success',
          		  title: 'Your work has been saved',
          		  showConfirmButton: false,
          		  timer: 2000
          		});
          	dat = data;
          },
          error  :function(data){
          	Swal.fire({
        		  position: 'top-end',
        		  icon: 'error',
        		  title: 'Please Provide Valid Data and Try Again',
        		  showConfirmButton: false,
        		  timer: 2000
        		});
          	$("#addstatus").html('<div>error</div>');
          },
          complete :function(){
          	$('#pagecontent1').html(prev);
          	$("#addstatus").html(dat);
          }
      });
}
  	function transfer(toacc) {
  		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='none';
		document.getElementById('addamount').style.display='none';
  		document.getElementById('transamount').style.display='block';
  		$("#toaccno").val(toacc);
	}
  	function trans() {
  		var prevd;
  		var data1;
  		var toaccno = $("#toaccno").val();
		$.ajax({
			url: 'TransferMoney',
			type:'POST',
			dataType:'html',
			data:{
				'toaccno':toaccno,
				'money': $("#money").val()
			},beforeSend: function() {
				prevd=$("#transamount").html();
				$("#transamount").html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},success: function(data) {
				data1=data;
				$("#transamount").html(data1);
				$("#transtatus").html(data1);
			},complete: function() {
				$("#transamount").html(data1);
				$("#transtatus").html(data1);
			}
		});
	}
  	function debittrans() {
  		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('addamount').style.display='none';
		document.getElementById('transamount').style.display='none';
		$.ajax({
			url: 'DebitTrans',
			type: 'POST',
			dataType:'html',
			data:{},beforeSend: function() {
				$('#datas').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},success: function(data) {
				$("#datas").html(data);
			}
		});
	}
  	function credittrans() {
  		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('addamount').style.display='none';
		document.getElementById('transamount').style.display='none';
		$.ajax({
			url: 'CreditTrans',
			type: 'POST',
			dataType:'html',
			data:{},beforeSend: function() {
				$('#datas').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},success: function(data) {
				$("#datas").html(data);
			}
		});
	}
    function fetchbenificiery() {
    	document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='block';
		document.getElementById('addamount').style.display='none';
		document.getElementById('transamount').style.display='none';
		$.ajax({
			url: 'FetchBenificiery',
			type:'POST',
			dataType:'html',
			data: {
			},beforeSend: function() {
				$('#datas').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
			},success: function(data) {
				$("#datas").html(data);
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
  	function cardDetails() {
		var cardNo = '<%=cardno %>';
		var cvv ="CVV"+" "+'<%=cvv%>';
		document.getElementById('CardNo').innerHTML=cardNo;
		document.getElementById('cvv').innerHTML=cvv;
	}
  	function addben() {
  		document.getElementById('addamount').style.display='none';
  		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='block';
		document.getElementById('view1').style.display='none';
		document.getElementById('transamount').style.display='none';
	}
  	function home() {
  		document.getElementById('addamount').style.display='block';
  		document.getElementById('mydata').style.display='none';
		document.getElementById('imgcontainer').style.display='block';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='none';
		document.getElementById('transamount').style.display='none';
	}
  	function mydata() {
  		document.getElementById('addamount').style.display='none';
  		document.getElementById('mydata').style.display='block';
		document.getElementById('imgcontainer').style.display='none';
		document.getElementById('pagecontent1').style.display='none';
		document.getElementById('view1').style.display='none';
		document.getElementById('transamount').style.display='none';
	}
  	function addmoney() {
		$.ajax({
			url:'AddMoney',
			type:'POST',
			dataType:'html',
			data: {
				'accno':$("#accnotoadd").val(),
				'amt':$("#amt").val()
			},beforeSend: function() {
				
			},success: function() {
				
			}
		})
	}
  	
  </script>
<script type="text/javascript">
			function msg() {
				 Swal.fire({
					  icon: 'success',
					  title: 'Wohoo!!!Your Account is Active',
					  text: '!!!Welcome for the First Time!!!',
					  footer: 'We are happy to have you here!'
					})
			}
			 </script>

<script>
$(document).ready(function(){
	$.ajax({
		url : 'LastLogindet',
		type: 'POST',
		dataType: 'html',
		data:{
			'custid' : '<%=id%>'
		},beforeSend: function() {
			
		},success: function(data) {
			$("#lastlogin").html(data);
		}
	}); 
	$("#ifsc").blur(function() {
		$.ajax({
			url : 'FetchBranchFromIfsc',
			type: 'POST',
			dataType: 'html',
			data:{
				'ifsc' : $("#ifsc").val()
			},beforeSend: function() {
				
			},success: function(data) {
				$("#branch").val(data);
			}
		});
	});
	$.ajax({
		url : 'CheckBalance',
		type: 'POST',
		dataType: 'html',
		data:{
			
		},beforeSend: function() {
			
		},success: function(data) {
			$("#accbal").html(data);
		}
	});
	document.getElementById('pass1').style.display='none';
	document.getElementById('pagecontent1').style.display='none';
	$("#home").addClass("nav-button-active");
	$("#addemp,#upemp,#view,#recyclebin,#view2").addClass("nav-button"); 
  $("#home").click(function(){
	$("#addemp,#upemp,#view,#recyclebin,#view2").removeClass("nav-button");
	$("#addemp,#upemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
    $("#addemp,#upemp,#view,#recyclebin,#view2").addClass("nav-button");
    $("#home").removeClass("nav-button-active");
    $("#home").addClass("nav-button-active");
  });
  $("#addemp").click(function(){
		$("#home,#upemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
		$("#home,#upemp,#view,#recyclebin,#view2").removeClass("nav-button");
	    $("#home,#upemp,#view,#recyclebin,#view2").addClass("nav-button");
	    $("#addemp").removeClass("nav-button");
	    $("#addemp").addClass("nav-button-active");
	  });
  $("#upemp").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button");
	    $("#home,#addemp,#view,#recyclebin,#view2").addClass("nav-button");
	    $("#upemp").addClass("nav-button-active");
	  });
  $("#view").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button");
	    $("#home,#addemp,#upemp,#recyclebin,#view2").addClass("nav-button");
	    $("#view").addClass("nav-button-active");
	  });
  $("#recyclebin").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button");
	    $("#home,#addemp,#upemp,#view,#view2").addClass("nav-button");
	    $("#recyclebin").addClass("nav-button-active");
	  });
  $("#view2").click(function(){
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button-active");
		$("#home,#upemp,#addemp,#view,#recyclebin,#view2").removeClass("nav-button");
	    $("#home,#addemp,#upemp,#view,#view2,#recyclebin").addClass("nav-button");
	    $("#view2").addClass("nav-button-active");
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
<title>GIB Internet Banking</title>
</head>
<body onkeypress="return isKeyPressed(event)"
	onkeydown="return isKeyPressed(event)">
	<%
	if(msg==null){
		
		
	}else{
		
		if(msg.equals("acc_active")){
			 %>
	<script type="text/javascript">
			 	msg();
				</script>
	<%
		}
	}
%>
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
	<!-- <ul style="background-color: #FFFFFF;list-style-type: none;margin: 0;padding: 0;overflow: hidden;">
 <li class="li1">
 
 </ul> -->

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
					<button type="button" id="addemp" onclick="addben()">Add
						Beneficiary</button>
				</li>
				<li class="nav-item">
					<button type="button" id="upemp" onclick="fetchbenificiery()">Transfer
						Money</button>
				</li>
				<li class="nav-item">
					<button type="button" id="view" onclick="debittrans()">Debit
						Transaction</button>
				</li>
				<li class="nav-item">
					<button type="button" id="view2" onclick="credittrans()">Credit
						Transaction</button>
				</li>
			</ul>
		</div>
	</nav>
	<div id="imgcontainer">
		<div id="overlay" style="background-color: rgba(0, 120, 255, 0.4)">
			<div class="headertextcontainer"
				style="width: 30%; background-color: rgba(255, 255, 255, 0.9); min-width: 310px;">
				<h2 class="Headertext"
					style="color: #929292; text-shadow: 0px; float: left; text-align: left;">
					Welcome
					<%=name %><br>
				</h2>
				<br>
				<br> <span class="acc_det" id="">Account Number: <%=acc %></span><br>
				<span class="acc_det" id="accbal">Available Balance: <%=bal %></span>
				<button class="header-btn"
					style="outline: none; background-color: #B600FF;"
					onclick="mydata()">
					<span>My Account Details</span>
				</button>

			</div>
			<div class="headertextcontainer"
				style="width: 30%; background-color: rgba(255, 255, 255, 0.9); float: right; padding-top: 35px; min-width: 380px;">
				<h2 class="Headertext" style="color: #0A2E6E;">
					Debit Card<br>
				</h2>
				<div class="debitcard">
					<div class="debitcard-overlay">
						<img src="images\GIBLogo.png" width="50px" height="50px"
							alt="GIB Logo" style="padding: 0px; margin-left: 8px;">
						<h4>Great Indian Bank</h4>
						<br>
						<img src="images\chip.PNG" class="chip" alt="evm">
						<div class="cardno-cont">
							<span class="cardno" id="CardNo" style="font-weight: bolder;">XXXX
								XXXX XXXX XXXX</span><span class="valid-thru">Valid<br>thru
							</span><span class="exp" id="exp"><%=exp %></span><span class="exp"
								id="cvv"> CVV XXX</span>
						</div>
						<span class="holdername"><%=name %></span> <img alt=""
							src="images/visa.png" class="visa">
					</div>
				</div>
				<button class="header-btn"
					style="outline: none; background-color: #B600FF;"
					onclick="cardDetails()">
					<span>View Card details</span>
				</button>

			</div>

		</div>
	</div>
	<div class="pagecontent" id="pagecontent1">
		<h3
			style="margin: 0px; width: 100%; text-align: center; padding: 0px;">Add
			New Benificiery</h3>
		<form action="" method="POST" onsubmit="addBenifeiceiry()">
			<label for="accno"
				style="position: absolute; font-weight: normal; left: 1%; cursor: pointer;">Enter
				Account Number</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Account Number" name="accno" id="accno"> <label
				for="toaccname"
				style="position: absolute; left: 32%; font-weight: normal; cursor: pointer;">Account
				Holder Name</label> <input type="text" name="toaccname" id="toaccname"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Account Holder Name"> <label for="bankname"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Name
				of Bank</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Name of Bank" name="bankname" id="bankname" value="GIB"
				readonly="readonly"><br> <label for="ifsc"
				style="position: absolute; font-weight: normal; left: 1%; cursor: pointer;">IFSC
				Code</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="IFSC Code" name="ifsc" id="ifsc"> <label
				for="branch"
				style="position: absolute; left: 32%; font-weight: normal; cursor: pointer;">Branch
				Name</label> <input type="text" name="branch" id="branch"
				style="width: 30%; margin-top: 20px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Branch Name" readonly="readonly"> <label
				for="acc_type"
				style="font-weight: normal; position: absolute; left: 62%; cursor: pointer;">Account
				Type</label> <input type="text"
				style="width: 30%; margin-top: 40px; margin-bottom: 20px; margin-right: 10px;"
				placeholder="Name of Bank" name="acc_type" id="acc_type"
				value="Savings Account" readonly="readonly"><br> <input
				type="submit" value="Submit" class="button1"
				style="width: 60%; margin-left: 17%; margin-top: 20px; margin-right: 10px; outline: none;"
				onclick="">
		</form>
		<div id="addstatus"></div>
	</div>
	<div id="addamount" class="addamount">
		<h3
			style="margin: 0px; width: 100%; text-align: center; padding: 0px;">Add
			Money</h3>
		<form action="" method="POST" onsubmit="addmoney()">
			<input type="text" value="<%=acc%>" id="accnotoadd" name="accnotoadd"
				hidden=""> <input type="text"
				placeholder="Enter Amount in Rupees" style="margin-top: 30px;"
				id="amt" name="amt"> <input type="submit"
				class="view-button"
				style="width: 80%; margin-left: 10%; margin-top: 40px;">
		</form>
	</div>
	<div id="transamount" class="transamount">
		<h3
			style="margin: 0px; width: 100%; text-align: center; padding: 0px;">Add
			Money</h3>
		<form action="" method="POST" onsubmit="trans()">
			<input type="text" id="toaccno" hidden=""> <input type="text"
				placeholder="Enter Amount in Rupees" style="margin-top: 30px;"
				id="money" name="money"> <input type="submit"
				class="view-button"
				style="width: 80%; margin-left: 10%; margin-top: 40px;"
				value="Transfer Money">
		</form>
		<div id="transtatus"></div>
	</div>
	<div class="mydata" id="mydata">
		<img alt="proeffect" id="proeffect" class="MyProfile"
			src="images/profile.png"
			style="cursor: pointer; height: 16%; width: 16%; margin-left: 43%; margin-top: 5%;">
		<div class="profiledetail">
			<%=id %></div>
		<div class="profiledetail">
			<%=name %></div>
		<div class="profiledetail" style="font-weight: bolder;">Registered
			Mobile Number</div>
		<div class="profiledetail">
			<%=mob %></div>
		<div class="profiledetail" style="font-weight: bolder;">Date of
			Birth</div>
		<div class="profiledetail">
			<%=dob %></div>
		<div class="profiledetail" style="font-weight: bolder;">Primary
			Email</div>
		<div class="profiledetail">
			<%=pemail %></div>
		<div class="profiledetail" style="font-weight: bolder;">
			Secondary Email</div>
		<div class="profiledetail">
			<%=semail %></div>
		<div class="profiledetail" style="font-weight: bolder;">Father
			's Name</div>
		<div class="profiledetail">
			<%=Father %></div>
		<div class="profiledetail" style="font-weight: bolder;">Mother
			's Name</div>
		<div class="profiledetail">
			<%=mother %></div>
		<div class="profiledetail" style="font-weight: bolder;">Mailing
			Address</div>
		<div class="profiledetail">
			<%=maddr %></div>
		<div class="profiledetail" style="font-weight: bolder;">Permanent
			Address</div>
		<div class="profiledetail">
			<%=peraddr %></div>
		<div id="passchange" class="profiledetail"
			onclick="document.getElementById('pass1').style.display='block'"
			style="font-weight: bolder;">Change Your Password</div>
		<div class="profiledetail" id="pass1">
			<form action="" method="POST" onsubmit="changepassword()">
				<input type="text" id="empid" name="empid" value="<%=name%>"
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
		<div class="profiledetail" style="font-weight: bolder;">Last
			Login On</div>
		<div id="lastlogin"></div>
	</div>
	<div id="view1" class="view">
		<span id="datas"></span>
	</div>

	<div class="" id="Success"></div>
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