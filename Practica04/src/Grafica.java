import java.util.ArrayList;

/**
 * Practica 04: Clase Grafica 
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Grafica {

    /** Lista de vertices de la grafica */
    ArrayList<Vertice> vertices = new ArrayList<>();

    /**
     * Constructor vacio de una grafica.
     */
    public Grafica(){}

    /**
     * Constructor de una grafica dado un vertice inicial.
     * 
     * @param vertice el vertice inicial de la grafica
     */
    public Grafica(Vertice vertice){
        vertices.add(vertice);
    }

    /** 
     * Agrega un vertice a la grafica.
     * 
     * @param vertice el nuevo vertice a agregar a la grafica
     */
    public void agregarVertice(Vertice vertice){
        this.vertices.add(vertice);
    }

    /**
     * Agrega una arista a la grafica.
     * 
     * @param verticeOrigen el vertice de un extremo
     * @param verticeDestino el vertice del otro extremo
     */
    public void agregarFlecha(Vertice verticeOrigen, Vertice verticeDestino){

        if(existeVertice(verticeOrigen) && existeVertice(verticeDestino)){
           
            verticeOrigen.crearArista(verticeDestino);
        }
    }


    /**
     * Verifica si existe el vertice dado en la grafica.
     * 
     * @param vertice el vertice a verficar
     */
    private boolean existeVertice(Vertice vertice){

        for (Vertice verticeActual : vertices) {
            if(verticeActual.getNombreVertice().equals(vertice.getNombreVertice()))
                return true;
        }
        
        return false;
    }


    /**
     * Regresa el vertice de la grafica dado su nombre.
     * 
     * @param nombre el nombre del vertice
     * @return el vertice encontrado
     */
    public Vertice getVertice(String nombre){

        Vertice v = null;
        for (Vertice vertice : vertices) {
            if(vertice.getNombreVertice().equals(nombre))
                v = vertice;
        }

        return v;
    }

    @Override
    public String toString(){

        String representacion = "";

        for (Vertice vertice : vertices) {
            representacion += vertice.represetacionConexiones() + "\n";
        }


        return representacion;
    }



    
    /**
     * Remueve las aparciones de 'v' en las vecindades de los vertices
     * de la grafica.
     * 
     * @param v vertice a verificar
     */
    private void removerVecinosDeVertices(Vertice v){
        for(int i = 0; i<this.vertices.size(); ++i){
            for(int j = 0; j<this.vertices.get(i).getGrado(); ++j){
                for(int k = 0; k<v.getGrado(); ++k){
                    if(this.vertices.get(i).getVecinos().get(j).getNombreVertice().equals(v.getVecinos().get(k).getNombreVertice())){
                        this.vertices.get(i).eliminarVecino(v.getVecinos().get(k));
                        j--;
                    }
                }
            }
        }
    }

    /**
     * Agrega las adyacencias que estaban con el vertice v.
     * 
     * @param g grafica anterior a la eliminacion de v
     * @param s conjunto independiente a verificar
     * @param v vertice eliminado
     */
    private void agregarVerticesEliminados(Grafica g, ArrayList<Vertice> s, Vertice v){

        Vertice verticeActual;
        for(int i = 0; i<g.vertices.size(); ++i){

            //Si es el vertice 'v'
            if(g.vertices.get(i).getNombreVertice().equals(v.getNombreVertice()))
                continue;

            verticeActual = g.vertices.get(i);

            if(getVerticeConjunto(s, verticeActual.getNombreVertice()) != null && verticeActual.esVecino(v)){
                Vertice verticeEnS = getVerticeConjunto(s, verticeActual.getNombreVertice());
                verticeEnS.crearFlecha(v);
            }    
            
        }

    }



}