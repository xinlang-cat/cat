function getbasic(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/information/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $("#contract_no").text(d.contract_no);
            $("#item_name").text(d.name);
            $("#item_number").text(d.item_number);
            $("#start_date").text(d.start_date.substring(0, 10));
            $("#end_date").text(d.end_date.substring(0, 10));
            $("#outline").text(d.outline);
            $(".responsible_unit").text(d.responsible_unit);
            getCompanyInfo(d.responsible_unit);
            $(".management_unit").text(d.management_unit);
            $("#entrusting_party").text(d.entrusting_party);
            $("#entrusting_company").val(d.entrusting_party);
            $("#management_company").val(d.management_unit);

        }
    })
}
function getCompanyInfo(name) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/name/'+name,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#phone").text(data.principalPhone);
            $("#site").text(data.address);
            $("#linkman").text(data.principal);
        }
    });
}
/*function getCompanyInfo(id) {
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
}*/
function getItem_user(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/personnel/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            var str = '';

            var count = 1;
            $(data).each(function () {
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

                str += '<tr>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + count + '</td>' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.name + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.sex + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.age + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.professional_title + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.specialty + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' + this.organization + '</td>\n' +
                    '<td style="font-size: 14px;font-weight: bolder;">' +  this.responsibilities + '</td>' +
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
            if (d){
                if(d.count!=undefined){
                    name = getLablename(d.target);
                }else {
                    name= d.content;
                }
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
    var count = 0;
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fundSource/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            for(var key in data[0]){
                var date= data[0][key];
                if(key != 'id' && key != 'item_id'&& key != 'responsible_unit'){
                    $('#'+key).val(date);
                    count +=  parseInt(date);
                }
            }
            $("#countMoney").val(count);

        }
    })
}

