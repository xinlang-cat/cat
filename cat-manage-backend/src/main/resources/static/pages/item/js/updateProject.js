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
            $('select[name=type]').val(d.type);
            $('input[name=name]').val(d.name);
            $('input[name=entrusting_party]').val(d.entrusting_party);
            $('input[name=responsible_unit]').val(d.responsible_unit);
            $('input[name=management_unit]').val(d.management_unit);
            $('input[name=document_number]').val(d.document_number);
            $('textarea[name=overall_objective]').val(d.overall_objective);
            $('textarea[name=research_contents]').val(d.research_contents);
            $('input[name=period]').val(d.start_date.substring(0, 7) + ' - ' + d.end_date.substring(0, 7));

        }
    })
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
                    str = '<tr>' +
                        '<td class="tdDorder mainTd_1">' +
                        '<input type="checkbox" title="" lay-skin="primary" lay-filter="c_one">' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder">' + serial + '<input type="hidden" name="type" value="' + this.type + '"></td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<input type="text" placeholder="请输入" name="content" value="' + this.content + '" lay-verify="required">' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<input class="date" type="text" placeholder="请输入" name="period" value="'+this.start_date.substring(0, 7) + ' - ' + this.end_date.substring(0, 7)+'" lay-verify="required">' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field" style="position: relative;">' +
                        '<select class="site" lay-verify="required" lay-filter="site">' +
                        '<option value="'+this.site+'">'+text+'</option>\n' +
                        '</select>\n' +
                        '<input type="hidden" name="site" value="'+this.site+'">'+
                        '<button type="button" class="layui-btn layui-btn-xs" name="refresh"\n' +
                        ' style="position: absolute;right: 0;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                        '<i class="layui-icon">&#xe669;</i>\n' +
                        '</button>' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<div class="userIds"></div>' +
                        '</div>' +
                        '</td>' +
                        '</tr>';

                    $('#' + this.type).closest('thead').nextAll('tbody').append(str);
                    //获取项目人员
                    var d = [];
                    $('input[name=user_id]').each(function () {
                        var UId = $(this).val();
                        if (UId != '') {
                            var name = $(this).next().val();
                            var data = {name: name, value: UId,};
                            d.push(data)
                        }
                    });
                    //渲染实施人员选择
                    var xm = xmSelect.render({
                        name: 'userIds',
                        layVerify: 'required',
                        layVerType: 'msg',
                        el: $('#' + this.type).closest('thead').nextAll('tbody').children().last().find('.userIds')[0],
                        model: {
                            label: {
                                type: 'text',
                            }
                        },
                        data: d
                    });
                    xm.setValue(this.userIds.split(','))
                } else {
                    var option = '';
                    $.ajax({
                        type: 'get',
                        url: domainName + '/api-label/label/tree/' + 'INDICATORS_OF_LIBRARY',
                        async: false,
                        success: function (data) {
                            var ds = data[0].child;
                            $(ds).each(function () {
                                if (this.sign == content) {
                                    option += '<option value=' + this.sign + ' selected>' + this.content + '</option>';
                                } else {
                                    option += '<option value=' + this.sign + ' >' + this.content + '</option>';
                                }
                            });
                        }
                    })
                    str = '<tr>' +
                        '<td class="tdDorder mainTd_1">' +
                        '<input type="checkbox" title="" lay-skin="primary" lay-filter="c_one">' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder">' + serial + '<input type="hidden" name="type" value="' + this.type + '"></td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<select name="content">\n' +
                        '<option value=""></option>' + option + '\n' +
                        '</select>' +
                        '</div>' +
                        '</td>' +
                        '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                        '<div class="field">' +
                        '<input type="text" placeholder="请输入" name="count" value="' + this.count + '" lay-verify="required">' +
                        '</div>' +
                        '</td>' +
                        ' </tr>';
                    $('#' + this.type).closest('thead').nextAll('tbody').append(str);
                }
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
                var serial = $('button[name=scheduling]').closest('thead').nextAll('tbody').children().length + 1;
                var str = '<tr>' +
                    '<td class="tdDorder mainTd_1">' +
                    ' <input type="checkbox" title="" lay-skin="primary" lay-filter="c_one">' +
                    '  </td>' +
                    '<td class="mainTd_1 tdDorder">' + serial + '</td>' +
                    '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                    ' <div class="field">' +
                    ' <input type="text" placeholder="请输入" name="annual" value="' + this.annual + '" lay-verify="required">' +
                    ' </div>' +
                    ' </td>' +
                    '<td class="mainTd_1 tdDorder" rowspan="1" colspan="1">' +
                    ' <div class="field">' +
                    '   <textarea name="objectives" placeholder="请输入内容" class="layui-textarea">' + this.objectives + '</textarea>' +
                    '  </div>' +
                    ' </td>' +
                    '  </tr>';
                $('button[name=scheduling]').closest('thead').nextAll('tbody').append(str);
            })
        }
    })
}

function queryPersonnel(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/personnel/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $(data).each(function () {
                var serial = $('button[name=personnel]').closest('thead').nextAll('tbody').children().length + 1;
                var str = '<tr>\n' +
                    '                            <td class="tdDorder mainTd_1">\n' +
                    '                                <input type="checkbox" title="" lay-skin="primary" lay-filter="c_one">\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder">'+serial+'</td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="hidden" placeholder="请输入" name="user_id" value="' + this.user_id + '">\n' +
                    '                                    <input type="text" placeholder="请输入" name="name" value="' + this.name + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="sex" value="' + this.sex + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="age" value="' + this.age + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="professional_title" value="' + this.professional_title + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="specialty" value="' + this.specialty + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="organization" value="' + this.organization + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                            <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                <div class="field">\n' +
                    '                                    <input type="text" placeholder="请输入" name="responsibilities" value="' + this.responsibilities + '">\n' +
                    '                                </div>\n' +
                    '                            </td>\n' +
                    '                        </tr>';
                $('button[name=personnel]').closest('thead').nextAll('tbody').append(str);
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
                    '                                        <input type="text" placeholder="请输入" name="subject" value="' + this.subject + '" readonly>\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="money" value="' + this.money + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="content" value="' + this.content + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="remark" value="' + this.remark + '">\n' +
                    '                                        <input type="hidden" name="type" value="' + this.type + '">\n' +
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
            $('.fundSource').find('input[name=responsible_unit]').val(d.responsible_unit);
            $('.fundSource').find('input[name=state_science_and_technology_department]').val(d.state_science_and_technology_department);
            $('.fundSource').find('input[name=state_other_department]').val(d.state_other_department);
            $('.fundSource').find('input[name=area_department]').val(d.area_department);
            $('.fundSource').find('input[name=city_department]').val(d.city_department);
            $('.fundSource').find('input[name=county_department]').val(d.county_department);
            $('.fundSource').find('input[name=own_fund]').val(d.own_fund);
            $('.fundSource').find('input[name=bank_loan]').val(d.bank_loan);
            $('.fundSource').find('input[name=offshore_fund]').val(d.offshore_fund);
            $('.fundSource').find('input[name=other_fund]').val(d.other_fund);
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
                } else if (this.type == 1) {
                    text = '乙方';
                } else {
                    text = '丙方';
                }
                var str = '<tr>\n' +
                    '                                <td class="tdDorder mainTd_1" rowspan="3">\n' +
                    '                                    <span>' + text + '</span>\n' +
                    '                                    <input type="hidden" name="type" value="' + this.type + '">\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>联系人</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="linkman" value="' + this.linkman + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>电话</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="phone" value="' + this.phone + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>\n' +
                    '                            <tr>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>传真</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="fax" value="' + this.fax + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>邮编</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="postcode" value="' + this.postcode + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>\n' +
                    '                            <tr>\n' +
                    '                                <td class="mainTd_1 tdDorder"><span>地址</span></td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="site" value="' + this.site + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <span>E-mail（电子邮件）</span>\n' +
                    '                                </td>\n' +
                    '                                <td class="mainTd_1 tdDorder" rowspan="1" colspan="1">\n' +
                    '                                    <div class="field">\n' +
                    '                                        <input type="text" placeholder="请输入" name="e_mail" value="' + this.e_mail + '">\n' +
                    '                                    </div>\n' +
                    '                                </td>\n' +
                    '                            </tr>';
                $('#contactWay').append(str);
            })
        }
    })
}