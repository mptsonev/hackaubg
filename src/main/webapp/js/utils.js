

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

		sessionStorage.setItem('userName', login.userName);
		
		sendMessage(login,function(data){
			alert("Successfully registered");
			if(login.role == "student") {
				
			} else if(login.role == "teacher") {
				window.location.href = "/ui/create-room.html";
			}
		}, function(er){
			alert("Error is: " + er)
		});
	}
	
	$("#login").click(function(){
		register();
	})
	
	// Create Room
	function createRoom() {
		var adminCreator = {};
		adminCreator.roomName = $("#roomName").val();
		adminCreator.pictureURL = $("#pictureURL").val();
		adminCreator.subject = $("#subject").val();
		adminCreator.userName = userName;
		sessionStorage.setItem('adminCreator', JSON.stringify(adminCreator));
		window.location.href = "/";
	}
	
	function createRoomRequest(teacherVideoId) {
		var adminCreator = sessionStorage.getItem('adminCreator');
		adminCreator = JSON.parse(adminCreator);
		adminCreator.cmd = "createClassroom";
		adminCreator.teacherVideoId = teacherVideoId;
		adminCreator.whiteboardId = makeid();
		adminCreator.userName = sessionStorage.getItem('userName');
		
		openWhiteBoardRoom(adminCreator.whiteboardId);
		
		debugger;
		sendMessage(adminCreator,function(data){
			alert("Successfully registered");
		}, function(er){
			alert("Error is: " + er)
		});				
	}
	
	$("#createRoom").click(function(){
		createRoom();
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
	
	function makeid() {
	  var text = "";
	  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	  for (var i = 0; i < 5; i++)
	    text += possible.charAt(Math.floor(Math.random() * possible.length));

	  return text;
	}	
	
	