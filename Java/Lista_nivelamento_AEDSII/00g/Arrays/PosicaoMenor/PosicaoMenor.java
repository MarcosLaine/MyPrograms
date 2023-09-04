import java.util.Scanner;

public class PosicaoMenor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de elementos N: ");
        int N = scanner.nextInt();

        int[] elementos = new int[N];

        for (int i = 0; i < N; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            elementos[i] = scanner.nextInt();
        }

        int posicaoMenor = 0;

        for (int i = 1; i < N; i++) {
            if (elementos[i] < elementos[posicaoMenor]) {
                posicaoMenor = i;
            }
        }

        System.out.println("A posição do menor elemento é: " + posicaoMenor);

        scanner.close();
    }
}
