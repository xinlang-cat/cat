/*此js用于新增项目*/

//添加一行
function add_row(e, sign) {
    layui.use(['layer', 'form', 'laydate'], function () {
        var layer = layui.layer
            , form = layui.form
            , laydate = layui.laydate;
        var str = '';
        var tbody = $(e).parent().next().find('tbody');
        var rowspan = tbody.children().length + 1;//改变单元格所跨的行数
        tbody.children().first().children().first().attr('rowspan', rowspan);
        if (sign == 'RESEARCH_CONTENTS') {
            str = '<tr>\n' +
                '<td>研究内容' + rowspan + '\n' +
                '<input type="hidden" name="item_id">\n' +
                '</td>\n' +
                '<td>\n' +
                '<input class="form-control" lay-verify="required" placeholder="标题" type="text" name="title">\n' +
                '<br>\n' +
                '<textarea placeholder="内容" class="layui-textarea form-control" lay-verify="required" name="content"></textarea>\n' +
                '</td>\n' +
                '</tr>';
        } else if (sign == 'PROJECT_TEAM') {

            str = ' <tr>\n' +
                '                    <td>\n' +
                '                        <input class="form-control" placeholder="姓名" type="text" readonly>\n' +
                '                        <input type="hidden" name="item_id">\n' +
                '                        <input type="hidden" name="user_id">\n' +
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
        }
        tbody.append(str);
        //渲染
        lay('.date').each(function () {
            laydate.render({
                elem: this
                , showBottom: false
                , trigger: 'click'
            });
            $(".date").removeAttr("lay-key");
        });
    });
}

//删除一行
function del_row(e) {
    var dom = $(e).parent().next().find('tbody').children();
    if (dom.length == 1) {
        return;
    }
    $(e).parent().next().find('tbody').children().last().remove();
    var tbody = $(e).parent().next().find('tbody');
    tbody.children().first().children().first().attr('rowspan', tbody.children().length);
}

//添加考核指标类型
function addTarget() {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;
        index = layer.open({
            title: "添加指标",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
                '<blockquote class="layui-elem-quote layui-text">\n' +
                '  选择要添加的指标每点击一次”确定“按钮添加一条。\n' +
                '</blockquote>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">选择指标类型</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <select lay-verify="required" lay-filter="targetType" name="type" id="targetType">\n' +
                '        <option value=""></option>\n' +
                '      </select>\n' +
                '    </div>\n' +
                '  </div>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">指标条数：</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
                '    </div>\n' +
                '  </div>' +
                '</div>',
            yes: function (index, layero) {
                var text = $("#targetType option:selected").text();
                var type = $("#targetType").val();
                var nodes = $('#INDICATORS').find('input[name=type][value=' + type + ']');
                if (nodes.length != 0) {
                    //先判断是不是数量指标
                    var str = '';
                    if (type == 'QUANTITY_INDICATORS') {
                        str = '<tr>\n' +
                            '                    <td><input type="hidden" name="item_id">\n' +
                            '                        <input type="hidden" name="type" value="QUANTITY_INDICATORS">\n' +
                            '                        <input type="hidden" name="status" value="0">\n' +
                            '                        <select class="form-control input-sm" lay-verify="required" lay-ignore name="target">\n' +
                            '                           <option value="">请选择</option>\n' +
                            '                        </select></td>\n' +
                            '                    <td><input class="form-control" lay-verify="required" placeholder="数量" type="text" name="count"></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text"\n' +
                            '                               name="start_date" readonly></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text"\n' +
                            '                               name="end_date" readonly></td>\n' +
                            '                    <td>\n' +
                            '                        <select class="form-control input-sm site" lay-verify="required" lay-ignore>\n' +
                            '                            <option value=\'\'>请选择</option>\n' +
                            '                        </select>\n' +
                            '                        <input type="hidden" name="district">\n' +
                            '                        <button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                            '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                            '                            <i class="layui-icon">&#xe669;</i>\n' +
                            '                        </button>\n' +
                            '                    </td>' +
                            '                    <td><div class="userIds"></div></td>\n' +
                            '                </tr>';
                    } else {
                        str = '<tr>\n' +
                            '                    <td colspan="2"><input type="hidden" name="item_id">\n' +
                            '                        <input type="hidden" name="type" value="' + type + '">\n' +
                            '                        <input type="hidden" name="status" value="0">' +
                            '                        <input type="hidden" name="target" value=" ">\n' +
                            '                        <input type="hidden" name="count" value=" ">' +
                            '                        <textarea placeholder="内容" class="layui-textarea form-control" lay-verify="required"\n' +
                            '                                  name="content"></textarea></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text"\n' +
                            '                               name="start_date" readonly></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text"\n' +
                            '                               name="end_date" readonly></td>\n' +
                            '                    <td>\n' +
                            '                        <select class="form-control input-sm site" lay-verify="required" lay-ignore>\n' +
                            '                            <option value=\'\'>请选择</option>\n' +
                            '                        </select>\n' +
                            '                        <input type="hidden" name="district">\n' +
                            '                        <button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                            '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                            '                            <i class="layui-icon">&#xe669;</i>\n' +
                            '                        </button>\n' +
                            '                    </td>\n' +
                            '                    <td><div class="userIds"></div></td>\n' +
                            '                </tr>';
                    }
                    //改变单元格跨的行数，并添加
                    $('#INDICATORS').children().first().children().first().attr('rowspan', $('#INDICATORS').children().length + 1);
                    nodes.first().parent().prev().attr('rowspan', nodes.length + 1);
                    nodes.last().parent().parent().after(str);
                    $('#count').text(nodes.length + 1 + '条');//改变条数
                } else {
                    //先判断是不是数量指标
                    var str = '';
                    if (type == 'QUANTITY_INDICATORS') {
                        str = '<tr>\n' +
                            '                    <td rowspan="1">' + text + '</td>\n' +
                            '                    <td><input type="hidden" name="item_id">\n' +
                            '                        <input type="hidden" name="type" value="QUANTITY_INDICATORS">\n' +
                            '                        <input type="hidden" name="status" value="0">\n' +
                            '                        <select class="form-control input-sm" lay-verify="required" lay-ignore name="target">\n' +
                            '                           <option value="">请选择</option>\n' +
                            '                        </select></td>\n' +
                            '                    <td><input class="form-control" lay-verify="required" placeholder="数量" type="text" name="count"></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text"\n' +
                            '                               name="start_date" readonly></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text"\n' +
                            '                               name="end_date" readonly></td>\n' +
                            '                    <td>\n' +
                            '                        <select class="form-control input-sm site" lay-verify="required" lay-ignore>\n' +
                            '                            <option value=\'\'>请选择</option>\n' +
                            '                        </select>\n' +
                            '                        <input type="hidden" name="district">\n' +
                            '                        <button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                            '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                            '                            <i class="layui-icon">&#xe669;</i>\n' +
                            '                        </button>\n' +
                            '                    </td>\n' +
                            '                    <td><div class="userIds"></div></td>\n' +
                            '                </tr>';
                    } else {
                        str = ' <tr>\n' +
                            '                    <td rowspan="1">' + text + '</td>\n' +
                            '                    <td colspan="2"><input type="hidden" name="item_id">\n' +
                            '                        <input type="hidden" name="type" value="' + type + '">\n' +
                            '                        <input type="hidden" name="status" value="0">' +
                            '                        <input type="hidden" name="target" value=" ">\n' +
                            '                        <input type="hidden" name="count" value=" ">\n' +
                            '                        <textarea c placeholder="内容" class="layui-textarea form-control" lay-verify="required"\n' +
                            '                                  name="content"></textarea></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="开始时间" type="text"\n' +
                            '                               name="start_date" readonly></td>\n' +
                            '                    <td><input class="form-control date" lay-verify="required" placeholder="结束时间" type="text"\n' +
                            '                               name="end_date" readonly></td>\n' +
                            '                    <td>\n' +
                            '                        <select class="form-control input-sm site" lay-verify="required" lay-ignore>\n' +
                            '                            <option value=\'\'>请选择</option>\n' +
                            '                        </select>\n' +
                            '                        <input type="hidden" name="district">\n' +
                            '                        <button type="button" class="layui-btn layui-btn-xs" onclick="refresh(this);"\n' +
                            '                                style="position: absolute;right: 20px;top: 50%;margin-top:-11px;background-color: #e1e1e1;">\n' +
                            '                            <i class="layui-icon">&#xe669;</i>\n' +
                            '                        </button>\n' +
                            '                    </td>\n' +
                            '                    <td><div class="userIds"></div></td>\n' +
                            '                </tr>';
                    }
                    //改变单元格跨的行数，并添加
                    $('#INDICATORS').children().first().children().first().attr('rowspan', $('#INDICATORS').children().length + 1);
                    $('#INDICATORS').append(str);
                    $('#count').text('1条');//改变条数
                }
                initSelectData('INDICATORS_OF_LIBRARY', $('#INDICATORS').find('input[name=type][value=' + type + ']').last().parent().find('select[name=target]'));//初始化新添加的表格中的指标选择
                $('#INDICATORS').find('input[name=type][value=' + type + ']').last().parent().parent().find('button').trigger('click');//点击新添加的表格中的地点刷新按钮，起到初始化的作用
                //渲染人员选择
                var d = [];
                $('input[name=user_id]').each(function () {
                    var UId = $(this).val();
                    if(UId!=''){
                        var name = $(this).prev().prev().val();
                        var data = {name: name, value: UId, };
                        d.push(data)
                    }
                })
                xmSelect.render({
                    name: 'userIds',
                    layVerify: 'required',
                    layVerType: 'msg',
                    el: $('#INDICATORS').find('input[name=type][value=' + type + ']').last().parent().parent().find('.userIds')[0],
                    data: d
                })
                //渲染日期组件
                lay('.date').each(function () {
                    laydate.render({
                        elem: this
                        , showBottom: false
                        , trigger: 'click'
                    });

                });
            }
            , btn2: function (index, layero) {//关闭
                layer.close(index);
            }
        });
        initSelectData("TARGET_TYPE", $('#targetType'));
        form.render();
        //监听指标类型选择，统计条数
        form.on('select(targetType)', function (data) {
            var length = $('#INDICATORS').find('input[name=type][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

//删除指标
function deleteTarget() {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;
        index = layer.open({
            title: "删除指标",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
                '<blockquote class="layui-elem-quote layui-text">\n' +
                '  选择要删除的指标每点击一次”确定“按钮删除最后一条。\n' +
                '</blockquote>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">选择指标类型</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <select lay-verify="required" lay-filter="targetType" name="type" id="targetType">\n' +
                '        <option value=""></option>\n' +
                '      </select>\n' +
                '    </div>\n' +
                '  </div>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">指标条数：</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
                '    </div>\n' +
                '  </div>' +
                '</div>',
            yes: function (index, layero) {
                var type = $("#targetType").val();
                var nodes = $('#INDICATORS').find('input[name=type][value=' + type + ']');
                var tr_last = nodes.last().parent().parent();
                tr_last.remove();//删除
                //改变单元格跨的行数
                $('#INDICATORS').children().first().children().first().attr('rowspan', $('#INDICATORS').children().length);
                nodes.first().parent().prev().attr('rowspan', nodes.length - 1);
                $('#count').text((nodes.length - 1 < 0 ? 0 : nodes.length - 1) + '条');//改变条数

            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        initSelectData("TARGET_TYPE", $('#targetType'));
        form.render();
        //监听指标类型选择，统计条数
        form.on('select(targetType)', function (data) {
            var length = $('#INDICATORS').find('input[name=type][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

//添加经费
function addFund() {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;
        index = layer.open({
            title: "添加经费",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
                '<blockquote class="layui-elem-quote layui-text">\n' +
                '  选择要添加的费用每点击一次”确定“按钮添加一条。\n' +
                '</blockquote>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">选择费用类型</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <select lay-verify="required" lay-filter="fundType" name="type" id="fundType">\n' +
                '        <option value=""></option>\n' +
                '        <option value="0">直接费用</option>\n' +
                '        <option value="1">间接费用</option>\n' +
                '      </select>\n' +
                '    </div>\n' +
                '  </div>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">费用条数：</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
                '    </div>\n' +
                '  </div>' +
                '</div>',
            yes: function (index, layero) {
                var type = $("#fundType").val();
                var str = '<tr>\n' +
                    '                    <td><input type="hidden" name="item_id">\n' +
                    '                        <input type="hidden" name="type" value="' + type + '">\n' +
                    '                        <select class="form-control input-sm" lay-verify="required" lay-ignore name="subject">\n' +
                    '                            <option value=\'\'>请选择</option>\n' +
                    '                        </select></td>\n' +
                    '                    <td><input class="form-control" lay-verify="required" placeholder="金额（万元）" type="text"\n' +
                    '                               name="money"></td>\n' +
                    '                    <td><select class="form-control input-sm" lay-verify="required" lay-ignore name="source">\n' +
                    '                        <option value=\'\'>请选择</option>\n' +
                    '                    </select></td>\n' +
                    '                    <td><textarea placeholder="开支内容" class="layui-textarea form-control" lay-verify="required"\n' +
                    '                                  name="remark"></textarea>\n' +
                    '                    </td>\n' +
                    '                </tr>';
                $('#thead').attr('rowspan', $('#EXPENDITURE').children().length + 1);
                if (type == 0) {
                    $('#direct').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + type + ']').length + 1);
                    $('#interval').before(str);
                    $('#count').text($('#EXPENDITURE').find('input[name=type][value=' + type + ']').length + '条');//改变条数
                    initSelectData('BUDGET_SUBJECT', $('#interval').prev().find('select[name=subject]'));
                    initSelectData('FUNDING_SOURCE', $('#interval').prev().find('select[name=source]'));
                } else {
                    $('#indirect').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + type + ']').length + 1);
                    $('#EXPENDITURE').append(str);
                    $('#count').text($('#EXPENDITURE').find('input[name=type][value=' + type + ']').length + '条');//改变条数
                    initSelectData('BUDGET_SUBJECT', $('#EXPENDITURE').children().last().find('select[name=subject]'));
                    initSelectData('FUNDING_SOURCE', $('#EXPENDITURE').children().last().find('select[name=source]'));
                }
            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        form.render();
        //监听选择，统计条数
        form.on('select(fundType)', function (data) {
            var length = $('#EXPENDITURE').find('input[name=type][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

//删除经费
function deleteFund() {
    layui.use(['form', 'laydate'], function () {
        var form = layui.form
            , laydate = layui.laydate;
        index = layer.open({
            title: "删除经费",
            btn: ['确定', '关闭'],
            type: 1,
            area: ['800px', '500px'],
            content: '<div class="layui-form" style="margin: 20px auto;width: 80%;">' +
                '<blockquote class="layui-elem-quote layui-text">\n' +
                '  选择要删除的费用每点击一次”确定“按钮删除最后一条。\n' +
                '</blockquote>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">选择费用类型</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <select lay-verify="required" lay-filter="fundType" name="type" id="fundType">\n' +
                '        <option value=""></option>\n' +
                '        <option value="0">直接费用</option>\n' +
                '        <option value="1">间接费用</option>\n' +
                '      </select>\n' +
                '    </div>\n' +
                '  </div>' +
                '<div class="layui-form-item">\n' +
                '    <label class="layui-form-label" style="width: 120px">费用条数：</label>\n' +
                '    <div class="layui-input-block" style="margin-left: 120px;">\n' +
                '      <span style="line-height: 36px;font-size: 16px;margin-left: 10px;" id="count"></span>\n' +
                '    </div>\n' +
                '  </div>' +
                '</div>',
            yes: function (index, layero) {
                var type = $("#fundType").val();
                if (type == 0) {
                    if ($('#count').text() == '3条') {
                        return
                    }
                    $('#interval').prev().remove();//删除
                    $('#direct').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + type + ']').length);
                } else {
                    if ($('#count').text() == '2条') {
                        return
                    }
                    $('#EXPENDITURE').children().last().remove();//删除
                    $('#indirect').attr('rowspan', $('#EXPENDITURE').find('input[name=type][value=' + type + ']').length);
                }
                //改变单元格跨的行数
                $('#thead').attr('rowspan', $('#EXPENDITURE').children().length);
                $('#count').text($('#EXPENDITURE').find('input[name=type][value=' + type + ']').length + '条');//改变条数

            }, btn2: function (index, layero) {
                layer.close(index);
            }
        })
        form.render();
        //监听指标类型选择，统计条数
        form.on('select(fundType)', function (data) {
            var length = $('#EXPENDITURE').find('input[name=type][value=' + data.value + ']').length;
            $('#count').text(length + '条');
        });
    });
}

//创建用户
function createUser(userName,node) {
    layui.use(['form', 'laydate','util'], function () {
        var form = layui.form
            , laydate = layui.laydate
            ,util = layui.util;
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
                '       <div id="nowMajor"></div>\n' +
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
            success: function(layero, index){
                form.render();
                //渲染日期组件
                laydate.render({
                    elem: '#birthDate'
                    , type: 'year'
                });
                var selcetMajor = xmSelect.render({
                    el: '#nowMajor',
                    tips: '从事专业',
                    name: 'nowMajor',
                    layVerify: 'required',
                    layVerType: 'msg',
                    radio: true,
                    clickClose: true,
                    data: []
                })
                $.ajax({
                    type: 'get',
                    url: domainName + '/api-label/label/tree/INDUSTRY_GROUP',
                    async: false,
                    success: function (data) {
                        var d = [];
                        $(data[0].child).each(function () {
                            var obj = {name: this.content, value: this.sign};
                            d.push(obj);
                        })
                        selcetMajor.update({
                            data:d
                        });
                    }
                })
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
                   remoteMethod: function(val, cb, show){
                       //这里如果val为空, 则不触发搜索
                       if(!val){
                           return cb([]);
                       }
                       //这里引入了一个第三方插件axios, 相当于$.ajax
                       $.ajax({
                           type: 'get',
                           url: domainName + '/api-c/company/link',
                           data: 'signName='+val,
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
                    var user = {username:d.username,password:d.password,nickname:d.nickname,sex:d.sex,type:'BACKEND'};
                    var userInfo = {name:d.nickname,phone:d.username,sex:d.sex,birthDate:Date.parse(new Date(d.birthDate)),idType:d.idType,idCard:d.idCard,academicTitle:d.academicTitle,nowMajor:d.nowMajor,deptName:d.deptName};
                    $.ajax({
                        type : 'post',
                        url : domainName + '/api-u/users-anon/register',
                        contentType: "application/json; charset=utf-8",
                        async: false,
                        data : JSON.stringify(user),
                        success : function(data) {
                            userInfo.userId = data.id;
                            node.val($('input[name=username]').val());
                            addUserInfo(userInfo,node,index);
                        }
                    });
                    return false;
                });
            }
        })
    });
}
//添加用户信息
function addUserInfo(userInfo,node,index) {
    $.ajax({
        type : 'post',
        url : domainName + '/project-user/user',
        async: false,
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(userInfo),
        success : function(data) {
            userInfoUtil(data,node);
            layer.msg("成功", {shift: -1, time: 1000}, function(){
                layer.close(index);
            });
        }
    });
}