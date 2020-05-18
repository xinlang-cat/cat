// function initTreeLabel(id, sign) {
//     $.ajax({
//         type: 'get',
//         url: domainName + '/api-label/label/tree/' + sign,
//         contentType: "application/json; charset=utf-8",
//         success: function (res) {
//             tree.render({
//                 elem: id
//                 , data: convert(res)
//                 , showCheckbox: true  //是否显示复选框
//                 , onlyIconControl: true
//                 , click: function (obj) {
//                     var data = obj.data;  //获取当前点击的节点数据
//                     layer.msg('状态：' + obj.state + '<br>节点数据：' + JSON.stringify(data));
//                 }
//             });
//         }
//     });
// }
//
// function convert(res) {
//     var data = [];
//     $.each(res, function (i, item) {
//         var data1 = {
//             id: item.id,
//             title: item.content,
//             children: null,
//             disabled: true
//         };
//         if (item.child.length > 0) {
//             setProperty(data1, item.child);
//         }
//         data[i] = data1;
//     })
//     return data;
// }
//
// function setProperty(data, children1) {
//     var arr = [];
//     $.each(children1, function (i, item) {
//         var d = {
//             id: item.id,
//             title: item.content,
//             children: null
//         };
//         if (item.child.length > 0) {
//             setProperty(arr, item.child, i);
//         }
//         arr[i] = d;
//     })
//     data.children = arr;
// }
//
// //#################################################################################################################################################################################################################################################################
// var layedit, index, layer, tree, util, laydate, form;
// layui.use(['tree', 'layedit', 'upload', 'layer', 'util', 'laydate'], function () {
//     layedit = layui.layedit;
//     layer = layui.layer;
//     laydate = layui.laydate;
//     tree = layui.tree;
//     util = layui.util;
//     form = layui.form;
//
// });
//
// function back() {
//     layer.close(index);
// }
//
// function choose(line) {
//     var sign = "INDUSTRY_GROUP";
//     var element = '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="labelList"><form class="layui-form" onsubmit="return false" ' +
//         'id="LabelForm"><div class="layui-form-item"><div class="layui-input-block" id="label">';
//     $.ajax({
//         type: 'get',
//         url: domainName + '/api-label/label/tree/' + sign,
//         async: false,
//         success: function (data) {
//             $.each(data[0].child, function (i, item) {
//                 element += ' <input type="radio" name="' + line + '" value="' + item.sign + '" title="' + item.content + '" id="' + item.id + '">'
//
//             });
//             element += '</div></div><div class="layui-form-item"><div class="layui-input-block"><button class="layui-btn" ' +
//                 'onclick="back()">返回</button><button class="layui-btn" onclick="SaveLabel(' + line + ')">保存</button></div></div></form></div>';
//             index = layer.open({
//                 title: "请选择",
//                 type: 1,
//                 area: ['800px', '400px'],
//                 maxmin: true,
//                 shadeClose: true,
//                 content: element
//             });
//             form.render('radio');
//
//         }
//     });
// }
//
// function SaveLabel(name) {
//     var res = $("input[type='radio']:checked").val();
//     var text = $("input[type='radio']:checked").attr('title');
//     var title = "type" + name;
//     var value = "labelSign" + name;
//     $("#" + title).val(text);
//     $("#" + value).val(res);
//     layer.msg("成功", {shift: -1, time: 1000}, function () {
//         layer.close(index);
//     });
//  }
// var sUserAgent = navigator.userAgent.toLowerCase();
// var isPC = true;
// if (/ipad|iphone|midp|rv:1.2.3.4|ucweb|android|windows ce|windows mobile/.test(sUserAgent)) {
//     isPC = false;
// }

$(function () {
    var link = document.createElement("link");
    link.rel = "stylesheet";
    link.type = "text/css";
    link.href = "//at.alicdn.com/t/font_1682849_3q8aaf8z15b.css";
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(link);

    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/INDUSTRY_GROUP',
        async: false,
        success: function (data) {
            var str = '<div id="INDUSTRY_GROUP" class=\'weui-popup__container\'>\n' +
                '    <div class="weui-popup__overlay"></div>\n' +
                '    <div class="weui-popup__modal" style="background-color: white;">\n' +
                '        <header class=\'demos-header\'>\n' +
                '            <h2 class="demos-second-title">匹配专家</h2>\n' +
                '            <p class="demos-sub-title">一名专家可能在多个产业团中,最后一次选择的产业团有效。</p>' +
                '        </header>\n' +
                '        <div class="weui-interval"></div>';
            $.each(data[0].child, function (i, item) {
                str += '<div>\n' +
                    '       <div class="weui-cell INDUSTRY_TITLE" data-sign="' + item.sign + '">\n' +
                    '            <div class="weui-cell__hd">\n' +
                    '                <label class="weui-centre-title">' + item.content + '</label>\n' +
                    '            </div>\n' +
                    '       <div class="weui-cell__bd">\n' +
                    '            <a href="javascript:;"\n' +
                    '                  style="float: right;width: 40px;"><i class="iconfont icon-jiantou-7"></i></a>\n' +
                    '       </div>\n' +
                    '       </div>\n' +
                    '       <div class="userList">\n' +
                    '             <div class="weui-line"></div>\n' +
                    '             <div class="weui-cells weui-cells_checkbox">\n' +
                    '             </div>\n' +
                    '        </div>\n' +
                    '        <div class="weui-interval"></div>\n' +
                    '    </div>';
            });
            str += '<a href="javascript:;" style="margin:40px 30px;" class="weui-btn weui-btn_primary" onclick="saveExpertAndItem()">保存</a></div></div>';
            $('body').append(str);
            $.each($('.userList'), function (i, item) {
                $(item).hide();
            });
        }
    });
});


$(document).on('click', '.INDUSTRY_TITLE', function (e) {
    var icon = $(this).find('i');
    var userDiv = $(this).next('div');
    var inputArr = $(userDiv).find('input');
    if (inputArr.length <= 0) {
        var sign = $(this).data('sign');
        $.get(domainName + '/project-user/item/assign/expert/' + sign, function (res) {
            var str = '';
            $.each(res[sign], function (i, item) {
                str +=
                    '<label class="weui-cell weui-check__label">\n' +
                    '    <div class="weui-cell__hd">\n' +
                    '         <input type="radio" class="weui-check" name="' + item.userId + '" value="' + item.userType + '">\n' +
                    '         <i class="weui-icon-checked"></i>\n' +
                    '    </div>\n' +
                    '    <div class="weui-cell__bd">\n' +
                    '         <p>' + item.name + '</p>\n' +
                    '         <ul class="weui-media-box__info">\n' +
                    '             <li class="weui-media-box__info__meta">' + item.phone + '</li>\n' +
                    '             <li class="weui-media-box__info__meta">' + item.deptName + '</li>\n' +
                    '         </ul>\n' +
                    '     </div>\n' +
                    '</label>';
            });
            var checkboxDiv = $(userDiv).find('div.weui-cells_checkbox:last');
            $(checkboxDiv).append(str);
        });
    }
    if (userDiv.is(':hidden')) {
        $(userDiv).show();
        $(icon).attr('class', 'iconfont icon-jiantou-6');
    } else {
        $(userDiv).hide();
        $(icon).attr('class', 'iconfont icon-jiantou-7');
    }
});
var itemId = null;
var defaultSave = true;
var param = null;
$.matchExpert = function (params) {
    param = params;
    var elem = params.elem;
    itemId = params.itemId;
    defaultSave = params.defaultSave;
    var elemClass = $(elem).attr('class');
    $(elem).attr('class', elemClass + ' open-popup');
    $(elem).data('target', '#INDUSTRY_GROUP');
};

function saveExpertAndItem() {
    var divs = $('.INDUSTRY_TITLE');
    var data = new Array();
    $.each(divs,function (i,item) {
        var itemUser = {
            labelSign : $(item).data('sign')
        };
        var inputs = $($(item).next('div')).find('input:checked');
        if(inputs.length>0){
            $.each(inputs,function (i,it) {
                itemUser['userId'] = $(it).attr('name');
                itemUser['userType'] = $(it).val();
                itemUser['itemId'] = itemId;
            });
            data.push(itemUser);
        }
    });
    console.log(data);
    if (defaultSave) {
        $.ajax({
            type: 'post',
            url: domainName + '/project-user/item/list',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (res) {
                $.toast('操作成功');
                $.closePopup();
            },
            error: function (res) {
                $.toast('操作失败', 'forbidden');

            }
        });
    }else {
        param.defaultSaveFun(data);
        $.closePopup();
    }

}






