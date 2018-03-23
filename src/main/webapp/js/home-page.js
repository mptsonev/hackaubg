
	$("#testButton").click(function(){
		var data = {};
		data.cmd = "Test";
		data.test = "Hey Baby"
		sendMessage(data, function(resp){
			alert("Response is: " + resp);
		}, function(er) {
			alert("Error occured: " + er);
		});
	})