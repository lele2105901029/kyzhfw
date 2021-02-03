app.controller('dataSortingController',function ($scope,dataSortingService,$controller) {

    //前端假继承,通过$controller指令将$scope范围打通
    //原因是因为前端一个页面中只能有一个controller所以将这两个的Scope域打通,这时一个页面就可以同时引入这两个controller了.
    $controller('baseController',{$scope:$scope});

    //ajax请求后端findAll方法
  /*  $scope.findAll = function () {
        $http.get('../brand/findAll.do').success(
            function (response) {
                $scope.list = response;
            }
        )
    }*/

    $scope.searchEntity = {}; //打开页面查询时，的查询时防止没有赋值

    //定义分页方法
    $scope.findPage = function(){
        dataSortingService.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage,$scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows; //显示的list内容
                $scope.paginationConf.totalItems = response.total;  //将总记录数设置到分页插件参数上
            }
        )
    }



    $scope.entity = {}; //定义变量

    $scope.parentId = 0;  //默认是一级分类0

    //新增方法
    $scope.save = function () {
        $scope.entity.parentId = $scope.parentId;
        if($scope.entity.id == null){ //新增
            dataSortingService.insert($scope.entity).success(
                function (response) {
                    //新增成功刷新页面
                    if(response.success){ //Result success,message
                        //$scope.findPage();//刷新列表
                        $scope.findByParentId( $scope.parentId);
                    }else{
                        alert(response.message);
                    }
                }
            )
        }else{ //修改
            dataSortingService.update($scope.entity).success(
                function (response) {
                    //新增成功刷新页面
                    if(response.success){ //Result success,message
                        //$scope.findPage();//刷新列表
                        $scope.findByParentId( $scope.parentId);
                    }else{
                        alert(response.message);
                    }
                }
            )
        }
    }

    //数据回显
    $scope.findOne = function (id) {
        dataSortingService.findOne(id).success(
            function (response) {
                $scope.entity = response;//{id:xxx,name:'xxx',firstChar:'xxxx'}
            }
        )
    }

    //品牌删除方法
    $scope.del = function () {
        dataSortingService.del($scope.selectIds).success(
            function (response) {
                //删除成功刷新页面
                if(response.success){ //Result success,message
                    //$scope.findPage();//刷新列表
                    $scope.findByParentId( $scope.parentId);

                    $scope.selectIds = [];
                }else{
                    alert(response.message);
                }
            }
        )
    }

//根据分类id获取该分类下的所有分类列表数据
    $scope.findByParentId = function (id) {
        dataSortingService.findByParentId(id).success(
            function (response) {
                $scope.list = response;
            }
        )
    }

    $scope.grade = 1; //层级
    $scope.entity_1 = {};
    $scope.entity_2 = {};

    //selectCat({id:0})
    $scope.selectCat = function (p_entity) {

        //维护parentId就行
        $scope.parentId = p_entity.id;

        $scope.grade += 1;

        if($scope.grade == 1){
            //一级
            $scope.entity_1 = {};
            $scope.entity_2 = {};
        }else if($scope.grade == 2){
            //二级
            $scope.entity_1 = p_entity;
            $scope.entity_2 = {};
        }else if($scope.grade == 3){
            //三级
            $scope.entity_2 = p_entity;
        }

        //查询分类列表数据
        $scope.findByParentId(p_entity.id);
    }
})


