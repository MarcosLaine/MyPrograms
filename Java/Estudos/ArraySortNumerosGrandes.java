import java.util.Arrays;
import java.util.Random;

public class ArraySortNumerosGrandes {
    public static void main(String[] args) {
        int[] array = new int[100000000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000000);  // Preenche o array com números aleatórios entre 0 e 99
        }

        // Imprimindo o array original
        // System.out.print("Array original: [");
        // for (int i : array) {
        //     System.out.print(i + " ");
        // }
        // System.out.println("]");

        //================================== ARRAY SORTING ======================================


        int[] arrayOrdenado = new int[100000000];

        System.arraycopy(array, 0, arrayOrdenado, 0, 100000000);  // Copia o array original para arrayOrdenado
        
        long  tempoInicio = System.currentTimeMillis();  // Registra o tempo de início  
        Arrays.sort(arrayOrdenado);  // Ordena o arrayOrdenado
        long  tempoFim = System.currentTimeMillis();  // Registra o tempo de fim


        // Imprimindo o array ordenado
        // System.out.print("Array ordenado: [");
        // for (int i : arrayOrdenado) {
        //     System.out.print(i + " ");
        // }
        // System.out.println("]");
        long tempoTotal = tempoFim - tempoInicio;
        System.out.println("Tempo de ordenação: " + tempoTotal + " ms, " +  tempoTotal / 1000.0 + " s");

        //================================== ARRAY SORTING ======================================

        int[] arrayOrdenadoParcialmente = new int[100000000];

        // Copia o array original para arrayOrdenadoParcialmente
        System.arraycopy(array, 0, arrayOrdenadoParcialmente, 0, 100000000);


        long tempoInicioParcial = System.currentTimeMillis();  // Registra o tempo de início  
        Arrays.sort(arrayOrdenadoParcialmente, 0, 7000000);  // Ordena parcialmente os primeiros 6 elementos
        long tempoFimParcial = System.currentTimeMillis();  // Registra o tempo de fim


        // Imprimindo o array parcialmente ordenado
        // System.out.print("Array parcialmente ordenado: [");
        // for (int i : arrayOrdenadoParcialmente) {
        //     System.out.print(i + " ");
        // }
        // System.out.println("]");
        long tempoTotalParcial = tempoFimParcial - tempoInicioParcial;
        System.out.println("Tempo de ordenação Parcial: " + tempoTotalParcial + " ms, " +  tempoTotalParcial / 1000.0 + " s");
    }
}
