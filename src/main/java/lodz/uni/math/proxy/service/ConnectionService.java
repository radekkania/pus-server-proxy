package lodz.uni.math.proxy.service;

import org.springframework.web.socket.WebSocketSession;

public interface ConnectionService {
    void createNewConnection(WebSocketSession session);
    void disconnect(String sessionId);
    void sendMessage(String sessionId, String message);
}
