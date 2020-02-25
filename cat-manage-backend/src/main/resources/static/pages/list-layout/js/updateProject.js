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
    var formdata = $("#form4").serializeJson();
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
    var formdata = $("#form3").serializeJson();
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

    var formdata = $("#form5").serializeArrayObj();
    var remarks = $('.remark');

    $(formdata).each(function () {

        if (this.subject == 'INSTALLATION_COST') {
            this.remark = $(remarks[0]).val()
        } else if (this.subject == 'MATERIALS_EXPENSES') {
            this.remark = $(remarks[1]).val()
        } else if (this.subject == 'COST_OF_TESTING_AND_PROCESSING') {
            this.remark = $(remarks[2]).val()
        } else if (this.subject == 'FUEL_AND_POWER_CHARGRS') {
            this.remark = $(remarks[3]).val()
        } else if (this.subject == 'TRAVEL_ON_BUSINESS') {
            this.remark = $(remarks[4]).val()
        } else if (this.subject == 'PUBLISH') {
            this.remark = $(remarks[5]).val()
        } else if (this.subject == 'SERVICE_FEE') {
            this.remark = $(remarks[6]).val()
        } else if (this.subject == 'EXPERT_CONSULTATION_FEE') {
            this.remark = $(remarks[7]).val()
        } else if (this.subject == 'OTHER_EXPENSES') {
            this.remark = $(remarks[8]).val()
        } else if (this.subject == 'PERFORMANCE_OF_THE_SPENDING') {
            this.remark = $(remarks[9]).val()
        } else if (this.subject == 'OTHER_CHARGES') {
            this.remark = $(remarks[10]).val()
        }
    })

    $.ajax({
        type: 'put',
        url: domainName + '/project-item/item/fund/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(formdata),
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