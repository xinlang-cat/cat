function queryInformation(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/information/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $('input[name=contract_no]').val(d.contract_no);
            $('input[name=type]').val(analysisLablename(d.type));
            $('input[name=name]').val(d.name);
            $('input[name=entrusting_party]').val(d.entrusting_party);
            $('input[name=responsible_unit]').val(d.responsible_unit);
            $('input[name=management_unit]').val(d.management_unit);
            $('input[name=document_number]').val(d.document_number);
            $('textarea[name=overall_objective]').val(d.overall_objective);
            $('textarea[name=research_contents]').val(d.research_contents);
            $('input[name=period]').val(d.start_date.substring(0, 7) + ' - ' + d.end_date.substring(0, 7));
            $('input[name=status]').val(d.status);

            $("#contractName").text(analysisFile(d.accessory).name);
            $("#contractName").attr('href', analysisFile(d.accessory).url);

            $('input[name=province]').val(province(d.province_code));
            $('input[name=city]').val(city(d.city_code));
            $('input[name=area]').val(area(d.area_code));
            $('input[name=street]').val(street(d.street_code));
        }
    })
}
/*解析文件*/
function analysisFile(id) {

    if (id == '') {

        return '';
    }
    var text = {};
    $.ajax({
        type: 'get',
        url: domainName + '/api-f/files/' + id,
        async: false,
        success: function (data) {
            text = data;
        }
    });
    return text;
}

function province(provinceCode) {
    var provinceName = '';
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/one/'+provinceCode,
        async : false,
        success : function(data) {
            provinceName = data.provinceName;
        }
    });
    return provinceName;
}

function city(cityCode) {
    var cityName = '';
    $.ajax({
        type : 'get',
        url : domainName + '/map/city/one/'+cityCode,
        async : false,
        success : function(data) {
            cityName = data.cityName;
        }
    });
    return cityName;
}

function area(areaCode) {
    var areaName = '';
    $.ajax({
        type : 'get',
        url : domainName + '/map/area/one/'+areaCode,
        async : false,
        success : function(data) {
            areaName = data.areaName;
        }
    });
    return areaName;
}
function street(streetCode) {
    var streetName = '';
    $.ajax({
        type : 'get',
        url : domainName + '/map/street/one/'+streetCode,
        async : false,
        success : function(data) {
            streetName = data.streetName
        }
    });
    return streetName;
}

function queryIndicators(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/indicators/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $(data).each(function () {
                var content = this.content;
                var serial = $('#' + this.type).closest('thead').nextAll('tbody').children().length + 1;
                var str = '';
                if (this.type != 'QUANTITY_INDICATORS') {
                    var text = getsuperior(this.site);
                    var userIds = this.userIds.split(',');
                    var userNames = [];
                    $('input[name=user_id]').each(function (i,d) {
                        var UId = $(d).val();
                        $(userIds).each(function () {
                            if (UId == this) {
                                var name = $(d).next().text();
                                userNames.push(name)
                            }
                        });
                    })
                    str = '<tr>' +
                        '<td class="mainTd_1 tdDorder">' + serial + '<input type="hidden" name="type" value="' + this.type + '"></td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<span>' + this.content + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<span>' + this.start_date.substring(0, 7) + ' - ' + this.end_date.substring(0, 7) + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field" style="position: relative;">' +
                        '<span>' + text + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<span>' + userNames + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        '</tr>';
                } else {
                    str = '<tr>' +
                        '<td class="mainTd_1 tdDorder">' + serial + '<input type="hidden" name="type" value="' + this.type + '"></td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<span>' + analysisLablename(this.content) + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<span>' + this.count + '</span>\n' +
                        '</div>' +
                        '</td>' +
                        ' </tr>';
                }
                $('#' + this.type).closest('thead').nextAll('tbody').append(str);
            })
        }
    })
}

function queryScheduling(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/scheduling/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $(data).each(function () {
                var serial = $('#scheduling').closest('thead').nextAll('tbody').children().length + 1;
                var str = '<tr>' +
                    '<td class="mainTd_1 tdDorder">' + serial + '</td>' +
                    '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                    ' <div class="field">' +
                    '<span>' + this.annual + '</span>\n' +
                    ' </div>' +
                    ' </td>' +
                    '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                    ' <div class="field">' +
                    '<span>' + this.objectives + '</span>\n' +
                    '  </div>' +
                    ' </td>' +
                    '  </tr>';
                $('#scheduling').closest('thead').nextAll('tbody').append(str);
            })
        }
    })
}

function queryPersonnel(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/personnel/list',
        data: "item_id=" + id + "&user_type=PARTY_B_MEMBER",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $(data).each(function () {
                var serial = $('#personnel').closest('thead').nextAll('tbody').children().length + 1;
                var str = '<tr>\n' +
                    '                            <td class="mainTd_1 tdDorder">'+serial+'</td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <input type="hidden"  name="user_id" value="' + this.user_id + '">\n' +
                    '                                <span>' + this.name + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.sex + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.age + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.professional_title + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.specialty + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.organization + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                <span>' + this.responsibilities + '</span>\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                        </tr>';
                $('#personnel').closest('thead').nextAll('tbody').append(str);
            })
        }
    })
}

function queryFundBudget(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fundBudget/list',
        data: "item_id=" + id+'&type=first_party_provide',
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $(data).each(function () {
                var str = '<tr>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.subject + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.money + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.content + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.remark + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>';
                $('#fundBudget').append(str);
            })
        }
    })
}

function queryFundSource(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fundSource/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $('.fundSource').find('.responsible_unit').text(d.responsible_unit);
            $('.fundSource').find('.state_science_and_technology_department').text(d.state_science_and_technology_department);
            $('.fundSource').find('.state_other_department').text(d.state_other_department);
            $('.fundSource').find('.area_department').text(d.area_department);
            $('.fundSource').find('.city_department').text(d.city_department);
            $('.fundSource').find('.county_department').text(d.county_department);
            $('.fundSource').find('.own_fund').text(d.own_fund);
            $('.fundSource').find('.bank_loan').text(d.bank_loan);
            $('.fundSource').find('.offshore_fund').text(d.offshore_fund);
            $('.fundSource').find('.other_fund').text(d.other_fund);
        }
    })
}

function queryContactWay(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/contactWay/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $(data).each(function () {
                var text = '';
                if (this.type == 1) {
                    text = '甲方';
                } else if (this.type == 2) {
                    text = '乙方';
                } else {
                    text = '丙方';
                }
                var str = '<tr>\n' +
                    '                                <td class="tdDorder mainTd_1" rowspan="4">\n' +
                    '                                    <span>' + text + '</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>负责人</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + getUserName(this.leader) + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>负责人电话</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.leader_phone + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>\n' +
                    '                            <tr>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>联系人</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.linkman + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>联系人电话</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.linkman_phone + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>\n' +
                    '                            <tr>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>传真</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.fax + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>邮编</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.postcode + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>\n' +
                    '                            <tr>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>地址</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.site + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>E-mail（电子邮件）</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                       <span>' + this.e_mail + '</span>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>';
                $('#contactWay').append(str);
            })
        }
    })
}
function getUserName(userId) {
    var userName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user-anon/'+userId,
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data) {
            userName = data[0].name;
        }
    });
    return userName;
}