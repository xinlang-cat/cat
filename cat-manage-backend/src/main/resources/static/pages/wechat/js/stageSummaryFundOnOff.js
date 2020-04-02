$('.on-off').click(function () {
    var icon = $(this).find('i');
    var div = $(this).next('div');
    if (div.is(':hidden')) {
        $(div).show();
        $(icon).attr('class', 'iconfont icon-jiantou-6');
    } else {
        $(div).hide();
        $(icon).attr('class', 'iconfont icon-jiantou-7');
    }
});

$.each($('.on-off'), function (i, item) {
    $($(item).next('div')).hide();
});

function set_on_Off(obj) {
    var icon = $(obj).find('i');
    var div = $(obj).next('div');
    if (div.is(':hidden')) {
        $(div).show();
        $(icon).attr('class', 'iconfont icon-jiantou-6');
    } else {
        $(div).hide();
        $(icon).attr('class', 'iconfont icon-jiantou-7');
    }
}