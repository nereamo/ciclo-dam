package nerea.cat.paucasesnoves.ut6.practica.component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nerea.cat.paucasesnoves.ut6.practica.auxiliares.AplicacionException;

/**
 *
 * @author Nerea
 */
public class Main {

//    public static void main(String[] args) {
//
//        try {
//            MongoDB mongoDB = new MongoDB();
//
//            //Resultados categoría
//            System.out.println("Categorias: ");
//            List<Categoria> categorias = mongoDB.getCategoria();
//            
//            for (Categoria categoria : categorias) {
//                System.out.println(categoria);
//            }
//
//            //Resultados actores
//            System.out.println("\nActores: ");
//            List<Actor> actores = mongoDB.getActores();
//
//            for (Actor actor : actores) {
//                System.out.println(actor);
//            }
//
//            
//            //Resultados películas
//            int categoryId = 11;
//            int actorId = 162;
//            
//            int count = 0;
//            
//            System.out.println("\nPelículas: ");
//            List<Film> peliculas = MongoDB.getFilmCategoriaActor(categoryId, actorId);
//
//            for (Film pelicula : peliculas) {
//                count ++;
//                System.out.println("Se han encontrado " +  count + " peliculas de la categoria " + categoryId + " y el actor " + actorId);
//
//                System.out.println("Película: " + pelicula.toString());   
//            }
//
//        } catch (AplicacionException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
