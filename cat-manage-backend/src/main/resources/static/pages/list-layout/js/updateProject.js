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
function updateTarget() {
    var formdata = $("#form3").serializeJson();
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/target/multi',
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
function updatePersonnel() {
    var formdata = $("#form4").serializeArrayObj();
    $(formdata).each(function(){
        var ids = [];
        var targetIds = this.targetIds.split(',');
        $(targetIds).each(function(){
            var target = this;
            $(targets).each(function(){
                if(target==this.target){
                    ids.push(this.id);
                }
            });
        });
        this.targetIds=ids;
    });
    if(formdata.length>1){
        formdata = JSON.stringify(formdata)
    }else {
        formdata = "[" + JSON.stringify(formdata) + "]"
    }
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/user/multi',
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
function updateFund() {
    var formdata = $("#form5").serializeJson();
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/fund/multi',
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
function updateContacts() {
    var formdata = $("#form6").serializeJson();
    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/company/multi',
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