app.controller('baseController',function ($scope) {

    //分页配置
    $scope.paginationConf = {
        currentPage:1,  				//当前页
        totalItems:10,					//总记录数
        itemsPerPage:10,				//每页记录数
        perPageOptions:[10,20,30,40,50,60], //分页选项，下拉选择一页多少条记录
        onChange:function(){			//页面变更后触发的方法
            $scope.findPage();		//启动就会调用分页组件
        }
    };

    //定义数组保存用户勾选ids
    $scope.selectIds = [];
    $scope.updateSelection = function (id,$event) {
        if($event.target.checked){ //check是勾选状态
            $scope.selectIds.push(id);  //前端给$scope.selectIds压入对象
        }else{
            //$scope.selectIds.indexOf(id)获取当前id所在位置   splice是前端数组移除（需要两个参数）
            $scope.selectIds.splice($scope.selectIds.indexOf(id),1); //参数一：id在数组的位置，参数二：删除个数
        }
    }

    //判断当前id是否存在selectIds数组中
    $scope.isSelect = function (id) {
        if($scope.selectIds.indexOf(id) != -1){
            return true;
        }
        return false;
    }
})