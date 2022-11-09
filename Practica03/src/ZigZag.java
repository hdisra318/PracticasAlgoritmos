import java.util.ArrayList;

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
     * Secuencia k-zig-zag
     */
    int[] kZigZag;

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

        kZigZag = new int[n];

        //Elemento al que se esta apuntando 
        int elem = ((int) n/2) + 1;
        int lastElemMin = elem, lastElemMax = elem;

        int i = 1, j = 1;

        kZigZag[0] = elem;

        while(i<n){

            //Incremento
            for(; j<k; j++){

                lastElemMax++;
                if(i>=n || lastElemMax > n){
                    break;
                }

                kZigZag[i] = lastElemMax;
                i++;
    
            }

            //Decremento
            for(int l = 0; l<k; l++){

                lastElemMin--;
                if(i>=n || lastElemMin <= 0){
                    break;
                }

                kZigZag[i] = lastElemMin;

                i++;
            }

            j = 0;

        }

        
        for (int x : kZigZag) {
            System.out.print(x + " ");
        }

    }



    /**
     * Algoritmo Local Insertion Sort
     */
    public void localInsertionSort(){

        //Lista "bi-ligada"
        ArrayList<Integer> l = new ArrayList<>();

        //Apuntador a un elemento en l
        Integer ui; 

        l.add(kZigZag[0]);
        ui = l.get(0);

        int n = kZigZag.length;

        for(int i = 1; i<n; i++){

            if(ui.intValue() < kZigZag[i]){
                //Insertar a la dereceha de ui
                ui = insertaDerecha(ui, kZigZag[i], l);
            }else{

                ui = insertaIzquierda(ui, kZigZag[i], l);
            }

        } 

        for(int i = 0; i<l.size(); i++){

            System.out.print(l.get(i)+" ");
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

        int pos = l.indexOf(ui);

        //Indica si ya se agrego el numero a la lista
        boolean agregado = false;

        for(int i = pos; i<l.size(); i++){

            if(elem <= l.get(i)){
                l.add(i, elem);
                ui = Integer.valueOf(elem);
                agregado = true;
                break;
            }
            
        }

        if(!agregado){
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

        int pos = l.indexOf(ui);

        //Indica si ya se agrego el numero a la lista
        boolean agregado = false;

        for(int i = pos; i>=0; i--){

            if(elem >= l.get(i)){
                l.add(i, elem);
                ui = Integer.valueOf(elem);
                agregado = true;
                break;
            }
            
        }

        if(!agregado){
            l.add(0, elem);
            ui = Integer.valueOf(elem);
        }

        return ui;
        
    }


    /**
     * Algoritmo Insertion Sort
     */
    public void insertionSort(){

        int temp;

        for(int i = 0; i<kZigZag.length; i++){

            temp = kZigZag[i];

            for(int j = i; j>0 && kZigZag[j-1] > temp; j--){
                kZigZag[j] = 
            }

        }
    }

}