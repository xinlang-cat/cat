
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
function queryDeptCode(id) {
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/'+id,
        async : false,
        success : function(data) {
            deptCode = data.dept_code;
        }
    });
}
//初始化人员选择
function initUserSelect(deptCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/api-c/user/dept/'+deptCode,
        async : false,
        success : function(data) {
            var select = $("#userSelect");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var userId = d.userId;
                var name = d.name;
                select.append("<option value='"+ userId +"'>" +name+"</option>");
            }
        }
    });
}