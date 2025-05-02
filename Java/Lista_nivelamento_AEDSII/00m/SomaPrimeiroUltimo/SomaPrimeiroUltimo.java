import java.util.Scanner;

public class SomaPrimeiroUltimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de números: ");
        int n = scanner.nextInt();

        int soma = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Digite o " + i + "º número: ");
            int numero = scanner.nextInt();

            if (i % 2 == 1) {
                soma += numero;
            }
        }

        System.out.println("A soma dos números nas posições ímpares é: " + soma);

        scanner.close();
    }
}
