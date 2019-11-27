/*获取基本信息*/
var status;

function getBasic(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/' + id,
        async: false,
        success: function (data) {
            status = data.status;
            var str = '<thead>\n' +
                '                </thead>\n' +
                '                <tbody>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">合同编号</td>\n' +
                '                        <td>\n' +
                '                            <p id="contract_number">' + data.contract_number + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">计划类别</td>\n' +
                '                        <td>\n' +
                '                            <p id="plan_category">' + data.plan_category + '</p>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">项目名称</td>\n' +
                '                        <td>\n' +
                '                            <p id="item_name">' + data.item_name + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">计划批次</td>\n' +
                '                        <td>\n' +
                '                            <p id="batch">' + data.batch + '</p>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">委托单位</td>\n' +
                '                        <td>\n' +
                '                            <p id="consignor">' + data.consignor + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">承担单位</td>\n' +
                '                        <td>\n' +
                '                            <p id="undertaker">' + data.undertaker + '</p>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">管理单位</td>\n' +
                '                        <td>\n' +
                '                            <p id="supervisor_dept">' + data.supervisor_dept + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">管理代表</td>\n' +
                '                        <td>\n' +
                '                            <p id="supervisor">' + data.supervisor + '</p>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">实施地点</td>\n' +
                '                        <td>\n' +
                '                            <p id="district">' + data.district + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">合同文件</td>\n' +
                '                        <td>\n' +
                '                            <a id="contract_file" href="">' + data.contract_file + '</a>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">开始时间</td>\n' +
                '                        <td>\n' +
                '                            <p id="start_dateStr">' + data.start_dateStr + '</p>\n' +
                '                        </td>\n' +
                '                        <td style="font-weight: 600;">结束时间</td>\n' +
                '                        <td>\n' +
                '                            <p id="end_dateStr">' + data.end_dateStr + '</p>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                    <tr>\n' +
                '                        <td style="font-weight: 600;">总体目标</td>\n' +
                '                        <td colspan="3">\n' +
                '                            <p id="overall_objective">' + data.overall_objective + '</p>\n' +
                '                            <div class="operation">\n' +
                '                                <div class="layui-btn-group">\n' +
                '                                    <a href="updateBasic.html?id=' + id + '" class="layui-btn layui-btn-sm">\n' +
                '                                        <i class="layui-icon">&#xe642;</i>\n' +
                '                                    </a>\n' +
                '                                    <a href="#?id=' + id + '" class="layui-btn layui-btn-disabled layui-btn-sm">\n' +
                '                                        <i class="layui-icon">&#xe640;</i>\n' +
                '                                    </a>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </td>\n' +
                '                    </tr>\n' +
                '                </tbody>';
            $("#basis").append(str);
            planCategory(data.plan_category);
            district(data.district);
            contractFile(data.contract_file);
        }
    });
}

/*解析计划类别*/
function planCategory(sign) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            var name = data[0].content;
            $("#plan_category").text(name);
        }
    });
}

/*解析地点*/
function district(code) {
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/tree/' + code,
        async: false,
        success: function (data) {
            var select = $("#map"),
                provinceName,
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
            $("#district").text(str);
        }
    });
}

/*解析合同文件*/
function contractFile(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-f/files/' + id,
        async: false,
        success: function (data) {
            var url = data.url,
                name = data.name;
            //$("#file").val(name);
            $("#contract_file").text(name);
            $("#contract_file").attr("href", url);
        }
    });
}

function bntShow() {

    if (status == 0) {
        $(".btn-box>div").css("display", "block");
        $("table tr td").mouseover(function() {
            $(this).find('div').css("display", "initial");
        });
        $("table tr td").mouseout(function() {
            $(this).find('div').css("display", "none");
        });
    } else {
        $(".btn-box>div:last").css("display", "block");
    }
}