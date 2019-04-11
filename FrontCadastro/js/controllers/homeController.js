angular.module("app").controller("homeController", function ($scope) {
    $scope.texto = "Pagina principal";
    $scope.resposta = true;
    console.log($scope.resposta);


    $scope.getResposta = function () {
        console.log($scope.resposta);
    };

    $scope.setFalseResposta = function () {
        $scope.resposta = false;
    };
});