//package Lista_nivelamento_AEDSII.00g.notas Universidade;
import java.util.Scanner;

public class notas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a nota máxima na prova: ");
        double notaMaxima = scanner.nextDouble();

        int totalAlunos = 20;
        int contador = 0;
        double somaNotas = 0;
        int alunosAbaixoMediaUniversidade = 0;
        int alunosConceitoA = 0;

        while (contador < totalAlunos) {
            System.out.print("Digite a nota do aluno " + (contador + 1) + ": ");
            double nota = scanner.nextDouble();

            somaNotas += nota;

            if (nota < 0 || nota > notaMaxima) {
                System.out.println("Nota inválida. Digite novamente.");
                continue;
            }

            if (nota < 0.6 * notaMaxima) {
                alunosAbaixoMediaUniversidade++;
            }

            if (nota > 0.9 * notaMaxima) {
                alunosConceitoA++;
            }

            contador++;
        }

        double mediaTurma = somaNotas / totalAlunos;
        System.out.println("Média da turma: " + mediaTurma);
        System.out.println("Alunos abaixo da média da universidade: " + alunosAbaixoMediaUniversidade);
        System.out.println("Alunos com conceito A: " + alunosConceitoA);

        scanner.close();
    }
}
