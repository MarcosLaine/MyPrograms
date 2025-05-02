import java.util.Scanner;

public class SomaPrimeiroUltimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de elementos N: ");
        int N = scanner.nextInt();

        int[] numeros = new int[N];

        for (int i = 0; i < N; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        System.out.println("Soma dos elementos correspondentes:");
        for (int i = 0; i < N / 2; i++) {
            int soma = numeros[i] + numeros[N - i - 1];
            System.out.println(numeros[i] + " + " + numeros[N - i - 1] + " = " + soma);
        }

        // Se N for ímpar, somamos o elemento do meio com ele mesmo
        if (N % 2 != 0) {
            System.out.println(numeros[N / 2] + " + " + numeros[N / 2] + " = " + (numeros[N / 2] * 2));
        }

        scanner.close();
    }
}
