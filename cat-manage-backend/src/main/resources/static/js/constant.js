

var domainName = "http://192.168.5.13";



var loginPage = "/api-b/login.html";

function   formatDate(now)   {
    var   now= new Date(now);
    var   year=now.getFullYear();
    var   month=now.getMonth()+1;
    var   date=now.getDate();
    var   hour=now.getHours();
    var   minute=now.getMinutes();
    var   second=now.getSeconds();
    return   year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日"+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2);
}
function   formatDate1(now)   {
    var   now= new Date(now);
    var   year=now.getFullYear();
    var   month=now.getMonth()+1;
    var   date=now.getDate();
    return   year+"年"+fixZero(month,2)+"月"+fixZero(date,2)+"日";
}

function   formatDate2(now)   {
    var   now= new Date(now);
    var   year=now.getFullYear();
    return   year;
}

function   formatDate3(now)   {
    var   now= new Date(now);
    var   year=now.getFullYear();
    var   month=now.getMonth()+1;
    var   date=now.getDate();
    return   year+"/"+fixZero(month,2)+"/"+fixZero(date,2);
}

//时间如果为单位数补0
function fixZero(num,length){
    var str=""+num;
    var len=str.length;     var s="";
    for(var i=length;i-->len;){
        s+="0";
    }
    return s+str;
}
