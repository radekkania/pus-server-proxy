package lodz.uni.math.proxy.handler;

import lodz.uni.math.proxy.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class ConnectionHandler extends AbstractWebSocketHandler {

    @Autowired
    ConnectionService connectionService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("Connected with server"));
        connectionService.createNewConnection(session);
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        connectionService.sendMessage(session.getId(), message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        connectionService.disconnect(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }
}
