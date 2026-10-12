package org.example.psp_u4_api_montoya_olivares_nerea.Service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.psp_u4_api_montoya_olivares_nerea.Model.VideoGame;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Conecta las peticiones de los clientes con la API.
 * Permite obtener, crear, actualizar y eliminar videojuegos de la API.
 * Todos los cambios son guardados en el archivo JSON ("Videojocs_DB.txt")
 */

@Path("/videoGames")
public class Service {

    //Lista que simula la base de datos de videojuegos
    private static List<VideoGame> gamesList = new ArrayList<VideoGame>(List.of(
            new VideoGame(1, "Crash Bandicoot", "1996", "Aventura", "Naughty Dog"),
            new VideoGame(2, "Horizon Zero Dawn", "2017", "Mundo abierto", "Guerrilla Games"),
            new VideoGame(3, "Ghost of Tsushima", "2020", "Mundo abierto", "Sucker Punch Productions"),
            new VideoGame(4, "Black Myth: Wukong", "2020", "Rol", "Game Science"),
            new VideoGame(5, "Grand Theft Auto V", "2013", "Acción", "Rockstar Games")
    ));

    //Instáncia de la clase Utils que permite guardar y obtener videojuegos
    private static Utils utils = new Utils();

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames
     * Devuelve todos los videojuegos de la api
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {
        List<VideoGame> allGames = utils.load(); //Carga la lista completa con los videojuegos
        if(allGames.isEmpty()) {
            utils.save(gamesList);
            return Response.ok(gamesList).build();
        }
        return Response.ok(allGames).build();
    }

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames/{id}
     * Devuelve el videojuego que contenga el id
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameForId(@PathParam("id") int id) {
        List<VideoGame> allGames = utils.load(); //Cargar lista completa

        //Recorrer la lista para obtener la coincidencia con el "id"
        for (VideoGame game : allGames) {
            if (game.getId() == id) {
                return Response.ok(game).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Game with ID " + id + " not found").build();
    }

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames/enterprise/{enterprise}
     * Devuelve el videojuego que contenga la empresa
     */
    @GET
    @Path("/enterprise/{enterprise}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameForEnterprise(@PathParam("enterprise") String enterprise) throws UnsupportedEncodingException {
        List<VideoGame> gameEnterprise = new ArrayList<>();
        List<VideoGame> allGames = utils.load(); //Cargar lista completa
        String decodedEnterprise = URLDecoder.decode(enterprise, "UTF-8"); //Convierte texto codificado de la URL a texto normal

        //Recorrer la lista para obtener la coincidencia con "enterprise"
        for (VideoGame game : allGames) {

            if (game.getEnterprise().equalsIgnoreCase(decodedEnterprise)) {
                gameEnterprise.add(game);
            }
        }

        if (gameEnterprise.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No games found for enterprise: " + decodedEnterprise).build();
        }
        return Response.ok(gameEnterprise).build();
    }

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames/{create}
     * Crea un nuevo videojuego
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewGame(VideoGame newGame) {
        List<VideoGame> allGames = utils.load(); //Cargar lista completa

        for(VideoGame game : allGames) {
            if(game.getId() == newGame.getId()) { //Validar si el "ID" ya existe
                return Response.status(Response.Status.CONFLICT).entity("Cannot create: ID " + newGame.getId()
                        + " is already assigned to '" + game.getTitle() + "'").build();
            }
        }

        allGames.add(newGame); //Añade videojuego a la lista
        utils.save(allGames); //Guarda la lista en archivo
        gamesList = allGames; //Lista y API actualizadas

        return Response.status(Response.Status.CREATED).entity(newGame).build();
    }

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames/{update}
     * Actualiza un videojuego
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGame(VideoGame updateGame) {
        List<VideoGame> allGames = utils.load(); //Cargar lista completa

        //Recorrer la lista para obtener la coincidencia con el "id" y actualizar con los nuevos datos
        for (VideoGame game : allGames) {

            if (game.getId() == updateGame.getId()) {

                game.setId(updateGame.getId());
                game.setTitle(updateGame.getTitle());
                game.setYear(updateGame.getYear());
                game.setModality(updateGame.getModality());
                game.setEnterprise(updateGame.getEnterprise());

                utils.save(allGames); //Guarda la lista en archivo

                return Response.ok(game).build(); //Devuelve juego actualizado
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Cannot update: Game with ID "
                + updateGame.getId() + " does not exist").build();
    }

    /**
     * URL: http://localhost:8080/psp_u4_api_montoya_olivares_nerea_war_exploded/api/videoGames/delete/{id}
     * Elimina un videojuego
     */
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGame(@PathParam("id") int id) {
        List<VideoGame> allGames = utils.load(); //Cargar lista completa

        //Recorrer la lista para obtener la coincidencia con el "id"
        for (VideoGame game : allGames) {

            //Si coinciden, elimina videojuego y guarda la lista en archivo
            if (game.getId() == id) {

                allGames.remove(game);
                utils.save(allGames);

                return Response.ok(allGames).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Cannot delete: Game with ID " + id + " not found").build();
    }
}