package Web1;

public class ConversionStringEntero {
    public static void main(String[] args) {
        
    	//Asigno
        String cadena = "200";
     
        int E;
        
        // Integer.parseInt para entero
        E = Integer.parseInt(cadena);
        
        System.out.println("Ejercicio do\n");
        System.out.println("Cadena: \"" + cadena + "\"");
        System.out.println("Valor convertido a entero (E) = " + E);
        
        // Ej
        System.out.println("\nEjemplo ");
        System.out.println("E + 100 = " + (E + 100));
    }
}