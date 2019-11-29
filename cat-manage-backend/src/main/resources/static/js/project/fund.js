/*资金构成*/
function fundSource(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/fund/all/'+id,
        async : false,
        success : function(data) {
            var str1;
            var str2;
            var str3 = '';
            $(data).each(function () {
                str1 = '<thead>\n' +
                    '       <tr>\n' +
                    '           <th>来源</th>\n' +
                    '           <th>金额(万元)</th>\n' +
                    '           <th>备注</th>\n' +
                    '       </tr>\n' +
                    '   </thead>\n' +
                    '   <tbody>';
                str2 = '</tbody>';
                str3 += '<tr>\n' +
                    '       <td>'+this.source+'</td>\n' +
                    '       <td>'+this.money+'</td>\n' +
                    '       <td>\n' +
                    '           <p>'+ this.remark+'</p>\n' +
                    '           <div class="operation">\n' +
                    '               <div class="layui-btn-group">\n' +
                    '                   <a href="updateFund.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                    '                       <i class="layui-icon">&#xe642;</i>\n' +
                    '                   </a>\n' +
                    '                   <a class="layui-btn layui-btn-sm" onclick="deleteFund('+this.id+');">\n' +
                    '                       <i class="layui-icon">&#xe640;</i>\n' +
                    '                   </a>\n' +
                    '               </div>\n' +
                    '           </div>\n' +
                    '       </td>\n' +
                    '    </tr>';
            });
            $("#fundSource").append(str1);
            $("#fundSource").append(str3);
            $("#fundSource").append(str2);
        }
    });
}

function deleteFund(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/fund/' + id,
            success: function (data) {
                window.location.reload();
                layer.msg("删除成功");
            }
        });
        layer.close(1);
    });
}