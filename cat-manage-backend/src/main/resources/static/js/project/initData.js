function initplanCategorySelect(sign){
    $.ajax({
        type : 'get',
        url : domainName + '/api-label/label/tree/'+sign,
        async : false,
        success : function(data) {
            var select = $("#planCategory");
            //select.append("<option value=''>- 请选择 -</option>");
            var ds = data[0].child;
            for(var i=0; i<ds.length; i++){
                var d = ds[i];
                var sign = d['sign'];
                var name = d['content'];

                select.append("<option value='"+ sign +"'>" +name+"</option>");
                //   }
            }
        }
    });
}
function initTypeSelect(sign){
    $.ajax({
        type : 'get',
        url : domainName + '/api-label/label/tree/'+sign,
        async : false,
        success : function(data) {
            var select = $("#type");
            //select.append("<option value=''>- 请选择 -</option>");
            var ds = data[0].child;
            for(var i=0; i<ds.length; i++){
                var d = ds[i];
                var sign = d['sign'];
                var name = d['content'];

                select.append("<option value='"+ sign +"'>" +name+"</option>");
                //   }
            }
        }
    });
}
/*获取机构*/
function initCompanySelect(){
    $.ajax({
        type : 'get',
        url : domainName + '/api-c/company/all',
        async : false,
        success : function(data) {
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var sign = d['deptCode'];
                var name = d['signName'];
                $("#consignor").append("<option value='"+ sign +"'>" +name+"</option>");
                $("#undertaker").append("<option value='"+ sign +"'>" +name+"</option>");
                $("#supervisor_dept").append("<option value='"+ sign +"'>" +name+"</option>");
            }
        }
    });
}
/*获取监理*/
function initCompanyUserSelect(code){
    $.ajax({
        type : 'get',
        url : domainName + '/api-c/user/dept/'+code,
        async : false,
        success : function(data) {
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var sign = d['userId'];
                var name = d['name'];
                $("#supervisor").append("<option value='"+ sign +"'>" +name+"</option>");
            }
        }
    });
}

function getprovince(){
    $.ajax({
        type : 'get',
        url : domainName + '/map/province/all',
        async : false,
        success : function(data) {
            var select = $("#map");
            //select.append('<option value="">- 请选择 -</option>');
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.provinceCode;
                var name = d.provinceName;

                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

function getCity(provinceCode){
    $.ajax({
        type : 'get',
        url : domainName + '/map/city/all/'+provinceCode,
        async : false,
        success : function(data) {
            var select = $("#map");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.cityCode;
                var name = d.cityName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

function getArea(CityCode){
    $.ajax({
        type : 'get',
        url : domainName + '/map/area/all/'+CityCode,
        async : false,
        success : function(data) {
            var select = $("#map");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.areaCode;
                var name = d.areaName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

function getStreet(AreaCode){
    $.ajax({
        type : 'get',
        url : domainName + '/map/street/all/'+AreaCode,
        async : false,
        success : function(data) {
            var select = $("#map");
            for(var i=0; i<data.length; i++){
                var d = data[i];
                var code = d.streetCode;
                var name = d.streetName;
                select.append("<option value='"+ code +"'>" +name+"</option>");
            }
        }
    });
}

/*获取上级地区*/
function getsuperior(code){
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

             select.append("<option value='"+ code +"'>" +str+"</option>");
        }
    });
}

function getContractFile(id){
    $.ajax({
        type : 'get',
        url : domainName + '/api-f/files/'+id,
        async : false,
        success : function(data) {
            var id = data.id,
                name = data.name;
            $("#file").val(name);
            $("#contract").val(id);
        }
    });
}

