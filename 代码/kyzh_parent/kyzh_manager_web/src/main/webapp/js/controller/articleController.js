 //控制层 
app.controller('articleController' ,function($scope,$controller   ,articleService){
	
	$controller('baseController',{$scope:$scope});//继承

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		articleService.findAll().success(
			function(response){
				$scope.list=response;
			}
		);
	}
	
	//定义搜索对象，需要先声明，如果不声明直接ajax查询，会发生400错误，搜索对象没有传递的原因
    $scope.searchEntity = {};

	//分页+模糊搜索
	$scope.findPage=function(){
		articleService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage ,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);
	}
	
	//新增数据回显查询实体
	$scope.findOne=function(id){
		articleService.findOne(id).success(
			function(response){
				$scope.entity= response;
			}
		);
	}

    //初始化
    $scope.entity = {article:{},articleContent:{}};

	//保存 
	$scope.save=function(){
		var serviceObject;//服务层对象
		if($scope.entity.article.id!=null){//如果有ID
			serviceObject=articleService.update( $scope.entity ); //修改
		}else{
			serviceObject=articleService.add( $scope.entity  );//增加
		}
		serviceObject.success(
			function(response){
				if(response.success){
		        	$scope.findPage();//刷新列表
				}else{
					alert(response.message);
				}
			}
		);
	}
	
	 
	//批量删除 $scope.selectIds是页面多选框绑定的该对象的多个id值
	$scope.dele=function(){
		//获取选中的复选框
		articleService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findPage();//刷新列表
					$scope.selectIds=[];
				}
			}
		);
	}
    //查询文章分类下拉列表
    //$scope.brandList = {data: [{id:1,text:'itcast87'},{id:2,text:'duplicate'},{id:3,text:'invalid'},{id:4,text:'wontfix'}]};
    $scope.findList = function () {
        articleService.findBrandList().success(
            function (response) {
                $scope.brandList = {data:response};
            }
        )
    }
    $scope.config3 = {data: [{id:1,text:'itcast87'},{id:2,text:'duplicate'},{id:3,text:'invalid'},{id:4,text:'wontfix'}]};
});
