import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(100);

        System.out.println("Unsorted array: ");
        printArray(array, 0);

        mergeSort(array, 0);

        System.out.println("Sorted array: ");
        printArray(array, 0);
    }

    private static void printArray(int[] array, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void mergeSort(int[] array, int level) {
        int length = array.length;

        if (length < 2) {
            printArray(array, level);
            return;
        }

        int meio = length / 2;

        int[] esquerda = new int[meio];
        int[] direita = new int[length - meio];

        for (int i = 0; i < meio; i++) {
            esquerda[i] = array[i];
        }
        for (int i = meio; i < length; i++) {
            direita[i - meio] = array[i];
        }

        mergeSort(esquerda, level + 1);
        mergeSort(direita, level + 1);
        
        merge(esquerda, direita, array);

        printArray(array, level);
    }

    private static void merge(int[] esquerda, int[] direita, int[] array) {
        int parteEsquerda = esquerda.length;
        int parteDireita = direita.length;

        int i = 0, esq = 0, dir = 0;

        while (esq < parteEsquerda && dir < parteDireita) {
            if (esquerda[esq] < direita[dir]) {
                array[i] = esquerda[esq];
                i++; esq++;
            } else {
                array[i] = direita[dir];
                i++; dir++;
            }
        }
        while (esq < parteEsquerda) {
            array[i] = esquerda[esq];
            i++; esq++;
        }

        while (dir < parteDireita) {
            array[i] = direita[dir];
            i++; dir++;
        }
    }
}
