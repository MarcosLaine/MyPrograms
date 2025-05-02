import java.util.Scanner;

public class VetoresIntercalados {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho dos vetores N: ");
        int N = scanner.nextInt();

        int[] vetor1 = new int[N];
        int[] vetor2 = new int[N];

        System.out.println("Digite os elementos do primeiro vetor:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vetor1[i] = scanner.nextInt();
        }

        System.out.println("Digite os elementos do segundo vetor:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vetor2[i] = scanner.nextInt();
        }

        System.out.println("Vetores intercalados:");
        for (int i = 0; i < N; i++) {
            System.out.print(vetor1[i] + " " + vetor2[i] + " ");
        }

        scanner.close();
    }
}
