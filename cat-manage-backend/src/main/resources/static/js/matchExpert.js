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
var layedit ,index,layer,tree,util,laydate,form;
layui.use(['tree','layedit','upload','layer','util', 'laydate'], function(){
    layedit = layui.layedit;
    layer  = layui.layer;
    laydate = layui.laydate;
    tree = layui.tree;
    util = layui.util;
    form = layui.form;

});

function back() {
    layer.close(index);
}
function choose(line) {
    var sign ="INDUSTRY_GROUP";
    var element = '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="labelList"><form class="layui-form" onsubmit="return false" id="LabelForm"><div class="layui-form-item"><div class="layui-input-block" id="label">';
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            $.each(data[0].child, function (i, item) {
                    element += ' <input type="radio" name="' + line + '" value="' + item.sign + '" title="' + item.content + '" id="' + item.id + '">'

            });

                element += '</div></div><div class="layui-form-item"><div class="layui-input-block"><button class="layui-btn" onclick="back()">返回</button><button class="layui-btn" onclick="SaveLabel(' + line + ')">保存</button></div></div></form></div>';

            index = layer.open({
                title: "请选择",
                type: 1,
                area: ['800px', '400px'],
                maxmin: true,
                shadeClose: true,
                content: element
            });
                form.render('radio');

        }
    });

}
function SaveLabel(name) {
    var res =$("input[type='radio']:checked").val();
    var text =$("input[type='radio']:checked").attr('title');
    var title = "type"+name;
    var value="labelSign"+name;
    $("#"+title).val(text);
    $("#"+value).val(res);
    layer.msg("成功", {shift: -1, time: 1000}, function(){
        layer.close(index);
    });
}


