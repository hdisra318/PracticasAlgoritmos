import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

        return this.vertices.contains(vertice);
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
     * Ejecucion del programa
     */
    public static void main(String[] args){

        //Archivo donde esta la grafica
        File archivo = new File("Grafica1.txt");

        //Grafica resultante
        Grafica grafica = new Grafica();

        try{
            Scanner sc = new Scanner(archivo);

            //Leyendo cuales son los vértices de la grafica
            String verticesStr = sc.nextLine();

            String arregloVertices[] = verticesStr.split(",");

            for (String vertice : arregloVertices) {
                grafica.agregarVertice(new Vertice(vertice));
            }

            /* Leyendo las aristas del archivo para la grafica */
            String flecha;
            while(sc.hasNext()){

                flecha = sc.nextLine();

                //Eliminando los espacios al final de la oracion (si los hay)
                flecha = flecha.trim();
                String arregloFlecha[] = flecha.split(",");

                grafica.agregarFlecha(new Vertice(arregloFlecha[0]), new Vertice(arregloFlecha[1]));


                

            }

            System.out.println("Grafica:\n");
            System.out.println(grafica);

        }catch(FileNotFoundException fnfe){
            System.out.println("Error al leer el archivo");
        }
    }

}