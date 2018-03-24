

	function sendMessage(data, success, ecb) {
		$.ajax({
			  type: "POST",
			  url: "/services",
			  data: data,
			  success: success,
			  error: ecb,
			  dataType: "json"
			});
	}

	
	// Registration
	function register() {
		var login = {};
		login.cmd = "register";
		login.userName = $("#userName").val();
		login.password = $("#password").val();
		login.role = $("#role").val();
		
		sendMessage(login,function(data){
			alert("Successfully registered")
		}, function(er){
			alert("Error is: " + er)
		});
	}
	
	$("#login").click(function(){
		register();
	})

	function addVideoInDialog(id, stream) {
		var newDiv = $(document.createElement('div')); 
		newDiv.html('<video id="' + id + '" autoplay style="width:100%; height:100%;  overflow:hidden;"></video>');
		newDiv.dialog({width: 440,height:320, open: function(){
			$(this).css('overflow', 'hidden')
		}});
		
		var myVideoTag = $('#' + id)[0];
		myVideoTag.srcObject = stream;		

	}
	
	