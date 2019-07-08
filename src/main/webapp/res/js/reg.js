$(document).ready(function() {
    let $url = 'http://localhost:8080';
    layui.use(['layer', 'form', 'laydate'], function() {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        form.on('submit(regBtu)', function(data) {
            layer.msg('正在入驻请稍等...', {
                time: 0,
                icon: 16,
                shade: [0.3, '#393D49']
            });
            $.ajax({
                url: $url + '/member.action?methodName=reg',
                method: 'post',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data.field),
                dataType: 'JSON',
                success: function(res) {
                    switch (res.regStatus) {
                        case 1:
                            layer.msg('恭喜成功入驻课工场', {
                                icon: 1
                            });
                            let $member = res.member;
                            sessionStorage.setItem("kgc_member", JSON.stringify($member));
                            // localStorages.setItem('kgc_member', res.member);
                            location.replace('activate.html');
                            break;
                        case 0:
                            layer.msg('好可惜未能成功入驻课工场，请再试一次', {
                                icon: 5
                            });
                            break;

                        default:
                            break;
                    }
                },
            })
            return false;
        });
    });
});