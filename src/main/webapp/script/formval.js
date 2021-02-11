$(document).ready(function() {
		$("#unameval").hide();
		$("#passval").hide();
		$("#uname").blur(function(){
			var uname = $("#uname").val();
			if(uname==''){
				$('#unameval').show();
			}else{
				$('#unameval').hide();
			}
		});
		$("#pass").blur(function(){
			var pass = $("#pass").val();
			if(pass==''){
				$('#passval').show();
			}else{
				$('#passval').hide();
			}
		});
	});