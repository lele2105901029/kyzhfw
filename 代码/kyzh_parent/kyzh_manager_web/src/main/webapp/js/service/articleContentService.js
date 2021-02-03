//服务层
app.service('articleContentService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../articleContent/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNo,pageSize,searchEntity){
		return $http.post('../articleContent/findPage.do?pageNo='+pageNo+'&pageSize='+pageSize, searchEntity);
		//return $http.post('../testdata.json'); //前端测试效果的json文件
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../articleContent/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../articleContent/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../articleContent/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../articleContent/delete.do?ids='+ids);
	}  	
});
