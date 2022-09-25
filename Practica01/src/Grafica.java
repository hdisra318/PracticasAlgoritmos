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
     * Agrega una flecha a la grafica.
     * 
     * @param verticeOrigen el vertice de donde sale la flecha
     * @param verticeDestino el vertice donde llega la flecha
     */
    public void agregarFlecha(Vertice verticeOrigen, Vertice verticeDestino){

        if(existeVertice(verticeOrigen) && existeVertice(verticeDestino)){
           
            verticeOrigen.crearFlecha(verticeDestino);
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
     * Funcion que regresa el Conjunto Independiente de G tal que cualquier vertice
     * en la grafica puede ser alcanzado a partir del conjunto por una trayectoria de
     * a lo mas 2.
     * 
     * @return el Conjunto Independiente formado
     */
    public ArrayList<Vertice> conjIndepTeor(){

        // El Conjunto Independiente encontrado
        ArrayList<Vertice> conjIndep = new ArrayList<>();

        // Llamada a la funcion auxiliar
        return conjIndepTeorAux(conjIndep);
    }

    /**
     * Funcion auxiliar de conjIndepTeor que ayuda a formar el
     * Conjunto Independiente que se busca.
     * 
     * @param conjIndep el conjunto independiente formado
     * @return el conjunto independiente buscado
     */
    public ArrayList<Vertice> conjIndepTeorAux(ArrayList<Vertice> conjIndep){

        /* Casos Base */
        if(this.vertices.size() == 1){
            conjIndep.add(this.vertices.get(0));
            return conjIndep;
        }

        if(this.vertices.size() == 2){
            Vertice vertice0 = this.vertices.get(0);
            Vertice vertice1 = this.vertices.get(1);
            if(vertice0.esVecino(vertice1)){
                //Solo agregamos un vertice al conjunto
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

        if(this.vertices.size() == 3){

            //Casos con 3 vertices
            ArrayList<Vertice> conjIndepAux = new ArrayList<>();
            conjIndepAux.add(this.vertices.get(0));//Inicialmente con el primer vertice
            conjIndep = conjIndepAux;
            for(int i = 0; i<this.vertices.size(); ++i){
                
                conjIndepAux = new ArrayList<>();
                conjIndepAux.add(this.vertices.get(i));

                for(int j = 0; j<this.vertices.size(); ++j){
                    //Si el conjunto es independiente agregando al vertice en la posicion j
                    if(esIndependiente(conjIndepAux, this.vertices.get(j))){
                        conjIndepAux.add(this.vertices.get(j));
                    }
                }

                if(mayorGrado(conjIndep,conjIndepAux)){
                    conjIndep = conjIndepAux;
                }
                
            }

            return conjIndep;
            
        }


        /* Recursion */
        //Agarrar a un vertice de la grafica (el ultimo registrado)
        Vertice v = this.vertices.get(this.vertices.size()-1);

        //Eliminar a los vecinos de v de la grafica, incluyendolo
        this.vertices.removeAll(v.getVecinos());

        //Eliminar a v y sus vecinos de los vecinos de los demas vertices
        this.removerVecinosDeVertices(v);

        //Llamada recursiva
        conjIndep = conjIndepTeorAux(conjIndep);

        //Agregar a v y sus vecinos despues de generar al conjunto indepenediente anterior
        Grafica graficaOriginal = Main.creaGrafica();

        //Agregar los vertices eliminados antes de la llamada recursiva
        agregarVerticesEliminados(graficaOriginal, conjIndep, v);

        if(esIndependiente(conjIndep, v)){
            conjIndep.add(v);
        }

        return conjIndep;
    }

    /** 
     * Verifica si la suma de los grados de los vertices del conjunto 
     * independiente 's' es mayor que los de s2.
     * 
     * @param s conjunto independiente actual
     * @param s2 conjunto independiente a verificar
     * @return true si cumple, false en otro caso
     */
    public boolean mayorGrado(ArrayList<Vertice> s, ArrayList<Vertice> s2){

        boolean mayor;

        int gradoS1 = 0;
        int gradoS2 = 0;

        //Obteniendo grados de todos lo vertices del conjunto s
        for(int i = 0; i<s.size(); ++i){
            gradoS1 += s.get(i).getGrado();
        }

        //Obteniendo grados de todos lo vertices del conjunto s2
        for(int i = 0; i<s2.size(); ++i){
            gradoS2 += s2.get(i).getGrado();
        }

        if(gradoS1< gradoS2){
            mayor = true;
        }else{
            mayor = false;
        }

        return mayor;
    }

    /**
     * Verfica si un conjunto es independiente si se agrega el vertice dado.
     * 
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

    /**
     * Devuelve el vertice dado el nombre en el conjunto independiente.
     * 
     * @param s conjunto independiente
     * @param nombre nombre del vertice 
     * @return el vertice buscado
     */
    private Vertice getVerticeConjunto(ArrayList<Vertice> s, String nombre){

        for(int i = 0; i<s.size(); ++i){
            if(s.get(i).getNombreVertice().equals(nombre))
                return s.get(i);
        }

        return null;
    }


}