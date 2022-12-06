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
            String flecha;
            while(sc.hasNext()){

                flecha = sc.nextLine();
                
                //Eliminando los espacios al final de la oracion (si los hay)
                flecha = flecha.trim();
                String arregloFlecha[] = flecha.split(",");

                Vertice vOrigen = grafica.getVertice(arregloFlecha[0]);
                Vertice vDestino = grafica.getVertice(arregloFlecha[1]);

                if(vDestino == null && vOrigen == null)
                    continue;

                //Agregando las flechas leidas del archivo
                grafica.agregarFlecha(vOrigen, vDestino);
                    
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

        //Impresion del conjunto independiente
        System.out.println("Conjunto Independiente = "+grafica.conjIndepTeor());

        lecturaConsol.close();
    }
}
