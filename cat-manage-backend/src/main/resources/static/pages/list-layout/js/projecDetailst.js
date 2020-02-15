function getBasic(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/basic/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];

            //span数据
            $("#contract_no_text").text(d.contract_no);
            $("#category_text").text(analysisLableContent(d.category));
            $("#item_name_text").text(d.item_name);
            $("#consignor_text").text(d.consignor);
            $("#undertaker_text").text(d.undertaker);
            $("#administrator_text").text(d.administrator);

            $("#start_date_text").text(d.start_date.substring(0, 10));
            $("#end_date_text").text(d.end_date.substring(0, 10));
            $("#item_number_text").text(d.item_number);
            $("#contract_file_text").attr('href', analysisFile(d.contract_file).url);
            $("#contract_file_text").text(analysisFile(d.contract_file).name);
            $("#outline_text").text(d.outline);
            //表单数据
            $("#contract_no").val(d.contract_no);
            $("#category").val(d.category);
            $("#item_name").val(d.item_name);
            $("#consignor").val(d.consignor);
            $("#undertaker").val(d.undertaker);
            $("#administrator").val(d.administrator);

            $("#start_date").val(d.start_date.substring(0, 10));
            $("#end_date").val(d.end_date.substring(0, 10));
            $("#item_number").val(d.item_number);
            $("#contract").val(analysisFile(d.contract_file).name);
            $("#contract_file").val(d.contract_file);
            $("#outline").val(d.outline);
        }
    })
}

/*解析标签名称*/
function analysisLableContent(sign) {
    var text = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            text = data[0].content;
        }
    });
    return text;
}

/*解析部门名称*/
function analysisDeptName(code) {
    var text = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/' + code,
        async: false,
        success: function (data) {
            text = data.signName;
        }
    });
    return text;
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
    //console.log(text.name)
    return text;
}

/*获取主要内容*/
function getContent(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/content/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];

            $("#content_text").text(d.content);
            $("#content").val(d.content);
        }
    })
}

/*获取考核指标*/
function getTarget(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $(data).each(function () {
                var t = this;
                var text = getsuperior(this.district);
                var option = initTargetSelect('INDICATORS_OF_LIBRARY', this.target);
                var userNames = analysisUser(this.userIds);

                var arr = $('#indicators').find('input[name=type][value=' + this.type + ']');

                var str1 = '<td colspan="2"><input type="hidden" name="id" value="' + this.id + '">' +
                    '                       <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                    '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                    '                        <p>' + analysisLableContent(this.target) + '</p>\n' +
                    '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="target">\n' +
                    '                        ' + option + '\n' +
                    '                        </select></td>\n' +
                    '                    <td><p>' + this.start_date.substring(0, 10) + '</p><input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                    '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                    '                    <td><p>' + this.end_date.substring(0, 10) + '</p><input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                    '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                    '                    <td><p>' + text + '</p>\n' +
                    '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                    '                            <option value="1" selected>' + text + '</option>\n' +
                    '                        </select>\n' +
                    '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                    '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                    '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                    '                            <i class="layui-icon">&#xe669;</i>\n' +
                    '                        </button>\n' +
                    '                    </td>\n' +
                    '                    <td>\n' +
                    '                        <p>' + this.count + '</p><input class="form-control hidden" lay-verify="required" placeholder="数量" type="text" name="count" value="' + this.count + '">\n' +
                    '                    </td>\n' +
                    '                </tr>',
                    str2 = '<td colspan="2"><input type="hidden" name="id" value="' + this.id + '">' +
                        '                       <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                        '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                        '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                        '                        <input type="hidden" name="target" value="' + this.target + '">\n' +
                        '                        <input type="hidden" name="count" value="' + this.count + '">' +
                        '                        <p>' + this.content + '</p>\n' +
                        '                        <textarea placeholder="内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                        '                                  name="content">' + this.content + '</textarea></td>\n' +
                        '                    <td><p>' + this.start_date.substring(0, 10) + '</p><input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                        '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                        '                    <td><p>' + this.end_date.substring(0, 10) + '</p><input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                        '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                        '                    <td><p>' + text + '</p>\n' +
                        '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                        '                            <option value="1" selected>' + text + '</option>\n' +
                        '                        </select>\n' +
                        '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                        '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                        '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                        '                            <i class="layui-icon">&#xe669;</i>\n' +
                        '                        </button>\n' +
                        '                    </td>\n' +
                        '                    <td><p>' + userNames + '</p>\n' +
                        '                        <div class="userIds hidden"></div>\n' +
                        '                    </td>\n' +
                        '                </tr>',
                    str = '';

                if (arr.length != 0) {//判断是否已存在该类型指标
                    if (this.type == 'QUANTITY_INDICATORS') { //判断是否是数量指标
                        str = '<tr>' + str1;
                    } else {
                        str = '<tr>' + str2;
                    }
                    //单元格所跨的行数+1
                    arr.first().parent().prev().attr('rowspan', arr.length + 1);
                    arr.last().parent().parent().after(str);
                } else {
                    if (this.type == 'QUANTITY_INDICATORS') { //判断是否是数量指标
                        str = '<tr><td rowspan="1">' + analysisLableContent(this.type) + '</td>' + str1;
                    } else {
                        str = '<tr><td rowspan="1">' + analysisLableContent(this.type) + '</td>' + str2;
                    }
                    $('#indicators').append(str);
                }
                //渲染人员选择
                if (this.type != 'QUANTITY_INDICATORS') {

                    var d = [];
                    $('input[name=user_id]').each(function () {
                        var UId = $(this).val();
                        if (UId != '') {
                            var name = $(this).prev().prev().val();
                            var data = {name: name, value: UId,};
                            d.push(data)
                        }
                    })
                    var xm = xmSelect.render({
                        name: 'userIds',
                        layVerify: 'required',
                        layVerType: 'msg',
                        el: $('#indicators').find('input[name=type][value=' + t.type + ']').last().parent().parent().find('.userIds')[0],
                        data: d
                    })
                    xm.setValue(this.userIds.split(','))
                }
            })
        }
    })
}


/*获取全部指标类型*/
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

/*获取全部考核指标,标签都能用*/
function initTargetSelect(sign, selected) {
    var str = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            var ds = data[0].child;
            $(ds).each(function () {
                if (this.sign == selected) {
                    str += '<option value=' + this.sign + ' selected>' + this.content + '</option>';
                } else {
                    str += '<option value=' + this.sign + '>' + this.content + '</option>';
                }
            });
        }
    })
    return str;
}

/*获取地点*/
function getsuperior(code) {
    var text = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/tree/' + code,
        async: false,
        success: function (data) {
            var provinceName,
                city,
                area,
                street;
            var str;
            if (data.street != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                street = data.street.streetName;
                str = provinceName + '-' + city + '-' + area + '-' + street + '-';
            } else if (data.area != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                str = provinceName + '-' + city + '-' + area + '-';
            } else if (data.city != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                str = provinceName + '-' + city + '-';
            } else if (data.province != undefined) {
                provinceName = data.province.provinceName;
                str = provinceName + '-';
            }
            text = str;
        }
    });
    return text;
}

/*解析实施人员*/
function analysisUser(ids) {
    var userIds = ids.split(',');
    var userNames = [];
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user/' + ids,
        async: false,
        success: function (data) {
            $(data).each(function () {
                userNames.push(this.name);
            });
        }
    })
    return userNames;
}

/*获取项目人员*/
function getPersonnel(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/user/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            console.log(data)
            var str = '';
            $(data).each(function (index) {
                var d = getUserInfo(this.user_id);
                var sex = d.sex || '',
                    birthDate = d.birthDate || '',
                    idType = d.idType || '',
                    idCard = d.idCard || '',
                    academicTitle = d.academicTitle || '',
                    nowMajor = d.nowMajor || '',
                    deptName = d.deptName || '',
                    name = d.name || '';

                if (sex === '1') {
                    sex = '男';
                } else {
                    sex = '女';
                }
                // 获得今天的时间，计算年龄
                var date = new Date();
                var startDate = new Date(birthDate);
                var newDate = date.getTime() - startDate.getTime();
                var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);
                if (isNaN(age)) {
                    age = "";
                }
                str += '<tr>\n' +
                    '                    <td>\n' +
                    '                        <p>' + name + '</p>\n' +
                    '                        <input class="form-control name hidden" placeholder="姓名" type="text" value="' + name + '" readonly>\n' +
                    '                        <input type="hidden" name="item_id" value="' + id + '">\n' +
                    '                        <input type="hidden" name="user_id" value="' + this.user_id + '">\n' +
                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                    '                    </td>\n' +
                    '                    <td><p>' + sex + '</p>' +
                    '                        <input class="form-control hidden" placeholder="性别" type="text" value="' + sex + '" readonly></td>\n' +
                    '                    <td><p>' + age + '</p>' +
                    '                        <input class="form-control hidden" placeholder="年龄" type="text" value="' + age + '" readonly></td>\n' +
                    '                    <td><p>' + idType + '/' + idCard + '</p>' +
                    '                        <input class="form-control hidden" placeholder="证件类型/证件号码" value="' + idType + '/' + idCard + '" type="text" readonly></td>\n' +
                    '                    <td><p>' + academicTitle + '</p>' +
                    '                        <input class="form-control hidden" placeholder="职称" type="text" value="' + academicTitle + '" readonly></td>\n' +
                    '                    <td><p>' + nowMajor + '</p>' +
                    '                        <input class="form-control hidden" placeholder="从事专业" type="text" value="' + nowMajor + '" readonly></td>\n' +
                    '                    <td><p>' + deptName + '</p>' +
                    '                        <input class="form-control hidden" placeholder="工作单位" type="text" value="' + deptName + '" readonly></td>\n' +
                    '                    <td>\n' +
                    '                        <p>' + d.phone + '</p>\n' +
                    '                        <input class="form-control userName hidden" placeholder="手机号码" type="text" value="' + d.phone + '">\n' +
                    '                    </td>\n' +
                    '                </tr>';
            });
            $('#personnel').children().first().children().first().attr('rowspan', data.length + 1);
            $("#personnel").append(str);
        }
    })
}

function getUserInfo(id) {
    var userInfo = {};
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user-anon/' + id,
        async: false,
        success: function (data) {
            userInfo = data[0];
        }
    })
    return userInfo;
}

/*解析负责的指标*/
function analysisResponsible(id) {
    var targetText = '';
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/list',
        data: "id=" + id,
        async: false,
        success: function (data) {
            var d = data[0];
            targetText = analysisLableContent(d.target);
        }
    })
    return targetText;
}

//查询当前用户所在的公司代码
function gatDeptCode() {
    var deptCode = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/now-user',
        async: false,
        success: function (data) {
            deptCode = data.deptCode;
        }
    });
    return deptCode;
}

//初始化人员选择
function initUserSelectData(deptCode, selected) {
    var str = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/user/dept/' + deptCode,
        async: false,
        success: function (data) {
            $(data).each(function () {
                if (this.userId == selected) {
                    str += '<option value=' + this.userId + ' selected>' + this.name + '</option>';
                } else {
                    str += '<option value=' + this.userId + '>' + this.name + '</option>';
                }
            })
        }
    });
    return str;
}

function getFund(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fund/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            $(data).each(function () {
                var sourceOption = initTargetSelect('FUNDING_SOURCE', this.source);
                var nodes = $('#expenditure').find('input[name=subject][value=' + this.subject + ']');

                if (nodes.parent().prev().find('textarea').val() == '') {
                    nodes.parent().prev().find('textarea').val(this.remark);
                    nodes.parent().prev().find('p').text(this.remark);
                    nodes.prev().prev().val(this.item_id);
                    nodes.prev().val(this.type);
                    nodes.prev().prev().prev().text(analysisLableContent(this.source));
                    nodes.next().val(this.source);
                    nodes.parent().next().find('input').val(this.money);
                    nodes.parent().next().find('p').text(this.money);
                }else {
                    var str = '<tr>\n' +
                        '                    <td><p>'+analysisLableContent(this.source)+'</p>\n' +
                        '                        <input type="hidden" name="item_id" value="'+this.item_id+'">\n' +
                        '                        <input type="hidden" name="type" value="'+this.type+'">\n' +
                        '                        <input type="hidden" name="subject" value="' + this.subject + '">\n' +
                        '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="source">\n' +
                        '                        ' + sourceOption + '\n' +
                        '                        </select></td>\n' +
                        '                    <td><p>'+this.money+'</p><input class="form-control hidden" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                        '                               name="money" value="'+this.money+'"></td>\n' +
                        '                </tr>';
                    $('#expenditure').children().first().children().first().attr('rowspan', $('#expenditure').children().length + 1);
                    nodes.first().parent().parent().children().eq(0).attr('rowspan', nodes.length + 1);
                    nodes.first().parent().parent().children().eq(1).attr('rowspan', nodes.length + 1);
                    nodes.first().parent().parent().children().eq(4).attr('rowspan', nodes.length + 1);
                    nodes.last().parent().parent().after(str);
                }
                //小计
                var subjects = $('#expenditure').find('input[name=subject][value=' + this.subject + ']');
                var subtotal = 0;
                $(subjects).each(function () {
                    var money = $(this).parent().next().find('input[name=money]').val();
                    if (money !== '') {
                        subtotal += money - 0;
                    }
                })
                if (subtotal != 0) {
                    $(subjects).first().parent().next().next().text(subtotal);
                } else {
                    $(subjects).first().parent().next().next().text('');
                }
            })
        }
    });
}

function getFund1(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/fund/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            var str = '';
            var total = 0;
            $(data).each(function () {
                total += this.money;
                var subjectOption = initTargetSelect('BUDGET_SUBJECT', this.subject);
                var sourceOption = initTargetSelect('FUNDING_SOURCE', this.source);
                var subject = analysisLableContent(this.subject);
                var source = analysisLableContent(this.source);
                $('#thead').attr('rowspan', $('#EXPENDITURE').children().length + 1);
                if (this.type == 0) {
                    var length = $('#EXPENDITURE').find('input[name=type][value=' + this.type + ']').length;
                    if (length == 0) {
                        str = '<tr>\n' +
                            '                    <td rowspan="1" id="direct">（一）直接费用</td>\n' +
                            '                    <td><input type="hidden" name="item_id" value="' + id + '">\n' +
                            '                        <input type="hidden" name="type" value="0">\n' +
                            '                        <p>' + subject + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="subject">\n' +
                            '                            ' + subjectOption + '\n' +
                            '                        </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.money + '</p>\n' +
                            '                        <input class="form-control hidden" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                            '                              name="money" value="' + this.money + '"></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + source + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="source">\n' +
                            '                        ' + sourceOption + '\n' +
                            '                    </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.remark + '</p>\n' +
                            '                        <textarea placeholder="开支内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                            '                                  name="remark">' + this.remark + '</textarea>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    } else {
                        str = '<tr>\n' +
                            '                    <td><input type="hidden" name="item_id" value="' + id + '">\n' +
                            '                        <input type="hidden" name="type" value="0">\n' +
                            '                        <p>' + subject + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="subject">\n' +
                            '                            ' + subjectOption + '\n' +
                            '                        </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.money + '</p>\n' +
                            '                        <input class="form-control hidden" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                            '                               name="money" value="' + this.money + '"></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + source + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="source">\n' +
                            '                        ' + sourceOption + '\n' +
                            '                    </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.remark + '</p>\n' +
                            '                        <textarea placeholder="开支内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                            '                                  name="remark">' + this.remark + '</textarea>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    }
                    $('#direct').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + this.type + ']').length + 1);
                    $('#interval').before(str);
                } else {
                    var length = $('#EXPENDITURE').find('input[name=type][value=' + this.type + ']').length;
                    if (length == 0) {
                        str = '<tr>\n' +
                            '                    <td rowspan="1" id="indirect">（二）间接费用</td>\n' +
                            '                    <td><input type="hidden" name="item_id" value="' + id + '">\n' +
                            '                        <input type="hidden" name="type" value="1">\n' +
                            '                        <p>' + subject + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="subject">\n' +
                            '                            ' + subjectOption + '\n' +
                            '                        </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.money + '</p>\n' +
                            '                        <input class="form-control hidden" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                            '                               name="money" value="' + this.money + '"></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + source + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="source">\n' +
                            '                        ' + sourceOption + '\n' +
                            '                    </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.remark + '</p>\n' +
                            '                        <textarea placeholder="开支内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                            '                                  name="remark">' + this.remark + '</textarea>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    } else {
                        str = '<tr>\n' +
                            '                    <td><input type="hidden" name="item_id" value="' + id + '">\n' +
                            '                        <input type="hidden" name="type" value="1">\n' +
                            '                        <p>' + subject + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="subject">\n' +
                            '                            ' + subjectOption + '\n' +
                            '                        </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.money + '</p>\n' +
                            '                        <input class="form-control hidden" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                            '                              name="money" value="' + this.money + '"></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + source + '</p>\n' +
                            '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="source">\n' +
                            '                        ' + sourceOption + '\n' +
                            '                    </select></td>\n' +
                            '                    <td>\n' +
                            '                        <p>' + this.remark + '</p>\n' +
                            '                        <textarea placeholder="开支内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                            '                                  name="remark">' + this.remark + '</textarea>\n' +
                            '                    </td>\n' +
                            '                </tr>';
                    }
                    $('#indirect').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + this.type + ']').length + 1);
                    $('#EXPENDITURE').append(str);
                }
            })
            $("#fund").append(str);
            $("#total").text(total);
        }
    });
}

function getContacts(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/company/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            $(data).each(function () {
                if (this.type == 0) {
                    $("#phone_0").text(this.phone);
                    $("#postal_code_0").text(this.postal_code);
                    $("#site_0").text(this.site);
                    $("#email_0").text(this.email);
                    $("#fax_0").text(this.fax);
                    $("#linkman_0").text(this.linkman);

                    $("#phone_0").next().val(this.phone);
                    $("#postal_code_0").next().val(this.postal_code);
                    $("#site_0").next().val(this.site);
                    $("#email_0").next().val(this.email);
                    $("#fax_0").next().val(this.fax);
                    $("#linkman_0").next().val(this.linkman);
                } else if (this.type == 1) {
                    $("#phone_1").text(this.phone);
                    $("#linkman_1").text(this.linkman);
                    $("#postal_code_1").text(this.postal_code);
                    $("#site_1").text(this.site);
                    $("#email_1").text(this.email);
                    $("#fax_1").text(this.fax);

                    $("#phone_1").next().val(this.phone);
                    $("#linkman_1").next().val(this.linkman);
                    $("#postal_code_1").next().val(this.postal_code);
                    $("#site_1").next().val(this.site);
                    $("#email_1").next().val(this.email);
                    $("#fax_1").next().val(this.fax);
                } else if (this.type == 2) {
                    $("#phone_2").text(this.phone);
                    $("#postal_code_2").text(this.postal_code);
                    $("#site_2").text(this.site);
                    $("#email_2").text(this.email);
                    $("#fax_2").text(this.fax);
                    $("#linkman_2").text(this.linkman);

                    $("#phone_2").next().val(this.phone);
                    $("#postal_code_2").next().val(this.postal_code);
                    $("#site_2").next().val(this.site);
                    $("#email_2").next().val(this.email);
                    $("#fax_2").next().val(this.fax);
                    $("#linkman_2").next().val(this.linkman);
                }

            })
        }
    })
}