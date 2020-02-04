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
            $("#consignor_text").text(analysisDeptName(d.consignor));
            $("#undertaker_text").text(analysisDeptName(d.undertaker));
            $("#administrator_text").text(analysisDeptName(d.administrator));

            $("#start_date_text").text(d.start_date.substring(0, 10));
            $("#end_date_text").text(d.end_date.substring(0, 10));
            $("#item_number_text").text(d.item_number);
            $("#contract_file_text").attr('href',analysisFile(d.contract_file).url);
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
    var text = {};
    $.ajax({
        type: 'get',
        url: domainName + '/api-f/files/'+id,
        async: false,
        success: function (data) {
            text = data;
        }
    });
    console.log(text.name)
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
            var str = '';
            $(data).each(function (index) {
                if (index == 0) {
                    str = '<td>研究内容1\n' +
                        '                        <input type="hidden" name="id" value="' + this.id + '">\n' +
                        '                        <input type="hidden" name="item_id" value="' + id + '">\n' +
                        '                    </td>\n' +
                        '                    <td>\n' +
                        '                        <p id="title_text">' + this.title + '</p>\n' +
                        '                        <input class="form-control hidden" lay-verify="required" placeholder="标题" type="text" name="title" value="' + this.title + '">\n' +
                        '                        <br>\n' +
                        '                        <p id="content_text">' + this.content + '</p>\n' +
                        '                        <textarea placeholder="内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                        '                                  name="content">' + this.content + '</textarea>\n' +
                        '                    </td>';
                    $("#mian-content").children().first().append(str);
                } else {
                    var rowspan = $("#mian-content").children().length + 1;//改变单元格所跨的行数
                    $("#mian-content").children().first().children().first().attr('rowspan', rowspan);
                    str = '<tr>\n' +
                        '                    <td>研究内容' + rowspan + '\n' +
                        '                        <input type="hidden" name="id" value="' + this.id + '">\n' +
                        '                        <input type="hidden" name="item_id" value="' + id + '">\n' +
                        '                    </td>\n' +
                        '                    <td>\n' +
                        '                        <p id="title_text">' + this.title + '</p>\n' +
                        '                        <input class="form-control hidden" lay-verify="required" placeholder="标题" type="text" name="title" value="' + this.title + '">\n' +
                        '                        <br>\n' +
                        '                        <p id="content_text">' + this.content + '</p>\n' +
                        '                        <textarea placeholder="内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                        '                                  name="content">' + this.content + '</textarea>\n' +
                        '                    </td>\n' +
                        '                </tr>';
                    $("#mian-content").append(str);
                }
            })
        }
    })
}

var targets;

/*获取考核指标*/
function getTarget(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            targets = data;//后面人员的负责分工用到
            var targetTypes = getTargetTypeAll('TARGET_TYPE');//先获取全部指标类型
            $(targetTypes).each(function (i) {
                var targetType = this;
                var str = '';
                var n = 0;
                $(data).each(function (j) {
                    if (targetType.sign == this.type) {
                        n++;//符合的次数
                        var target = analysisLableContent(this.target);
                        var option = initTargetSelect('INDICATORS_OF_LIBRARY', this.target);
                        var text = getsuperior(this.district);
                        var userNames = analysisUser(this.userIds);
                        if (n == 1) {
                            if (this.type != 'QUANTITY_INDICATORS') {
                                str = '<tr>\n' +
                                    '                    <td rowspan="1">' + targetType.content + '</td>\n' +
                                    '                    <td colspan="2"><input type="hidden" name="id" value="' + this.id + '">' +
                                    '                        <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                                    '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                                    '                        <input type="hidden" name="target" value=" ">\n' +
                                    '                        <p>' + this.content + '</p>\n' +
                                    '                        <textarea placeholder="内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                                    '                                  name="content">' + this.content + '</textarea></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.start_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                                    '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.end_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                                    '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + text + '</p>\n' +
                                    '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                                    '                            <option value="1" selected>' + text + '</option>\n' +
                                    '                        </select>\n' +
                                    '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                                    '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                                    '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                                    '                            <i class="layui-icon">&#xe669;</i>\n' +
                                    '                        </button>\n' +
                                    '                    </td>\n' +
                                    '                    <td><div class="userIds">'+userNames+'</div></td>\n' +
                                    '                </tr>';
                            } else {
                                str = '<tr>\n' +
                                    '                    <td rowspan="1">' + targetType.content + '</td>\n' +
                                    '                    <td><input type="hidden" name="id" value="' + this.id + '">' +
                                    '                        <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                                    '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                                    '                        <input type="hidden" name="content" value=" ">\n' +
                                    '                        <p>' + target + '</p>\n' +
                                    '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="target">\n' +
                                    '                        ' + option + '\n' +
                                    '                        </select></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.count + '</p>\n' +
                                    '                        <input class="form-control hidden" lay-verify="required" placeholder="数量"\n' +
                                    '                               type="text" name="count" value="' + this.count + '"></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.start_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                                    '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.end_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                                    '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + text + '</p>\n' +
                                    '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                                    '                            <option value="1" selected>' + text + '</option>\n' +
                                    '                        </select>\n' +
                                    '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                                    '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                                    '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                                    '                            <i class="layui-icon">&#xe669;</i>\n' +
                                    '                        </button>\n' +
                                    '                    </td>\n' +
                                    '                    <td><div class="userIds">'+userNames+'</div></td>\n' +
                                    '                </tr>';
                            }
                        } else {
                            if (this.type != 'QUANTITY_INDICATORS') {
                                str += '<tr>\n' +
                                    '                    <td colspan="2"><input type="hidden" name="id" value="' + this.id + '">' +
                                    '                        <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                                    '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                                    '                        <input type="hidden" name="target" value=" ">\n' +
                                    '                        <p>' + this.content + '</p>\n' +
                                    '                        <textarea placeholder="内容" class="layui-textarea form-control hidden" lay-verify="required"\n' +
                                    '                                  name="content">' + this.content + '</textarea></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.start_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                                    '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.end_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                                    '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + text + '</p>\n' +
                                    '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                                    '                            <option value="1" selected>' + text + '</option>\n' +
                                    '                        </select>\n' +
                                    '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                                    '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                                    '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                                    '                            <i class="layui-icon">&#xe669;</i>\n' +
                                    '                        </button>\n' +
                                    '                    </td>\n' +
                                    '                    <td><div class="userIds">'+userNames+'</div></td>\n' +
                                    '                </tr>';
                            } else {
                                str += '<tr>\n' +
                                    '                    <td><input type="hidden" name="id" value="' + this.id + '">' +
                                    '                        <input type="hidden" name="item_id" value="' + this.item_id + '">\n' +
                                    '                        <input type="hidden" name="type" value="' + this.type + '">\n' +
                                    '                        <input type="hidden" name="status" value="' + this.status + '">\n' +
                                    '                        <input type="hidden" name="content" value=" ">\n' +
                                    '                        <p>' + target + '</p>\n' +
                                    '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="target">\n' +
                                    '                        ' + option + '\n' +
                                    '                        </select></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.count + '</p>\n' +
                                    '                        <input class="form-control hidden" lay-verify="required" placeholder="数量"\n' +
                                    '                               type="text" name="count" value="' + this.count + '"></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.start_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="开始时间" type="text"\n' +
                                    '                               name="start_date" value="' + this.start_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + this.end_date.substring(0, 10) + '</p>\n' +
                                    '                        <input class="form-control date hidden" lay-verify="required" placeholder="结束时间" type="text"\n' +
                                    '                               name="end_date" value="' + this.end_date.substring(0, 10) + '" readonly></td>\n' +
                                    '                    <td>\n' +
                                    '                        <p>' + text + '</p>\n' +
                                    '                        <select class="form-control input-sm site hidden" lay-verify="required" lay-ignore>\n' +
                                    '                            <option value="1" selected>' + text + '</option>\n' +
                                    '                        </select>\n' +
                                    '                        <input type="hidden" name="district" value="' + this.district + '">\n' +
                                    '                        <button type="button" class="layui-btn layui-btn-xs hidden" onclick="refresh(this);"\n' +
                                    '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                                    '                            <i class="layui-icon">&#xe669;</i>\n' +
                                    '                        </button>\n' +
                                    '                    </td>' +
                                    '                    <td><div class="userIds">'+userNames+'</div></td>\n' +
                                    '                </tr>';
                            }
                        }
                    }
                    $('#INDICATORS').children().first().children().first().attr('rowspan', data.length + 1);
                })
                $('#INDICATORS').append(str);
                $('#INDICATORS').find('input[name=type][value=' + targetType.sign + ']').first().parent().prev().attr('rowspan', n);
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
function  analysisUser(ids) {
    var userIds = ids.split(',');
    var userNames = [];
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user/'+ids,
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
function getItemUser(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/user/list',
        data: "item_id=" + id,
        async: false,
        success: function (data) {
            var str = '';
            $(data).each(function (index) {
                var option = initUserSelectData(gatDeptCode(), this.user_id);
                var responsible = [];//负责分工
                $(this.targetIds).each(function () {
                    responsible.push(analysisResponsible(this));
                })
                var userInfo = getUserInfo(this.user_id);
                if (userInfo.nowMajor != '') {
                    userInfo.nowMajor = analysisLablename(userInfo.nowMajor);
                }
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
                if (index == 0) {
                    str = '<tr>\n' +
                        '                    <td rowspan="' + data.length + '" class="table-info">项目组人员信息</td>\n' +
                        '                    <td>\n' +
                        '                        <input type="hidden" name="item_id" value="' + id + '">\n' +
                        '                        <p>' + userInfo.name + '</p>\n' +
                        '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="user_id">\n' +
                        '                            ' + option + '\n' +
                        '                        </select></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + sex + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="性别" type="text" readonly value="' + sex + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + age + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="年龄" type="text" readonly value="' + age + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.idType + '/' + userInfo.idCard + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="证件类型/证件号码" type="text" readonly value="' + userInfo.idType + '/' + userInfo.idCard + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.academicTitle + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="职称" type="text" readonly value="' + userInfo.academicTitle + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.nowMajor + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="从事专业" type="text" readonly value="' + userInfo.nowMajor + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.deptName + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="工作单位" type="text" readonly value="' + userInfo.deptName + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>'+userInfo.phone+'</p>\n' +
                        '                        <input class="form-control hidden" placeholder="负责或参与的指标" type="text" value="项目主持人" readonly>\n' +
                        '                        <input type="hidden" name="targetIds" value="COMPERE">\n' +
                        '                        <input type="hidden" name="type" value="0">\n' +
                        '                    </td>\n' +
                        '                </tr>';
                } else {
                    str += '<tr>\n' +
                        '                    <td>\n' +
                        '                        <input type="hidden" name="item_id" value="' + id + '">\n' +
                        '                        <p>' + userInfo.name + '</p>\n' +
                        '                        <select class="form-control input-sm hidden" lay-verify="required" lay-ignore name="user_id">\n' +
                        '                            ' + option + '\n' +
                        '                        </select></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + sex + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="性别" type="text" readonly value="' + sex + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + age + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="年龄" type="text" readonly value="' + age + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.idType + '/' + userInfo.idCard + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="证件类型/证件号码" type="text" readonly value="' + userInfo.idType + '/' + userInfo.idCard + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.academicTitle + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="职称" type="text" readonly value="' + userInfo.academicTitle + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.nowMajor + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="从事专业" type="text" readonly value="' + userInfo.nowMajor + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>' + userInfo.deptName + '</p>\n' +
                        '                        <input class="form-control hidden" placeholder="工作单位" type="text" readonly value="' + userInfo.deptName + '"></td>\n' +
                        '                    <td>\n' +
                        '                        <p>'+userInfo.phone+'</p>\n' +
                        '                        <input class="form-control targetIds hidden" placeholder="负责或参与的指标" type="text" readonly value="' + responsible + '">\n' +
                        '                        <input type="hidden" name="targetIds" value="' + this.targetIds + '">\n' +
                        '                    </td>\n' +
                        '                </tr>';
                }
            })
            $("#PERSONNEL").append(str);
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