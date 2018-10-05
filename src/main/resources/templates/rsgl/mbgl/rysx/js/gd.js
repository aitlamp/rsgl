$(function () {

})

// 职务分页数据
function gdDataPage(gwdjid) {
    var t = $("#gdTable").bootstrapTable({
        url: ctxPath + 'gd/getGdPage',
        method: 'get',
        dataType: "json",
        striped: true,//设置为 true 会有隔行变色效果
        pagination: true, //分页
        pageSize: 10,//如果设置了分页，页面数据条数
        search: false, //显示搜索框
        toolbar: '#gd_toolbar',
        sidePagination: "server", //服务端处理分页
        responseHandler: function (res) { //获取返回的数据的时候做相应处理，让bootstrap table认识我们的返回格式
            return {
                "rows": res.content, // 具体每一个bean的列表
                "total": res.totalElements  // 总共有多少条返回数据
            }
        },
        queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是分页用的
            return {//这里的params是table提供的
                offset: params.offset,  //从数据库第几条记录开始
                limit: params.limit, //找多少条
                gwdjid: gwdjid
            };
        },
        idField: "userId",//指定主键列
        columns: [
            {
                title: '挡位名称',
                field: 'dwmc',
                align: 'left'
            },
            {
                title: '显示顺序',
                field: 'pwsx',
                align: 'center',
                width: '50'
            },
            {
                title: '状态',
                field: 'dqzt',
                align: 'center',
                width: '100'
            },
            {
                title: '操作',
                field: 'lbid',
                width: '120',
                align: 'center',
                formatter: function (value, row, index) {//自定义显示可以写标签哦~
                    return '<a href="#" mce_href="#" onclick="doGdUpdate(\'' + row.gwdjid + '\', \'' + row.dwid + '\')">修改</a> &nbsp;' +
                        '<a href="#" mce_href="#" onclick="doGdDelete(\'' + row.dwid + '\')">删除</a> ';
                }
            }
        ]
    });
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数
        //console.log("load success");
        $(".pull-right").css("display", "block");
    });
}

//添加
function doGdAdd() {
    var gjinfo = $("#gjTable").bootstrapTable("getSelections");

    var index = layer.open({
        title: '添加职等',
        area: ['80%', '80%'],
        type: 2,
        content: ctxPath + "gd/editUI/" + gjinfo[0].gwdjid + "/null"
    });
}

//修改
function doGdUpdate(gwdjid, gdid) {
    var index = layer.open({
        title: '修改职等信息',
        area: ['80%', '80%'],
        type: 2,
        content: ctxPath + "gd/editUI/" + gwdjid + "/" + gdid
    });
}

//删除
function doGdDelete(gdid) {
    layer.confirm('确定删除?', {icon: 3, title: '提示'}, function (index_confirm) {
        // 打开遮罩
        var index_load = layer.load({shade: [1, '#000']});
        $.ajax({
            type: "post",
            url: ctxPath + "gd/doDelete/" + gdid,
            success: function (result) {
                layer.close(index_load);//关闭遮罩
                if (result) {
                    layer.msg("删除成功.");
                    $("#gdTable").bootstrapTable('refresh');//刷新table
                } else {
                    layer.msg("删除失败，请与管理员联系！");
                }
            },
            error: function (xhr, status, error) {
                layer.close(index_load);//关闭遮罩
                layer.msg("保存失败，请稍后重试！");
            }
        });
        layer.close(index_confirm);
    });
}