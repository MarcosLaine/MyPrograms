import java.util.Scanner;

public class ExercicioWhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalAlunos = 5;
        int contador = 0;
        double somaNotas = 0;

        while (contador < totalAlunos) {
            System.out.print("Digite a nota do aluno " + (contador + 1) + ": ");
            double nota = scanner.nextDouble();
            somaNotas += nota;
            contador++;
        }

        double media = somaNotas / totalAlunos;
        System.out.println("A média das notas dos alunos é: " + media);

        scanner.close();
    }
}
