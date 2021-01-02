package org.com.chat.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("{} Connection Open. ");
		sessionList.add(session);
		session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		for (WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(session.getAcceptedProtocol() + ":" + msg));
		}
//		session.sendMessage(new TextMessage("RECEIVED: " + msg));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("::::::::::::::::::::::: session close:::::::::::::::::::::::::::::::");
		sessionList.remove(session);
		log.info("{} Connection Closed.", session.getPrincipal().getName());
	}
}
