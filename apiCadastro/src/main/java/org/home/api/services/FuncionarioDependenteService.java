package org.home.api.services;


import org.home.api.model.FuncionarioDependente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FuncionarioDependenteService {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    public FuncionarioDependente atualizar(FuncionarioDependente funcionarioDependente) throws Exception {
        validar(funcionarioDependente);
        manager.merge(funcionarioDependente);
        return funcionarioDependente;
    }

    public FuncionarioDependente buscarPorId(Integer idFuncionarioDependente) throws Exception {
        FuncionarioDependente funcionarioDependente = manager.find(FuncionarioDependente.class, idFuncionarioDependente);
        if (funcionarioDependente == null) {
            throw new Exception("Funcionario dependente não encontrado!");
        }
        return funcionarioDependente;
    }

    public List<FuncionarioDependente> buscarPorPropriedade(String whereClause, Object parametro) {
        return manager.createQuery("select f from Funcionario f " + whereClause + parametro, FuncionarioDependente.class).getResultList();
    }

    public List<FuncionarioDependente> buscarTodos() {
        return manager.createQuery("select f from FuncionarioDependente f ", FuncionarioDependente.class).getResultList();
    }

    public void deletar(Integer idFuncionarioDependente) throws Exception {
        FuncionarioDependente funcionarioDependente = buscarPorId(idFuncionarioDependente);


        if (funcionarioDependente != null) {
            manager.remove(funcionarioDependente);
        } else {
            throw new Exception("Funcionario dependente não encontrado!");
        }
    }

    public Long quantidadeComPropriedade(String whereClause, Object parametro) {
        return (Long) manager.createQuery("select count(f.idFuncionarioDependente) from FuncionarioDependente f " + whereClause + parametro).getSingleResult();
    }

    public FuncionarioDependente salvar(FuncionarioDependente funcionarioDependente) throws Exception {
       // validar(funcionarioDependente);
        manager.persist(funcionarioDependente);
        return funcionarioDependente;
    }

    private void validar(FuncionarioDependente entity) throws Exception {
        if (entity.getNome() == null || entity.getNome().isEmpty() || entity.getNome().length() != 11) {
            throw new Exception("O nome deve ser informado!");
        }
        if (entity.getEmail() == null || entity.getEmail().isEmpty() || entity.getEmail().length() > 50) {
            throw new Exception("Deve ser informado um e-mail válido!");
        }
       // if (entity.getDataNascimento() == null || entity.getDataNascimento().isEmpty() || entity.getDataNascimento().length() > 12) {
        // throw new Exception("Deve ser informado um telefone válido!");
        //}
    }
}
