import java.util.Scanner;

public class CalculaNotasAlunos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantidadeAlunos = 5;
        double[] notas = new double[quantidadeAlunos];
        double somaNotas = 0;
        double menorNota = Double.MAX_VALUE;

        for (int i = 0; i < quantidadeAlunos; i++) {
            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();

            somaNotas += notas[i];

            if (notas[i] < menorNota) {
                menorNota = notas[i];
            }
        }

        double mediaNotas = somaNotas / quantidadeAlunos;

        System.out.println("Soma das notas: " + somaNotas);
        System.out.println("Média das notas: " + mediaNotas);
        System.out.println("Menor nota: " + menorNota);

        scanner.close();
    }
}
