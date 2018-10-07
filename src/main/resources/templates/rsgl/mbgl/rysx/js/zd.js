var zjid = "root"; // 定义全局变量zjid

$("#zjTreeBody").height($(window).height() - 106);
var treeObj;
var treeParam;
var lastNodeId;

$(function () {
    // 职级树
    zjTree();
})

// 职级信息树
function zjTree() {
    //初始化树
    treeObj = $.fn.zTree.init($("#zjTree"), {
        async: {    //ztree异步请求数据
            enable: true,
            url: ctxPath + "zj/getZjTree",//请求action方法
            autoparam: ["id"],
            otherParam: function () {
                return treeParam;
            },
        },
        data: {
            simpleData: {
                enable: true,//如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId，并且让数据满足父子关系。
                idKey: "zjid",
                pIdKey: "",
                rootPId: ""
            },
            key: {
                name: "zjmc",
                url: ""
            }
        },
        view: {
            showLine: true,//显示连接线
            showIcon: false//显示节点图片
            //fontCss: {color:"red"}
            //fontCss: setFontCss//节点颜色
        },
        callback: {
            //beforeClick: zTreeBeforeClick,
            onAsyncSuccess: function () {
                treeObj.expandAll(true);
                if (lastNodeId) {
                    var lastNode = treeObj.getNodeByParam("zjid", lastNodeId, null);
                    treeObj.selectNode(lastNode);
                } else {
                    treeObj.selectNode(treeObj.getNodes()[0]);
                }
            },
            onClick: function (event, treeId, treeNode) {
                lastNodeId = treeNode.zjid;
                $("#zdTable").bootstrapTable('refresh', {
                    query: {
                        zjid: lastNodeId
                    }
                });
            }
        }
    });
}

// 职务分页数据
function zdDataPage(zjid) {
    var t = $("#zdTable").bootstrapTable({
        url: ctxPath + 'zd/getZdPage',
        method: 'get',
        dataType: "json",
        striped: true,//设置为 true 会有隔行变色效果
        pagination: true, //分页
        pageSize: 10,//如果设置了分页，页面数据条数
        search: false, //显示搜索框
        toolbar: '#zd_toolbar',
        sidePagination: "server", //服务端处理分页
        responseHandler: function (res) { //获取返回的数据的时候做相应处理，让bootstrap table认识我们的返回格式
            return {
                "rows": res.content, // 具体每一个bean的列表
                "total": res.totalElements  // 总共有多少条返回数据
            }
        },
        queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是分页用的
            // 职级id
            zjid = getZjid();
            return $.extend(params, {zjid: zjid});
        },
        idField: "userId",//指定主键列
        columns: [
            {
                title: '职等名称',
                field: 'zdmc',
                align: 'left',
                width: '200'
            },
            {
                title: '说明',
                field: 'sm',
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
                    return '<a href="#" mce_href="#" onclick="doZdUpdate(\'' + row.zjid + '\', \'' + row.zdid + '\')">修改</a> &nbsp;' +
                        '<a href="#" mce_href="#" onclick="doZdDelete(\'' + row.zdid + '\')">删除</a> ';
                }
            }
        ]
    });
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数
        //console.log("load success");
        $(".pull-right").css("display", "block");
    });
}

// 获取职级id
function getZjid() {
    if (treeObj) {
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length == 1) {
            var node = nodes[0];
            zjid = node.zjid;
        }
    }
    return zjid;
}

//添加
function doZdAdd() {
    // 职级id
    getZjid();

    var index = layer.open({
        title: '添加职等',
        area: ['80%', '80%'],
        type: 2,
        content: ctxPath + "zd/editUI/" + zjid + "/null"
    });
}

//修改
function doZdUpdate(zjid, zdid) {
    var index = layer.open({
        title: '修改职等信息',
        area: ['80%', '80%'],
        type: 2,
        content: ctxPath + "zd/editUI/" + zjid + "/" + zdid
    });
}

//删除
function doZdDelete(zdid) {
    // 职级id
    getZjid();

    layer.confirm('确定删除?', {icon: 3, title: '提示'}, function (index_confirm) {
        // 打开遮罩
        var index_load = layer.load({shade: [1, '#000']});
        $.ajax({
            type: "post",
            url: ctxPath + "zd/doDelete/" + zdid,
            success: function (result) {
                layer.close(index_load);//关闭遮罩
                if (result) {
                    layer.msg("删除成功.");
                    //刷新table
                    $("#zdTable").bootstrapTable('refresh', {
                        query: {
                            zjid: zjid
                        }
                    });
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