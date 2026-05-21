package Web1;

public class DeclararVariables {
    public static void main(String[] args) {
        
    	//Asigno valores
        int a;
          
        int x, y;
          
        double doubleX;
            
        double doubleX2, doubleY;
        
        int a1 = 5, b = 6, c = 7;

        boolean sw = false;
    
        String cad = null;
        
        final double PI = 3.14;
        
      // Header
        System.out.println("RESULTADOS DEL EJERCICIO 1\n");
        
        // Valores random 
        a = 10;
        x = 20;
        y = 30;
        doubleX = 45.5;
        doubleX2 = 66.2;
        doubleY = 77.3;
        
        // Muestro valores
        System.out.println("1a) entero a = " + a);
        System.out.println("1b) entero x = " + x + ", y = " + y);
        System.out.println("1c) double x = " + doubleX);
        System.out.println("1d) double x = " + doubleX2 + ", y = " + doubleY);
        System.out.println("1e) enteros a = " + a1 + ", b = " + b + ", c = " + c);
        System.out.println("1f) lógico sw = " + sw);
        System.out.println("1g) Cadena cad = " + cad);
        System.out.println("1h) Constante PI = " + PI);
    }
}