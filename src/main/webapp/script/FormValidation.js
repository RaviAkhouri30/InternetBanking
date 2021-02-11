
  		function validateLogin() {
			var uname = document.forms["login"]["uname"].value;
			var pass = document.forms["login"]["pass"].value;
			if(uname=='' || pass==''){
				Swal.fire({
					  icon: 'error',
					  title: 'Oops...',
					  text: 'Please Enter Valid User Name and Password!!!',
					  footer: '<a href>Why do I have this issue?</a>'
					})
				return false;
			}
		}
