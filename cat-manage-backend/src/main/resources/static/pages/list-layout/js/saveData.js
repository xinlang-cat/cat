
function saveBasics() {
    var formdata = $("#form1").serializeObject();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/basic',
        contentType: "application/json; charset=utf-8",
        async: false,
        data: JSON.stringify(formdata),
        success: function (data) {
            $("input[name='item_id']").val(data.id);
        }
    });
}

function addContent() {
    var formdata = $("#form2").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/content/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
        }
    });
}
var targets;
function addTarget1() {//数量指标
    var formdata = $("#form3").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/target/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
            targets=data;
        }
    });
}
function addTarget2() {//其他指标
    var formdata = $("#form4").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/target/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
        }
    });
}
function addPersonnel() {
    var formdata = $("#form5").serializeArrayObj();
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
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/user/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(formdata),
        success: function (data) {
        }
    });
}