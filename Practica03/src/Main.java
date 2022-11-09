import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Practica 03: Ordenamientos
 * 
 * Clase que ejecuta el programa
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class Main {

    
    public static void main(String[] args) {
        
        /* Ejecución del Programa */

        Scanner sc = new Scanner(System.in);
        int n = 0,k = 0;

        while(true){
            try{
                System.out.print("-> Ingresa el tamanio de la secuencia a generar:  ");
                n = sc.nextInt();
                sc.nextLine();
                System.out.print("-> Ingresa el numero 'k' para formar la secuencia k-zig-zag:  ");
                k = sc.nextInt();
                sc.nextLine();

                if(!(n>0 && k>0)){
                    throw new InputMismatchException();
                }

                break;
            }catch(InputMismatchException ime){
                System.out.println("\nIngresa un numero valido");
                sc.nextLine();
            }
        }

        ZigZag secuencia = new ZigZag();
        secuencia.generaSecuenciaKZigZag(n, k);

        System.out.println();
        secuencia.localInsertionSort();

    }

}
