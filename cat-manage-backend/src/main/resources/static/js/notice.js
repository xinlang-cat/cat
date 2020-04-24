layui.use(['layer', 'util', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var h = $(window).height() - 15;
    var w = $(window).width() - 30;
    var pageUrl = window.location.pathname;
    var startIndex = pageUrl.lastIndexOf("/") + 1;
    var endIndex = pageUrl.indexOf(".");
    var pageName = pageUrl.substring(startIndex, endIndex);
    $.get(domainName + "/api-n/notice/list", {pageName: pageName}, function (res) {
        if (!jQuery.isEmptyObject(res[0])) {
            var noticeHtml = "<div class=\"layui-col-md12\">\n" +
                "      <div class=\"layui-card\">\n" +
                "        <div class=\"layui-card-header\">" + res[0].title + "</div>\n" +
                "        <div class=\"layui-card-body\">\n" +
                "          " + res[0].content + "" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>";
            layer.open({
                title: "公告",
                type: 1,
                area: [w + 'px', h + 'px'],
                maxmin: true,
                shadeClose: true,
                content: noticeHtml
            });
        }
    });
});


