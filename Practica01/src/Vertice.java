/**
 * Clase Vertice
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Vertice {
    
    /** Nombre del vertice */
    private String nombreVertice;

    /**
     * Constructor del Vertice
     * @param nombreVertice el nombre del vertice a crear.
     */
    public Vertice(String nombreVertice){
        this.nombreVertice = nombreVertice;
    }

    /**
     * Regresa el nombre del vertice
     * @return el nombre del vertice.
     */
    public String getNombreVertice(){
        return this.nombreVertice;
    }

    @Override
    public String toString(){
        return "("+nombreVertice+")";
    }
}