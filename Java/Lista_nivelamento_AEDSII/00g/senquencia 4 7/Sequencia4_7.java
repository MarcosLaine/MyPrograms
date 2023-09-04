import java.util.Scanner;

public class Sequencia4_7 {
       public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro N: ");
        int N = scanner.nextInt();

        int numero = 1;
        int incremento = 4;
        int contador = 0;

        while (contador < N) {
            System.out.println(numero);
            if (contador % 2 == 0) {
                numero += incremento;
            } else {
                numero += (incremento + 3);
            }
            contador++;
        }

        scanner.close();
    }
}