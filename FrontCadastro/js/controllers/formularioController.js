angular.module("app").controller("formularioController", function ($scope, formularioAPI) {
    $scope.tituloApp = "Formulario";
    $scope.funcionario = [];
    $scope.funcionarioDependente = [];
    $scope.formulario = null;
    $scope.indexList = null;
    $scope.formularios = [];
    $scope.link = "";
    $scope.mensagemDeErro ="";


    var carregaFormulario = function () {
        $scope.formularios = [];
        formularioAPI.getFormulario()
            .then(function (response) {
                $scope.formularios = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };


    $scope.gerarLinkFormulario = function (item, index) {
        $scope.produtoInserir = angular.copy(item);
        $scope.link = "localhost:3000/#!/formularioResposta/"+$scope.produtoInserir.idFormulario;
    }

    $scope.editaFormarlulario = function (item, index) {
        $scope.produtoInserir = angular.copy(item);
        $scope.indexList = index;
    }

    $scope.excluirFormulario = function (index) {
        $scope.formulario.splice(index, 1);
    }


    $scope.adicionarFormulario = function (formulario) {
        var novaFormulario = angular.copy(formulario);
        formularioAPI.salvarFormulario(novaFormulario)
            .then(function (response) {
                delete $scope.formulario;
                $scope.formularioForm.$setPristine();
                carregaFormulario();
            })
            .catch(function (response) {
                console.log(response);
                console.log(JSON.stringify(response));
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.mensagem ? response.mensagem : mensagem;
            });
    };

    $scope.removerFormulario = function (formularioParaRemover) {
        if (!confirm('Deseja realmente excluir?')) {
            return;
        };
        formularioAPI.removerFormulario(formularioParaRemover)
            .then(function (response) {
                carregaFormulario();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
                carregaFormulario();
            });

    };


    $scope.editarFormulario = function (formularioParaEditar) {
        console.log(formularioParaEditar.dataInicial);
        console.log(formularioParaEditar.dataFinal);
        formularioParaEditar.dataInicial = new Date(formularioParaEditar.dataInicial);
        formularioParaEditar.dataFinal = new Date(formularioParaEditar.dataFinal);
        $scope.formulario = angular.copy(formularioParaEditar);
        $scope.indexList = null;
    };

    $scope.temFormularioSelecionado = function (formulario) {
        return formulario.some(function (item) {
            return item.selecionado;
        });
    };

    $scope.ordenarPor = function (nomeDoCampo) {
        $scope.campoParaOrdenacao = nomeDoCampo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };
    carregaFormulario();

});