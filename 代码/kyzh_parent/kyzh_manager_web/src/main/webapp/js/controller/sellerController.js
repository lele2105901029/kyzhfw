 //控制层 
app.controller('sellerController' ,function($scope,$controller   ,sellerService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		sellerService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    

	//初始化参数用
	$scope.init = function(){

	}

	//定义搜索对象，需要先声明，如果不声明直接ajax查询，会发生400错误，搜索对象没有传递的原因
    $scope.searchEntity = {};

	//分页+模糊搜索
	$scope.findPage=function(){			
		sellerService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage ,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//新增数据回显查询实体
	$scope.findOne=function(id){				
		sellerService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象

		serviceObject=sellerService.add( $scope.entity  );//增加

		serviceObject.success(
			function(response){
				if(response.success){
		        	//$scope.findPage();//刷新列表
					location.href = "shoplogin.html"
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 $scope.selectIds是页面多选框绑定的该对象的多个id值
	$scope.dele=function(){			
		//获取选中的复选框			
		sellerService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findPage();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}

	//运营商修改状态
	$scope.updateStatus = function (id,status) {
		sellerService.updateStatus(id,status).success(
			function (response) {
				//刷新列表
				if(response.success){
                    $scope.findPage();//刷新列表
				}else{
					alert(response.message);
				}
            }
		)
    }
   
});	
