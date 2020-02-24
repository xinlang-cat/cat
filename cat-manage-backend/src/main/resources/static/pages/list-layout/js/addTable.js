/*此js用于新增项目*/

//主要研究内容
function add_research_contents() {
    var str = '<tr><td><input type="hidden" name="item_id"></td><td><textarea placeholder="主要研究内容" class="layui-textarea form-control" lay-verify="required" name="content"></textarea></td></tr>';
    //单元格所跨的行数+1
    var rowspan = $('#research-contents').children().length + 1;
    $('#research-contents').children().first().children().first().attr('rowspan', rowspan);
    //添加到后面
    $('#research-contents').append(str);
}

function del_research_contents() {
    var children = $('#research-contents').children();
    if (children.length == 2) {
        return;
    }
    $('#research-contents').children().last().remove();
    $('#research-contents').children().first().children().first().attr('rowspan', $('#research-contents').children().length);
}

//项目人员
function add_personnel() {
    var str = '<tr>\n' +
        '                    <td>\n' +
        '                        <input class="form-control name" placeholder="姓名" type="text" readonly>\n' +
        '                        <input type="hidden" name="item_id">\n' +
        '                        <input type="hidden" name="user_id">\n' +
        '                        <input type="hidden" name="type" value="1">\n' +
        '                    </td>\n' +
        '                    <td><input class="form-control" placeholder="性别" type="text" readonly></td>\n' +
        '                    <td><input class="form-control" placeholder="年龄" type="text" readonly></td>\n' +
        '                    <td><input class="form-control" placeholder="证件类型/证件号码" type="text" readonly></td>\n' +
        '                    <td><input class="form-control" placeholder="职称" type="text" readonly></td>\n' +
        '                    <td><input class="form-control" placeholder="从事专业" type="text" readonly></td>\n' +
        '                    <td><input class="form-control" placeholder="工作单位" type="text" readonly></td>\n' +
        '                    <td>\n' +
        '                        <input class="form-control userName" placeholder="手机号码" type="text">\n' +
        '                    </td>\n' +
        '                </tr>';
    //单元格所跨的行数+1
    var rowspan = $('#personnel').children().length + 1;
    $('#personnel').children().first().children().first().attr('rowspan', rowspan);
    //添加到后面
    $('#personnel').append(str);
    //另有它用
    var itemId = $('input[name=item_id]').val();
    $("input[name='item_id']").val(itemId);
}

function del_personnel() {
    var children = $('#personnel').children();
    if (children.length == 2) {
        return;
    }
    $('#personnel').children().last().remove();
    $('#personnel').children().first().children().first().attr('rowspan', $('#personnel').children().length);
}

//考核指标
function add_indicators() {
    layui.use(['layer', 'form', 'laydate'], function () {
        var layer = layui.layer,
            form = layui.form,
            laydate = layui.laydate;

        var boolean = false;
        var str = '<div class="layui-form" style="margin: 20px auto;width: 80%;"><blockquote class="layui-elem-quote layui-text">' +
            '选择要添加的指标每点击一次”确定“按钮添加一条</blockquote><div class="layui-form-item"><label class="layui-form-label" style="width: 120px">' +
            '选择指标类型</label><div class="layui-input-block" style="margin-left: 120px;"><select lay-verify="required" lay-filter="targetType" name="type" id="targetType">\n' +
            '<option value=""></option></select></div></div><div class="layui-form-item"><label class="layui-form-label" style="width: 120px">指标条数：</label>\n' +
            '<div class="layui-input-block" style="margin-left: 120px;"><span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
            '</div></div></div>';
        layer.open({
            title: "添加指标",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: str,
            yes: function (index, layero) {
                var text = $("#targetType option:selected").text(),
                    type = $("#targetType").val();
                var arr = $('#indicators').find('input[name=type][value=' + type + ']');

                var str1 = '<td colspan="2"><input type="hidden" name="item_id"><input type="hidden" name="type" value="QUANTITY_INDICATORS"><input type="hidden" name="status" value="0">' +
                    '<select class="form-control input-sm" lay-verify="required" lay-ignore name="target"><option value=\'\'>请选择</option></select></td>\n' +
                    '<td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text" name="start_date" readonly></td>\n' +
                    '<td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text" name="end_date" readonly></td>\n' +
                    '<td><select class="form-control input-sm site" lay-verify="required" lay-ignore><option value=\'\'>请选择</option></select>\n' +
                    '<input type="hidden" name="district"><button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                    ' style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;"><i class="layui-icon">&#xe669;</i>\n' +
                    '</button></td><td><input class="form-control" lay-verify="required" placeholder="数量" type="text" name="count"></td></tr>',

                    str2 = '<td colspan="2"><input type="hidden" name="item_id"><input type="hidden" name="type" value="' + type + '">\n' +
                        '<input type="hidden" name="status" value="0"><input type="hidden" name="target" value=" "><input type="hidden" name="count" value=" ">\n' +
                        '<textarea placeholder="内容" class="layui-textarea form-control" lay-verify="required" name="content"></textarea></td>\n' +
                        '<td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text" name="start_date" readonly></td>\n' +
                        '<td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text" name="end_date" readonly></td>\n' +
                        '<td><select class="form-control input-sm site" lay-verify="required" lay-ignore><option value=\'\'>请选择</option>\n' +
                        '</select><input type="hidden" name="district"><button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                        'style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;"><i class="layui-icon">&#xe669;</i>\n' +
                        '</button></td><td><div class="userIds"></div></td></tr>',
                    str = '';
                $('#indicators').children().first().children().first().attr('rowspan', $('#indicators').children().length + 1);
                if (boolean) {//判断是否已存在该类型指标
                    if (type == 'QUANTITY_INDICATORS') { //判断是否是数量指标
                        str = '<tr>' + str1;
                    } else {
                        str = '<tr>' + str2;
                    }
                    //单元格所跨的行数+1
                    arr.first().parent().prev().attr('rowspan', arr.length + 1);
                    arr.last().parent().parent().after(str);
                    $('#count').text(arr.length + 1 + '条');//改变条数
                } else {
                    if (type == 'QUANTITY_INDICATORS') { //判断是否是数量指标
                        str = '<tr><td rowspan="1">' + text + '</td>' + str1;
                    } else {
                        str = '<tr><td rowspan="1">' + text + '</td>' + str2;
                    }
                    $('#indicators').append(str);
                    $('#count').text('1条');//改变条数
                    boolean = true;

                }
                //另有它用
                var itemId = $('input[name=item_id]').val();
                $("input[name='item_id']").val(itemId);

                initSelectData('INDICATORS_OF_LIBRARY', $('#indicators').find('input[name=type][value=' + type + ']').last().parent().find('select[name=target]'));//初始化新添加的表格中的指标选择
                $('#indicators').find('input[name=type][value=' + type + ']').last().parent().parent().find('button').trigger('click');//点击新添加的表格中的地点刷新按钮，起到初始化的作用
                //渲染人员选择
                var d = [];
                $('input[name=user_id]').each(function () {
                    var UId = $(this).val();
                    if (UId != '') {
                        var name = $(this).prev().prev().val();
                        var data = {name: name, value: UId,};
                        d.push(data)
                    }
                })
                //渲染日期组件
                lay('.date').each(function () {
                    laydate.render({
                        elem: this
                        , showBottom: false
                        , trigger: 'click'
                    });
                });
                xmSelect.render({
                    name: 'userIds',
                    layVerify: 'required',
                    layVerType: 'msg',
                    el: $('#indicators').find('input[name=type][value=' + type + ']').last().parent().parent().find('.userIds')[0],
                    data: d
                })
            },
            btn2: function (index, layero) {//关闭
                layer.close(index);
            }
        })
        //赋值
        initSelectData("TARGET_TYPE", $('#targetType'));
        //渲染
        form.render();
        //监听指标类型选择，统计条数
        form.on('select(targetType)', function (data) {
            var arr = $('#indicators').find('input[name=type][value=' + data.value + ']');
            $('#count').text(arr.length + '条');
            if (arr.length != 0) {
                boolean = true;
            }
        });
    });
}

function del_indicators() {
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            form = layui.form;
        var str = '<div class="layui-form" style="margin: 20px auto;width: 80%;"><blockquote class="layui-elem-quote layui-text">\n' +
            ' 选择要删除的指标每点击一次”确定“按钮删除最后一条。</blockquote><div class="layui-form-item">\n' +
            '<label class="layui-form-label" style="width: 120px">选择指标类型</label><div class="layui-input-block" style="margin-left: 120px;">\n' +
            '<select lay-verify="required" lay-filter="targetType" name="type" id="targetType"><option value=""></option></select>\n' +
            '</div></div><div class="layui-form-item"><label class="layui-form-label" style="width: 120px">指标条数：</label>\n' +
            '<div class="layui-input-block" style="margin-left: 120px;"><span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
            '</div></div></div>';
        var arr;
        layer.open({
            title: "删除指标",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: str,
            yes: function (index, layero) {
                arr.last().parent().parent().remove();//删除
                //改变单元格跨的行数
                $('#indicators').children().first().children().first().attr('rowspan', $('#indicators').children().length);
                arr.first().parent().prev().attr('rowspan', arr.length - 1);
                $('#count').text((arr.length - 1 < 0 ? 0 : arr.length - 1) + '条');//改变条数

            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        initSelectData("TARGET_TYPE", $('#targetType'));
        form.render();
        //监听指标类型选择，统计条数
        form.on('select(targetType)', function (data) {
            arr = $('#indicators').find('input[name=type][value=' + data.value + ']');
            $('#count').text(arr.length + '条');
        });
    });
}

//经费
function add_fund() {
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            form = layui.form;

        var str = '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
            '<blockquote class="layui-elem-quote layui-text">\n' +
            '  选择要添加的科目每点击一次”确定“按钮添加一条。\n' +
            '</blockquote>' +
            '<div class="layui-form-item">\n' +
            '    <label class="layui-form-label" style="width: 120px">选择科目</label>\n' +
            '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
            '      <select lay-verify="required" lay-filter="fundType" name="subject" id="subject">\n' +
            '        <option value=""></option>\n' +
            '      </select>\n' +
            '    </div>\n' +
            '  </div>' +
            '<div class="layui-form-item">\n' +
            '    <label class="layui-form-label" style="width: 120px">来源条数：</label>\n' +
            '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
            '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
            '    </div>\n' +
            '  </div>' +
            '</div>';
        layer.open({
            title: "添加经费来源",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: str,
            yes: function (index, layero) {
                var subject = $("#subject").val();
                var str = '<tr>\n' +
                    '                    <td>\n' +
                    '                        <input type="hidden" name="item_id">\n' +
                    '                        <input type="hidden" name="type" value="0">\n' +
                    '                        <input type="hidden" name="subject" value="' + subject + '">\n' +
                    '                        <select class="form-control input-sm" lay-verify="required" lay-ignore name="source">\n' +
                    '                            <option value=\'\'>请选择</option>\n' +
                    '                        </select></td>\n' +
                    '                    <td><input class="form-control" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                    '                               name="money"></td>\n' +
                    '                </tr>';
                $('#expenditure').children().first().children().first().attr('rowspan', $('#expenditure').children().length + 1);
                var nodes = $('#expenditure').find('input[name=subject][value=' + subject + ']');
                nodes.first().parent().parent().children().eq(0).attr('rowspan', nodes.length + 1);
                nodes.first().parent().parent().children().eq(1).attr('rowspan', nodes.length + 1);
                nodes.first().parent().parent().children().eq(4).attr('rowspan', nodes.length + 1);
                nodes.last().parent().parent().after(str);
                $('#count').text(nodes.length + 1 + '条');//改变条数
                initSelectData('FUNDING_SOURCE', $('#expenditure').find('input[name=subject][value=' + subject + ']').last().next());

                //另有它用
                var itemId = $('input[name=item_id]').val();
                $("input[name='item_id']").val(itemId);
            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        //赋值
        initSelectData("BUDGET_SUBJECT", $('#subject'));
        form.render();
        //监听选择，统计条数
        form.on('select(fundType)', function (data) {
            var length = $('#expenditure').find('input[name=subject][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

function del_fund() {
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            form = layui.form;
        var str = '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
            '<blockquote class="layui-elem-quote layui-text">\n' +
            '  选择要删除的科目每点击一次”确定“按钮删除该科目最后一条来源。\n' +
            '</blockquote>' +
            '<div class="layui-form-item">\n' +
            '    <label class="layui-form-label" style="width: 120px">选择科目</label>\n' +
            '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
            '      <select lay-verify="required" lay-filter="fundType" name="subject" id="subject">\n' +
            '        <option value=""></option>\n' +
            '      </select>\n' +
            '    </div>\n' +
            '  </div>' +
            '<div class="layui-form-item">\n' +
            '    <label class="layui-form-label" style="width: 120px">来源条数：</label>\n' +
            '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
            '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
            '    </div>\n' +
            '  </div>' +
            '</div>'
        layer.open({
            title: "删除来源",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: str,
            yes: function (index, layero) {
                var subject = $("#subject").val();

                var nodes = $('#expenditure').find('input[name=subject][value=' + subject + ']');
                if (nodes.length == 1) {
                    return
                }
                nodes.last().parent().parent().remove();
                $('#expenditure').children().first().children().first().attr('rowspan', $('#expenditure').children().length);
                nodes.first().parent().parent().children().eq(0).attr('rowspan', nodes.length - 1);
                nodes.first().parent().parent().children().eq(1).attr('rowspan', nodes.length - 1);
                nodes.first().parent().parent().children().eq(4).attr('rowspan', nodes.length - 1);
                $('#count').text(nodes.length - 1 + '条');//改变条数
            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        //赋值
        initSelectData("BUDGET_SUBJECT", $('#subject'));
        form.render();
        //监听选择，统计条数
        form.on('select(fundType)', function (data) {
            var length = $('#expenditure').find('input[name=subject][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

//创建用户
function createUser(userName, node) {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;
        index = layer.open({
            title: "创建用户",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: '<form class="layui-form" action="" lay-filter="example" style="width: 80%;margin: 20px auto;">\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">手机</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input type="tel" name="username" value="' + userName + '" lay-verify="required|phone" autocomplete="off" class="layui-input">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">密码</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input type="text" name="password" value="123456" placeholder="请输入密码" autocomplete="off" class="layui-input" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">姓名</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input type="text" name="nickname" autocomplete="off" placeholder="请输入姓名" class="layui-input" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">性别</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input type="radio" name="sex" value="1" title="男" checked="">\n' +
                '      <input type="radio" name="sex" value="0" title="女">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">出生时间</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input type="text" class="layui-input" placeholder="出生时间" name="birthDate" id="birthDate" readonly="readonly" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">证件类型</label>\n' +
                '    <div class="layui-input-block">\n' +
                '        <select class="form-control input-sm" name="idType" id="idType">\n' +
                '          <option value="身份证">身份证</option>\n' +
                '          <option value="护照">护照</option>\n' +
                '        </select>\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">证件号码</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input class="layui-input" placeholder="证件号码" type="text" name="idCard" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">职称</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input class="layui-input" placeholder="单位/公司级别" type="text" name="academicTitle" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">从事专业</label>\n' +
                '    <div class="layui-input-block">\n' +
                '      <input class="layui-input" placeholder="现从事专业" type="text" name="nowMajor" lay-verify="required">\n' +
                '    </div>\n' +
                '  </div>\n' +
                '  <div class="layui-form-item">\n' +
                '    <label class="layui-form-label">工作单位</label>\n' +
                '    <div class="layui-input-block">' +
                '       <div id="deptName"></div>\n' +
                '    </div>\n' +
                '  </div>' +
                '<button class="layui-btn sub" lay-submit lay-filter="addUser" id="addUser">提交</button>\n' +
                '</form>',
            yes: function (index, layero) {
                $('#addUser').trigger('click');
            }, btn2: function (index, layero) {
                layer.close(index);
            },
            success: function (layero, index) {
                form.render();
                //渲染日期组件
                laydate.render({
                    elem: '#birthDate'
                    , type: 'year'
                });
                var selcetDept = xmSelect.render({
                    el: '#deptName',
                    tips: '工作单位',
                    name: 'deptName',
                    layVerify: 'required',
                    layVerType: 'msg',
                    radio: true,
                    clickClose: true,
                    filterable: true,
                    remoteSearch: true,
                    delay: 500,
                    remoteMethod: function (val, cb, show) {
                        //这里如果val为空, 则不触发搜索
                        if (!val) {
                            return cb([]);
                        }
                        //这里引入了一个第三方插件axios, 相当于$.ajax
                        $.ajax({
                            type: 'get',
                            url: domainName + '/api-c/company/link',
                            data: 'signName=' + val,
                            async: false,
                            success: function (data) {
                                var d = [];
                                $(data.data).each(function () {
                                    var obj = {name: this.signName, value: this.signName};
                                    d.push(obj);
                                })
                                cb(d);
                            }
                        })
                    }
                })
                //监听提交
                form.on('submit(addUser)', function (data) {
                    var d = data.field;
                    var user = {
                        username: d.username,
                        password: d.password,
                        nickname: d.nickname,
                        sex: d.sex,
                        type: 'BACKEND'
                    };
                    var userInfo = {
                        name: d.nickname,
                        phone: d.username,
                        sex: d.sex,
                        birthDate: Date.parse(new Date(d.birthDate)),
                        idType: d.idType,
                        idCard: d.idCard,
                        academicTitle: d.academicTitle,
                        nowMajor: d.nowMajor,
                        deptName: d.deptName
                    };
                    $.ajax({
                        type: 'post',
                        url: domainName + '/api-u/users-anon/register',
                        contentType: "application/json; charset=utf-8",
                        async: false,
                        data: JSON.stringify(user),
                        success: function (data) {
                            userInfo.userId = data.id;
                            node.val($('input[name=username]').val());
                            addUserInfo(userInfo, node, index);
                        }
                    });
                    return false;
                });
            }
        })
    });
}

//添加用户信息
function addUserInfo(userInfo, node, index) {
    $.ajax({
        type: 'post',
        url: domainName + '/project-user/user',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(userInfo),
        success: function (data) {
            userInfoUtil(data, node);
            layer.msg("成功", {shift: -1, time: 1000}, function () {
                layer.close(index);
            });
        }
    });
}