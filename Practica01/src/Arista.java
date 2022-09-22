/**
 * Clase Arista
 * 
 * @author Israel Hernandez Dorantes
 * @version 1.0 2022
 */
public class Arista {
    
    /** Vertice donde sale la flecha */
    private Vertice verticeOrigen;

    /** Vertice donde llega la flecha */
    private Vertice verticeDestino;

    /**
     * Construtor de la arista (flecha)
     * 
     * @param x vertice donde sale la flecha
     * @param y vertice donde llega la flecha
     */
    public Arista(Vertice x, Vertice y){
        this.verticeOrigen = x;
        this.verticeDestino = y;
    }

    /**
     * Regresa el vertice de donde sale la flecha
     * @return el vertice de donde sale la flecha
     */
    public Vertice getOrigenFlecha(){
        return this.verticeOrigen;
    }

    /**
     * Regresa el vertice a donde llega la flecha
     * @return el vertice a donde llega la flecha
     */
    public Vertice getDestinoFlecha(){
        return this.verticeDestino;
    }

    @Override
    public String toString(){

        return verticeOrigen + "-->" + verticeDestino;
    }
}
