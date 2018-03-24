

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
	
	var peer = null;
	var myId = null;
	
	function initializeMe() {
		peer = new Peer({ host : 'asphericpeerjs.herokuapp.com' });
		
		peer.on('open', function(id) {
		  myId = id;
		  console.log('My peer ID is: ' + id);
		});
		peer.on('call', function(call) {
			navigator.mediaDevices.getUserMedia({video: {width: 320, height: 160, framerate: {max: 10}}, audio: true}).then(function(myStream){
				call.answer(myStream);
				call.on('stream',function(remoteStream){
					var partnerVideoTag = $('#videoObject')[0];
					partnerVideoTag.srcObject = remoteStream;	
				})
			})
		})		
	}
	
	function callUser(remoteUserId) {
		navigator.mediaDevices.getUserMedia({video: {width: 320, height: 160, framerate: {max: 10}}, audio: true}).then(function(myStream){
			var myVideoTag = $('#myVideoObject')[0];
			myVideoTag.srcObject = myStream;
			var call = peer.call(remoteUserId, myStream);
			call.on('stream', function(remoteStream) {
				var partnerVideoTag = $('#videoObject')[0];
				partnerVideoTag.srcObject = remoteStream;				
			});
		}, function(err) {
			debugger;
			console.log('Failed to get local stream' ,err);
		});
	}
