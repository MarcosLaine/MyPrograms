import java.util.Scanner;

public class OrdenacaoArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do array: ");
        int n = scanner.nextInt();

        int[] array = new int[n];

        // Lê os elementos do array
        for (int i = 0; i < n; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // Aplica o algoritmo de ordenação Selection Sort
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = array[i];
            array[i] = array[indiceMenor];
            array[indiceMenor] = temp;
        }

        System.out.println("Array ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        scanner.close();
    }
}
