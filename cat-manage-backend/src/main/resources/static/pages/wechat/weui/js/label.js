$("input[data-type=radio],input[data-type=checkbox]").click(function () {
    var property = $(this).attr('id');
    var type = $(this).data("type");
    var inputs = $('.selectHtml').find('input');
    $.each(inputs, function (i, item) {
        $(item).attr('type', type);
    });
    $($('#select').find('a:last')).remove();
    $('.selectHtml').after('<a href="javascript:;" style="margin:40px 30px;" class="weui-btn weui-btn_primary" onclick="closeSelect(\'' + property + '\')">确定</a>');
    $('#select').popup();
});

var labels = null;

$.ajax({
    type: 'get',
    url: domainName + '/api-label/label/tree/INDUSTRY_GROUP',
    async: false,
    success: function (data) {
        labels = data[0].child;
        var str = '<div id="select" class=\'weui-popup__container popup-bottom\'>';
        str += '<div class="weui-popup__overlay"></div>';
        str += '<div class="weui-popup__modal" style="background-color: white;">';
        str += '<div class="weui-cells weui-cells_checkbox selectHtml">';
        $.each(data[0].child, function (i, item) {
            str += '<label class="weui-cell weui-check__label " style="float: left;"><div class="weui-cell__hd">';
            str += '<input type="radio" class="weui-check" name="checkbox5" value="' + item.sign + '" title="' + item.content + '">';
            str += '<i class="weui-icon-checked"></i></div><div class="weui-cell__bd"><p>' + item.content + '</p></div></label>';
        });
        str +='</div></div></div>';
        $('body').append(str);
    }
});

function getLabelContent(property,signs){
    var params = signs.split(',');
    var arr = new Array();
    $.each(labels, function (i, item) {
        $.each(params,function (i,param) {
            if(item.sign == param){
                arr.push(item.content);
            }
        });
    });
    $('#'+property).val(arr.join(','));
    $('input[name='+property+']').val(signs);
}

function closeSelect(property) {
    var inputs = $('.selectHtml').find('input[name=checkbox5]:checked');
    var arr = new Array();
    var arr2 = new Array();
    $.each(inputs, function (i, item) {
        arr.push($(item).val());
        arr2.push($(item).attr('title'));
    });
    $('#' + property).val(arr2.join(','));
    $('input[name=' + property + ']').val(arr.join(','));
    $.closePopup();
}
