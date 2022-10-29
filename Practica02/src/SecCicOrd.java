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

    /** Numero de operaciones elementales que se ejecutan en el algoritmo */
    public int numOper = 0;

    /**
     * Define la secuencia cilicamente ordenada
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

        //Si el tamaño dado es 1
        if(sco.length == 1){
            sco[0] = (int)Math.floor(Math.random() * 100);
            return;
        }


        //Genera un indice aleatorio de donde empezara la secuencia (y que no sea el 0)
        int nuevoIndice = 0;
        while(nuevoIndice <= 0)
            nuevoIndice = (int)Math.floor(Math.random() * sco.length);


        //Para generar numeros aleatorios de forma ascendente desde el indice aleatorio
        for(int i = nuevoIndice; i<sco.length; ++i){

            double num;

            //Para que el numero mas pequeno de la secuencia se encuentre entre 1-100
            if(i == nuevoIndice){
                num = Math.floor(Math.random() * 100);
            }else{

                num = sco[i-1] + Math.floor(Math.random() * 10);
                while(sco[i-1] >= num){
                    num = sco[i-1] + Math.floor(Math.random() * 10);
                }

            }
            sco[i] = (int)num;

        }

        //Para generar los numeros aleatorios de forma ascendente hasta el indice aleatorio
        for(int i = 0; i<nuevoIndice; ++i){
            double num;
            if(i == 0){
                num = sco[sco.length-1] + Math.floor(Math.random() * 10);
                while(sco[sco.length-1] >= num){
                    num = sco[sco.length-1] + Math.floor(Math.random() * 10);
                }

            }else {
                num = Math.floor(Math.random() * 10);
                while(sco[i-1] >= num){
                    num = sco[i-1] + Math.floor(Math.random() * 10);
                }
            }

            sco[i] = (int)num;
        }
        


    }


    /**
     * Algoritmo de la Busqueda Binaria Ciclica.
     * 
     * @return el indice del elemento mas pequeno
     */
    public int BusquedaBinariaCiclica(){

        return BusquedaBinariaCiclicaAux(0, sco.length-1);
    }

    /**
     * Proceso auxiliar del algoritmo de Busqueda Binaria Ciclica.
     * 
     * @param izq primer indice del arreglo
     * @param der ultimo indice del arreglo
     * @return el indice del elemento mas pequeno
     */
    private int BusquedaBinariaCiclicaAux(int izq, int der){

        //Comparacion ==
        numOper++;
        if(izq == der)
            return izq;

        //Operaciones de =, +, /
        numOper += 3;
        int mitad = (int)((izq + der) / 2);

        //Comparacion <
        numOper++;
        if(sco[mitad] < sco[der]){
            return BusquedaBinariaCiclicaAux(izq, mitad);
        }else{
            //Operacion +
            numOper++;
            return BusquedaBinariaCiclicaAux(mitad + 1, der);
        }
    }

    /**
     * Regresa la secuencia ciclicamente ordenada generada.
     * @return la secuencia ciclicamente ordenada generada
     */
    public String toString(){
        String strSCO = "";

        for(int i = 0; i<sco.length; ++i){
            strSCO += sco[i] + " ";
        }

        return strSCO;
    }


}