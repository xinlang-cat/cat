/*项目人员*/
function personnel(id){
    $.ajax({
        type : 'get',
        url : domainName + '/project-item/item/user/all/'+id,
        async : false,
        success : function(data) {
            var str1;
            var str2;
            var str3 = '';
            $(data).each(function () {
                var responsible = this.itemUser.responsible || '',
                    name = this.projectUser[0].name || '',
                    sex = this.projectUser[0].sex || '',
                    birthDate = this.projectUser[0].birthDate || '',
                    idType = this.projectUser[0].idType || '',
                    idCard = this.projectUser[0].idCard || '',
                    academicTitle = this.projectUser[0].academicTitle || '',
                    nowMajor = this.projectUser[0].nowMajor || '',
                    deptName = this.projectUser[0].deptName || '';
                if(sex==1){
                    sex='男';
                }else {
                    sex='女';
                }
                // 获得今天的时间，计算年龄
                var date = new Date();
                var startDate = new Date(birthDate);
                var newDate = date.getTime() - startDate.getTime();
                var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 /365);
                if (isNaN(age)){
                    age = "";
                }

                str1 = '<thead>\n' +
                    '       <tr>\n' +
                    '       <th>姓名</th>\n' +
                    '       <th>性别</th>\n' +
                    '       <th>年龄</th>\n' +
                    '       <th>证件类型/证件号码</th>\n' +
                    '       <th>职称</th>\n' +
                    '       <th>从事专业</th>\n' +
                    '       <th>工作单位</th>\n' +
                    '       <th>研究开发中的责任分工</th>\n' +
                    '       </tr>\n' +
                    '   </thead>\n' +
                    '   <tbody>';
                str2 = '</tbody>';
                str3 += '<tr>\n' +
                    '       <td>'+name+'</td>\n' +
                    '       <td>'+sex+'</td>\n' +
                    '       <td>'+age+'</td>\n' +
                    '       <td>'+idType+'/'+idCard+'</td>\n' +
                    '       <td>'+academicTitle+'</td>\n' +
                    '       <td>'+nowMajor+'</td>\n' +
                    '       <td>'+deptName+'</td>\n' +
                    '       <td>\n' +
                    '           <p>'+responsible+'</p>\n' +
                    '           <div class="operation">\n' +
                    '               <div class="layui-btn-group">\n' +
                    '                   <a href="updatePersonnel.html?id='+this.itemUser.id+'" class="layui-btn layui-btn-sm">\n' +
                    '                       <i class="layui-icon">&#xe642;</i>\n' +
                    '                   </a>\n' +
                    '                   <a class="layui-btn layui-btn-sm" onclick="deleteUser('+this.itemUser.id+')">\n' +
                    '                       <i class="layui-icon">&#xe640;</i>\n' +
                    '                   </a>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '         </td>\n' +
                    '     </tr>';
            })
            $("#personnel").append(str1);
            $("#personnel").append(str3);
            $("#personnel").append(str2);
        }
    });
}

function deleteUser(id) {
    layer.confirm('确定要删除吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            type: 'delete',
            url: domainName + '/project-item/item/user/' + id,
            success: function (data) {
                window.location.reload();
                layer.msg("删除成功");
            }
        });
        layer.close(1);
    });
}