//显示地址，但无法更改
function getProvince(provinceCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/one/'+provinceCode,
        async : false,
        success : function(data) {
            $("#province").val(data.provinceName);
        }
    });
}

function getCity(cityCode){
    $.ajax({
        type : 'get',
        url : domainName + '/map/city/one/'+cityCode,
        async : false,
        success : function(data) {
            $("#city").val(data.cityName);
        }
    });
}

function getArea(areaCode){
    $.ajax({
        type : 'get',
        url : domainName + '/map/area/one/'+areaCode,
        async : false,
        success : function(data) {
            var name = data.areaName;
            $("#area").val(name);
        }
    });
}

function getStreet(streetCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/map/street/one/'+streetCode,
        async : false,
        success : function(data) {
            $("#street").val(data.streetName);
        }
    });
}
//显示地址，并且可更改
function getAddress(provinceCode,cityCode,areaCode,streetCode) {
    province();
    $("#provinceCode").val(provinceCode);
    city(provinceCode);
    $("#cityCode").val(cityCode);
    area(cityCode);
    $("#areaCode").val(areaCode);
    street(areaCode);
    $("#streetCode").val(streetCode);
}

function province() {
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/all',
        async : false,
        success : function(data) {
            var select = $("#provinceCode");
            select.append("<option value=''>请选择省份</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.provinceCode;
                var name = d.provinceName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

function city(provinceCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/map/city/all/'+provinceCode,
        async : false,
        success : function(data) {
            var select = $("#cityCode");
            $("#cityCode,#areaCode,#streetCode").empty();
            select.append("<option value=''>请选择城市</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.cityCode;
                var name = d.cityName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

function area(cityCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/map/area/all/'+cityCode,
        async : false,
        success : function(data) {
            var select = $("#areaCode");
            $("#areaCode,#streetCode").empty();
            select.append("<option value=''>请选择区县</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.areaCode;
                var name = d.areaName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}
function street(areaCode) {
    $.ajax({
        type : 'get',
        url : domainName + '/map/street/all/'+areaCode,
        async : false,
        success : function(data) {
            var select = $("#streetCode");
            $("#streetCode").empty();
            select.append("<option value=''>请选择街道/镇</option>");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.streetCode;
                var name = d.streetName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

