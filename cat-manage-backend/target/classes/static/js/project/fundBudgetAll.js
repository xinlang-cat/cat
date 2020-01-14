/*资金总预算*/
function fundBudgetAll(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/budget/all/'+id,
        async : false,
        success : function(data) {
            var str1;
            var str2;
            var str3 = '';
            var count=0;
            $(data).each(function () {
                count+=this.money;
                analysisSource(this.fund_id);
                str1 = '<thead>\n' +
                    '       <tr>\n' +
                    '           <th>科目</th>\n' +
                    '           <th>金额(万元)</th>\n' +
                    '           <th>资金来源</th>\n' +
                    '           <th>开支内容</th>\n' +
                    '       </tr>\n' +
                    '   </thead>\n' +
                    '   <tbody>\n' +
                    '       <tr>\n' +
                    '           <td>合计</td>\n' +
                    '           <td>'+count+'</td>\n' +
                    '           <td>/</td>\n' +
                    '           <td>/</td>\n' +
                    '      </tr>';
                str2 = '<tbody>';
                str3 += '<tr>\n' +
                    '       <td>'+this.subject+'</td>\n' +
                    '       <td>'+this.money+'</td>\n' +
                    '       <td>'+sourceName+'</td>\n' +
                    '       <td>\n' +
                    '           <p>'+ this.content+'</p>\n' +
                    '           <div class="operation">\n' +
                    '               <div class="layui-btn-group">\n' +
                    '                   <a href="updateBudget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                    '                       <i class="layui-icon">&#xe642;</i>\n' +
                    '                   </a>\n' +
                    '                   <a class="layui-btn layui-btn-sm" onclick="deleteBudget('+this.id+');">\n' +
                    '                       <i class="layui-icon">&#xe640;</i>\n' +
                    '                   </a>\n' +
                    '               </div>\n' +
                    '           </div>\n' +
                    '       </td>\n' +
                    '    </tr>';
            });
            $("#fundBudgetAll").append(str1);
            $("#fundBudgetAll").append(str3);
            $("#fundBudgetAll").append(str2);
        }
    });
}

/*解析来源*/
var sourceName;
function analysisSource(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/fund/'+id,
        async : false,
        success : function(data) {
            sourceName = data.source;
        }
    });
}
/*删除资金预算*/
function deleteBudget(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/budget/' + id,
            success: function (data) {
                window.location.reload();
                layer.msg("删除成功");
            }
        });
        layer.close(1);
    });
}