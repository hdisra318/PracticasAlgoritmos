/**
 * Practica 04: Clase Arista 
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Arista {
    
    /** Vertice a los que une la arista */
    Vertice v1, v2;

    /** Peso de la arista */
    int peso;


    /**
     * Crea una arista.
     * 
     * @param vertice1 vertice al que incide la arista
     * @param vertice2 el otro vertice al que incide la arista
     * @param peso el peso de la arista
     */
    public Arista(Vertice vertice1, Vertice vertice2, int peso){

        v1 = vertice1;
        v2 = vertice2;
        this.peso = peso;
        v1.setVecino(v2);
        v2.setVecino(v1);

    }


    @Override
    public String toString() {
        
        return v1.toString() + " --- " + v2.toString();
    }


}
