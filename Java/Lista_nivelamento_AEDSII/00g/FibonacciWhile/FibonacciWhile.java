import java.util.Scanner;

public class FibonacciWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro n: ");
        int n = scanner.nextInt();

        int termoAnterior = 0;
        int termoAtual = 1;

        int contador = 2; // Já temos os dois primeiros termos (0 e 1)

        while (contador <= n) {
            int proximoTermo = termoAnterior + termoAtual;
            termoAnterior = termoAtual;
            termoAtual = proximoTermo;
            contador++;
        }

        System.out.println("O " + n + "-ésimo termo da sequência de Fibonacci é: " + termoAtual);

        scanner.close();
    }
}
