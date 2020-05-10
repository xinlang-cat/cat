function checkPermission() {
	var pers = [];
	$.ajax({
		type : 'get',
		url : domainName + '/api-u/users/current',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			pers = data.permissions;
			$("[permission]").each(function() {
				var arr = $(this).attr("permission").split(',');
				var index = -1;
				for(var i=0;i<arr.length;i++){
					if($.inArray(arr[i], pers) > -1){
						index++;
						break;
					}
				}
				if (index < 0) {
					$(this).hide();
				}
			});
		}
	});
	
	return pers;
}

function checkNoPermission() {
	var pers = [];
	$.ajax({
		type : 'get',
		url : domainName + '/api-u/users/current',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			pers = data.permissions;
			$("[no-permission]").each(function() {
				var arr = $(this).attr("no-permission").split(',');
				var index = -1;
				for(var i=0;i<arr.length;i++){
					if($.inArray(arr[i], pers) > -1){
						index++;
						break;
					}
				}
				if (index >= 0) {
					$(this).hide();
				}
			});
		}
	});

	return pers;
}

function currentUser() {
	var currentUser = {};
	$.ajax({
		type : 'get',
		url : domainName + '/api-u/users/current',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			currentUser = data;
		}
	});
	return currentUser;
}