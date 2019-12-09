function initLabel(id,sign) {
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
            children : null
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