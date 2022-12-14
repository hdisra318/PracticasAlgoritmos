import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Practica 02: Secuencias ciclicamente ordenadas.
 * 
 * Clase que realiza la ejecucion del prorama
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        
        /* Inicio de la ejecicion del programa */
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el tamanio de la secuencia ciclicamente ordenada: ");
        
        int tamSCO = 0;
        boolean salir = false;
        while(!salir){

            try {
                tamSCO = sc.nextInt();

                if(tamSCO <= 0){
                    System.out.print("Por favor escirbe un numero valido: ");
                }else{
                    salir = true;
                }

            } catch (InputMismatchException ime) {
                System.out.print("Por favor escirbe un numero valido: ");
                sc.nextLine();
            }
        }
        // Creacion del ejemplar
        SecCicOrd sco = new SecCicOrd(tamSCO);
        System.out.println("El ejemplar formado fue: "+ sco);

        System.out.println("El indice del menor elemento es: "+sco.BusquedaBinariaCiclica());

        System.out.println("La cantidad de operaciones elementales ejecutadas es: "+sco.numOper);
    }
}
