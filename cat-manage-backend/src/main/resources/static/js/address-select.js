$(function () {
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
});

$("#provinceCode").change(function () {
    var provinceCode = $("#provinceCode").val();
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
});

$("#cityCode").change(function () {
    var cityCode = $("#cityCode").val();
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
});

$("#areaCode").change(function () {
    var areaCode = $("#areaCode").val();
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
});