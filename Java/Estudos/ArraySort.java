import java.util.Arrays;
import java.util.Random;

public class ArraySort {
    public static void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);  // Preenche o array com números aleatórios entre 0 e 99
        }

        // Imprimindo o array original
        System.out.print("Array original: [");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("]");

        //================================== ARRAY SORTING ======================================

        int[] arrayOrdenado = new int[10];

        System.arraycopy(array, 0, arrayOrdenado, 0, 10);  // Copia o array original para arrayOrdenado
        
        Arrays.sort(arrayOrdenado);  // Ordena o arrayOrdenado

        // Imprimindo o array ordenado
        System.out.print("Array ordenado: [");
        for (int i : arrayOrdenado) {
            System.out.print(i + " ");
        }
        System.out.println("]");

        //================================== ARRAY SORTING ======================================

        int[] arrayOrdenadoParcialmente = new int[10];

        // Copia o array original para arrayOrdenadoParcialmente
        System.arraycopy(array, 0, arrayOrdenadoParcialmente, 0, 10);

        Arrays.sort(arrayOrdenadoParcialmente, 0, 6);  // Ordena parcialmente os primeiros 6 elementos

        // Imprimindo o array parcialmente ordenado
        System.out.print("Array parcialmente ordenado: [");
        for (int i : arrayOrdenadoParcialmente) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
}
