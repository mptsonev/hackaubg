

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
		peer = new Peer({host:'peerjs-server.herokuapp.com', secure:true, port:443, key: 'peerjs', debug: 3});
		
		peer.on('open', function(id) {
		  myId = id;
		  console.log('My peer ID is: ' + id);
		});
		peer.on('call', function(call) {
			navigator.mediaDevices.getUserMedia({video: {width: 320, height: 160, framerate: {max: 10}}, audio: true}).then(function(myStream){
				call.on('stream',function(remoteStream){
					addVideoInDialog("xxxx", remoteStream);
				})
			})
		})		
	}
	
	function addVideoInDialog(id, stream) {
		var newDiv = $(document.createElement('div')); 
		newDiv.html('<video id="' + id + '" autoplay></video>');
		newDiv.dialog({width: 325,height:171});
		
		var myVideoTag = $('#' + id)[0];
		myVideoTag.srcObject = stream;		

	}
	
	function callUser(remoteUserId) {
		navigator.mediaDevices.getUserMedia({video: {width: 320, height: 160, framerate: {max: 10}}, audio: true}).then(function(myStream){
			addVideoInDialog(myId, myStream);
			var call = peer.call(remoteUserId, myStream);
			call.on('stream', function(remoteStream) {
				addVideoInDialog(remoteUserId, remoteStream);
			});
		}, function(err) {
			debugger;
			console.log('Failed to get local stream' ,err);
		});
	}
