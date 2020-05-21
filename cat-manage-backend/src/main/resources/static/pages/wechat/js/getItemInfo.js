

function queryInformation(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/information/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $('#contract_no').text(d.contract_no);
            $('#type').text(analysisLablename(d.type));
            $('#name').text(d.name);
            $('#entrusting_party').text(d.entrusting_party);
            $('#responsible_unit').text(d.responsible_unit);
            $('#management_unit').text(d.management_unit);
            $('#document_number').text(d.document_number);
            $('#overall_objective').text(d.overall_objective);
            $('#research_contents').text(d.research_contents);
            $('#period').text(d.start_date.substring(0, 7) + ' - ' + d.end_date.substring(0, 7));
            $('#status').text(d.status);

            $('#site').text(province(d.province_code) + '-' + city(d.city_code) + '-' + area(d.area_code) + '-' + street(d.street_code));
        }
    })
}

function analysisLablename(sign) {
    var text = '';
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            text = data[0].content;
        }
    });
    return text;
}

function province(provinceCode) {
    var provinceName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/one/' + provinceCode,
        async: false,
        success: function (data) {
            provinceName = data.provinceName;
        }
    });
    return provinceName;
}

function city(cityCode) {
    var cityName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/city/one/' + cityCode,
        async: false,
        success: function (data) {
            cityName = data.cityName;
        }
    });
    return cityName;
}

function area(areaCode) {
    var areaName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/area/one/' + areaCode,
        async: false,
        success: function (data) {
            areaName = data.areaName;
        }
    });
    return areaName;
}

function street(streetCode) {
    var streetName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/street/one/' + streetCode,
        async: false,
        success: function (data) {
            streetName = data.streetName
        }
    });
    return streetName;
}