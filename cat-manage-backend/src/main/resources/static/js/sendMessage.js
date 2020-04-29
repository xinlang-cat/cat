function sendMeaasge(content,title,userIds) {
    var createUserId;
    var createUserName;
    var createTime = new Date();

    $.ajax({
        type: 'get',
        url: domainName + '/api-u/users/current',
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            createUserId = data.id;
            createUserName = data.username;

        }
    });

    var message = {
        "createUserId": createUserId,
        "createUserName": createUserName,
        "content": content,
        "title": title,
        "createTime": createTime,
        "userIds": userIds
    };
    $.ajax({
        type: 'post',
        url: domainName + '/api-n/message',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(message),
        success: function (data) {

        }
    });
}
function getPARTY_C(id) {

    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/personnel/list',
        data: "item_id=" + id + "&user_type=PARTY_C",
        async: false,
        success: function (data) {
            $(data).each(function () {
                id = this.user_id;
            })
        }
    })

    return id;
}

function getPARTY_B(id) {

    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/personnel/list',
        data: "item_id=" + id + "&user_type=PARTY_B_PRINCIPAL",
        async: false,
        success: function (data) {
            $(data).each(function () {
                id = this.user_id;
            })
        }
    })

    return id;
}