angular.module("app").factory("funcionarioAPI", function ($http, configAPI) {
    var _getFuncionario = function () {
        return $http.get(configAPI.resourceFuncionario);
    };

    var _salvarFuncionario = function (funcionario) {
        if (!!funcionario.idFuncionario) {
            return $http.put(configAPI.resourceFuncionario, funcionario)
        }
        return $http.post(configAPI.resourceFuncionario, funcionario);
    };

    var _removerFuncionario = function (funcionarioParaRemover) {
        var url = configAPI.resourceFuncionario + "/" + funcionarioParaRemover.idFuncionario;
        return $http.delete(url);
    }

    return {
        getFuncionario: _getFuncionario,
        salvarFuncionario: _salvarFuncionario,
        removerFuncionario: _removerFuncionario
    };
});