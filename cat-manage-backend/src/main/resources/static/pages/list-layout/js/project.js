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
            getLableContent(d.category, $("#category"));
            getDept(d.consignor, $("#consignor"));
            getDept(d.undertaker, $("#undertaker"));
            getDept(d.administrator, $("#administrator"));
        }
    })
}

/*解析标签名称*/
function getLableContent(sign, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            var name = data[0].content;
            node.text(name);
        }
    });
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

/*获取主要内容*/
function getcontent(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/content/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var str = '';
            $(data).each(function () {
                str += '<tr><td></td>'+
                    '<td>' +
                    '<p>' + this.title + '</p>' +
                    '<span>' + this.content + '</span>' +
                    '<div class="operation">' +
                    '<div class="layui-btn-group">' +
                    '<button onclick="update_con(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                    '<i class="layui-icon">&#xe642;</i>' +
                    '</button>' +
                    '<button onclick="delete_con(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                    '<i class="layui-icon">&#xe640;</i>' +
                    '</button>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '</tr>';
            })
            $("#mian-content").append(str);
        }
    })
}

/*修改主要内容*/
function update_con(id) {
    layer.open({
        title: "修改主要内容",
        type: 2,
        area: ['750px', '450px'],
        maxin: true,
        shadeClose: true,
        content: ['update_content.html?id=' + id]
    });
}

function delete_con(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/content/' + id,
            success: function (data) {
                layer.msg("删除成功");
                location.reload();
            }
        });
        layer.close(1);
    });
}

function getTarget(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var targetTypes = getTargetTypeAll('TARGET_TYPE');//获取全部指标类型
            $(targetTypes).each(function () {
                var targetType = this;
                var str1 = '';
                var str2 = '';
                var str3 = '';
                var str4 = '';
                $(data).each(function () {
                    if (targetType.sign == this.type) {
                        var name = getLablename(this.target);
                        var text = getsuperior(this.district);
                        str1 = '<div class="module target">\n' +
                            '<table lay-even lay-skin="nob" class="layui-table">\n' +
                            '                <colgroup>\n' +
                            '                    <col width="30%">\n' +
                            '                    <col width="10%">\n' +
                            '                    <col width="20%">\n' +
                            '                    <col width="20%">\n' +
                            '                    <col width="20%">\n' +
                            '                </colgroup>\n' +
                            '                <thead>\n' +
                            '                <th class="header" colspan="5">' + targetType.content + '</th>\n' +
                            '                <tr>\n' +
                            '                    <td>内容</td>\n' +
                            '                    <td>数量</td>\n' +
                            '                    <td>时间</td>\n' +
                            '                    <td>地点</td>\n' +
                            '                    <td>人员</td>\n' +
                            '                </tr>\n' +
                            '                </thead>\n' +
                            '                <tbody>';
                        str3 = '</tbody>\n' +
                            '    </table>\n' +
                            ' </div>';
                        str2 += '<tr>\n' +
                            '<td style="text-align: center;">' + name.content + '</td>\n' +
                            '<td style="text-align: center;">' + this.count + '</td>\n' +
                            '<td style="text-align: center;">' + this.start_date.substring(0, 10) + '至' + this.end_date.substring(0, 10) + '</td>\n' +
                            '<td style="text-align: center;" id="district">'+text+'</td>\n' +
                            '<td style="text-align: center;">' +
                            '<div class="operation">' +
                            '<div class="layui-btn-group">' +
                            '<button onclick="update_target(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                            '<i class="layui-icon">&#xe642;</i>' +
                            '</button>' +
                            '<button onclick="delete_con(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                            '<i class="layui-icon">&#xe640;</i>' +
                            '</button>' +
                            '</div>' +
                            '</div>' +
                            '</td>' +
                            '</tr>';
                    }
                })
                $('#forma').append(str1 + str2 + str3);
            })
        }
    })
}

function getTargetTypeAll(sign) {
    var targetTypes;
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            targetTypes = data[0].child;
        }
    });
    return targetTypes;
}

function getsuperior(code){
    var text = '';
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/tree/'+code,
        async : false,
        success : function(data) {
            var provinceName,
                city,
                area,
                street;
            var str;
            if(data.street != undefined){
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                street = data.street.streetName;
                str = provinceName+'-'+city+'-'+area+'-'+street+'-';
            }else if(data.area != undefined){
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                str = provinceName+'-'+city+'-'+area+'-';
            }else if(data.city != undefined){
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                str = provinceName+'-'+city+'-';
            }else if(data.province != undefined){
                provinceName = data.province.provinceName;
                str = provinceName+'-';
            }
            text=str;
        }
    });
    return text;
}

function getFund(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fund/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            $("#zijinwenhao").text(data[0].doc_number);
            var str = '';
            var total = 0;
            $(data).each(function () {
                total += this.money;
                var content = getLablename(this.subject);
                var source = getLablename(this.source);
                str += ' <tr>\n' +
                    '<td>' + content.content + '</td>\n' +
                    '<td>' + this.money + '</td>\n' +
                    ' <td>' + source.content + '</td>\n' +
                    '<td>' + this.remark + '' +
                    '<div class="operation">' +
                    '<div class="layui-btn-group">' +
                    '<button onclick="updata_fund(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                    '<i class="layui-icon">&#xe642;</i>' +
                    '</button>' +
                    '<button onclick="delete_fund(' + this.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                    '<i class="layui-icon">&#xe640;</i>' +
                    '</button>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '</tr>';

            })
            $("#fund").append(str);
            $("#total").text(total);
        }
    });
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

function delete_fund(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/fund/' + id,
            success: function (data) {
                layer.msg("删除成功");
                location.reload();
            }
        });
        layer.close(1);
    });
}

function getItem_user(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/user/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            var str = '';
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
                var date = new Date();
                var startDate = new Date(userInfo.birthDate);
                var newDate = date.getTime() - startDate.getTime();
                var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);
                if (isNaN(age)) {
                    age = "";
                }
                str += '<tr>\n' +
                    '<td>' + userInfo.name + '</td>\n' +
                    '<td>' + sex + '</td>\n' +
                    '<td>' + age + '</td>\n' +
                    '<td>' + userInfo.idType + '/' + userInfo.idCard + '</td>\n' +
                    '<td>' + userInfo.academicTitle + '</td>\n' +
                    '<td>' + userInfo.nowMajor + '</td>\n' +
                    '<td>' + userInfo.deptName + '</td>\n' +
                    '<td >' + names +
                    '<div style="right: -40px;" class="operation">' +
                    '<div class="layui-btn-group">' +
                    '<button onclick="delete_user(' + this.itemUser.id + ')" type="button" class="layui-btn layui-btn-sm">' +
                    '<i class="layui-icon">&#xe640;</i>' +
                    '</button>' +
                    '</div>' +
                    '</div>' +
                    '</td>' +
                    '</tr>'
            })
            $("#item_user").append(str);
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

function delete_user(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-user/user/' + id,
            success: function (data) {
                layer.msg("删除成功");
                location.reload();
            }
        });
        layer.close(1);
    });
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

function getCompanyInfo(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/company/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            $(data).each(function () {
                if (this.type == 0) {
                    $("#phone0").text(this.phone);
                    $("#postal_code0").text(this.postal_code);
                    $("#site0").text(this.site);
                    $("#email0").text(this.email);
                    $("#fax0").text(this.fax);
                    $("#linkman0").text(this.linkman);
                } else if (this.type == 1) {
                    $("#phone1").text(this.phone);
                    $("#linkman1").text(this.linkman);
                    $("#postal_code1").text(this.postal_code);
                    $("#site1").text(this.site);
                    $("#email1").text(this.email);
                    $("#fax1").text(this.fax);
                } else if (this.type == 2) {
                    $("#phone2").text(this.phone);
                    $("#postal_code2").text(this.postal_code);
                    $("#site2").text(this.site);
                    $("#email2").text(this.email);
                    $("#fax2").text(this.fax);
                    $("#linkman2").text(this.linkman);
                }

            })
        }
    })
}