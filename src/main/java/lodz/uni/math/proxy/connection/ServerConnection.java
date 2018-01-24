package lodz.uni.math.proxy.connection;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private WebSocketSession session;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public ServerConnection(WebSocketSession session, String hostName, String port) throws IOException {
        this.session = session;
        this.connect(hostName, port);
    }

    public void connect(String hostName, String port) throws IOException {
        socket = new Socket(hostName, Integer.parseInt(port));
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(this).start();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        for (;;) {
            handleMessage();
        }
    }

    private void handleMessage() {
        try {
            String message = in.readLine();
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            //place for log
        }
    }
}
