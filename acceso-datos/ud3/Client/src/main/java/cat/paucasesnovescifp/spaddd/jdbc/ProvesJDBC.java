
package cat.paucasesnovescifp.spaddd.jdbc;

import cat.paucasesnovescifp.spaddd.jdbc.auxiliars.JDBCException;
import cat.paucasesnovescifp.spaddd.jdbc.baseDades.BaseDades;
import cat.paucasesnovescifp.spaddd.jdbc.dades.Editor;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nerea
 */

public class ProvesJDBC {

    public static void main(String[] args) {
        BaseDades db = new BaseDades();
        Scanner sc = new Scanner(System.in);
        Set<Editor> editors;

        try {

            db.conexioBaseDades(); //Conexió a la base de dades

            //-------------Afegir Editors al buffer------------------
            
            boolean afegir = true;

            while (afegir) {    //Bucle while per tornar a demanar si es vol afegir un altre editor.

                Editor nouEditor = new Editor(); //Instáncia del objecte editor
                
                System.out.println("\nQuin editor vols afegir? ");

                System.out.print("NOM: ");
                String nom = sc.nextLine().trim();
                
                nouEditor.setNom(nom);
                
                System.out.print("EMAIL: ");
                String email = sc.nextLine().trim();

                nouEditor.setEmail(email);
               
                db.afegirEditor(nouEditor); //Cridar al métode afegirEditor de la class BaseDades

                System.out.println("\nVols afegir més editors? (si/no)");
                String afegirEditor = sc.nextLine().trim().toLowerCase();

                if (afegirEditor.equals("no")) {    //Condició, si la opció es 'si' podem afegir mes editors.
                    afegir = false;
                } else if (!afegirEditor.equals("si")) {
                    afegir = false;
                }
            }

            editors = db.guardarEditors();  //Métode de la class BaseDades que guarda els editors creats al buffer

            System.out.println("\nInformació del buffer:");

            for (Editor editor : editors) { //Mostra els editors presents al buffer
                System.out.println(editor);
            }

            //-------------Eliminar Editors del buffer------------------
            
            boolean eliminar = true;

            while (eliminar) {  //Bucle while per tornar a demanar si es vol eliminar un editor.

                System.out.println("\nVols eliminar algún editor? (si/no)");

                String respostaEliminar = sc.nextLine().trim();

                if (respostaEliminar.equalsIgnoreCase("Si")) {   //Condició, si la opció es 'si' podem eliminar l`editor.
                    
                    System.out.println("\nQuin editor vols eliminar? ");
                    System.out.print("NOM: ");
                    String eliminarEditor = sc.nextLine();

                    Editor editorEliminar = new Editor(eliminarEditor, null); //Instáncia del objecte editor, no té en conte el email.
                    db.eliminarEditor(editorEliminar);  //Cridar al métode eliminarEditor de la class BaseDades

                } else if (respostaEliminar.equalsIgnoreCase("No")) {
                    eliminar = false;

                }
            }

            editors = db.guardarEditors();  //Métode de la class BaseDades que guarda els editors creats al buffer
            System.out.println("\nInformació del buffer:");

            for (Editor editor : editors) {     //Mostra els editors presents al buffer
                System.out.println(editor);
            }

            //-------------Insertar Editors de la base de dades------------------
            
            System.out.println("\nTots els editors presenta al buffer s'insertarán a la base de dades.");

            int filasInsertadas = db.inserirEditorsBD();    //Cridar al métode inserirEditorsBD de la class BaseDades

            System.out.println("\nS'han insertat " + filasInsertadas + " editors a la base de dades."); //Si s'han insertat correctament, mostrará cuantes files s'han insertat

            
            //-------------Eliminar Editors de la base de dades------------------
            
            boolean eliminarBD = true;

            while (eliminarBD) {  //Bucle while per tornar a demanar si es vol eliminar un editor.

                System.out.println("\nVols eliminar algún editor de la Base de Dades? (si/no)");
                String respostaEliminar = sc.nextLine().trim().toLowerCase();

                if (respostaEliminar.equalsIgnoreCase("Si")) {   //Condició, si la opció es 'si' podem eliminar l`editor.

                    System.out.println("\nQuin editor vols eliminar? (Introdueix l'ID de l'editor) ");
                    
                    System.out.print("ID: ");
                    int eliminarEditor = sc.nextInt(); 
                    sc.nextLine();

                    try {
                        int filasEliminadas = db.eliminarEditorsBD(eliminarEditor); 

                        if (filasEliminadas > 0) { //Si s'ha eliminat algún editor de la BD, mostra cuantes files s'han eliminat
                            System.out.println("S'han eliminat " + filasEliminadas + " editors a la base de dades.");
                        } else {
                            System.out.println("No s'ha trobat cap editor amb aquest ID.");
                        }

                    } catch (SQLException e) {
                        System.out.println("Error en eliminar l'editor: " + e.getMessage());
                    }

                } else if (respostaEliminar.equalsIgnoreCase("No")) {
                    eliminarBD = false; //resposta 'NO', surt del bucle
                    System.out.println("Procés finalitzat!");
                } 
            }  
            
        } catch (SQLException e) {
            System.err.println("Error al connectar a la base de dades: " + e.getMessage());
            e.printStackTrace();
        } catch (JDBCException ex) {
            Logger.getLogger(ProvesJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sc.close(); //Cerrar el Scanner
        }
    }
    }


