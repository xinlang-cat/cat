function queryInformation(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/information/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $('input[name=contract_no]').val(d.contract_no);
            $('input[name=type]').val(analysisLablename(d.type));
            $('input[name=name]').val(d.name);
            $('input[name=entrusting_party]').val(d.entrusting_party);
            company(d.responsible_unit);
            $('input[name=responsible_unit]').val(d.responsible_unit);
            $('input[name=management_unit]').val(d.management_unit);
            $('input[name=document_number]').val(d.document_number);
            $('textarea[name=overall_objective]').val(d.overall_objective);
            $('textarea[name=research_contents]').val(d.research_contents);

            $('input[name=status]').val(d.status);
            $('input').attr("readonly", "readonly");
        }
    })
}

function queryContactWay(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/contactWay/list',
        data: "item_id=" + id + "&status=2",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $('input[name=leaderName]').val(getUserName(d.leader));
            $('input[name=leaderPhone]').val(d.leader_phone);
        }

    })
}

function getUserName(userId) {
    var userName = '';
    $.ajax({
        type: 'get',
        url: domainName + '/project-user/user-anon/' + userId,
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data) {
            userName = data[0].name;
        }
    });
    return userName;
}

function company(name) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-c/company/name/' + name,
        success: function (data) {
            var address;
            $.ajax({
                type: 'get',
                url: domainName + '/map/province/one/' + data.provinceCode,
                async: false,
                success: function (data) {
                    address = data.provinceName;

                }
            });
            $.ajax({
                type: 'get',
                url: domainName + '/map/city/one/' + data.cityCode,
                async: false,
                success: function (data) {
                    address = address + data.cityName;
                }
            });
            $.ajax({
                type: 'get',
                url: domainName + '/map/area/one/' + data.areaCode,
                async: false,
                success: function (data) {
                    var name = data.areaName;
                    address = address + name;
                }
            });
            $.ajax({
                type: 'get',
                url: domainName + '/map/street/one/' + data.streetCode,
                async: false,
                success: function (data) {
                    address = address + data.streetName;
                }
            });
            address = address + data.address;

            $("#address").val(address);

        }
    });
}