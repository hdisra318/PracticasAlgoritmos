/*
 * Algoritmo Shell Sort
 */
public class ShellSort {

    /* Operaciones hechas con los incrementos de Shell */
    int opShell = 0;
    
    /* Operaciones hechas con los incrementos de Hibbard */
    int opHibbard = 0;

    /* Shell Sort con los incrementos de Shell */
    public void ShellSortIncremShell(int[] a, int n){

        int j,h,v;

        h = (int) (Math.floor(n/2));

        while(h>0) {
            for(int i = h; i<n; i++){

                v = a[i];

                j = i;
                while(j>=h && a[j-h]>v){
                    opShell++;
                    a[j] = a[j-h];

                    j = j-h;
                }

                a[j] = v;
            }

            System.out.println("Cantidad de operaciones en el incremento "+h+": "+opShell);

            h = (int) (Math.floor(h/2));
        }
    }



    /* Shell Sort con los incrementos de Hibbard */
    public void ShellSortIncremHibbard(int[] a, int n){

        int[] incrementosH = incrementosHibbard(n);

        int i,j;
        int tmp;

        for( int increm = incrementosH.length-1; increm>= 0; increm--){
            int h = incrementosH[increm];
            for(i = h; i<a.length; i++){
                tmp = a[i];
                for(j = i; j >= h; j -= h){
                    if(tmp < a[j - h]){
                        opHibbard++;
                        a[j] = a[j - h];
                    } else {
                        break;
                    }
                    
                }
                a[j] = tmp; 
            }
            System.out.println("Cantidad de operaciones en el incremento "+incrementosH[increm]+": "+opHibbard);
        }

    }


    /* Calcula los incrementos de Hibbard dado el tamaño de
       la secuencia */
    public int[] incrementosHibbard(int n){

        //Logaritmo natural de n+1
        int numIncrementos = (int) (Math.floor(Math.log10(n+1) / Math.log10(2)));

        int incremHibbard[] = new int[numIncrementos];

        for(int k = 0; k<numIncrementos; ++k){

            incremHibbard[k] = (int) Math.pow(2, k+1) - 1;
        }


        return incremHibbard;
    }



    /* Imprime la secuencia */
    public String secuenciaString(int[] secuencia){

        String sec = "";

        for(int i = 0; i<secuencia.length; ++i){
            sec += secuencia[i] + " ";
        }

        return sec;
    }


    public static void main(String[] args) {
        
        ShellSort shellSort = new ShellSort();

        // Secuencia de 32 elementos que realiza menos operaciones con 
        // la version de Shell que la de Hibbard
        int[] sec1 = {1,2,3,4,32,31,30,29,5,6,7,8,28,27,26,25,9,10,11,12,
                24,23,22,21,13,14,15,16,20,19,18,17};
        int[] sec2 = {1,2,3,4,32,31,30,29,5,6,7,8,28,27,26,25,9,10,11,12,
            24,23,22,21,13,14,15,16,20,19,18,17};

        System.out.println("Secuencia original = "+shellSort.secuenciaString(sec2)+"\n");
        
        System.out.println("-- Shell Sort version Shell --");
        shellSort.ShellSortIncremShell(sec1, 32);
        System.out.println("- Cantidad de operaciones finales con los incrementos "+
         "de Shell: "+shellSort.opShell+"\n");

        System.out.println("\n-- Shell Sort version Hibbard --");
        shellSort.ShellSortIncremHibbard(sec2, 32);
        System.out.println("- Cantidad de operaciones finales con los incrementos "+
        "de Hibbard: "+shellSort.opHibbard);

        System.out.println("\nSecuencia ordenada: "+shellSort.secuenciaString(sec2));
    }
}