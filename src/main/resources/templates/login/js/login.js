$(function () {
    //将登录页面跳转到主界面
    if (top != window) {
        top.location.href = window.location.href;
    }

    //表单验证
    $("#login_form").bootstrapValidator({
        live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '通用的验证失败消息',//好像从来没出现过
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    }
                }
            },
            userPwd: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {//点击提交之后
        // 阻止表单提交
        e.preventDefault();
        // 打开遮罩
        var index_load = layer.load({shade: [1, '#000']});
        // ajax提交
        $.ajax({
            type: "post",
            url: "/doLogin",
            data: {
                userName: $("#userName").val(),
                userPwd: md5($("#userPwd").val())
            },
            success: function (result) {
                //关闭遮罩
                layer.close(index_load);
                if (result.code == "00") {
                    //登录成功，跳转页面
                    window.location.replace("/main");
                } else {
                    layer.msg(result.msg);
                }
            },
            error: function (xhr, status, error) {
                //关闭遮罩
                layer.close(index_load);
                layer.msg("登录失败，请稍后重试！");
            }
        });
    });
});