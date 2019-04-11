package org.home.api.resources;


import org.home.api.model.FuncionarioDependente;
import org.home.api.services.FuncionarioDependenteService;
import org.home.api.utils.MensagemResposta;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("funcionarioDependente")
public class FuncionarioDependenteResource {

    @Inject
    private FuncionarioDependenteService service;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(FuncionarioDependente funcionarioDependente) {
        try {
            return Response.ok(service.atualizar(funcionarioDependente)).status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.ok(new MensagemResposta(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Integer idFuncionarioDependente) {
        try {
            service.quantidadeComPropriedade("where f.idFuncionarioDependente = ", idFuncionarioDependente);
            FuncionarioDependente funcionarioDependente = service.buscarPorId(idFuncionarioDependente);
            return Response.ok(funcionarioDependente).build();
        } catch (Exception e) {
            MensagemResposta resultado = new MensagemResposta(e.getMessage());
            return Response.ok(resultado).status(200).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FuncionarioDependente> buscarTodos() {
        return service.buscarTodos();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer idFuncionarioDependente) {
        try {
            service.deletar(idFuncionarioDependente);
            return Response.ok("Funcionário removido com sucesso!").build();
        } catch (Exception e) {
            MensagemResposta resultado = new MensagemResposta(e.getMessage());
            return Response.ok(resultado).status(404).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(FuncionarioDependente funcionarioDependente) {
        try {
            return Response.ok(service.salvar(funcionarioDependente)).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.ok(new MensagemResposta(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
