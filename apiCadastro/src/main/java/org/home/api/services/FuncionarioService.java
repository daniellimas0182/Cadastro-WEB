package org.home.api.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.home.api.model.Formulario;
import org.home.api.model.Funcionario;
import org.home.api.model.FuncionarioDependente;
import org.home.api.utils.ValidaCPF;


@Stateless
public class FuncionarioService {


    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private FuncionarioDependenteService funcionarioDependenteService;
    @Inject
    private FormularioService formularioService;

    public Funcionario atualizar(Funcionario funcionario) throws Exception {
        validar(funcionario);

        List<FuncionarioDependente> listaDependentes = funcionario.getFuncionarioDependente();
        Funcionario funcionarioSaved = manager.merge(funcionario);

        // saveDependentes(listaDependentes, funcionarioSaved);

        return funcionarioSaved;
    }

    public Funcionario buscarPorId(Integer idFuncionario) throws Exception {
        Funcionario funcionario = manager.find(Funcionario.class, idFuncionario);
        if (funcionario == null) {
            throw new Exception("Funcionario não encontrado!");
        }
        return funcionario;
    }

    public List<Funcionario> buscarPorPropriedade(String whereClause, Object parametro) {
        return manager.createQuery("select f from Funcionario f " + whereClause + parametro, Funcionario.class).getResultList();
    }

    public List<Funcionario> buscarPorCpfEEmail(String cpf, String email) {
        return manager.createQuery("select f from Funcionario f where" + " f.cpf =" + "'" + cpf + "'" + " and f.email = '" + email + "'", Funcionario.class).getResultList();
    }

    public List<Funcionario> buscarTodos() {
        return manager.createQuery("select f from Funcionario f ", Funcionario.class).getResultList();
    }

    public void deletar(Integer idFuncionario) throws Exception {
        Funcionario funcionario = buscarPorId(idFuncionario);


        if (funcionario != null) {
            manager.remove(funcionario);
        } else {
            throw new Exception("Funcionario não encontrado!");
        }
    }

    public Long quantidadeComPropriedade(String whereClause, Object parametro) {
        return (Long) manager.createQuery("select count(f.idFuncionario) from Funcionario f " + whereClause + parametro).getSingleResult();
    }

    public Funcionario salvar(Funcionario funcionario) throws Exception {
        validar(funcionario);

        List<FuncionarioDependente> listaDependentes = funcionario.getFuncionarioDependente() == null ? new ArrayList<>() :
                funcionario.getFuncionarioDependente();

        Funcionario funcionarioSaved = manager.merge(funcionario);

        if (!listaDependentes.isEmpty()) {
            saveDependentes(listaDependentes, funcionarioSaved);
        }

        return funcionario;
    }

    public void saveDependentes(List<FuncionarioDependente> listaDependentes, Funcionario funcionarioSaved) throws Exception {
        for (FuncionarioDependente dependente : listaDependentes) {
            dependente.setFuncionario(funcionarioSaved);
            funcionarioDependenteService.salvar(dependente);
        }
    }

    private void validar(Funcionario entity) throws Exception {
        if (entity.getCpf() == null || entity.getCpf().isEmpty() || entity.getCpf().length() != 11) {
            throw new Exception("Um CPF válido deve ser informado!");
        }

        if (!ValidaCPF.isCPF(entity.getCpf())) {
            throw new Exception("Um CPF válido deve ser informado!");
        }

        if (entity.getNome() == null || entity.getNome().isEmpty() || entity.getNome().length() > 50) {
            throw new Exception("O nome deve ser informado!");
        }
        if (entity.getTelefone() == null || entity.getTelefone().isEmpty() || entity.getTelefone().length() > 12) {
            throw new Exception("Deve ser informado um telefone válido!");
        }
        if (entity.getRg() == null || entity.getRg().isEmpty() || entity.getRg().length() > 12) {
            throw new Exception("Um RG válido deve ser informado!");
        }
        if (entity.getEmail() == null || entity.getEmail().isEmpty() || entity.getEmail().length() > 50) {
            throw new Exception("Deve ser informado um e-mail válido!");
        }
        if (entity.getEndereco() == null || entity.getEndereco().isEmpty() || entity.getEndereco().length() > 50) {
            throw new Exception("O endereço deve ser informado!");
        }

        if (entity.getFormulario() != null) {
            Formulario formulario = formularioService.buscarPorId(entity.getFormulario().getIdFormulario());

            System.out.println("" + LocalDate.now());
            System.out.println("" + formulario.getDataInicial());
            System.out.println("" + formulario.getDataFinal());

            if (LocalDate.now().isBefore(formulario.getDataInicial())) {
                throw new Exception("Formulario indisponivel nesta data, tente novamente após " + formulario.getDataInicial());
            }

            if (LocalDate.now().isAfter(formulario.getDataFinal())) {
                throw new Exception("Formulario indisponivel, prazo encerrado!");
            }

        }

        List<Funcionario> funcionarios = buscarPorCpfEEmail(entity.getCpf(), entity.getEmail());

        funcionarios.stream().forEach(f -> System.out.println(f.toString()));

        List<Funcionario> collect = funcionarios.stream().filter(f -> f.getFormulario() != null && f.getFormulario().getIdFormulario()
                .equals(entity.getFormulario().getIdFormulario())).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            throw new Exception("Já existe um funcionario com este CPF e Email para este formulario!");
        }
    }
}
