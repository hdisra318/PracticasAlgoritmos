import java.util.ArrayList;
import java.util.Arrays;

/**
 * Practica 03: Ordenamientos
 * 
 * Clase que genera la secuencia k-zig-zag e implementa los algoritmos
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0
 */
public class ZigZag {

    /**
     * Secuencia k-zig-zag para el algortimo Local Insertion Sort
     */
    int[] kZigZagLIS;

    /**
     * Secuencia k-zig-zag para el algortimo Insertion Sort
     */
    int[] kZigZagIS;

    /**
     * Numero de operaciones elementales realizadas en el algoritmo 
     * Local Insertion Sort
     */
    int numOperLIS;

    /**
     * Numero de operaciones elementales realizadas en el algoritmo 
     * Insertion Sort
     */
    int numOperIS;

    /**
     * Crea una secuencia k-zig-zag
     * 
     * @param n el tamanio de la secuencia
     * @param k que corresponde a la forma k-zig-zag
     */
    public void generaSecuenciaKZigZag(int n, int k){

        if(!(k >= 1 && k <= ((int) n / 2))){
            System.out.println("La k no se encuentra dentro del rango requerido");
            return;
        }

        kZigZagLIS = new int[n];

        //Elemento al que se esta apuntando 
        int elem = ((int) n/2) + 1;
        int lastElemMin = elem, lastElemMax = elem;

        int i = 1, j = 1;

        kZigZagLIS[0] = elem;

        while(i<n){

            //Incremento
            for(; j<k; j++){

                lastElemMax++;
                if(i>=n || lastElemMax > n){
                    break;
                }

                kZigZagLIS[i] = lastElemMax;
                i++;
    
            }

            //Decremento
            for(int l = 0; l<k; l++){

                lastElemMin--;
                if(i>=n || lastElemMin <= 0){
                    break;
                }

                kZigZagLIS[i] = lastElemMin;

                i++;
            }

            j = 0;

        }

        
        System.out.println("\n-> Secuencia "+k+"-zig-zag generada:");
        for (int x : kZigZagLIS) {
            System.out.print(x + " ");
        }

        System.out.println();
        kZigZagIS = Arrays.copyOfRange(kZigZagLIS, 0, n);


    }


    /**
     * Algoritmo Local Insertion Sort
     */
    public void localInsertionSort(){

        //Lista "bi-ligada"
        ArrayList<Integer> l = new ArrayList<>();

        //Apuntador a un elemento en l
        Integer ui; 

        l.add(kZigZagLIS[0]);

        /* Operacion de asignacion: = */
        numOperLIS++;
        ui = l.get(0);

        /* Operacion de asignacion: = */
        numOperLIS++;
        for(int i = 1; i<kZigZagLIS.length; i++){
            /* Operaciones de: < y ++ (+, =) */
            numOperLIS += 3;

            /* Operacion de comparacion: < */
            numOperLIS++;
            if(ui.intValue() < kZigZagLIS[i]){

                /* Operacion de asignacion: =  */
                numOperLIS++;
                //Insertar a la dereceha de ui
                ui = insertaDerecha(ui, kZigZagLIS[i], l);

            }else{

                /* Operacion de asignacion: =  */
                numOperLIS++;
                ui = insertaIzquierda(ui, kZigZagLIS[i], l);
            }

        } 

        /* Operacion de asignacion: =  */
        numOperLIS++;
        int p = 0;
        while(p<l.size()){
            /* Operacion de comparacion: < */
            numOperLIS++;

            /* Operacion de asignacion: = */
            numOperLIS++;
            kZigZagLIS[p] = l.get(p);

            /* Operacion de incremento: ++ (+, =) */
            numOperLIS += 2;
            p++;
        }

    }

    /**
     * Busca, moviendose hacia la derecha de la lista, 
     * la posicion correcta del nuevo elemento y lo inserta
     * en la lista cuando la encuentra.
     * 
     * @param ui apuntador a un elemento de la lista
     * @param elem elemento a insertar
     * @param l lista
     * @return el apuntador del ultimo elemento agregado
     */
    private Integer insertaDerecha(Integer ui, int elem, ArrayList<Integer> l){

        /* Operacion de asignacion: =  */
        numOperLIS++;
        int pos = l.indexOf(ui);

        /* Operacion de asignacion: =  */
        numOperLIS++;
        //Indica si ya se agrego el numero a la lista
        boolean agregado = false;

        /* Operacion de asignacion =  */
        numOperLIS++;
        for(int i = pos; i<l.size(); i++){
            /* Operaciones de < y ++ (+, =) */
            numOperLIS += 3;

            /* Operacion de comparacion: <= */
            numOperLIS++;
            if(elem <= l.get(i)){
                l.add(i, elem);

                /* Operaciones de asignacion: = */
                numOperLIS += 2;
                ui = Integer.valueOf(elem);
                agregado = true;

                break;
            }
            
        }

        /* Operacion de negacion: ! */
        numOperLIS++;
        if(!agregado){
            
            /* Operacion de asignacion = */
            numOperLIS++;
            l.add(l.size(), elem);
            ui = Integer.valueOf(elem);
        }

        return ui;
    }


    /**
     * Busca, moviendose hacia la izquierda de la lista, 
     * la posicion correcta del nuevo elemento y lo inserta
     * en la lista cuando la encuentra.
     * 
     * @param ui apuntador a un elemento de la lista
     * @param elem elemento a insertar
     * @param l lista
     * @return el apuntador del ultimo elemento agregado
     */
    private Integer insertaIzquierda(Integer ui, int elem, ArrayList<Integer> l){

        /* Operacion de asignacion: = */
        numOperLIS++;
        int pos = l.indexOf(ui);

        /* Operacion de asignacion: = */
        numOperLIS++;
        //Indica si ya se agrego el numero a la lista
        boolean agregado = false;

         /* Operacion de asignacion: =  */
         numOperLIS++;
        for(int i = pos; i>=0; i--){
            /* Operaciones de >= y -- (-, =) */
            numOperLIS += 3;

            /* Operacion de comparacion: >= */
            numOperLIS++;
            if(elem >= l.get(i)){
                l.add(i, elem);

                /* Operaciones de aignacion: = */
                numOperLIS += 2;
                ui = Integer.valueOf(elem);
                agregado = true;

                break;
            }
            
        }

        /* Operacion de negacion: ! */
        numOperLIS++;
        if(!agregado){
            l.add(0, elem);

            /* Operacion de asignacion: = */
            numOperLIS++;
            ui = Integer.valueOf(elem);
        }

        return ui;
        
    }


    /**
     * Algoritmo Insertion Sort
     */
    public void insertionSort(){

        int temp;

        /* Operador de asignacion: = */
        numOperIS++;
        for(int i = 1; i<kZigZagIS.length; i++){
            /* Operaciones de < y ++ (+, =) */
            numOperIS += 3;

            /* Operacion de asignacion: = */
            numOperIS++;
            temp = kZigZagIS[i];

            /* Operacion de asignacion = y resta - */
            numOperIS += 2;
            for(int j = i-1; j>=0 && kZigZagIS[j] > temp; j--){
                /* Operaciones de >=, &&, > y -- (=, -) */
                numOperIS += 5;
                
                /* Operaciones de asignacion = y suma + */
                numOperIS += 3;
                kZigZagIS[j+1] = kZigZagIS[j];
                kZigZagIS[j] = temp;
            }
        }
        
    }


    /**
     * Imprime la secuencia ordenada con el numero de operaciones realizadas
     * para cada algoritmo.
     */
    public void printSecuencia(){

        System.out.println("- Secuencia ordenada por Local Insertion Sort:");
        for(int i = 0; i<kZigZagLIS.length; ++i){
            System.out.print(kZigZagLIS[i] + " ");
        }
        System.out.println("\nNumero de operaciones elementales realizadas: "+numOperLIS+"\n");

        System.out.println("- Secuencia ordenada por Insertion Sort:");
        for(int i = 0; i<kZigZagIS.length; ++i){
            System.out.print(kZigZagIS[i] + " ");
        }
        System.out.println("\nNumero de operaciones elementales realizadas: "+numOperIS+"\n");

    }

}