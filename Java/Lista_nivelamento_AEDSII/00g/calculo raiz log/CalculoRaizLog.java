import java.util.Scanner;

public class CalculoRaizLog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        double numero1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double numero2 = scanner.nextDouble();

        double menor = Math.min(numero1, numero2);
        double maior = Math.max(numero1, numero2);

        double raizCubicaMenor = Math.cbrt(menor);
        double logaritmoMenor = Math.log(menor) / Math.log(maior); // log no formato base 10

        System.out.println("Raiz cúbica do menor: " + raizCubicaMenor);
        System.out.println("Logaritmo do menor na base do maior: " + logaritmoMenor);

        scanner.close();
    }
}
