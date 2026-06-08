package controlador;

import java.util.*;
import modelo.Estudiante;
import util.GestorArchivos;

public class ControladorCalificaciones {
    
    public void registrarCalificacion(Estudiante estudiante) {
        try {
            GestorArchivos.guardarEstudiante(estudiante);
            System.out.println("✓ Calificación registrada exitosamente!");
        } catch (Exception e) {
            System.out.println("✗ Error al guardar: " + e.getMessage());
        }
    }
    
    public List<Estudiante> obtenerReporte(String mes, String curso) {
        try {
            List<Estudiante> estudiantes = GestorArchivos.cargarEstudiantes(mes, curso);
            estudiantes.sort(Comparator.comparing(Estudiante::getApellido));
            return estudiantes;
        } catch (Exception e) {
            System.out.println("Error al cargar reporte: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void generarReporte(String mes, String curso) {
        List<Estudiante> estudiantes = obtenerReporte(mes, curso);
        
        if (estudiantes.isEmpty()) {
            System.out.println("\nNo hay calificaciones registradas para " + mes + " - Curso " + curso);
            return;
        }
        
        System.out.println("\n==========================================================");
        System.out.println("Colegio Dios es bueno.");
        System.out.println("Reporte de Calificaciones de " + mes);
        System.out.println("Curso: " + curso);
        System.out.println("==========================================================");
        System.out.printf("%-12s %-12s %-10s %-7s %-9s %-9s %-8s %-7s%n", 
                         "Nombre", "Apellido", "Matemática", "Lengua", 
                         "Naturales", "Sociales", "Promedio", "Literal");
        System.out.println("----------------------------------------------------------");
        
        double sumaPromedios = 0;
        int contadorValidos = 0;
        
        for (Estudiante est : estudiantes) {
            try {
                double promedio = est.calcularPromedio();
                sumaPromedios += promedio;
                contadorValidos++;
                String literal = est.obtenerLiteral();
                
                System.out.printf("%-12s %-12s %-10.2f %-7.2f %-9.2f %-9.2f %-8.2f %-7s%n",
                                 est.getNombre(), est.getApellido(),
                                 est.getMatematica(), est.getLengua(),
                                 est.getNaturales(), est.getSociales(),
                                 promedio, literal);
            } catch (ArithmeticException e) {
                System.out.printf("%-12s %-12s %-10s %-7s %-9s %-9s %-8s %-7s%n",
                                 est.getNombre(), est.getApellido(),
                                 "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERR");
            }
        }
        
        System.out.println("----------------------------------------------------------");
        System.out.println("Total de estudiantes: " + estudiantes.size());
        
        if (contadorValidos > 0) {
            double promedioGeneral = sumaPromedios / contadorValidos;
            System.out.printf("Promedio general del curso: %.2f%n", promedioGeneral);
        }
        
        System.out.println();
    }
}