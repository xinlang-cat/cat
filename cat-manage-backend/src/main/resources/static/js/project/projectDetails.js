
/*获取基本信息*/
function getBasic(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/'+id,
        async : false,
        success : function(data) {
            $("#contract_number").text(data.contract_number);
            //$("#plan_category").text(data.plan_category);
            getPlanCategory(data.plan_category);
            $("#item_name").text(data.item_name);
            $("#batch").text(data.batch);

            $("#consignor").text(data.consignor);
            $("#undertaker").text(data.undertaker);
            $("#supervisor_dept").text(data.supervisor_dept);
            $("#supervisor").text(data.supervisor);

            //$("#district").text(data.district);
            getDistrict(data.district);
            //$("#contract_file").text(data.contract_file);
            getContractFile(data.contract_file);
            $("#start_dateStr").text(data.start_dateStr);
            $("#end_dateStr").text(data.end_dateStr);

            $("#overall_objective").text(data.overall_objective);
        }
    });
}

function getPlanCategory(sign){
    $.ajax({
        type : 'get',
        url : domainName + '/api-label/label/tree/'+sign,
        async : false,
        success : function(data) {
            var name = data[0].content;
            $("#plan_category").text(name);
        }
    });
}

/*获取地区*/
function getDistrict(code){
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/tree/'+code,
        async : false,
        success : function(data) {
            var select = $("#map"),
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
            $("#district").text(str);
        }
    });
}
/*获取合同文件*/
function getContractFile(id){
    $.ajax({
        type : 'get',
        url : domainName + '/api-f/files/'+id,
        async : false,
        success : function(data) {
            var url = data.url,
                name = data.name;
            //$("#file").val(name);
            $("#contract_file").text(name);
            $("#contract_file").attr("href",url);
        }
    });
}

/*获取资金构成*/
function getProjectFund(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/fund/all/'+id,
        async : false,
        success : function(data) {
            var count=0;
            var str = '';
            $(data).each(function () {
                count+=this.money;
                str += '<tr>'
                    + '<th>' + this.source + '</th>'
                    + ' <th style="text-align: center;">' + this.money + '</th>'
                    + ' <th>' + this.remark + '</th>'
                    + ' <th>'

                    + '</th>'  + ' <div style="width: 100%;height: 20px;align-self: center;">'
                    + '<button onclick="updat_fund('+this.id+')" style="height: 20px;width: 30px;" class="layui-btn layui-btn-sm">'
                    + '<p style="line-height: 20px;margin-left: -3px;"><i class="layui-icon">&#xe642;</i></p>'
                    + '</button>'
                    + '<button onclick="delete_fund('+this.id+')" style="height: 20px;width: 30px;align-self: center;"   class="layui-btn layui-btn-sm">  <p style="line-height: 20px;margin-left: -3px;"><i  class="layui-icon">&#xe640;</i></p>'
                    + '</button>'
                    + '</div>'
                    + '</tr>';
            });
            $("#summation").text(count);
            $("#TBODY_Fund").append(str);
        }
    });
}
/*获取资金预算*/
function getFundBudget(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/budget/all/'+id,
        async : false,
        success : function(data) {
            var count=0;
            var str = '';
            $(data).each(function () {
                count+=this.money;
                str += '<tr>'
                    + '<th>' + this.subject + '</th>'
                    + ' <th style="text-align: center;">' + this.money + '</th>'
                    + '<th></th>'
                    + ' <th>' + this.content + '</th>'
                    + ' <th>'
                    + ' <div style="width: 100%;height: 20px;align-self: center;">'
                    + '<button onclick="updat_budget('+this.id+')" style="height: 20px;width: 30px;" class="layui-btn layui-btn-sm">'
                    + '<p style="line-height: 20px;margin-left: -3px;"><i class="layui-icon">&#xe642;</i></p>'
                    + '</button>'
                    + '<button onclick="delete_budget('+this.id+');" style="height: 20px;width: 30px;align-self: center;"   class="layui-btn layui-btn-sm">  <p style="line-height: 20px;margin-left: -3px;"><i  class="layui-icon">&#xe640;</i></p>'
                    + '</button>'
                    + '</div>'
                    + '</th>'
                    + '</tr>';
            });
            $("#summation_budget").text(count);
            $("#TABLE_YUSUAN").append(str);
        }
    });
}

/*获取项目成员*/
function getProjectUser(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/user/all/'+id,
        async : false,
        success : function(data) {
            var str = '';
            $(data).each(function () {
                var responsible = this.itemUser.responsible || '',
                    name = this.projectUser[0].name || '',
                    sex = this.projectUser[0].sex || '',
                    birthDate = this.projectUser[0].birthDate || '',
                    idType = this.projectUser[0].idType || '',
                    idCard = this.projectUser[0].idCard || '',
                    academicTitle = this.projectUser[0].academicTitle || '',
                    nowMajor = this.projectUser[0].nowMajor || '',
                    deptName = this.projectUser[0].deptName || '';
                if(sex==1){
                    sex='男';
                }else {
                    sex='女';
                }
                // 获得今天的时间，计算年龄
                var date = new Date();
                var startDate = new Date(birthDate);
                var newDate = date.getTime() - startDate.getTime();
                var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 /365);
                if (isNaN(age)){
                    age = "";
                }
                str += '<tr>\n' +
                    '                <td style="text-align: center;">'+name+'</td>\n' +
                    '                <td style="text-align: center;">'+sex+'</td>\n' +
                    '                <td style="text-align: center;">'+age+'</td>\n' +
                    '                <td style="text-align: center;">'+idType+'/'+idCard+'</td>\n' +
                    '                <td style="text-align: center;">'+academicTitle+'</td>\n' +
                    '                <td style="text-align: center;">'+nowMajor+'</td>\n' +
                    '                <td style="text-align: center;">'+deptName+'</td>\n' +
                    '                <td style="text-align: center;">'+responsible+'</td>\n' +
                    '                <td>\n' +
                    '                    <div style="width: 100%;height: 20px;align-self: center;">\n' +
                    '                        <button style="height: 20px;width: 30px;" class="layui-btn layui-btn-sm" ' +
                    '                            onclick="updat_Personnel('+this.itemUser.id+')">\n' +
                    '                            <p style="line-height: 20px;margin-left: -3px;"> <i class="layui-icon">&#xe642;</i>\n' +
                    '                            </p>\n' +
                    '                        </button>\n' +
                    '                        <button style="height: 20px;width: 30px;align-self: center;"class="layui-btn layui-btn-sm"' +
                    '                            onclick="delete_Personnel('+this.itemUser.id+')">\n' +
                    '                            <p style="line-height: 20px;margin-left: -3px;"> <i class="layui-icon">&#xe640;</i>\n' +
                    '                            </p>\n' +
                    '                        </button>\n' +
                    '                    </div>\n' +
                    '                </td>\n' +
                    '            </tr>'
            })
            $("#projectUser").append(str);
        }
    });
}