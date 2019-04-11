package org.home.api.resources;


import org.home.api.model.Formulario;
import org.home.api.services.FormularioService;
import org.home.api.utils.MensagemResposta;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("formulario")
public class FormularioResource {

    @Inject
    private FormularioService service;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(Formulario formulario) {
        try {
            return Response.ok(service.atualizar(formulario)).status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.ok(new MensagemResposta(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Integer idFormulario) {
        try {
            service.quantidadeComPropriedade("where f.idFormulario = ", idFormulario);
            Formulario formulario = service.buscarPorId(idFormulario);
            return Response.ok(formulario).build();
        } catch (Exception e) {
            MensagemResposta resultado = new MensagemResposta(e.getMessage());
            return Response.ok(resultado).status(200).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Formulario> buscarTodos() {
        return service.buscarTodos();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer idFormulario) {
        try {
            service.deletar(idFormulario);
//            return Response.ok("Funcionário removido com sucesso!").build();
            return Response.ok(new MensagemResposta("Funcionário removido com sucesso!")).build();
        } catch (Exception e) {
            return Response.ok(new MensagemResposta(e.getMessage())).status(404).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(Formulario formulario) {
        try {
            return Response.ok(service.salvar(formulario)).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.ok(new MensagemResposta(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
