
/*研究内容*/
var content;
function researchContents(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/content/all/' + id,
        async: false,
        success: function (data) {
            content = data;
        }
    })
}
/*研究内容指标*/
var targets;
function target(id) {
    $.ajax({
        type: 'get',
        url: domainName + '/project-item/item/target/group/' + id,
        async: false,
        success: function (data) {
            targets = data;
        }
    })
}

var typeCount = 0;
function count(t) {
    $(targets).each(function () {
        if(this.type==t){
            typeCount++;
        }
    })
}

function content_target(id) {
    var str1;
    var str2 = '';
    var str3;
    researchContents(id);
    $(content).each(function () {
        var content = this;
        str1 = '<thead>\n' +
            '       <tr>\n' +
            '           <th>研究主题</th>\n' +
            '           <th>考核指标</th>\n' +
            '           <th>描述</th>\n' +
            '           <th>数量</th>\n' +
            '           <th>详情</th>\n' +
            '       </tr>\n' +
            '   </thead>\n' +
            '   <tbody>';
        str3 = '</tbody>';
        target(this.id);
        if(targets.length==0){
            str2 += '<tr>\n' +
                '       <td rowspan="1">'+content.headline+'</td>\n' +
                '       <td>/</td>\n' +
                '       <td>/</td>\n' +
                '       <td>/</td>\n' +
                '       <td>' +
                '           <a class="layui-btn layui-btn-xs" onclick="viewInfo1('+this.id+')">更多</a>' +
                '           <div class="operation">\n' +
                '               <div class="layui-btn-group">\n' +
                '                   <a href="updateContent.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                '                       <i class="layui-icon">&#xe642;</i>\n' +
                '                   </a>\n' +
                '                   <a class="layui-btn layui-btn-sm" onclick="deleteContent('+this.id+');">\n' +
                '                       <i class="layui-icon">&#xe640;</i>\n' +
                '                   </a>\n' +
                '              </div>\n' +
                '           </div>' +
                '       </td>\n' +
                '   </tr>';
        }
        var count1 = 0; //记录类型0的个数
        var count2 = 0; //记录类型1的个数
        var str4 = '';
        var str5 = '';
        var str6 = '';
        $(targets).each(function (index) {
            var target = this;
            analysisUnit(target.unit);
            count(target.type);
            if(target.type==0){
                if(index==0){
                    str4 += '<tr>\n' +
                        '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                        '       <td rowspan="'+typeCount+'">技术指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '   </tr>';
                }else if(count1==0) {
                    str5 += '<tr>\n' +
                        '       <td rowspan="'+typeCount+'">技术指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '    </tr>';
                }else {
                    str5 += '<tr>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '    </tr>';
                }
                count1++;
            }else {
                if(index==0){
                    str4 += '<tr>\n' +
                        '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                        '       <td rowspan="'+typeCount+'">经济指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '   </tr>';
                }else if(count2==0){
                    str6 += '<tr>\n' +
                        '       <td rowspan="'+typeCount+'">经济指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '    </tr>';
                }else {
                    str6 += '<tr>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+unit+'</td>\n' +
                        '       <td>' +
                        '           <a class="layui-btn layui-btn-xs" onclick="viewInfo2('+this.id+')">更多</a>' +
                        '           <div class="operation">\n' +
                        '               <div class="layui-btn-group">\n' +
                        '                   <a href="updateTarget.html?id='+this.id+'" class="layui-btn layui-btn-sm">\n' +
                        '                       <i class="layui-icon">&#xe642;</i>\n' +
                        '                   </a>\n' +
                        '                   <a class="layui-btn layui-btn-sm" onclick="deleteTaregt('+this.id+');">\n' +
                        '                       <i class="layui-icon">&#xe640;</i>\n' +
                        '                   </a>\n' +
                        '              </div>\n' +
                        '           </div>' +
                        '       </td>\n' +
                        '    </tr>';
                }
                count2++;
            }
            typeCount = 0;
            unit = '';
        })
        str2 += str4 + str5 + str6;
    })
    $('#content').append(str1);
    $('#content').append(str2);
    $('#content').append(str3);
}
/*解析单位*/
var unit = '';
function analysisUnit(sign) {
    $.ajax({
        type: 'get',
        url: domainName + '/api-label/label/tree/' + sign,
        async: false,
        success: function (data) {
            unit = data[0].content;
        }
    });
}

function viewInfo2(id){
    layer.open({
        title:"指标详情",
        type: 2,
        area: ['800px', '400px'],
        maxmin: true,
        shadeClose: true,
        content: ['../../list-layout/pages/viewtargetInfo.html?id='+id]

    });
}
function viewInfo1(id){
    layer.open({
        title:"内容详情",
        type: 2,
        area: ['800px', '400px'],
        maxmin: true,
        shadeClose: true,
        content: ['../../list-layout/pages/viewContentInfo.html?id='+id]

    });
}

/*删除指标*/
function deleteTaregt(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/target/' + id,
            success: function (data) {
                window.location.reload();
                layer.msg("删除成功");
            }
        });
        layer.close(1);
    });
}

/*删除内容*/
function deleteContent(id) {
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