
package nerea.psp_u4_api_api_montoyaolivaresnerea.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nerea.psp_u4_api_api_montoyaolivaresnerea.model.Obras;


/**
* @author Nerea
*/

@Path("/obras")
public class Service {
    private static List<Obras> listaObras = new ArrayList<Obras>() {
        {
            this.add(new Obras(1, "Sapiens: De animales a dioses", "2011", "Ensayo", "Yuval Noah Harari"));
            this.add(new Obras(2, "Los asquerosos", "2018", "Novela", "Santiago Lorenzo"));
            this.add(new Obras(3, "Hijos de la fábula", "2023", "Novela", "Fernando Aramburu"));
        }
    };

    public Service() {
    }

    @GET
    @Produces({"application/json"})
    public Response getObras() {
        return Response.ok(listaObras).build();
    }

    @GET
    @Path("/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response getObraById(@PathParam("id") int id) {
        Iterator var2 = listaObras.iterator();

        Obras obra;
        do {
            if (!var2.hasNext()) {
                return Response.status(Status.NOT_FOUND).entity("Obra no encontrada").build();
            }

            obra = (Obras)var2.next();
        } while(obra.getIdObra() != id);

        return Response.ok(obra).build();
    }

    @GET
    @Path("/autor/{autor}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response getObrasByAutor(@PathParam("autor") String autor) {
        System.out.println("Buscando autor: " + autor);
        List<Obras> obrasAutor = new ArrayList();
        Iterator var3 = listaObras.iterator();

        while(var3.hasNext()) {
            Obras obra = (Obras)var3.next();
            if (obra.getAutor().equalsIgnoreCase(autor)) {
                obrasAutor.add(obra);
            }
        }

        if (obrasAutor.isEmpty()) {
            return Response.status(Status.NOT_FOUND).entity("No se han encontrado obras de este autor.").build();
        } else {
            return Response.ok(obrasAutor).build();
        }
    }

    @POST
    @Path("/createObra")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response createObra(Obras obraRequest) {
        System.out.println("Intentando agregar obra: " + obraRequest.getTitol());
        listaObras.add(obraRequest);
        return Response.status(Status.CREATED).entity(listaObras).build();
    }

    @PUT
    @Path("/updateObra")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response updateObra(Obras obraUpdate) {
        Obras found = null;
        Iterator var3 = listaObras.iterator();

        while(var3.hasNext()) {
            Obras obra = (Obras)var3.next();
            if (obra.getIdObra() == obraUpdate.getIdObra()) {
                found = obra;
            }
        }

        if (found == null) {
            return Response.status(Status.NOT_FOUND).entity("Obra no encontrada").build();
        } else {
            found.setTitol(obraUpdate.getTitol());
            found.setAny(obraUpdate.getAny());
            found.setModalitat(obraUpdate.getModalitat());
            found.setAutor(obraUpdate.getAutor());
            return Response.ok(found).build();
        }
    }

    @DELETE
    @Path("/deleteObra/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response deleteObra(@PathParam("id") int id) {
        Obras found = null;
        Iterator var3 = listaObras.iterator();

        while(var3.hasNext()) {
            Obras obra = (Obras)var3.next();
            if (obra.getIdObra() == id) {
                found = obra;
                listaObras.remove(found);
                break;
            }
        }

        return found == null ? Response.status(Status.NOT_FOUND).entity("Obra no encontrada").build() : Response.ok(listaObras).build();
    }
}

