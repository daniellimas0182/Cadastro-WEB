angular.module("app").controller("funcionarioController", function ($scope, $routeParams, $http, $injector) {
    $scope.app = "Cadastro de Funcionario";
    $scope.funcionarios = [];
    $scope.funcionario = null;
    $scope.dependenteInserir = null;
    $scope.mensagemDeErro = "";

    var FuncionarioService = $injector.get('funcionarioAPI');
    function _carregarFuncionario() {

        console.log("$routeParams" + $routeParams.id);

        if ($routeParams.id !== undefined) {
            $scope.funcionario = {};
            $scope.funcionario.formulario = $routeParams.id;
            $scope.resposta = false;
        }

        FuncionarioService.getFuncionario()
            .then(function (response) {
                $scope.funcionarios = response.data;
            })
            .catch(function (response) {
                $scope.mensagemErro = "Ocorreu um problema ao consultar os funcionarios: "
                    + response.status + " - " + response.statusText + " " + response.data;
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.mensagem ? response.mensagem : mensagem;
            });
    };

    $scope.salvarFuncionario = function (funcionario) {
        console.log(funcionario);
        //funcionario.data = '2018-01-15';

        var formularioInseir = {
            "idFormulario": $scope.funcionario.formulario
        }

        if ($scope.funcionario.formulario !== undefined) {
            $scope.funcionario.formulario = formularioInseir;
        }

        _salvarFuncionario(funcionario);
    };

    function _salvarFuncionario(registroFuncionario) {
        FuncionarioService.salvarFuncionario(registroFuncionario)
            .then(function (response) {
                delete $scope.funcionario;
                $scope.funcionarioForm.$setPristine();
                _carregarFuncionario();
            })
            .catch(function (response) {
                $scope.mensagemErro = "Ocorreu um problema ao salvar o funcionario: "
                    + response.status + " - " + response.statusText + " " + response.data;
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    function _removerFuncionario(funcionario) {
        FuncionarioService.removerFuncionario(funcionario)
            .then(function (response) {

                //todo - verificar pq n carregou  

            })
            .catch(function (response) {
                $scope.mensagemErro = "Ocorreu um problema ao remover o funcionario: "
                    + response.status + " - " + response.statusText + " " + response.data;
                _carregarFuncionario();
            });
        _carregarFuncionario();
    }


    $scope.removerFuncionario = function (funcionario) {
        _removerFuncionario(funcionario);
    };

    $scope.editarFuncionario = function (funcionario) {
        funcionario.dataCadastro = new Date(funcionario.dataCadastro);
        funcionario.dataNascimento = new Date(funcionario.dataNascimento);
        $scope.funcionario = angular.copy(funcionario);
    };



    $scope.ordenarPor = function (campo) {
        $scope.criterioDeOrdenacao = campo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };

    _carregarFuncionario();

    $scope.adicionarDependenteLista = function (item) {
        if ($scope.indexList != undefined) {
            $scope.funcionario.funcionarioDependente.splice($scope.indexList, 1);
        }
        if ($scope.funcionario.funcionarioDependente == undefined) {
            $scope.funcionario.funcionarioDependente = [];
        }
        $scope.funcionario.funcionarioDependente.push(item);
        $scope.dependenteInserir = null;
        $scope.indexList = null;
    }

    $scope.editaDependenteLista = function (item, index) {
        $scope.dependenteInserir = angular.copy(item);
        $scope.indexList = index;
    }

    $scope.excluirDependenteLista = function (index) {
        $scope.funcionario.funcionarioDependente.splice(index, 1);
    }

});