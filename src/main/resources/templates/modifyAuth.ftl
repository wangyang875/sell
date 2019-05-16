<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<div>
    <#--<form>-->
        <form name="" role="form" action="/sell/seller/ModifyPassword" method="post">
                        邮箱名<input type="text" name="email" id="em"/>
                        <button type="button" class="btn btn-default btn-danger" onclick="postEmail()"><a href="javascript:void(0)">发送验证码</a></button>
                        验证码<input type="text" name="authCode"/>
                        新密码<input type="text" name="newPassword">
            <button type="submit" class="btn btn-default btn-success">确定</button>
        </form>
    <#--</form>-->
</div>
<script>
    function postEmail() {
        var email = document.getElementById('em').value;
        var url="/sell/seller/sendAuthcode?email="+email;
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (data) {

                alert("post success!");

            },

            error: function () {
                // alert("post failure!");

            }
        })
    }
</script>
</body>
</html>