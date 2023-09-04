import java.util.Scanner;

public class dezInteiros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maior = Integer.MIN_VALUE; // Inicializa com o menor valor possível para int

        for (int i = 0; i < 10; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            int numero = scanner.nextInt();

            if (numero > maior) {
                maior = numero;
            }
        }

        System.out.println("O maior número é: " + maior);

        scanner.close();
    }
}
