public class OrdenacaoRecursiva {
    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3, 7, 4, 6};
        System.out.print("Array antes da ordenação: ");
        imprimirArray(array);
        
        ordenarArray(array, 0, array.length - 1);
        
        System.out.print("Array após a ordenação: ");
        imprimirArray(array);
    }

    public static void ordenarArray(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = particionar(array, inicio, fim);
            ordenarArray(array, inicio, posicaoPivo - 1);
            ordenarArray(array, posicaoPivo + 1, fim);
        }
    }

    public static int particionar(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = inicio - 1;
        
        for (int j = inicio; j < fim; j++) {
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }
        
        trocar(array, i + 1, fim);
        return i + 1;
    }

    public static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void imprimirArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
