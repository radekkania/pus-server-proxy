<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        var sock;
        var url = 'ws://' + '127.0.0.1:8181' + '/proxy/echo';

        $(document).ready(function () {
            debugger;
            sock = new WebSocket(url);

            sock.onopen = function()
            {
                // Web Socket is connected, send data using send()
                sock.send("hello");
            };

            sock.onmessage = function (evt)
            {
                var received_msg = '<div>' + evt.data + '<div>';
                $(".content").append(received_msg);
            };

            sock.onclose = function()
            {
                // websocket is closed.
                alert("Connection is closed...");
            };
        })

        function sendMessage(content) {
            debugger;
            var content = $(".inputText").val();
            sock.send(content);
            $(".inputText").val("");
        }
    </script>
</head>
<body>
    HELLO WORLD
    <button id="socketConnectBtn" onclick="sendMessage()">Send</button>
    <textarea class="inputText" >
        Tu wpisz text
    </textarea>
    <div class="content">
        bla bla
    </div>
</body>

</html>