import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        Random random = new Random();

        int []numeros = new int[10];

        for(int i=0; i< numeros.length; i++){
            numeros[i] = random.nextInt(10000);
        }

        System.out.println("Original:");
        printArray(numeros);

        quicksort(numeros, 0, numeros.length-1);

        System.out.println("Ordenado:");
        printArray(numeros);
    }

   private static void printArray(int array[]){
        for(int i = 0; i< array.length; i++)
            System.out.print(array[i] + ", ");
        System.out.println();
    }

    private static void quicksort(int array[], int esq, int dir){

        if(esq >= dir)
            return;

        int pivo = array[(dir + esq)/2];

        int i = esq;
        int j = dir;

        while(i < j){
            while(array[i] < pivo)
                i++;
            while(array[j] > pivo)
                j--;

            if(i <= j){
                swap(array, i, j);
                i++;
                j--;
            }

        }

        if(esq < j) 
            quicksort(array, esq, j);
        if(i < dir) 
            quicksort(array, i, dir);

    }



    private static void swap(int []array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
