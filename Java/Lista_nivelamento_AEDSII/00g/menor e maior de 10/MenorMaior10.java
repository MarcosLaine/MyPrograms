import java.util.Scanner;

public class MenorMaior10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int menor = Integer.MAX_VALUE; // Inicializa com o maior valor possível para int
        int maior = Integer.MIN_VALUE; // Inicializa com o menor valor possível para int

        for (int i = 0; i < 10; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            int numero = scanner.nextInt();

            if (numero < menor) {
                menor = numero;
            }

            if (numero > maior) {
                maior = numero;
            }
        }

        System.out.println("Menor valor: " + menor);
        System.out.println("Maior valor: " + maior);

        scanner.close();
    }
}
