<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.10/semantic.min.css" />
<script type="text/javascript">
	var ws = null;
	var url = "ws://localhost:8080/chat";

	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('chat').disabled = !connected;
	}

	function connect() {
		ws = new WebSocket(url);

		ws.onopen = function() {
			setConnected(true);
		};
		ws.onmessage = function(event) {
			log(event.data);
		};
		ws.onclose = function(evet) {
			setConnected(false);
			log('info: Closing Connection.');
		};
	}

	function disconnect() {
		if (ws != null) {
			ws.close();
			ws = null;
		}
		setConnected(false);
	}

	function log(message) {
		var console = document.getElementById('logging').value;
		var p = document.createElement('p');
		p.appendChild(document.createTextNode(message));
		console.appendChild(p);
		while (console.childNodes.length > 12) {
			console.removeChild(console.firstChild);
		}
		console.scrollTop = console.scrollHeight;
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<div id="connect-container" class="ui centered grid">
			<div class="row">
				<button id="connect" onclick="connect();" class="uri green button">
					Connect</button>
				<button id="disconnect" disabled="disabled" onclick="disconnect();"
					class="uri red button">DisConnect</button>
			</div>
			<div class="row">
				<textarea id="message" style="width: 350px" class="ui input"
					placeholder="Chat Message"></textarea>
			</div>
		</div>
		<div id="console-container">
			<h3>Logging</h3>
			<div id="logging"></div>
		</div>
	</div>
</body>
</html>