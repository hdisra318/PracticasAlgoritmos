import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Practica 04: Clase para la ejecucion del programa.
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Main {


    /** Ruta del archivo de la grafica */
    public static String rutaArchivo;

    /**
     * Lee el archivo y crea la grafica.
     * 
     * @return la grafica creada
     * 
     */
    public static Grafica creaGrafica(){

        // Instancia de File con el archivo
        File archivo = new File(Main.rutaArchivo);

        //Grafica resultante
        Grafica grafica = new Grafica();

        try{
                
            Scanner sc = new Scanner(archivo);

            //Leyendo cuales son los vértices de la grafica
            String verticesStr = sc.nextLine();
            verticesStr = verticesStr.trim(); //Eliminando los espacios al final de la linea si existen
            String arregloVertices[] = verticesStr.split(",");

            for (String vertice : arregloVertices) {
                grafica.agregarVertice(new Vertice(vertice));
            }

            /* Leyendo las aristas del archivo para la grafica */
            String arista;
            while(sc.hasNext()){

                arista = sc.nextLine();
                
                //Eliminando los espacios al final de la oracion (si los hay)
                arista = arista.trim();
                String arregloArista[] = arista.split(",");

                Vertice vOrigen = grafica.getVertice(arregloArista[0]);
                Vertice vDestino = grafica.getVertice(arregloArista[1]);
                int peso = Integer.parseInt(arregloArista[2]);

                if(vDestino == null && vOrigen == null)
                    continue;

                //Agregando las aristas leidas del archivo
                grafica.agregarArista(vOrigen, vDestino, peso);
                    
            }

            sc.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("Error al leer el archivo");
            System.exit(0);
        }catch(Exception e){
            System.out.println("Error, el archivo no está en el formato especificado");
            System.exit(0);
        }

        return grafica;

    }

    public static void main(String[] args){

        /* Ejecucion del programa */

        Scanner lecturaConsol = new Scanner(System.in);
        System.out.println("-> Ingresa la ruta del archivo donde se encuentra la grafica:");
        String rutaArchivo = lecturaConsol.nextLine();
        System.out.println(); //Salto de linea
        Main.rutaArchivo = rutaArchivo;
        Grafica grafica = Main.creaGrafica();

        System.out.println("La aristas de la grafica son: \n"+grafica.toString());
        //Impresion del peso del arbol generador de peso minimo y las aristas que lo componen.
        System.out.println("Peso del arbol generador de peso minimo = "+grafica.kruskal());
        System.out.println("Aristas que componen al arbol generador de peso minimo:");
        for(int i = 0; i<grafica.aristasArbol.size(); ++i){
            System.out.println(grafica.aristasArbol.get(i).toString());
        }

        lecturaConsol.close();
    }
}
