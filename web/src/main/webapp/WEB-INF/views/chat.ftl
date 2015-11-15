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
    <script type="text/javascript">            
		var mengvalue = -1;
            //if(mengvalue<0){mengvalue=0;}
            var phoneWidth = parseInt(window.screen.width);
            var phoneScale = phoneWidth / 640;

            var ua = navigator.userAgent;
            if (/Android (\d+\.\d+)/.test(ua)) {
                var version = parseFloat(RegExp.$1);
                // andriod 2.3
                if (version > 2.3) {
                    document.write('<meta name="viewport" content="width=640, minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi">');
                    // andriod 2.3以上
                } else {
                    document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
                }
                // 其他系统
            } else {
              	document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
            }
            
	</script>
    <script src="http://res.websdk.rongcloud.cn/RongIMClient-0.9.10.min.js"></script>
    <link rel="stylesheet" href="assets/css/chat_style.css">
    </head>
<body>


<div style="margin: 10px 10px">
    <div style="float:left;">Talking with :  ${toUser.nickName!}</div>    
    <div style="float:right;margin-right:10px;"><button id="send">send</button></div>
    <input type="hidden" id="target" style="margin-right: 10px;" value="${toUser.id!}">
</div>
<div style="margin: 10px 10px">
	<textarea id="content" type="text" style="margin:10px;width: 40%;height:100px;" row="10" ></textarea>
</div>

<div id="convo" >  
<ul class="chat-thread" id="mydiv">	
</ul>
</div>
<div style="text-align:center;clear:both">
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
                var p = document.createElement("li");
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
            var con = RongIMClient.ConversationType.setValue("4");
            
            var mydiv = document.getElementById("mydiv");
            var p = document.createElement("li");
            p.style.marginRight = "10px";
            p.innerHTML = c.value;
            mydiv.appendChild(p);
                
            ins.sendMessage(con, to.value, RongIMClient.TextMessage.obtain(c.value || Date.now()), null, {
                onSuccess: function () {
                    console.log("send successfully");                    
                }, onError: function () {
                    console.log("send fail")
                }
            });
        }
    };    
</script>
</body>
</html>
