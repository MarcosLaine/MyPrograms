import java.util.Scanner;

public class DiagonaisMatriz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a ordem da matriz quadrada (N): ");
        int N = scanner.nextInt();

        int[][] matriz = new int[N][N];

        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Diagonal Principal:");
        for (int i = 0; i < N; i++) {
            System.out.print(matriz[i][i] + " ");
        }

        System.out.println("\nDiagonal Secundária:");
        for (int i = 0; i < N; i++) {
            System.out.print(matriz[i][N - 1 - i] + " ");
        }

        scanner.close();
    }
}
