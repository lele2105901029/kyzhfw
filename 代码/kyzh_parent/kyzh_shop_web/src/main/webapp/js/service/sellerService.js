//服务层
app.service('sellerService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../seller/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNo,pageSize,searchEntity){
		return $http.post('../seller/findPage.do?pageNo='+pageNo+'&pageSize='+pageSize, searchEntity);
		//return $http.post('../testdata.json'); //前端测试效果的json文件
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../seller/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../seller/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../seller/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../seller/delete.do?ids='+ids);
	}  	
});
