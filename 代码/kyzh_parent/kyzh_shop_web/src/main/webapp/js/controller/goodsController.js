 //控制层 
app.controller('goodsController' ,function($scope,$controller   ,goodsService,uploadService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//定义搜索对象，需要先声明，如果不声明直接ajax查询，会发生400错误，搜索对象没有传递的原因
    $scope.searchEntity = {};

	//分页+模糊搜索
	$scope.findPage=function(){			
		goodsService.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage ,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//新增数据回显查询实体
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象
		$scope.entity.goodsDesc.introduction = editor.html(); //从富文本编辑器取值
		if($scope.entity.goods.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
		        	location.href = "goods.html";
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 $scope.selectIds是页面多选框绑定的该对象的多个id值
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.findPage();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}



    //根据分类id获取该分类下的所有分类列表数据
    $scope.findByParentId = function (id) {
        goodsService.findByParentId(id).success(
            function (response) {
                $scope.itemCatList1 = response;
            }
        )
    }

    //观察entity.goods.category1Id是否发生变化  newValue：下拉选择后的新的id值
    $scope.$watch('entity.goods.category1Id',function (newValue, oldValue) {
		//alert('1234');
        goodsService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCatList2 = response;
            }
        )
    })

    //观察entity.goods.category2Id是否发生变化  newValue：下拉选择后的新的id值
    $scope.$watch('entity.goods.category2Id',function (newValue, oldValue) {
        //alert('1234');
        goodsService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCatList3 = response;
            }
        )
    })
	
	//观察分类id3的id值是否赋值，进行查询分类对象
	$scope.$watch('entity.goods.category3Id',function (newValue, oldValue) {
		//根据分类id获取分类对象
		goodsService.findItemCatById(newValue).success(
			function (response) {
				$scope.entity.goods.typeTemplateId = response.typeId; //获取分类对象上的模版id;
            }
		)
    })


	//typeTemplateId是否已赋值，根据id获取模版对象
    $scope.$watch('entity.goods.typeTemplateId',function (newValue, oldValue) {
        //根据分类id获取分类对象
        goodsService.findTypeTemplateById(newValue).success(
            function (response) {
            	//alert(JSON.stringify(response));

                $scope.typeTemplate = response; //模版对象
				$scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds); //将属性的字符串转成json对象
				$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            }
        )

		//获取该模版的规格信息
		goodsService.findSpecList(newValue).success(
			function (response) {
				$scope.specList = response;
            }
		)
    })


	$scope.image_entity = {};


	//图片上传
	$scope.uploadFile = function () {
		uploadService.uploadFile().success(
			function (response) {
				if(response.success){
					$scope.image_entity.url = response.message;
				}else{
					alert(response.message);
				}
            }
		)
    }




    //将图片添加到列表
    $scope.addImage = function () {
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }

    //将图片从列表中移除
    $scope.delImage = function ($index) {
		$scope.entity.goodsDesc.itemImages.splice($index,1);
    }

    //更新用户勾选规格选项的数据封装
    $scope.updateSpecAttribute = function (specName,optionName,$event) {
		//根据传入的规格名称查找specificationItems里是否已经存在该规格
        var object = searchObjectByKey($scope.entity.goodsDesc.specificationItems,specName)
		if(object != null){
			//找到后
			//判断勾选
			if($event.target.checked){ //勾选
                object.attributeValue.push(optionName)
			}else{
				//没勾选移除
				object.attributeValue.splice(object.attributeValue.indexOf(optionName),1);
				//判断如果规格选项没有，移除掉该规格
				if(object.attributeValue.length < 1){
                    $scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object),1);
				}
			}

		}else{
			//没找到直接push
            $scope.entity.goodsDesc.specificationItems.push({"attributeName":specName,"attributeValue":[optionName]})
		}


		//创建itemlist列表
        itemListCreate();
    }

    //循环选择
    searchObjectByKey=function(list,specName){
		//{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]},
        for (var i = 0; i < list.length; i++) {
			if(list[i].attributeName == specName){
				return list[i];
			}
        }
        return null;
	}


    $scope.init = function(){
        $scope.findByParentId(0);
        //初始化
        $scope.entity = {goodsDesc:{customAttributeItems:[],specificationItems:[],itemImages:[]},goods:{},itemList:[]};
    }

	itemListCreate = function () {
		//给itemList创建item对象
		$scope.entity.itemList = [{spec:{},price:0,num:99999,status:'0',isDefault:'0'}];
		//循环选择的规格属性
        var specList = $scope.entity.goodsDesc.specificationItems;
        for (var i = 0; i < specList.length; i++) {
        	//参数1 sku列表   参数2.规格名称  参数3[移动3g，移动4g]
            $scope.entity.itemList = itemRowCreate($scope.entity.itemList,specList[i].attributeName,specList[i].attributeValue);
        }
    }

    itemRowCreate = function (list,specName,options) {
		var newItemList = [];
        for (var i = 0; i < list.length; i++) {
            for (var j = 0; j < options.length; j++) {
                var oldRow = list[i];//{spec:{},price:0,num:99999,status:'0',isDefault:'0'}
				var newRow = JSON.parse(JSON.stringify(oldRow)); //前端深克隆
				//设置spec相关信息
				newRow.spec[specName] = options[j];  //{spec:{网络:移动3G},price:0,num:99999,status:'0',isDefault:'0'}
				newItemList.push(newRow);
            }
        }
        return newItemList;
    }
});	
