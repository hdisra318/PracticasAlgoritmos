import java.util.ArrayList;

/**
 * Clase Vertice
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Vertice {
    
    /** Nombre del vertice */
    private String nombreVertice;

    /** Vecinos o Vecindad del vertice */
    private ArrayList<Vertice> vecinos;

    /**
     * Constructor del Vertice
     * @param nombreVertice el nombre del vertice a crear.
     */
    public Vertice(String nombreVertice){
        this.nombreVertice = nombreVertice;
        this.vecinos = new ArrayList<>();
        this.vecinos.add(this);//Cada vertice es vecino de si mismo
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

    /**
     * Regresa la lista de vecinos del vertice.
     * @return la lista de vecinos del vertice
     */
    public ArrayList<Vertice> getVecinos(){
        return this.vecinos;
    }

    /**
     * Agrega a la lista de vecinos el vertice dado.
     * @param vecino el nuevo vecino del vertice
     */
    public void setVecino(Vertice vertice){
        this.vecinos.add(vertice);
    }

    /**
     * Regresa el grado del vertice.
     * @return el grado del vertice
     */
    public int getGrado(){
        return vecinos.size();
    }

    /**
     * Verifica si el vertice dado es vecino del vertice actual.
     * @param vertice el vertice a verificar
     * @return si vertice es vecino del vertice actual
     */
    public boolean esVecino(Vertice vertice){

        for(Vertice vecino : this.vecinos){
            if(vertice.nombreVertice.equals(vecino.nombreVertice))
                return true;
        }

        return false;
    }
}