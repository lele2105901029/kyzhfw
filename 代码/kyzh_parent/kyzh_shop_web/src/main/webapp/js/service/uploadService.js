app.service("uploadService", function($http) {

	this.uploadFile = function() {
		var formData = new FormData(); //上传文件的数据模型
		//参数名对应contorller中的参数名  参数内容
		formData.append("uploadFile", file.files[0]); //文件上传框的id必须是和append的第二个参数一致
		return $http({
			method : 'post',
			url : "../upload.do",
			data : formData,
			headers : {'Content-Type' : undefined}, //上传文件必须是这个类型，默认text/plain
			transformRequest : angular.identity  //对整个表单进行二进制序列化
		});
	}
})