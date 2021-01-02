<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="one">
		NickName:<input type="text" id="nickname" /> <input type="button"
			id="enter" value="enter" onclick="connect();" />
	</div>
	<div id="two" style="display: none">
		<input type="button" id="exit" value="out" /><br />
		<div id="chatarea"
			style="width: 400px; height: 600px; border: 1px solid;"></div>
		<input type="text" id="message" /> <input type="button" id="send"
			value="send" />
	</div>
</body>
<script type="text/javascript">
	one = document.getElementById("one");
	two = document.getElementById("two");
	
	document.getElementById("exit").addEventListener("click", function() {
		disconnect();
	});
	document.getElementById("send").addEventListener("click", function() {
		send();
	});
	var websocket;
	function connect() {
		websocket = new WebSocket("ws://localhost:8080/chat");
		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;
	}
	function disconnect() {
		msg = document.getElementById("nickname").value;
		websocket.send(msg + " is out.");
	}
	function send() {
		nickname = document.getElementById("nickname").value;
		msg = document.getElementById("message").value;
		websocket.send(nickname + ":" + msg);
		document.getElementById("message").value = "";
	}
	function onOpen() {
		nickname = document.getElementById("nickname").value;
		two = document.getElementById("two");
		two.style.display = 'block';
		websocket.send(nickname + " is login.");
	}
	function onMessage(evt) {
		data = evt.data;
		chatarea = document.getElementById("chatarea");
		chatarea.innerHTML = data + "<br/>" + chatarea.innerHTML
	}
	function onClose() {
	}
</script>
</html>