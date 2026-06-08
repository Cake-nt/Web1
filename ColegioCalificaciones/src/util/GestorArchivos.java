package util;

import java.io.*;
import java.util.*;
import modelo.Estudiante;

public class GestorArchivos {
    private static final String CARPETA_DATOS = "datos";
    
    public static void guardarEstudiante(Estudiante estudiante) throws IOException {
        File carpeta = new File(CARPETA_DATOS);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        
        String nombreArchivo = CARPETA_DATOS + "/calificaciones_" + 
                               estudiante.getMes() + "_" + 
                               estudiante.getCurso() + ".txt";
        
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            
            pw.println(estudiante.getNombre() + "|" +
                      estudiante.getApellido() + "|" +
                      estudiante.getMatematica() + "|" +
                      estudiante.getLengua() + "|" +
                      estudiante.getNaturales() + "|" +
                      estudiante.getSociales());
        }
    }
    
    public static List<Estudiante> cargarEstudiantes(String mes, String curso) throws IOException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String nombreArchivo = CARPETA_DATOS + "/calificaciones_" + mes + "_" + curso + ".txt";
        File archivo = new File(nombreArchivo);
        
        if (!archivo.exists()) {
            return estudiantes;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length == 6) {
                    Estudiante est = new Estudiante();
                    est.setNombre(datos[0]);
                    est.setApellido(datos[1]);
                    est.setCurso(curso);
                    est.setMes(mes);
                    est.setMatematica(Double.parseDouble(datos[2]));
                    est.setLengua(Double.parseDouble(datos[3]));
                    est.setNaturales(Double.parseDouble(datos[4]));
                    est.setSociales(Double.parseDouble(datos[5]));
                    estudiantes.add(est);
                }
            }
        }
        return estudiantes;
    }
}