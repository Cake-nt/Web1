package Web1;
import java.util.Scanner;

public class SumaPromedio {

	 public static void main(String[] args) {
	        Scanner teclado = new Scanner(System.in);
	        double suma = 0;
	        double numero;
	        
	        System.out.println("Calcular suma y obtener promedio\n");
	        
	        for (int i = 0; i < 10; i++) {
	            System.out.print("Ingrese el número " + (i + 1) + ": ");
	            numero = teclado.nextDouble();
	            suma = suma + numero;
	        }
	        
	        System.out.println("\nSuma total: " + suma);
	        System.out.println("Promedio: " + (suma / 10));
	        
	        teclado.close();
	    }
	}