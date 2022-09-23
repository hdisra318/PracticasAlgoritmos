import java.util.ArrayList;

/**
 * Clase Grafica 
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Grafica {

    /** Lista de vertices de la grafica */
    ArrayList<Vertice> vertices = new ArrayList<>();

    /** Lista de flechas de la grafica */
    ArrayList<Arista> flechas = new ArrayList<>();

    /**
     * Constructor de una grafica.
     */
    public Grafica(){}

    /**
     * Constructor de una grafica dado un vertice inicial
     * @param vertice el vertice inicial de la grafica
     */
    public Grafica(Vertice vertice){
        vertices.add(vertice);
    }

    /** 
     * Agrega un vertice a la grafica
     * @param vertice el nuevo vertice a agregar a la grafica
     */
    public void agregarVertice(Vertice vertice){
        this.vertices.add(vertice);
    }

    /**
     * Agrega una flecha a la grafica
     * @param verticeOrigen el vertice de donde sale la flecha
     * @param verticeDestino el vertice donde llega la flecha
     */
    public void agregarFlecha(Vertice verticeOrigen, Vertice verticeDestino){

        if(existeVertice(verticeOrigen) && existeVertice(verticeDestino)){
            Arista flecha = new Arista(verticeOrigen, verticeDestino);
            this.flechas.add(flecha);
        }
    }


    /**
     * Verifica si existe el vertice dado en la grafica
     * @param vertice el vertice a verficar
     */
    private boolean existeVertice(Vertice vertice){

        for (Vertice verticeActual : vertices) {
            if(verticeActual.getNombreVertice().equals(vertice.getNombreVertice()))
                return true;
        }
        
        return false;
    }

    @Override
    public String toString(){

        String representacion = "";

        for (Arista flecha : this.flechas) {
            representacion += flecha + "\n";
        }


        return representacion;
    }


    /**
     * Funcion que regresa el Conjunto Independiente de G tal que vertice
     * en la grafica puede ser alcanzado a partir del conjunto por una trayectoria
     * a lo mas 2.
     * 
     * @return el Conjunto Independiente formado
     */
    public ArrayList<Vertice> conjIndepTeor(){

        // El Conjunto Independiente encontrado
        ArrayList<Vertice> conjIndep = new ArrayList<>();

        Grafica g = this;
        // Llamada a la funcion auxiliar
        return conjIndepTeorAux(conjIndep, g);
    }

    /**
     * Funcion auxiliar de conjIndepTeor que ayuda a formar el
     * Conjunto Independiente que se busca.
     * 
     * @param conjIndep el conjunto independiente formado
     * @param g la grafica actual
     * @return el conjunto independiente buscado
     */
    public ArrayList<Vertice> conjIndepTeorAux(ArrayList<Vertice> conjIndep, Grafica g){

        /* Casos Base */
        if(g.vertices.size() == 1){
            conjIndep.add(g.vertices.get(0));
            return conjIndep;
        }

        if(g.vertices.size() == 2){
            Vertice vertice0 = g.vertices.get(0);
            Vertice vertice1 = g.vertices.get(1);
            if(vertice0.esVecino(vertice1) && vertice1.esVecino(vertice0)){
                //Solo agregamos un vertice al conjunto
                conjIndep.add(vertice0);

            }else if(vertice0.esVecino(vertice1)){
                conjIndep.add(vertice0);

            }else if(vertice1.esVecino(vertice0)){
                conjIndep.add(vertice1);

            }else{
                //Ninguno es vecino del otro
                conjIndep.add(vertice0);
                conjIndep.add(vertice1);
            }

            return conjIndep;
        }

        if(g.vertices.size() == 3){

            //Casos con 3 vertices
            for(int i = 0; i<g.vertices.size(); ++i){
                
                ArrayList<Vertice> conjIndepAux = new ArrayList<>();
                int maxTamConj;

                conjIndepAux.add(g.vertices.get(i));
                maxTamConj = 1;

                for(int j = 0; j<g.vertices.size(); ++j){
                    //Si el conjunto es independiente agregando al vertice en la posicion j
                    if(esIndependiente(conjIndepAux, g.vertices.get(j))){
                        conjIndepAux.add(g.vertices.get(j));
                    }
                }

                if(maxTamConj < conjIndepAux.size()){
                    maxTamConj = conjIndepAux.size();
                    conjIndep = conjIndepAux;
                }
                
            }

            return conjIndep;
            
        }


        /* Recursion */
        //Agarrar a un vertice de g
        Vertice v = g.vertices.get(g.vertices.size()-1);

        //Eliminar a los vecinos de v de g, incluyendolo
        g.vertices.removeAll(v.getVecinos());

        conjIndep = conjIndepTeorAux(conjIndep, g);

        if(esIndependiente(conjIndep, v)){
            conjIndep.add(v);
        }

        return conjIndep;
    }


    /**
     * Verfica si un conjunto es indipendiente si se agrega el vertice dado.
     * @param S el conjunto independiente a verificar
     * @param vertice a verficar en la grafica
     * @return true si es el conjunto es independiente, false en otro caso
     */
    public boolean esIndependiente(ArrayList<Vertice> S, Vertice v){

        for(Vertice vertice : S){
            if(v.esVecino(vertice) || vertice.esVecino(v))
                return false;
        }

        return true;
    }

}