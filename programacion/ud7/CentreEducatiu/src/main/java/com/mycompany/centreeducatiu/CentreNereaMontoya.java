package com.mycompany.centreeducatiu;

import com.mycompany.utilidades.Utilidades;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Class Main, contiene un switch y métodos necesarios para poder implementarlos en el menú.
 *
 * @author Nerea Montoya
 */
public class CentreNereaMontoya {

    private static LlistaPersones listPersones = new LlistaPersones();
    private static final String FILE_DAT = "datosNereaMontoya.dat";

    public static void main(String[] args) {
        
        listPersones = cargarPersonaDAT();

        int option = 0;

        while (option != 5) {

            System.out.println("\nMenú");
            System.out.println("-----------------------------------------");
            System.out.println("1. Añadir persona");
            System.out.println("2. Mostrar lista personas");
            System.out.println("3. Mostrar lista de profesores");
            System.out.println("4. Generar fichero de texto con nombres de alumnos");
            System.out.println("5. Salir del programa.");
            System.out.println("Elige una opción: ");

            option = Utilidades.pideEntero();

            switch (option) {

                case 1:
                    System.out.println("1. Añadir persona.");
                    newPersona(listPersones);
                    break;
                case 2:
                    System.out.println("2. Mostrar por pantalla lista personas.");
                    mostrarHorarios(listPersones);
                    break;
                case 3:
                    System.out.println("3. Mostrar lista de profesores.");
                    mostrarProfesores(listPersones);
                    break;
                case 4:
                    System.out.println("4. Generar fichero de texto con nombres de alumnos.");
                    crearArchivo(listPersones);
                    break;
                case 5:
                    System.out.println("Hasta la próxima.");
                    boolean exists = new File(FILE_DAT).exists();

                    guardarPersonaDAT(listPersones);

                    if (exists) {
                        System.out.println("Datos guardados en el archivo " + FILE_DAT);
                    }

                    break;
                default:
                    System.out.println("Elija una opción válida.");
                    break;
            }
        }
    }
    
    public static LlistaPersones cargarPersonaDAT() {
        File file = new File(FILE_DAT);

        if (file.exists()) {
            System.out.println("Archivo datosNereaMontoya.dat cargado correctamente.");
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
                return (LlistaPersones) input.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error al cargar el objeto desde el archivo.");
                return new LlistaPersones(); 
            }
        } else {
           
            System.out.println("El archivo datosNereaMontoya.dat no existe, creando uno nuevo.");
            LlistaPersones newList = new LlistaPersones();
            guardarPersonaDAT(newList); 
            return newList;
        }
    }
    
    public static void guardarPersonaDAT(LlistaPersones listPersona) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_DAT))) {
            output.writeObject(listPersona);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el objeto en el archivo.");
        }
    }

    public static void newPersona(LlistaPersones listPersones) {
        while (true) {
            System.out.println("Quieres añadir un alumno(A) o profesor(P)?");

            String opcion = Utilidades.pideString("");

            if (opcion.equalsIgnoreCase("A")) {
                String nif = Utilidades.pideString("Introduce el NIF: ");
                String nombre = Utilidades.pideString("Introduce el nombre: ");
                double notaMedia;

                while (true) {
                    notaMedia = Utilidades.pideDouble("Introduce la nota media: ");

                    if (notaMedia >= 1.0 && notaMedia <= 10.0) {
                        break;
                    } else {
                        System.out.println("La nota no puede ser inferior a 1 ni mayor que 10.");
                    }
                }

                if (comprobarNIF(listPersones, nif)) {
                    System.out.println("El NIF introducido ya está registrado. Pruebe de nuevo.");
                    return;
                }

                listPersones.añadirPersona(new Alumne(nif, nombre, notaMedia));
                System.out.println("Alumno añadido correctamente.");
                break;

            } else if (opcion.equalsIgnoreCase("P")) {
                String nif = Utilidades.pideString("Introduce el NIF: ");
                String nombre = Utilidades.pideString("Introduce el nombre: ");
                String especialidad = Utilidades.pideString("Introduce la especialidad: ");
                double salario = Utilidades.pideDouble("Intoduce el salario: ");
                listPersones.añadirPersona(new Professor(nif, nombre, especialidad, salario));
                System.out.println("Profesor añadido correctamente.");
                break;
            } else {
                System.out.println("Elige una opción válida");
            }
        }
    }

    private static boolean comprobarNIF(LlistaPersones listPersones, String nif) {
        for (Persona persona : listPersones.getPersones()) {
            if (persona.getNif().equals(nif)) {
                return true;
            }
        }
        return false;
    }
    
    public static void mostrarHorarios(LlistaPersones listPersones) {
        
        for (Persona persona : listPersones.getPersones()) {
            String tipo = (persona instanceof Alumne) ? "Alumno" : "Profesor";
            System.out.println(tipo + "= " + persona.getNom() + ", Horari= " + persona.calcularHorari());
        }
    }
    
    public static void mostrarProfesores(LlistaPersones listPersones) {
        
        boolean hayProfesores = false;

        for (Persona persona : listPersones.getPersones()) {
            if (persona instanceof Professor) {
                hayProfesores = true;
                System.out.println("Profesor= " + persona.getNom() + ", Especialidad= " + ((Professor) persona).getEspecialitat());

            }
        }

        if (!hayProfesores) {
            System.out.println("No se han encontrado profesores.");
        }
    }
    
    public static void crearArchivo(LlistaPersones listPersones) {
        
        File archivo = new File("notasNereaMontoya.txt");

        Map<Integer, List<String>> notasAlumnos = new LinkedHashMap<>();

        int totalAlumnos = 0;

        for (Persona persona : listPersones.getPersones()) {
            if (persona instanceof Alumne) {
                Alumne alumno = (Alumne) persona;
                int notaRedondeada = (int) Math.round(alumno.getNotaMitjana());

                notasAlumnos.put(notaRedondeada, new ArrayList<>());
                notasAlumnos.get(notaRedondeada).add(alumno.getNom() + " - Nota: " + alumno.getNotaMitjana());

                totalAlumnos++;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("Total de alumnos: " + totalAlumnos);
            writer.newLine();

            for (int nota = 10; nota >= 1; nota--) {
                if (notasAlumnos.containsKey(nota)) {
                    List<String> alumnos = notasAlumnos.get(nota);
                    writer.write("\n" + nota + " - alumnos " + alumnos.size());
                    writer.newLine();

                    for (String alumno : alumnos) {
                        writer.write("\t" + alumno);
                        writer.newLine();
                    }
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.write("\nArchivo generado por Nerea Montoya el " + sdf.format(new Date()));
            writer.newLine();

            System.out.println("Archivo generado correctamente en: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
            e.printStackTrace();
        }
    }
}