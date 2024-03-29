/*初始化计划类别、指标类型等（标签）*/
function initSelectData(sign, node) {
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
function getMyCompany(node) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/now-user',
        async: false,
        success: function (data) {
            node.val(data.signName);
            $('.fundSource').find('input[name=responsible_unit]').val(data.signName);
            $.ajax({
                type: 'get',
                url: domainName + '/project-user/users',
                contentType: "application/json; charset=utf-8",
                async: false,
                data: 'deptName=' + data.signName,
                success: function (d) {
                    var options = '';
                    $(d.data).each(function () {
                        options += '<option value="' + this.userId + '">' + this.name + '</option>';
                    });
                    $('#leader_2').append(options);
                }
            });
        }
    })
}

/*初始化地区*/
function initProvinceSelect() {
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/all',
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.provinceCode;
                var name = this.provinceName;
                $(".site").append("<option value='" + code + "'>" + name + "</option>");
            })
        }
    });
}

function getProvince(node) {
    $.ajax({
        type: 'get',
        url: domainName + '/map/province/all',
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.provinceCode;
                var name = this.provinceName;
                $(node).append("<option value='" + code + "'>" + name + "</option>");
            })
        }
    });
}

function getCity(provinceCode, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/map/city/all/' + provinceCode,
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.cityCode;
                var name = this.cityName;
                $(node).append("<option value='" + code + "'>" + name + "</option>");
            })
        }
    });
}

function getArea(CityCode, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/map/area/all/' + CityCode,
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.areaCode;
                var name = this.areaName;
                $(node).append("<option value='" + code + "'>" + name + "</option>");
            })
        }
    });
}

function getStreet(AreaCode, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/map/street/all/' + AreaCode,
        async: false,
        success: function (data) {
            $(data).each(function () {
                var code = this.streetCode;
                var name = this.streetName;
                $(node).append("<option value='" + code + "'>" + name + "</option>");
            })
        }
    });
}

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

//查询当前用户所在的公司代码
var deptCode;

function queryDeptCode() {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/now-user',
        async: false,
        success: function (data) {
            deptCode = data.deptCode;
        }
    });
}

//初始化人员选择
function initUserSelect(deptCode, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/user/dept/' + deptCode,
        async: false,
        success: function (data) {
            $(data).each(function () {
                var userId = this.userId;
                var name = this.name;
                node.append("<option value='" + userId + "'>" + name + "</option>");
            })
        }
    });
}

//查询用户信息
function queryUserInfo(usetId, node) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user-anon/' + usetId,
        async: false,
        success: function (data) {
            var d = data[0];
            userInfoUtil(d, node);
        }
    });
}

//用于数据填入表单
function userInfoUtil(d, node) {
    var sex = d.sex || '',
        birthDate = d.birthDate || '',
        idType = d.idType || '',
        idCard = d.idCard || '',
        academicTitle = d.academicTitle || '',
        nowMajor = d.nowMajor || '',
        deptName = d.deptName || '',
        name = d.name || '';
    /*    if(d.nowMajor!=''){
            nowMajor = analysisLablename(d.nowMajor);
        }*/
    if (sex == 1) {
        sex = '男';
    } else {
        sex = '女';
    }
    // 获得今天的时间，计算年龄
    var date = new Date();
    var startDate = new Date(birthDate);
    var newDate = date.getTime() - startDate.getTime();
    var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);
    if (isNaN(age)) {
        age = "";
    }
    node.parent().prevAll().eq(6).children('input').eq(0).val(name);
    node.parent().prevAll().eq(6).children('input').eq(2).val(d.userId);//userId
    node.parent().prevAll().eq(5).children('input').eq(0).val(sex);
    node.parent().prevAll().eq(4).children('input').eq(0).val(age);
    node.parent().prevAll().eq(3).children('input').eq(0).val(idType + '/' + idCard);
    node.parent().prevAll().eq(2).children('input').eq(0).val(academicTitle);
    node.parent().prevAll().eq(1).children('input').eq(0).val(nowMajor);
    node.parent().prevAll().eq(0).children('input').eq(0).val(deptName);
}

/*解析标签名称*/
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

/*获取机构*/
function initCompanySelect() {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/all',
        async: false,
        success: function (data) {
            $(data).each(function () {
                if (this.identity == 'PARTY_A') {
                    $("#entrusting_party").append("<option value='" + this.signName + "'>" + this.signName + "</option>");
                } else if (this.identity == 'PARTY_C') {
                    $("#management_unit").append("<option value='" + this.signName + "'>" + this.signName + "</option>");
                }
            })
        }
    });
}