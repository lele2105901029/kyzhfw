 //控制层 
app.controller('specificationController' ,function($scope,$controller   ,specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承

	//新增规格选项增加空行
    $scope.addTableRow = function(){
		$scope.entity.specOptions.push({});  //给规格选项集合压入空对象
	}

	//删除规格选项行
	$scope.delTableRow = function($index){
    	$scope.entity.specOptions.splice($index,1); //通过$index索引位置，直接删除
	}

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		specificationService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//定义搜索对象，需要先声明，如果不声明直接ajax查询，会发生400错误，搜索对象没有传递的原因
    $scope.searchEntity = {};

	//分页+模糊搜索
	$scope.findPage=function(){			
		specificationService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage ,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//新增数据回显查询实体
	$scope.findOne=function(id){				
		specificationService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}

	//初始化
	$scope.entity = {spec:{},specOptions:[]};

	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.spec.id!=null){//如果有ID,entity是封装对象
			serviceObject=specificationService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationService.add( $scope.entity  );//增加 
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
		specificationService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findPage();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
   
});	
