$(document).ready(function () {
    let $url = sessionStorage.getItem('$url')
    let $user = sessionStorage.getItem('user')
    let user = JSON.parse($user)
    if (user != null) {
        $.ajax({
            url: $url + '/front/user.action?methodName=getMenusByUserAccount',
            method: 'post',
            data: { 'account': user.account },
            dataType: 'JSON',
            success: function (rs) {
                console.log(rs)
                switch (rs.getMenusByUserAccountStatus) {
                    case "getMenusByUserAccountSuccess":
                        layer.load(2);
                        let $menus = rs.menus;
                        $.each($menus,function(index,element){
                            $('#menutree').append(element.menuName + '<br />');
                        })
                        //此处演示关闭
                        setTimeout(function () {
                            layer.closeAll('loading');
                        }, 2000);

                        break;
                    case "getMenusByUserAccountFailed":
                        layer.msg('没有查询到数据', {
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
    } else {
        logut()
    }
})