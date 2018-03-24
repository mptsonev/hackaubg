

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
			if(login.role == "student") {
				sessionStorage.setItem('role', "student");
				window.location.href = "/ui/list-rooms.html"
			} else if(login.role == "teacher") {
				sessionStorage.setItem('role', "teacher");
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
		adminCreator.userName = sessionStorage.getItem('userName');
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
		
		sendMessage(adminCreator,function(data){
		}, function(er){
			alert("Error is: " + er)
		});				
	}
	
	$("#createRoom").click(function(){
		createRoom();
	})
	
	// Get rooms
	function getAllRooms() {
		var getRooms = {};
		getRooms.cmd = "getClassrooms";
		
		sendMessage(getRooms,function(data){
			var classRoom = data.classrooms[data.classrooms.length - 1];
			$("#containerImage").attr("src", classRoom.pictureUrl);
			$("#containerTeacher").text("Teacher Name: " + classRoom.teacherName);
			$("#containerRoomSubject").text("Subject: " + classRoom.subject);
			$("#containerRoomName").text("Room Name: " + classRoom.roomName);
			$("#duration").text("Duration: " + msToTime(new Date() - classRoom.startTime));
			$("#allClassroomData").attr("data", JSON.stringify(classRoom));
			
			setInterval(function(){
				$("#duration").text("Duration: " + msToTime(new Date() - classRoom.startTime));
			}, 500)
		}, function(er){
			alert("Error is: " + er)
		});				
	}
	
	// Join room
	function joinRoom() {
		var roomData = JSON.parse(sessionStorage.getItem('joinedRoomData'));
		roomData = JSON.parse(roomData);
		connectWebcamToUser(roomData.teacherWebcamId);
		connectWhiteBoardRoom(roomData.whiteboardId);
	}
	
	// Join button pressed
	$("#allClassroomData").click(function(){
		var data = $(this).attr("data");
		sessionStorage.setItem('joinedRoomData', JSON.stringify(data));
		window.location.href = "/";
	})
	

	function addVideoInDialog(id, stream) {
		var dialogTitle = '';
		if(sessionStorage.getItem('role') == "teacher") {
			if(id == "my-video") {
				dialogTitle = "Teacher";
			} else {
				dialogTitle = "Student";
			}
		} else {
			if(id == "my-video") {
				dialogTitle = "Student";
			} else {
				dialogTitle = "Teacher";
			}			
		}
		
		var newDiv = $(document.createElement('div')); 
		newDiv.html('<video id="' + id + '" autoplay style="width:100%; height:100%;  overflow:hidden;"></video>');
		newDiv.dialog({width: 440,height:320, title: dialogTitle, open: function(){
			$(this).css('overflow', 'hidden')
		}});
		
		var myVideoTag = $('#' + id)[0];
		myVideoTag.srcObject = stream;		
	}
	
	function makeid() {
	  var text = "";
	  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	  for (var i = 0; i < 8; i++)
	    text += possible.charAt(Math.floor(Math.random() * possible.length));

	  return text;
	}	
	
	function msToTime(s) {
	  // Pad to 2 or 3 digits, default is 2
	  function pad(n, z) {
	    z = z || 2;
	    return ('00' + n).slice(-z);
	  }

	  var ms = s % 1000;
	  s = (s - ms) / 1000;
	  var secs = s % 60;
	  s = (s - secs) / 60;
	  var mins = s % 60;
	  var hrs = (s - mins) / 60;

	  return pad(hrs) + ':' + pad(mins) + ':' + pad(secs);
	}	
	
	