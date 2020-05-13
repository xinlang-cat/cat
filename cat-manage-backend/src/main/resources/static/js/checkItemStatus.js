import "./constant"
var itemStatus = getUrlParam("itemStatus");
//检查项目状态并设置按钮
$('[check-item-button]').each(function (i,item) {
    if(itemStatus == 4){
        $(item).remove();
    }
});

//判断项目是否已经完成
function checkItemStatus() {
    if(itemStatus == 4){
        return true;
    }
}