/*获取地点*/
function getsuperior(code) {
    var text = '';
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/tree/' + code,
        async: false,
        success: function (data) {
            var provinceName,
                city,
                area,
                street;
            var str;
            if (data.street != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                street = data.street.streetName;
                str = provinceName + '-' + city + '-' + area + '-' + street + '-';
            } else if (data.area != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                area = data.area.areaName;
                str = provinceName + '-' + city + '-' + area + '-';
            } else if (data.city != undefined) {
                provinceName = data.province.provinceName;
                city = data.city.cityName;
                str = provinceName + '-' + city + '-';
            } else if (data.province != undefined) {
                provinceName = data.province.provinceName;
                str = provinceName + '-';
            }
            text = str;
        }
    });
    return text;
}
function getItemName(id) {
    $.ajaxSettings.async = false;
    var data = '';
    $.get(domainName + "/project-item/item/information/list?id=" + id, function (res) {
        data = res[0]['name'];
    })
    $.ajaxSettings.async = true;
    return data;
}
function getUserName(user_id) {
    $.ajaxSettings.async = false;
    var data = '';
    $.get(domainName + "/project-user/user-anon/" + user_id, function (res) {
        data = res[0]['name'];
    })
    $.ajaxSettings.async = true;
    return data;
}
function getTime(apply_time) {
    var now = new Date(apply_time);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    return year + "/" + fixZero(month, 2) + "/" + fixZero(date, 2);
}
function getStatus(status) {
    var con = '';
    if (status == 1) {
        con = "待查定";
    } else if (status == 2) {
        con = "查定中";
    } else if (status == 3) {
        con = "查定合格";
    } else if (status == 4) {
        con = "查定不合格";
    } else if (status == 5) {
        con = "查定申请未通过";
    }
    return con;
}
function getTargetType(id) {
    var name = '';
    $.ajaxSettings.async = false;
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/indicators/list',
        contentType: "application/json; charset=utf-8",
        data: {
            id: id
        },
        success: function (data) {

            $.get(domainName + "/api-label/label/tree/" + data[0]['type'], function (res) {
                name = res[0].content;
            })

        }
    });
    $.ajaxSettings.async = true;

    return name;
}