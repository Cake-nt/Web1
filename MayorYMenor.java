package Web1;
import java.util.Scanner;

public class MayorYMenor {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        double numero;
        double min;
        double max;
        
        System.out.println("Encontrar numero mayor y menor \n");
        
        // Lee primer numero fuera de el bucle
        
        System.out.print("Ingrese el número 1: ");
        numero = teclado.nextDouble();
        min = numero;
        max = numero;
        
        // Lee los 9 numeros restantes
        
        for (int i = 2; i <= 10; i++) {
            System.out.print("Ingrese el número " + i + ": ");
            numero = teclado.nextDouble();
            
            if (numero < min) {
                min = numero;
            }
            if (numero > max) {
                max = numero;
            }
        }
        
        System.out.println("\n Resultados");
        System.out.println("Numero menor: " + min);
        System.out.println("Numero mayor: " + max);
        
        teclado.close();
    }
}