function initplanCategorySelect(){
    $.ajax({
        type : 'get',
        url : domainName + '/api-label/label/tree/PZBM',
        async : false,
        success : function(data) {
            var select = $("#planCategory");
            //select.append("<option value=''>- 请选择 -</option>");
            var ds = data[0].child;
            for(var i=0; i<ds.length; i++){
                var d = ds[i];
                var sign = d['id'];
                var name = d['content'];

                select.append("<option value='"+ sign +"'>" +name+"</option>");
                //   }
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
                provinceName = data.province.provinceName,
                city = data.city.cityName,
                area = data.area.areaName,
                street = data.street.streetName;
            var str = provinceName+'-'+city+'-'+area+'-'+street+'-';
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
            $("#file").val(id);
            $("#contract").val(name);
        }
    });
}

