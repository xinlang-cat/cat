function getbasic(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/basic/list',
        data: "id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var d = data[0];
            $("#contract_no").text(d.contract_no);
            $("#category").text(d.category);
            $("#item_name").text(d.item_name);
            $("#consignor").text(d.consignor);
            $("#undertaker").text(d.undertaker);
            $("#administrator").text(d.administrator);
            $("#item_number").text(d.item_number);
            $("#start_date").text(d.start_date);
            $("#end_date").text(d.end_date);
            $("#outline").text(d.outline);
        }
    })
}
function getcontent(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/content/list',
        data: "item_id=" + id,
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var str = '';
            $(data).each(function () {
                str += '<tr>' +
                    '<td>' +
                    '<span>'+this.title+'</span>' +
                    '<p>'+this.content+'</p>' +
                    '</td>' +
                    '</tr>';
            })
            $("#mian-content").append(str);
        }
    })
}
