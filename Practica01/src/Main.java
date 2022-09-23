import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Clase para la ejecucion del programa.
 * 
 * @author Israel Hernandez Dorantes - 318206604
 * @version 1.0 2022
 */
public class Main {

    public static void main(String[] args){

        /* Ejecucion del programa */

        Scanner lecturaConsol = new Scanner(System.in);
        //System.out.println("-> Ingresa la ruta del archivo donde se encuentra la grafica:");
        //String rutaArchivo = lecturaConsol.nextLine();
        System.out.println(); //Salto de linea

        // Instancia de File con el archivo
        File archivo = new File("Grafica1.txt");

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
            String flecha;
            while(sc.hasNext()){

                flecha = sc.nextLine();
            
                //Eliminando los espacios al final de la oracion (si los hay)
                flecha = flecha.trim();
                String arregloFlecha[] = flecha.split(",");

                //Agregando las flechas leidas del archivo
                grafica.agregarFlecha(new Vertice(arregloFlecha[0]), new Vertice(arregloFlecha[1]));
                
            }

            System.out.println("Grafica:\n");
            System.out.println(grafica);

            System.out.println("Conjunto Independiente = " + grafica.conjIndepTeor());

            sc.close();

        }catch(FileNotFoundException fnfe){
            System.out.println("Error al leer el archivo: "+ fnfe.getMessage());
        }
    }
}
