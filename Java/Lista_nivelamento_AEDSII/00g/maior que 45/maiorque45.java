import java.util.Scanner;

public class maiorque45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int numero1 = scanner.nextInt();

        System.out.print("Digite o segundo número: ");
        int numero2 = scanner.nextInt();

        if (numero1 > 45 || numero2 > 45) {
            int soma = numero1 + numero2;
            System.out.println("A soma dos números é: " + soma);
        } else if (numero1 > 20 && numero2 > 20) {
            int maior = Math.max(numero1, numero2);
            int menor = Math.min(numero1, numero2);
            int subtracao = maior - menor;
            System.out.println("A subtração dos números é: " + subtracao);
        } else if ((numero1 < 10 && numero2 != 0) || (numero2 < 10 && numero1 != 0)) {
            double divisao = (double) numero1 / numero2;
            System.out.println("A divisão dos números é: " + divisao);
        } else {
            System.out.println("Seu nome");
        }

        scanner.close();
    }
}
