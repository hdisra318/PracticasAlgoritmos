import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Practica 04: Clase Grafica 
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Grafica {

    /** Lista de vertices de la grafica */
    ArrayList<Vertice> vertices = new ArrayList<>();

    /** Lista de aristas de la grafica */
    ArrayList<Arista> aristas = new ArrayList<>();

    /** Conjuntos Ajeno */
    ArrayList<ArrayList<Vertice>> disjointSets = new ArrayList<>();

    /** Lista de aristas del arbol de peso minimo */
    ArrayList<Arista> aristasArbol = new ArrayList<>();

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
     * @param peso el peso de la arista
     */
    public void agregarArista(Vertice verticeOrigen, Vertice verticeDestino, int peso){

        if(existeVertice(verticeOrigen) && existeVertice(verticeDestino)){

            this.aristas.add(new Arista(verticeOrigen, verticeDestino, peso));
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
     * Algoritmo de Kruskal.
     * 
     * @return el peso del arbol generador de peso minimo
     */
    public int kruskal(){


        // Cola de Prioridades que ordena las aristas segun su peso
        PriorityQueue<Arista> queue = new PriorityQueue<>(aristas.size(), new Comparator<Arista>() {
            // El minimo es el de mayor prioridad
            public int compare(Arista e1, Arista e2){
                if(e1.peso < e2.peso) return -1;
                if(e1.equals(e2)) return 0;
                return 1;
            }
        });

        // Vertices auxiliares
        Vertice v, v_i, v_f;

        // Arista auxiliar
        Arista e;

        // Peso del arbol generador de peso minimo
        int pesoArbol = 0;

        
        ArrayList<Vertice> set1, set2;


        // Tomando al primer vertice
        v = this.vertices.get(0);
        int i = 1;
        while(v != null){
            // Creacion de conjunto ajeno con representante v
            this.creaConjunto(v);

            v = this.vertices.get(i);
            i++;
        }

        // Tomando la primer arista
        e = this.aristas.get(0);
        int llave;
        int j = 1;
        
        while(e != null){
            
            llave = e.peso;
            queue.add(e);
            e = this.aristas.get(j);
            j++;
        }


        while(!queue.isEmpty()){
            e = queue.poll();
            v_i = e.v1;
            v_f = e.v2;
            set1 = this.find(v_i);
            set2 = this.find(v_f);

            if(!set1.equals(set2)){
                this.union(set1, set2);
                pesoArbol += e.peso;
                aristasArbol.add(e);
            }

        }

        return pesoArbol;
    }


    /**
     * Crea un conjunto ajeno dado el representante.
     * 
     * @param rep representante del conjunto ajeno
     */
    private void creaConjunto(Vertice rep){

        ArrayList<Vertice> set = new ArrayList<>();
        set.add(rep);
        disjointSets.add(set);

    }

    /**
     * Encuentra el conjunto ajeno dado un vertice
     * 
     * @param vertice el vertice a buscar en un conjunto ajeno
     * @return el conjunto ajeno donde se encuentra el vertice
     */
    private ArrayList<Vertice> find(Vertice vertice){

        ArrayList<Vertice> set = null;

        boolean found = false;
        for(int i = 0; i < disjointSets.size(); i++){

            for(int j = 0; j < disjointSets.get(i).size(); ++j){

                if(vertice.equals(disjointSets.get(i).get(j))){
                    found = true;
                    set = disjointSets.get(i);
                }
            }

            if(found)
                break;
        }

        return set;

    }

    /**
     * Une dos conjuntos ajenos
     * 
     * @param set1 conjunto ajeno a unir
     * @param set2 conjunto ajeno a unir
     */
    private void union(ArrayList<Vertice> set1, ArrayList<Vertice> set2){

        // El nuevo conjunto estara en la posicion mas pequeña y la otra se borrara
    }

}