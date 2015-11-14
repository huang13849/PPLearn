<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script>
        //强制使用长链接
        //      window.WEB_XHR_POLLING = true;
        //强制使用flash
        //      window.WEB_SOCKET_FORCE_FLASH = true;
        //强制使用localstorage存储用户标识
        //      window.FORCE_LOCAL_STORAGE = true
    </script>
    <script src="assets/js/RongIMClient-0.9.10.min.js"></script>
</head>
<body>

</body>
<div style="margin: 10px 10px">
    content:<input id="content" type="text" style="width: 80%;" value="1">
</div>
<div style="margin: 10px 10px">
    send to:<input type="text" id="target" style="margin-right: 50px" value="TARGETID">
    <button id="send">send</button>
</div>

<div id="mydiv" style="width: 100%;height: 200px;background-color: oldlace;overflow-y: auto">

</div>
<div id="cons">

</div>
<script>
    window.onload = function () {
        var cons = document.getElementById("cons");
        window.log = function (x) {
            cons.innerHTML += x + "<br/>";
        };
        RongIMClient.init("82hegw5uh00ax");
        RongIMClient.setConnectionStatusListener({
            onChanged: function (status) {
                console.log(status.toString(), new Date())
            }
        });
        var mydiv = document.getElementById("mydiv");
        RongIMClient.getInstance().setOnReceiveMessageListener({
            onReceived: function (data) {
                var p = document.createElement("span");
                p.style.marginRight = "10px";
                p.innerHTML = data.getContent();
                mydiv.appendChild(p);
            }
        });

        var self = "";
        RongIMClient.connect("${token}", {
            onSuccess: function (x) {
                console.log("connected，userid＝" + x);
                self = x;
            },
            onError: function (x) {
                console.log(x)
            }
        });
        ins = RongIMClient.getInstance();
        var c = document.getElementById("content"), to = document.getElementById("target"), s = document.getElementById("send"), t = document.getElementById("type");
        s.onclick = function () {
            var con = RongIMClient.ConversationType.setValue(t.value);
            ins.sendMessage(con, to.value, RongIMClient.TextMessage.obtain(c.value || Date.now()), null, {
                onSuccess: function () {
                    console.log("send successfully");
                    c.value = (c.value * 1 + 1);
                }, onError: function () {
                    console.log("send fail")
                }
            });
        }
    };
</script>
</html>
