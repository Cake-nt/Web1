package vista;

import java.util.Scanner;
import controlador.ControladorCalificaciones;
import modelo.Estudiante;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ControladorCalificaciones controlador = new ControladorCalificaciones();
    
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("     COLEGIO DIOS ES BUENO");
        System.out.println("    SISTEMA DE CALIFICACIONES");
        System.out.println("====================================");
        
        while (true) {
            mostrarMenu();
            String opcion = leerOpcion();
            
            if (opcion.equalsIgnoreCase("ESC")) {
                System.out.println("\n¡Hasta luego!");
                break;
            }
            
            procesarOpcion(opcion);
        }
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("1- Registro de calificaciones");
        System.out.println("2- Reporte calificaciones por mes");
        System.out.println("3- Presione <ESC> para salir");
        System.out.println("==================================");
        System.out.print("Elija la opción deseada y pulse <ENTER>: ");
    }
    
    private static String leerOpcion() {
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
    }
    
    private static void procesarOpcion(String opcion) {
        try {
            switch (opcion) {
                case "1":
                    registrarCalificacion();
                    break;
                case "2":
                    generarReporte();
                    break;
                default:
                    if (!opcion.equalsIgnoreCase("ESC")) {
                        System.out.println("Opción inválida. Intente nuevamente.");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void registrarCalificacion() {
        try {
            System.out.println("\n--- REGISTRO DE CALIFICACIONES ---");
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            
            System.out.print("Curso (ej: 1B, 2A): ");
            String curso = scanner.nextLine();
            
            System.out.print("Mes (ej: Enero, Febrero): ");
            String mes = scanner.nextLine();
            
            System.out.print("Matemática (0-100): ");
            double matematica = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Lengua (0-100): ");
            double lengua = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Naturales (0-100): ");
            double naturales = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Sociales (0-100): ");
            double sociales = Double.parseDouble(scanner.nextLine());
            
            Estudiante estudiante = new Estudiante(nombre, apellido, curso, mes, 
                                                   matematica, lengua, naturales, sociales);
            controlador.registrarCalificacion(estudiante);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar números válidos para las calificaciones");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    private static void generarReporte() {
        try {
            System.out.println("\n--- GENERAR REPORTE ---");
            System.out.print("Mes (ej: Enero, Febrero): ");
            String mes = scanner.nextLine();
            
            System.out.print("Curso (ej: 1B, 2A): ");
            String curso = scanner.nextLine();
            
            controlador.generarReporte(mes, curso);
            
        } catch (Exception e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
        
        System.out.println("Presione ENTER para continuar...");
        scanner.nextLine();
    }
}