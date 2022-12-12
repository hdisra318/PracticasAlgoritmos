import java.util.ArrayList;

/**
 * Practica 04: Clase Vertice
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
     * Constructor del Vertice.
     * 
     * @param nombreVertice el nombre del vertice a crear.
     */
    public Vertice(String nombreVertice){
        this.nombreVertice = nombreVertice;
        this.vecinos = new ArrayList<>();
    }

    /**
     * Regresa el nombre del vertice.
     * 
     * @return el nombre del vertice
     */
    public String getNombreVertice(){
        return this.nombreVertice;
    }

    @Override
    public String toString(){
        return "("+nombreVertice+")";
    }

    /**
     * Regresa las representaciones de las conexiones del vertice 
     * actual con sus vecinos.
     * 
     * @return representacion de las conexiones con sus vecinos
     */
    public String represetacionConexiones(){
        String representacion = "";
        //Iterando sobre los vecinos
        for (int i = 0; i<vecinos.size(); ++i) {
            representacion += this + " --- " + vecinos.get(i);
        }

        return representacion;
    }


    /**
     * Regresa la lista de vecinos del vertice.
     * 
     * @return la lista de vecinos del vertice
     */
    public ArrayList<Vertice> getVecinos(){
        return this.vecinos;
    }

    /**
     * Agrega a la lista de vecinos el vertice dado.
     * 
     * @param vecino el nuevo vecino del vertice
     */
    public void setVecino(Vertice vertice){
        this.vecinos.add(vertice);
    }

    /**
     * Regresa el grado del vertice.
     * 
     * @return el grado del vertice
     */
    public int getGrado(){
        return vecinos.size();
    }

    /**
     * Verifica si el vertice dado es vecino del vertice actual.
     * 
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

    /**
     * Remueve el vecino dado de la vecindad.
     * 
     * @param v vertice a eliminar
     */
    public void eliminarVecino(Vertice v){

        for(int i = 0; i<this.getGrado(); ++i){

            if(this.vecinos.get(i).getNombreVertice().equals(v.getNombreVertice()))
                this.vecinos.remove(i);
        }
    }

}