package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.dto.response.EstadoResponseDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.service.estado.EstadoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estados")
public class EstadoResource {
    private static final Logger LOGGER = Logger.getLogger(EstadoResource.class);

    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado!");

        return Response
                .ok(EstadoResponseDTO.valueOf(estadoService.findById(id)))
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome, @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        LOGGER.info("Método findById foi executado com o parametro [" + nome + "]!");

        return Response
                .ok(estadoService.findByNome(nome, page, pageSize).stream().map(EstadoResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    public List<Estado> findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("page_size") @DefaultValue("100") int pageSize) {
        LOGGER.info("Método findAll foi executado!");

        return estadoService.findAll(page, pageSize);
    }

    @POST
    public Response create(@Valid EstadoRequestDTO estado) {
        LOGGER.info("Método create foi executado, estado: " + estado);

        return Response
                .status(Status.CREATED).entity(EstadoResponseDTO.valueOf(estadoService.create(estado)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid EstadoRequestDTO estado) {
        LOGGER.info("Método update foi executado, estado com id " + id + ": " + estado);

        estadoService.update(id, estado);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        estadoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/count")
    public long total() {
        return estadoService.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(String nome) {
        return estadoService.count(nome);
    }
}
