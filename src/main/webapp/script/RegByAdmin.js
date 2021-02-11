
    function save_data()

    {
        var fname=$("#fname").val();
        var doj1 = $("#doj1").val();
        var lname=$("#lname").val();
        var bname=$("#bname").val();
        var mail=$("#mail").val();
        var dob = $("#dob").val();
        var prev;
        $.ajax({
            url :'EmpDetailAdmin',
            type:'POST',
             dataType:'html',
             timeout:10000,
            data :{
                   'fname':fname,
                   'lname':lname,
                   'bname':bname,
                   'mail':mail,
                   'dob':dob,
                   'doj1':doj1
            },
            
            beforeSend:function(){
            	prev=$('#regform').html();
            	$('#regform').html('<div class="loader" style="margin-top:100px; margin-left:42%; padding:20px;"></div>');
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
            		/*$('#regform').html(prev);*/
            },
            error  :function(data){
            	Swal.fire({
          		  position: 'top-end',
          		  icon: 'error',
          		  title: 'Please Provide Valid Data and Try Again',
          		  showConfirmButton: false,
          		  timer: 2000
          		});
            },
            complete :function(){
            	$('#regform').html(prev);
            }
        });
    }
