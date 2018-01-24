package lodz.uni.math.proxy.service;

import lodz.uni.math.proxy.connection.ServerConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Value("${server.host}")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    public Map<String, ServerConnection> connections = new HashMap();

    @Override
    public void createNewConnection(WebSocketSession session) {
        try {
            ServerConnection newConn = new ServerConnection(session, serverHost, serverPort);
            connections.put(session.getId(), newConn);
        } catch (IOException e) {

        }
    }

    @Override
    public void disconnect(String sessionId) {
        connections.remove(sessionId);
    }

    @Override
    public void sendMessage(String sessionId, String message) {
        connections.get(sessionId).sendMessage(message);
    }
}
