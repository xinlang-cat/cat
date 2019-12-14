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
                str += '<tr>' +
                    '<td>' +
                    '<span>' + this.title + '</span>' +
                    '<p>' + this.content + '</p>' +
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
            var targetTypes = getTargetTypeAll('targetType');//获取全部指标类型
            $(targetTypes).each(function () {
                var targetType = this;
                var str1 = '';
                var str2 = '';
                var str3 = '';
                var str4='';
                $(data).each(function () {
                    if (targetType.sign == this.type) {

                        str1 = '<div class="module target">\n' +
                            '            <table lay-even lay-skin="nob" class="layui-table">\n' +
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
                            '<td style="text-align: center;">'+this.target+'</td>\n' +
                            '<td style="text-align: center;">'+this.count+'</td>\n' +
                            '<td style="text-align: center;">'+this.start_date.substring(0, 10)+'至'+this.end_date.substring(0, 10)+'</td>\n' +
                            '<td style="text-align: center;" id="district"></td>\n' +
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
                            '</div>'+
                            '</td>' +
                            '</tr>';
                  /*      getsuperior(this.district);*/
                        str4+=' <tr>' +
                            '<td style="text-align: center;">'+this.target+'</td>' +
                            ' <td style="text-align: center;">'+this.start_date.substring(0, 10)+'至'+this.end_date.substring(0, 10)+'</td>' +
                            '<td style="text-align: center;"></td>' +
                            '<td style="text-align: center;"></td>' +
                            '</tr>'
                    }
                })
                $('#forma').append(str1+str2+str3);
                $("#qita").append(str4);
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

/*function getsuperior(code){
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/tree/'+code,
        async : false,
        success : function(data) {
            var select = $("#district"),
                provinceName,
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

    /!*        select.append("<option value='"+ code +"'>" +str+"</option>");*!/
        }
    });
}*/


