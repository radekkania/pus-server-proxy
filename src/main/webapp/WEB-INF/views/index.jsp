<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
                var received_msg = '->' + evt.data + '\n';
                $(".content").append(received_msg);
            };

            sock.onclose = function()
            {
                alert("Connection is closed...");
            };
        });

        function sendMessage(content) {
            debugger;
            var content = $(".inputText").val();
            sock.send(content);
            $(".inputText").val("");

            var toDisplay = '<-' + content + '\n';

            $(".content").append(toDisplay);
            scrollToBottom();
        }

        function scrollToBottom() {
            var textarea = document.getElementById('textarea_id');
            textarea.scrollTop = textarea.scrollHeight;
        }

        function clearContent() {
            $(".content").val("");
        }

        function onEnterKeyup(event) {
            if (event.keyCode == 13) {
                debugger;
                $('[id$=socketConnectBtn]').click();
            }
        }
    </script>
</head>
<body onkeyup="onEnterKeyup(event)">
    <div>
        <textarea id="textarea_id" class="content" style="height:200px; width:600px;">
            bla bla
        </textarea>
    </div>
    <div>
        <input class="inputText" style="width:600px;" placeholder="Message to send"/>
    </div>
    <div>
        <button id="socketConnectBtn" onclick="sendMessage()">Send</button>
        <button id="clearContentBtn" onclick="clearContent()">Clear</button>
    </div>
</body>

</html>