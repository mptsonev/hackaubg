<html>
<head>
  <title>PeerJS - Video chat example</title>
  <link rel="stylesheet" href="js/style.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>	
  <script type="text/javascript" src="js/peer.js"></script>
  <script type="text/javascript" src="js/utils.js"></script>
  
  <jsp:include page="pages/whiteboard.jsp" />
  
  
  <script>

//both links are mandatory
//widget.html will internally use widget.js


</script>

<!-- 3rd i.e. last step -->
<script>
  
    // Compatibility shim
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

    // PeerJS object
    var peer = new Peer({host:'peerjs-server.herokuapp.com', secure:true, port:443, key: 'peerjs', debug: 3})

    peer.on('open', function(){
      $('#my-id').text(peer.id);
      if(sessionStorage.getItem('role') == "teacher") {
	      createRoomRequest(peer.id);
      }
    });

    // Receiving a call
    peer.on('call', function(call){
      // Answer the call automatically (instead of prompting user) for demo purposes
      call.answer(window.localStream);
      step3(call);
    });
    peer.on('error', function(err){
      alert(err.message);
      // Return to step 2 if error occurs
      step2();
    });

    function connectWebcamToUser(webcamid) {
        var call = peer.call(webcamid, window.localStream);
        step3(call);
    }
    
    // Click handlers setup
    $(function(){
//      $('#make-call').click(function(){
//        // Initiate a call!
//        var call = peer.call($('#callto-id').val(), window.localStream);
//        step3(call);
//      });

      $('#end-call').click(function(){
        window.existingCall.close();
        step2();
      });

      // Retry if getUserMedia fails
      $('#step1-retry').click(function(){
        $('#step1-error').hide();
        step1();
      });

      // Get things started
      step1();
    });

    function step1 () {
      // Get audio/video stream
      navigator.getUserMedia({audio: true, video: {width:640, height: 480} }, function(stream){
        // Set your video displays
        addVideoInDialog("my-video", stream);
        window.localStream = stream;

        if(sessionStorage.getItem('role') == "student") {
      	  joinRoom();
        }        
        
        step2();
      }, function(){ $('#step1-error').show(); });
    }

    function step2 () {
      $('#step1, #step3').hide();
      $('#step2').show();
    }

    function step3 (call) {
      // Hang up on an existing call if present
      if (window.existingCall) {
        window.existingCall.close();
      }

      // Wait for stream on the call, then set peer video display
      call.on('stream', function(stream){
    	addVideoInDialog("their-video", stream);
      });

      // UI stuff
      window.existingCall = call;
      $('#their-id').text(call.peer);
      call.on('close', step2);
      $('#step1, #step2').hide();
      $('#step3').show();
    }

  </script>


</head>

<body>
<!--  
  <div class="pure-g" style="position:relative" hidden>

      <div class="pure-u-1-3">
        <h2>PeerJS Video Chat</h2>

        <div id="step1">
          <p>Please click `allow` on the top of the screen so we can access your webcam and microphone for calls.</p>
          <div id="step1-error">
            <p>Failed to access the webcam and microphone. Make sure to run this demo on an http server and click allow when asked for permission by the browser.</p>
            <a href="#" class="pure-button pure-button-error" id="step1-retry">Try again</a>
          </div>
        </div>

        <div id="step2">
          <p>Your id: <span id="my-id">...</span></p>
          <p>Share this id with others so they can call you.</p>
          <h3>Make a call</h3>
          <div class="pure-form">
            <input type="text" placeholder="Call user id..." id="callto-id">
            <a href="#" class="pure-button pure-button-success" id="make-call">Call</a>
          </div>
        </div>

        <div id="step3">
          <p>Currently in call with <span id="their-id">...</span></p>
          <p><a href="#" class="pure-button pure-button-error" id="end-call">End call</a></p>
        </div>
      </div>
  </div>
  -->


</body>
</html>
