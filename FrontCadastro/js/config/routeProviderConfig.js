angular.module("app").config(AppConfig);

AppConfig.$inject = ['$routeProvider'];
function AppConfig($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'home.html',
            controller: 'homeController'
        })
        .when('/formulario', {
            templateUrl: 'views/formulario.html',
            controller: 'formularioController'
        })

        .when('/resposta', {
            templateUrl: 'views/resposta.html',
            controller: 'respostaController'
        })

        .when('/funcionario', {
            templateUrl: 'views/funcionario.html',
            controller: 'funcionarioController'
        })

        .when('/formularioResposta', {
            templateUrl: 'views/formularioResposta.html',
            controller: 'funcionarioController'
        })

        .when('/formularioResposta/:id', {
            templateUrl: 'views/formularioResposta.html',
            controller: 'funcionarioController'
        })
        
        .when('/meusContatos', {
            templateUrl: 'views/meusContatos.html'

        })
        .otherwise({ redirectTo: "/" });
}

