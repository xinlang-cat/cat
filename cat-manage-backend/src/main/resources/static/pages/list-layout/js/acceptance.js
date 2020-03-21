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
             var datas = getUserId();
               if (this.type == 1) {
                   var name=this.linkman;
                      if (name == datas) {
                          $("#manageDeptOpinion").attr("readonly", true);
                          $("#burgLeaderOpinion").attr("readonly", true);
                          $("#burgFinanceOpinion").attr("readonly", true);
                      }
                    $("#phone").text(this.phone);
                    $("#postal_code").text(this.postal_code);
                    $("#site").text(this.site);
                    $("#email").text(this.email);
                    $("#fax").text(this.fax);
                    $("#linkman").text(this.linkman);
                }
            if(this.type==2){
                       var name=this.linkman;
                       if(name==datas){
                           $("#burgLeaderOpinion").attr("readonly", true);
                           $("#burgFinanceOpinion").attr("readonly", true);
                           $("#applicationDeptOpinion").attr("readonly", true);
                           $("#applicationDate").attr("readonly", true);
                           $("#suggestDate").attr("readonly", true);
                           $("#cean").attr("readonly", true);
                           $("#katalog").attr("readonly", true);
                       }
                   }
                  if(this.type==0){
                             var name=this.linkman;
                             if(name==datas){
                                 $("#applicationDeptOpinion").attr("readonly", true);
                                 $("#manageDeptOpinion").attr("readonly", true);
                                 $("#applicationDate").attr("readonly", true);
                                 $("#suggestDate").attr("readonly", true);
                                 $("#cean").attr("readonly", true);
                                 $("#katalog").attr("readonly", true);
                             }
                         }
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
            var countMan = 0;

            var str = '';

            var count = 1;
            $(data).each(function () {
                var names = [];
                $(this.targetIds).each(function () {
                    var name = getResponsibilityName(this);
                    names.push(name);
                })
                var userInfo = getUserInfo(this.user_id);
                var academicTitleRank;
                var degree;
                academicTitleRank = userInfo.academicTitleRank;
                degree= userInfo.degree;

                if(academicTitleRank=="高级"){
                    advanced++;
                }else if (academicTitleRank=="中级"){
                    middle++;
                }else if (academicTitleRank=="初级"){
                    elementary++;
                }
                if(degree=="博士"){
                    doctor++;
                }else if (degree=="硕士"){
                    postgraduate++;
                }else if (degree=="学士"){
                    bachelor++;
                }

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
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.academicTitleRank + userInfo.academicTitle + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.academicDiplomas + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + userInfo.deptName + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + names + '</td>' +
                    '</tr>'
                count++;



            })
            count--;
            $("#countMan").val(count);
            $("#doctor").val(doctor);
            $("#middle").val(middle);
            $("#elementary").val(elementary);
            $("#advanced").val(advanced);
            $("#postgraduate").val(postgraduate);
            $("#bachelor").val(bachelor);

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
            if(d.count!=undefined){
                name = getLablename(d.target);
            }else {
                name= d.content;
            }

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
            name = data[0].content;
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
            datas = data.username;
            $("#createUserId").val(data.id);
        }
    })
    return datas;
}
function getFund(id) {
    var valued=  0;
    var value = 0;
    var count = 0;
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fund/list',
        data: "item_id=" + id,
        async: false,

        success: function (data) {
            $(data).each(function () {
                var name = this.source;
                value = this.money;

                valued=  parseInt($('#'+name).val());
                count = count+parseInt(value);
                if(!valued){
                    $('#'+name).val(value);
                }else {
                    $('#'+name).val(valued+parseInt(value));

                }

            })
            $("#countMoney").val(count);
        }
    })
}

