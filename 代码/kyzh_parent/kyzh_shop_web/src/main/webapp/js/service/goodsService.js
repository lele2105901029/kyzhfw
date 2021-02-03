//服务层
app.service('goodsService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../goods/findAll.do');		
	}
	//分页 
	this.findPage=function(pageNo,pageSize,searchEntity){
		return $http.post('../goods/findPage.do?pageNo='+pageNo+'&pageSize='+pageSize, searchEntity);
		//return $http.post('../testdata.json'); //前端测试效果的json文件
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../goods/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../goods/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../goods/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../goods/delete.do?ids='+ids);
	}

    this.findByParentId = function (id) {
        return $http.get('../itemCat/findByParentId.do?id='+id);
    }

    this.findItemCatById = function (id) {
		return $http.get('../itemCat/findOne.do?id='+id);
    }

    this.findTypeTemplateById = function (id) {
		return $http.get('../typeTemplate/findOne.do?id='+id);
    }

    this.findSpecList = function (id) {
		return $http.get('../typeTemplate/findSpecList.do?id='+id);
    }
});
