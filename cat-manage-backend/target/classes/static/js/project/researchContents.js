/*研究内容*/
function researchContents(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/content/all/' + id,
        async : false,
        success: function (data) {
            var str1;
            var str2;
            var str3 = '';
            $(data).each(function () {
                str1 = '<thead>\n' +
                    '           <th colspan="4" style="text-align: center;font-weight: 600;">主要研究内容</th>\n' +
                    '       </thead>\n' +
                    '       <tbody>';
                str2 = '</tbody>';
                str3 += '<tr>\n' +
                    '       <td>'+this.headline+'</td>\n' +
                    '       <td>\n' +
                    '           <p>'+this.content+'</p>\n' +
                    '           <div class="operation">\n' +
                    '               <div class="layui-btn-group">\n' +
                    '                   <a href="../project/update_content.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                    '                       <i class="layui-icon">&#xe642;</i>\n' +
                    '                   </a>\n' +
                    '                   <a class="layui-btn layui-btn-sm" onclick="del('+this.id+');">\n' +
                    '                       <i class="layui-icon">&#xe640;</i>\n' +
                    '                   </a>\n' +
                    '               </div>\n' +
                    '           </div>\n' +
                    '        </td>\n' +
                    '     </tr>';
            })
            $('#content').append(str1);
            $('#content').append(str3);
            $('#content').append(str2);
        }
    })
}
function del(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/content/' + id,
            success: function (data) {
                window.location.reload();
                layer.msg("删除成功");
            }
        });
        layer.close(1);
    });
}
