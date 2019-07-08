$(document).ready(function () {
    let $url;
    $.get("/json/serverconfig.json", function (result) {
        $url = result.protocol + result.domain + result.port + result.context;
    });
    layui.use(['layer', 'form', 'laydate'], function () {
        var layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;
        form.on('submit(loginBtu)', function (data) {
            $.ajax({
                url: $url + '/front/user.action?methodName=login',
                method: 'post',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data.field),
                dataType: 'JSON',
                success: function (rs) {
                    switch (rs.loginStatus) {
                        case "loginSuccess":
                            layer.msg('登录成功', {
                                icon: 1
                            });
                            let user = rs.user
                            sessionStorage.setItem("user", JSON.stringify(user))
                            sessionStorage.setItem("$url", $url)
                            location.replace('/html/admin.html')
                            break;
                        case "loginFailed":
                            $('#L_pass').val('')
                            $('#L_pass').focus()
                            layer.msg('账号或者密码错误', {
                                icon: 2
                            });
                            break;
                        default:
                            break;
                    }
                },
                error: function () {
                    layer.msg('服务器走丢了...', {
                        icon: 5
                    })

                },
            })
            return false;
        });
    });
});