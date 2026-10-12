/*
 */

package nerea.psp_u1_nereamontoyaolivares;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nerea
 */
public class Menu {
    public static final Scanner sc;
    public static String html;

    public Menu() {
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println(" ");
            System.out.println("Introduce una página web: ");
            System.out.println("Ejemplo: https://paucasesnovescifp.cat");
            String paginaWeb = sc.nextLine();
            String verifica = "https?://[\\w.-]+(\\.[a-z]{2,})+";
            Pattern p = Pattern.compile(verifica);
            Matcher m = p.matcher(paginaWeb);
            boolean b = m.matches();
            if (paginaWeb.equalsIgnoreCase("Salir")) {
                System.out.println("Salir");
                return;
            }

            if (!b) {
                System.out.println("Introduce una URL válida");
            } else {
                int listado = 0;

                while(listado != 8) {
                    System.out.println("------------------------------------------------------");
                    System.out.println(" ");
                    System.out.println("Menú:");
                    System.out.println("1. Cargar la página Web ");
                    System.out.println("2. Analizar el nombre de carácteres ");
                    System.out.println("3. Sustituir letra ");
                    System.out.println("4. Leer encrypted ");
                    System.out.println("5. Buscar palabras claves ");
                    System.out.println("6. Crear archivo index.html ");
                    System.out.println("7. Ejecutar archivo index.html ");
                    System.out.println("8. Salir ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Entrada no válida, introduce un número:");
                        sc.next();
                    } else {
                        listado = sc.nextInt();
                        sc.nextLine();

                        try {
                            switch (listado) {
                                case 1:
                                    System.out.println("Opción 1");
                                    CargarPaginaWeb.cargarPaginaWeb(paginaWeb);
                                    break;
                                case 2:
                                    System.out.println("Opción 2");
                                    ContarVocales.contarVocales();
                                    break;
                                case 3:
                                    System.out.println("Opción 3");
                                    SustituirLetras.sustituirLetras();
                                    break;
                                case 4:
                                    System.out.println("Opción 4");
                                    LeerEncrypted.leerEncrypted();
                                    break;
                                case 5:
                                    System.out.println("Opción 5");
                                    BuscarPalabra.buscarPalabra();
                                    break;
                                case 6:
                                    System.out.println("Opción 6");
                                    CrearArchivoIndex.crearArchivoIndex();
                                    break;
                                case 7:
                                    System.out.println("Opción 7");
                                    EjecutarIndex.ejecutarIndex();
                                    break;
                                case 8:
                                    System.out.println("Hasta luego!!");
                                    Exit.destroyAllChildren();
                                    break;
                                default:
                                    System.out.println("Porfavor, introduce una opción válida.");
                            }
                        } catch (IOException var8) {
                            IOException e = var8;
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    static {
        sc = new Scanner(System.in);
        html = " ";
    }
}
