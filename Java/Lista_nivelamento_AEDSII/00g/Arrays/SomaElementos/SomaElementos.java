import java.util.Scanner;

public class SomaElementos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de elementos N: ");
        int N = scanner.nextInt();

        int[] elementos = new int[N];
        int soma = 0;

        for (int i = 0; i < N; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            elementos[i] = scanner.nextInt();
        }

        if (N % 2 == 0) {
            System.out.println("Soma dos elementos adjacentes:");
            for (int i = 0; i < N; i += 2) {
                soma = elementos[i] + elementos[i + 1];
                System.out.println(elementos[i] + " + " + elementos[i + 1] + " = " + soma);
            }
        } else {
            System.out.println("O número de elementos é ímpar, não é possível fazer a soma dos elementos adjacentes.");
        }

        scanner.close();
    }
}
