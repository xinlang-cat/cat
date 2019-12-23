function updateBasics() {
    var formdata = $("#form1").serializeObject();
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/basic',
        contentType: "application/json; charset=utf-8",
        async: false,
        data: JSON.stringify(formdata),
        success: function (data) {
            layer.msg("成功", {shift: -1, time: 1000}, function () {
                window.location.reload();
            });
        }
    });
}
function updateContent() {
    var formdata = $("#form2").serializeJson();
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/content/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
            layer.msg("成功", {shift: -1, time: 1000}, function () {
                window.location.reload();
            });
        }
    });
}