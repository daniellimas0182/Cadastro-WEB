angular.module("app").factory("formularioAPI", function ($http, configAPI) {
    var _getFormulario = function () {
        return $http.get(configAPI.resourceFormulario);
    };

    var _salvarFormulario = function (formulario) {
        if (!!formulario.idFormulario) {
            return $http.put(configAPI.resourceFormulario, formulario)
        }
        return $http.post(configAPI.resourceFormulario, formulario);
    };

    var _removerFormulario = function (formularioParaRemover) {
        var url = configAPI.resourceFormulario + "/" + formularioParaRemover.idFormulario;
        return $http.delete(url);
    }

    return {
        getFormulario: _getFormulario,
        salvarFormulario: _salvarFormulario,
        removerFormulario: _removerFormulario
    };
});