package Web1;

public class ConversionStringAFloat {
    public static void main(String[] args) {
        
       
        String cadena = "200";
        
        float f1;
        
        // Float.parseFloat() Para float
        f1 = Float.parseFloat(cadena);
        
        
        System.out.println("Ejercicio 4 \n");
        System.out.println("Cadena original: \"" + cadena + "\"");
        System.out.println("Valor convertido a float (f1) = " + f1);
        
     
        System.out.println("\nSuma y division");
        System.out.println("f1 + 50.5 = " + (f1 + 50.5f));
        System.out.println("f1 / 2 = " + (f1 / 2));
        
        // Test
        System.out.println("\nTest");
        System.out.println("Como float: " + f1 + " puede convertirse a: " + f1 + ".0.0.0.0.0");
    }
}