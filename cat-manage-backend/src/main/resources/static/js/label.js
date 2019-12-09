function initTreeLabel(id,sign) {
    $.ajax({
        type : 'get',
        url : domainName + '/api-label/label/tree/'+sign,
        contentType: "application/json; charset=utf-8",
        success : function(res) {
            tree.render({
                elem: id
                ,data: convert(res)
                ,showCheckbox: true  //是否显示复选框
                ,onlyIconControl: true
                ,click: function(obj){
                    var data = obj.data;  //获取当前点击的节点数据
                    layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
                }
            });
        }
    });
}

function convert(res){
    var data = [];
    $.each(res,function (i,item) {
        var data1 = {
            id : item.id,
            title : item.content,
            children : null,
            disabled: true
        };
        if(item.child.length>0){
            setProperty(data1,item.child);
        }
        data[i] = data1;
    })
    return data;
}

function setProperty(data,children1){
    var arr  = [];
    $.each(children1,function (i,item) {
        var d = {
            id : item.id,
            title: item.content,
            children : null
        };
        if(item.child.length>0){
            setProperty(arr,item.child,i);
        }
        arr[i] = d;
    })
    data.children = arr;
}
//#################################################################################################################################################################################################################################################################
var index = null;
layui.use(['form', 'layer'], function() {
    var form = layui.form;
    $($("div[data-sign]")).click(function () {
        var status = $(this).data("status");
        var name = $(this).attr("id");
        var sign = $(this).data("sign");
        var element = '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="labelList"><form class="layui-form" onsubmit="return false" id="LabelForm"><div class="layui-form-item"><div class="layui-input-block" id="ckb">';
        $.ajax({
            type: 'get',
            url: domainName + '/api-label/label/tree/' + sign,
            async: false,
            success: function (data) {
                $.each(data[0].child, function (i, item) {
                    element += '<input type="checkbox" name="' + name + '" id="' + item.id + '" value="' + item.sign + '" title="' + item.content + '"><div class="layui-unselect layui-form-checkbox"><span>' + item.content + '</span><i class="layui-icon layui-icon-ok"></i></div>';
                });
                if (status) {
                    element += '</div></div><div class="layui-form-item"><div class="layui-input-block"><button class="layui-btn" onclick="back()">返回</button><button class="layui-btn" onclick="' + name + 'SaveLabel()">保存</button></div></div></form></div>';
                } else {
                    element += '</div></div><div class="layui-form-item"><div class="layui-input-block"><button class="layui-btn" onclick="back()">返回</button><button class="layui-btn" onclick="' + name + 'UpdateLabel()">保存</button></div></div></form></div>';
                }
                index = layer.open({
                    title: "请选择",
                    type: 1,
                    area: ['800px', '400px'],
                    maxmin: true,
                    shadeClose: true,
                    content: element
                });
                form.render('checkbox');
            }
        });
    });
});

function back() {
    layer.close(index);
}



