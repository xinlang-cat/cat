function getbasic(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/basic/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $("#contract_no").text(d.contract_no);
            $("#item_name").text(d.item_name);
            $("#item_number").text(d.item_number);
            $("#start_date").text(d.start_date.substring(0, 10));
            $("#end_date").text(d.end_date.substring(0, 10));
            $("#outline").text(d.outline);
            getDept(d.undertaker, $(".undertaker"));
            getDept(d.administrator, $("#administrator"));
        }
    })
}
/*解析部门名称*/
function getDept(sign, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/' + sign,
        async: false,
        success: function (data) {
            node.text(data.signName);
        }
    });
}
function getCompanyInfo(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/company/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            $(data).each(function () {
                if (this.type == 1) {
                    $("#phone").text(this.phone);
                    $("#postal_code").text(this.postal_code);
                    $("#site").text(this.site);
                    $("#email").text(this.email);
                    $("#fax").text(this.fax);
                    $("#linkman").text(this.linkman);
                }
             /*   if(this.type==2){
                    var name=this.linkman;
                    if(name==datas){
                        $("#souweituo").show();
                    }
                }
                if(this.type==0){
                    var name=this.linkman;
                    if(name==datas){
                        $("#zizhiqu").show();
                    }
                }
*/
            })
        }
    })
}
function getItem_user(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/user/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            var str = '';
            var count = 1;
            $(data).each(function () {
                var names = [];
                $(this.targetIds).each(function () {
                    var name = getResponsibilityName(this);
                    names.push(name.content);
                })
                var userInfo = getUserInfo(this.itemUser.user_id);
                var sex;
                if (userInfo.sex == 0) {
                    sex = '女';
                } else {
                    sex = '男';
                }
                str += '<tr>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + count + '</td>' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.name + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + sex + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.birthDate.substring(0, 10) + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.academicTitle + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.academicDiplomas + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.deptName + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + names + '</td>' +
                    '</tr>'
                count++;
            })
            $("#userinfo").append(str);
        }
    })
}
function getUserInfo(id) {
    var userinfo;
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user-anon/' + id,
        async: false,
        success: function (data) {
            userinfo = data[0];
        }
    })
    return userinfo;
}

function getResponsibilityName(id) {
    var name;
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/list',
        data: "id=" + id,
        async: false,
        success: function (data) {
            var d = data[0];
            name = getLablename(d.target);
        }
    })
    return name;
}

function getLablename(sign) {
    var name;
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            name = data[0];
        }
    })
    return name;
}

function getUserId() {
    var datas;
    $.ajax({
        type: 'get',
        url: domainName + '/api-u/users/current',
        async: false,
        success: function (data) {
            datas=data.id
            $("#createUserId").val(data.id);
        }
    })
    return datas;
}
