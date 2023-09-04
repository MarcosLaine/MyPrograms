import java.util.Scanner;

public class MatrizGrid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de linhas L: ");
        int L = scanner.nextInt();

        System.out.print("Digite o número de colunas C: ");
        int C = scanner.nextInt();

        int[][] matriz = new int[L][C];

        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print("Elemento da posição (" + (i + 1) + ", " + (j + 1) + "): ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Matriz em formato de grid:");
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
