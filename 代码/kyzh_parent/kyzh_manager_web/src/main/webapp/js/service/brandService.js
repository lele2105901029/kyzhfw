//定义brandService，前端服务，发送ajax请求
app.service('brandService',function ($http) {

    this.findPage = function (pageNum,pageSize,searchEntity) {
        return $http.post('../brand/findPage.do?pageNum='+pageNum+"&pageSize="+pageSize,searchEntity);
    }

    this.insert = function (entity) {
        return $http.post('../brand/insert.do',entity);
    }

    this.update = function (entity) {
        return $http.post('../brand/update.do',entity);
    }

    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id='+id);
    }

    this.del = function (ids) {
        return $http.get('../brand/delete.do?ids='+ids);
    }
})