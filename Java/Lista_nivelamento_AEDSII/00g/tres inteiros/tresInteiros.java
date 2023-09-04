import java.util.Scanner;

public class tresInteiros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o primeiro número:");
        int numero1 = scanner.nextInt();

        System.out.println("Digite o segundo número:");
        int numero2 = scanner.nextInt();

        System.out.println("Digite o terceiro número:");
        int numero3 = scanner.nextInt();

        int menor = Math.min(Math.min(numero1, numero2), numero3);
        int maior = Math.max(Math.max(numero1, numero2), numero3);

        System.out.println("Menor valor: " + menor);
        System.out.println("Maior valor: " + maior);

        scanner.close();
    }
}

