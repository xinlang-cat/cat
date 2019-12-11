
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