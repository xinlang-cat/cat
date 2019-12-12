
/*初始化计划类别、指标类型等（标签）*/
function initSelectData(sign,node) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            var ds = data[0].child;
            $(ds).each(function () {
                node.append('<option value=' + this.sign + '>' + this.content + '</option>');
            });
        }
    })
}
/*初始化公司、机构*/
function initCompanySelect() {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/all',
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.deptCode,
                    name = this.signName;
                $('#consignor').append('<option value=' + code + '>' + name + '</option>');
                $('#undertaker').append('<option value=' + code + '>' + name + '</option>');
                $('#administrator').append('<option value=' + code + '>' + name + '</option>');
            });
        }
    })
}
/*初始化地区*/
function initProvinceSelect(){
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/all',
        async : false,
        success : function(data) {
            $(data).each(function () {
                var code = this.provinceCode;
                var name = this.provinceName;
                $(".site").append("<option value='"+ code +"'>" +name+"</option>");
            })
        }
    });
}
function getProvince(node){
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/all',
        async : false,
        success : function(data) {
            $(data).each(function () {
                var code = this.provinceCode;
                var name = this.provinceName;
                $(node).append("<option value='"+ code +"'>" +name+"</option>");
            })
        }
    });
}
function getCity(provinceCode,node){
    $.ajax({
        type : 'get',
        url : domainName + '/map/city/all/'+provinceCode,
        async : false,
        success : function(data) {
            $(data).each(function () {
                var code = this.cityCode;
                var name = this.cityName;
                $(node).append("<option value='"+ code +"'>" +name+"</option>");
            })
        }
    });
}
function getArea(CityCode,node){
    $.ajax({
        type : 'get',
        url : domainName + '/map/area/all/'+CityCode,
        async : false,
        success : function(data) {
            $(data).each(function () {
                var code = this.areaCode;
                var name = this.areaName;
                $(node).append("<option value='"+ code +"'>" +name+"</option>");
            })
        }
    });
}
function getStreet(AreaCode,node){
    $.ajax({
        type : 'get',
        url : domainName + '/map/street/all/'+AreaCode,
        async : false,
        success : function(data) {
            $(data).each(function () {
                var code = this.streetCode;
                var name = this.streetName;
                $(node).append("<option value='"+ code +"'>" +name+"</option>");
            })
        }
    });
}
//查询当前用户所在的公司代码
var deptCode;
function queryDeptCode() {
    $.ajax({
        type : 'get',
        url : domainName + '/api-c/company/now-user',
        async : false,
        success : function(data) {
            deptCode = data.deptCode;
        }
    });
}
//初始化人员选择
function initUserSelect(deptCode,node) {
    $.ajax({
        type : 'get',
        url : domainName + '/api-c/user/dept/'+deptCode,
        async : false,
        success : function(data) {
            $(data).each(function () {
                var userId = this.userId;
                var name = this.name;
                node.append("<option value='"+ userId +"'>" +name+"</option>");
            })
        }
    });
}
//查询用户信息
function queryUserInfo(usetId,node) {
    $.ajax({
        type : 'get',
        url : domainName + '/project-user/user-anon/'+usetId,
        async : false,
        success : function(data) {
            var d = data[0];
            var sex = d.sex || '',
                birthDate = d.birthDate || '',
                idType = d.idType || '',
                idCard = d.idCard || '',
                academicTitle = d.academicTitle || '',
                nowMajor = d.nowMajor || '',
                deptName = d.deptName || '';
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
            node.parent().nextAll().eq(0).children().eq(0).val(sex);
            node.parent().nextAll().eq(1).children().eq(0).val(age);
            node.parent().nextAll().eq(2).children().eq(0).val(idType+'/'+idCard);
            node.parent().nextAll().eq(3).children().eq(0).val(academicTitle);
            node.parent().nextAll().eq(4).children().eq(0).val(nowMajor);
            node.parent().nextAll().eq(5).children().eq(0).val(deptName);
        }
    });
}
