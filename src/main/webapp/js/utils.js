

	function sendMessage(data, success, ecb) {
		$.ajax({
			  type: "POST",
			  url: "services",
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
