//form序列化为json
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");
	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
    return "";
}

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