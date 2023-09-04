import java.util.Scanner;

public class MaiorQueMedia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de números: ");
        int N = scanner.nextInt();

        int[] numeros = new int[N];
        int soma = 0;

        for (int i = 0; i < N; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
            soma += numeros[i];
        }

        double media = (double) soma / N;

        System.out.println("A média dos números é: " + media);
        System.out.println("Números maiores que a média:");

        for (int i = 0; i < N; i++) {
            if (numeros[i] > media) {
                System.out.println(numeros[i]);
            }
        }

        scanner.close();
    }
}
