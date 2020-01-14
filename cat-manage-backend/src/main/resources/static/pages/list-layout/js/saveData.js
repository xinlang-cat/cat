
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

function saveContent() {
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
function saveTarget() {
    var formdata = $("#form4").serializeJson();
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
function savePersonnel() {
    var formdata = $("#form3").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/user/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
        }
    });
}

function saveFund() {
    var formdata = $("#form5").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/fund/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
        }
    });
}
function saveContact() {
    var formdata = $("#form6").serializeJson();
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/company/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: formdata,
        success: function (data) {
        }
    });
}