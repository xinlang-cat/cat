
function add_basics(params) {
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/basic',
        contentType: "application/json; charset=utf-8",
        async: false,
        data: params,
        success: function (data) {
            $("input[name='item_id']").val(data.id);
        }
    });
}

function add_content() {
    let str;
    str = '  <tr>' +
        '<input name="item_id" type="hidden">'+
        ' <td style="text-align: center;">  <input style="border: none;" class="form-control" placeholder="标题" type="text" name="title" maxlength="50"' +
        ' data-bv-notempty="true"\n' +
        ' data-bv-notempty-message="标题 不能为空"></td>' +
        '<td style="text-align: center;">     <input style="border: none;" class="form-control" placeholder="内容" type="text" name="content" maxlength="50"' +
        ' data-bv-notempty="true"\n' +
        '  data-bv-notempty-message="内容 不能为空"></td>' +
        ' </tr>'

    $("#maincontent").append(str);
}

function add_content2(params) {
    $.ajax({
        type: 'post',
        url: domainName + '/project-item/item/content/multi',
        async: false,
        contentType: "application/json; charset=utf-8",
        data: params,
        success: function (data) {
            alert("成功添加内容");
        }
    });

}