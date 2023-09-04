import java.util.Scanner;

public class VetoresIntercalados2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho do primeiro vetor N: ");
        int N = scanner.nextInt();

        System.out.print("Digite o tamanho do segundo vetor M: ");
        int M = scanner.nextInt();

        int[] vetor1 = new int[N];
        int[] vetor2 = new int[M];

        System.out.println("Digite os elementos do primeiro vetor:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vetor1[i] = scanner.nextInt();
        }

        System.out.println("Digite os elementos do segundo vetor:");
        for (int i = 0; i < M; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vetor2[i] = scanner.nextInt();
        }

        int tamanhoMenor = Math.min(N, M);

        System.out.println("Vetores intercalados:");
        for (int i = 0; i < tamanhoMenor; i++) {
            System.out.print(vetor1[i] + " " + vetor2[i] + " ");
        }

        // Mostrar os elementos restantes do vetor mais longo
        for (int i = tamanhoMenor; i < N; i++) {
            System.out.print(vetor1[i] + " ");
        }
        for (int i = tamanhoMenor; i < M; i++) {
            System.out.print(vetor2[i] + " ");
        }

        scanner.close();
    }
}
