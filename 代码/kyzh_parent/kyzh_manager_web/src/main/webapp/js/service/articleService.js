//服务层
app.service('articleService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../article/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNo,pageSize,searchEntity){
		return $http.post('../article/findPage.do?pageNo='+pageNo+'&pageSize='+pageSize, searchEntity);
		//return $http.post('../testdata.json'); //前端测试效果的json文件
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../article/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../article/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../article/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../article/delete.do?ids='+ids);
	}
	//查询文章分类下拉列表
    this.findBrandList = function () {
        return $http.get('../dataSorting/selectOptions.do');
    }
});
