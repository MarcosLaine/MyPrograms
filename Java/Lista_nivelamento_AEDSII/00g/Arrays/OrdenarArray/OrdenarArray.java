import java.util.Scanner;
import java.util.Arrays;

public class OrdenarArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do vetor N: ");
        int N = scanner.nextInt();

        int[] vetor = new int[N];

        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vetor[i] = scanner.nextInt();
        }

        // Ordenar o vetor
        Arrays.sort(vetor);

        System.out.println("Vetor ordenado:");
        for (int i = 0; i < N; i++) {
            System.out.print(vetor[i] + " ");
        }

        scanner.close();
    }
}
