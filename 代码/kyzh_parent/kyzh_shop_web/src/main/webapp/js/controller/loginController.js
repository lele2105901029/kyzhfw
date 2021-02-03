app.controller('loginController',function ($scope,$http) {

    //显示用户名
    $scope.showName = function () {
        $http.get('../login/showName.do').success(
            function (response) {
                $scope.loginName = response;
            }
        )
    }
})