package Web1;
import java.util.Scanner;

public class Multiplos {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        double numero;
        double divisor;
        int contadorMultiplos = 0;
        
        System.out.println("Hallar multiplos del divisor\n");
        System.out.print("Ingrese el divisor: ");
        divisor = teclado.nextDouble();
        
        if (divisor == 0) {
            System.out.println("Error: El divisor no puede ser 0 pa :).");
            teclado.close();
            return;
        }
        
        System.out.println("\nIngrese los 10 números: \n");
        
        for (int i = 0; i < 10; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            numero = teclado.nextDouble();
            
            if (numero % divisor == 0) {
                System.out.println("  → " + numero + " ES múltiplo de " + divisor);
                contadorMultiplos++;
            } else {
                System.out.println("  → " + numero + " NO es múltiplo de " + divisor);
            }
        }
        
        System.out.println("\nTotal de múltiplos: " + contadorMultiplos);
        teclado.close();
    }
}