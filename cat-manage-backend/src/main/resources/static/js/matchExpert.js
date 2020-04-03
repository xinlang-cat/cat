

layui.use(['element', 'layer', 'form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    element.on('collapse(test)', function (data) {
        if (data.show) {
            var userDiv = $(this).next('div');
            var inputArr = $(userDiv).find('input');
            if (inputArr.length <= 0) {
                var sign = $(this).data('sign');
                $.get(domainName + '/project-user/item/assign/expert/' + sign, function (res) {
                    var str = '';
                    $.each(res[sign], function (i, item) {
                        str +=
                            '<div class="layui-form-item">\n' +
                            '    <input name="' + item.userId + '" value="' + item.name + '" title="' + item.name + '   |   ' + item.phone + '   |   ' + item.deptName + '" type="radio">' +
                            '</div>';
                    });
                    $(userDiv).append(str);
                    form.render('radio');
                });
            }
        }
    });
});


$.matchExpert = function (params) {
    var str = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/INDUSTRY_GROUP',
        async: false,
        success: function (data) {
            str += '<form class="layui-form" onsubmit="return false"><div class="layui-collapse" lay-filter="test" style="margin: 30px;">';
            $.each(data[0].child, function (i, item) {
                str += '<div class="layui-colla-item"><h2 class="layui-colla-title" data-sign="' + item.sign + '">' + item.content + '</h2>' +
                    '<div class="layui-colla-content"></div></div>';
            });
            str += '</div><div class="form-actions"><div class="row" align="center"><div class="col-md-12">' +
                '<button class="btn btn-primary" type="submit" id="SAVE_INDUSTRY_TITLE"><i class="fa fa-save"></i> 保存</button></div></div></div></form>';
        }
    });
    $(params.elem).click(function () {
        layer.open({
            title: "匹配专家",
            type: 1,
            area: [w + 'px', h + 'px'],
            maxmin: true,
            shadeClose: true,
            content: str,
        });
        layui.element.init();
    });

    $('body').on('click','#SAVE_INDUSTRY_TITLE',function () {
        var arr = new Array();
        $.each($('div.layui-colla-item'), function (i, item) {
            var data = {
                labelSign: $($(item)).find('h2').data('sign'),
                userType: 'EXPERT'
            };
            var inputs = $($(item)).find('input:checked');
            if (inputs.length > 0) {
                $.each(inputs, function (i, it) {
                    data['userId'] = $(it).attr('name');
                    data['itemId'] = params.itemId;
                });
                arr.push(data);
            }
        });
        if (params.defaultSave) {
            $.ajax({
                type: 'post',
                url: domainName + '/project-user/item/list',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(arr),
                success: function (res) {
                    alert('操作成功')
                },
                error: function (res) {
                    alert('操作失败');
                }
            });
        } else {
            params.defaultSaveFun(arr);
        }
    });
};








