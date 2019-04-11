package org.home.api.services;


import org.home.api.model.Formulario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FormularioService {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    public Formulario atualizar(Formulario formulario) throws Exception {
        validar(formulario);
        manager.merge(formulario);
        return formulario;
    }

    public Formulario buscarPorId(Integer idFormulario) throws Exception {
        Formulario formulario = manager.find(Formulario.class, idFormulario);
        if (formulario == null) {
            throw new Exception("Formulario não encontrado!");
        }
        return formulario;
    }

    public List<Formulario> buscarPorPropriedade(String whereClause, Object parametro) {
        return manager.createQuery("select f from Formulario f " + whereClause + parametro, Formulario.class).getResultList();
    }

    public List<Formulario> buscarTodos() {
        return manager.createQuery("select f from Formulario f ", Formulario.class).getResultList();
    }

    public void deletar(Integer idFormulario) throws Exception {
        Formulario formulario = buscarPorId(idFormulario);


        if (formulario != null) {
            manager.remove(formulario);
        } else {
            throw new Exception("Formulario não encontrado!");
        }
    }

    public Long quantidadeComPropriedade(String whereClause, Object parametro) {
        return (Long) manager.createQuery("select count(f.idFormulario) from Formulario f " + whereClause + parametro).getSingleResult();
    }

    public Formulario salvar(Formulario formulario) throws Exception {
        validar(formulario);
        manager.persist(formulario);
        return formulario;
    }

    private void validar(Formulario entity) throws Exception {
//        if (entity.getCpf() == null || entity.getCpf().isEmpty() || entity.getCpf().length() != 11) {
//            throw new Exception("Um CPF válido deve ser informado!");
//        }
//        if (entity.getNome() == null || entity.getNome().isEmpty() || entity.getNome().length() > 50) {
//            throw new Exception("O nome deve ser informado!");
//        }
//        if (entity.getTelefone() == null || entity.getTelefone().isEmpty() || entity.getTelefone().length() > 12) {
//            throw new Exception("Deve ser informado um telefone válido!");
//        }
    }
}
