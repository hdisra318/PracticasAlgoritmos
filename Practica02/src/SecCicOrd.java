import java.util.Random;

/**
 * Practica 02: Secuencias ciclicamente ordenadas.
 * 
 * Clase que realiza la secuencia ciclicamente ordenada (sco) y que 
 * contiene el algoritmo de busqueda 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 
 */
public class SecCicOrd {

    /** Arreglo que almacena la secuencia ciclicamente ordenada generada */
    private int sco[];


    /**
     * Define la secuencia
     * @param n tamanio de la secuencia
     */
    public SecCicOrd(int n){

        this.sco = new int[n];
        this.generaSCO();
    }


    /**
     * Genera una secuencia ciclicamente ordenada aleatoria
     */
    public void generaSCO(){

        //Para generar numeros aleatorios
        Random r = new Random();
        for(int i = 0; i<sco.length; ++i){

            double elem = Math.floor(Math.random() * 100);
            sco[i] = (int)elem;
            System.out.println(elem);
        }
    }


    /**
     * Regresa la secuencia ciclicamente ordenada genrada.
     * @return la secuencia ciclicamente ordenada genrada
     */
    public String toString(){
        String strSCO = "";

        for(int i = 0; i<sco.length; ++i){
            strSCO += sco[i] + " ";
        }

        return strSCO;
    }


}