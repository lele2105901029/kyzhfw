//定义brandService，前端服务，发送ajax请求
app.service('dataSortingService',function ($http) {

    this.findPage = function (pageNum,pageSize,searchEntity) {
        return $http.post('../dataSorting/findPage.do?pageNum='+pageNum+"&pageSize="+pageSize,searchEntity);
    }

    this.insert = function (entity) {
        return $http.post('../dataSorting/insert.do',entity);
    }

    this.update = function (entity) {
        return $http.post('../dataSorting/update.do',entity);
    }

    this.findOne = function (id) {
        return $http.get('../dataSorting/findOne.do?id='+id);
    }

    this.del = function (ids) {
        return $http.get('../dataSorting/delete.do?ids='+ids);
    }

    this.findByParentId = function (id) {
        return $http.get('../dataSorting/findByParentId.do?id='+id);
    }
})