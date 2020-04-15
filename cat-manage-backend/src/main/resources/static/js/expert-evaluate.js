var h = $(window).height() - 15;
var w = $(window).width() - 30;

function stageSummary() {
    layer.open({
        title: "阶段总结",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../list-layout/pages/stage-summary/stageSummaryList.html?item_id=' + itemId + '&user_id=' + userId]
    });
}

function fundUse() {
    layer.open({
        title: "经费使用",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../list/layout/pages/fundUse/fundBudget.html?id=' + itemId]
    });
}

function itemEndSummary() {
    layer.open({
        title: "结题总结",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../summary/addSummary.html?itemId=' + itemId]
    });
}

function checkTable() {
    layer.open({
        title: "查定记录",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../auditApply/applyList.html?item_id=' + itemId + '&userId=' + userId]
    });
}

function itemChange() {
    layer.open({
        title: "项目变更",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../modifyApply/modifyList.html?item_id=' + itemId + '&userId=' + userId]
    });
}

function itemInfo() {
    layer.open({
        title: "项目信息",
        type: 2,
        area: [w + 'px', h + 'px'],
        maxmin: true,
        shadeClose: true,
        content: ['../item/projectInfo.html?id=' + itemId]
    });
}
function setObjectProperty(attribute) {
    var target = $(attribute);
    var arr = new Array();
    for (var i = 0; i < target.length; i++) {
        var obj = {};
        var input = $(target[i]).find('input,select,textarea');
        var falg = false;
        for (var j = 0; j < input.length; j++) {
            falg = true;
            obj[$(input[j]).attr('name')] = $(input[j]).val();
            $(input[j]).attr('disabled', true)
        }
        if (falg) {
            arr.push(obj);
        }
        falg = false;
    }
    return arr;
}