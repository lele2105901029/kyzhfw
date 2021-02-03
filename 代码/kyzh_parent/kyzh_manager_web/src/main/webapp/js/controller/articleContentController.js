 //控制层
app.controller('articleContentController' ,function($scope,$controller   ,articleContentService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中
	$scope.findAll=function(){
		articleContentService.findAll().success(
			function(response){
				$scope.list=response;
			}
		);
	}
	
	//定义搜索对象，需要先声明，如果不声明直接ajax查询，会发生400错误，搜索对象没有传递的原因
    $scope.searchEntity = {};

	//分页+模糊搜索
	$scope.findPage=function(){
		articleContentService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage ,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}
		);
	}
	
	//新增数据回显查询实体
	$scope.findOne=function(id){
		articleContentService.findOne(id).success(
			function(response){
				$scope.entity= response;
			}
		);
	}
	//保存 
	$scope.save=function(){
		var serviceObject;//服务层对象
		if($scope.entity.id!=null){//如果有ID
			serviceObject=articleContentService.update( $scope.entity ); //修改
		}else{
			serviceObject=articleContentService.add( $scope.entity  );//增加
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
		articleContentService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findPage();//刷新列表
					$scope.selectIds=[];
				}
			}
		);				
	}
});	
