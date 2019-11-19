
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

function content_target(id) {
    var str1;
    var str2 = '';
    var str3;
    var str4;
    researchContents(id);
    $(content).each(function () {
        var content = this;
        str1 = '<thead>\n' +
            '       <tr>\n' +
            '           <th>研究内容</th>\n' +
            '           <th>考核指标</th>\n' +
            '           <th>描述</th>\n' +
            '           <th>数量</th>\n' +
            '           <th>操作</th>\n' +
            '       </tr>\n' +
            '   </thead>\n' +
            '   <tbody>';
        str3 = '</tbody>';
        target(this.id);
        if(targets.length==0){
            str2 += '<tr>\n' +
                '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                '       <td>/</td>\n' +
                '       <td>/</td>\n' +
                '       <td>/</td>\n' +
                '       <td>/</td>\n' +
                '   </tr>';
        }
        $(targets).each(function (index) {
            var target = this;
            if(target.type==0){
                if(index==0){
                    str2 += '<tr>\n' +
                        '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                        '       <td>经济指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+target.unit+'</td>\n' +
                        '       <td>更多</td>\n' +
                        '   </tr>';
                }else {
                    str2 += '<tr>\n' +
                        '       <td>经济指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>1'+target.count+target.unit+'</td>\n' +
                        '       <td>更多</td>\n' +
                        '    </tr>';
                }
            }else {
                if(index==0){
                    str2 += '<tr>\n' +
                        '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                        '       <td>数量指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>'+target.count+target.unit+'</td>\n' +
                        '       <td>更多</td>\n' +
                        '   </tr>';
                }else {
                    str2 += '<tr>\n' +
                        '       <td>数量指标</td>\n' +
                        '       <td>'+target.target+'</td>\n' +
                        '       <td>1'+target.count+target.unit+'</td>\n' +
                        '       <td>更多</td>\n' +
                        '    </tr>';
                }
            }

        })
    })
    $('#content').append(str1);
    $('#content').append(str2);
    $('#content').append(str3);
}

function targetGroup(index,tsrget,str2) {
    if(target.type==0){
        if(index==0){
            str2 += '<tr>\n' +
                '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                '       <td>经济指标</td>\n' +
                '       <td>'+target.target+'</td>\n' +
                '       <td>'+target.count+target.unit+'</td>\n' +
                '       <td>更多</td>\n' +
                '   </tr>';
        }else {
            str2 += '<tr>\n' +
                '       <td>经济指标</td>\n' +
                '       <td>'+target.target+'</td>\n' +
                '       <td>1'+target.count+target.unit+'</td>\n' +
                '       <td>更多</td>\n' +
                '    </tr>';
        }
    }else {
        if(index==0){
            str2 += '<tr>\n' +
                '       <td rowspan="'+targets.length+'">'+content.headline+'</td>\n' +
                '       <td>数量指标</td>\n' +
                '       <td>'+target.target+'</td>\n' +
                '       <td>'+target.count+target.unit+'</td>\n' +
                '       <td>更多</td>\n' +
                '   </tr>';
        }else {
            str2 += '<tr>\n' +
                '       <td>数量指标</td>\n' +
                '       <td>'+target.target+'</td>\n' +
                '       <td>1'+target.count+target.unit+'</td>\n' +
                '       <td>更多</td>\n' +
                '    </tr>';
        }
    }
    return str2;
}