import java.util.Scanner;

public class resultadoPartida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de gols do time mandante: ");
        int golsMandante = scanner.nextInt();

        System.out.print("Digite o número de gols do time visitante: ");
        int golsVisitante = scanner.nextInt();

        if (golsMandante > golsVisitante) {
            System.out.println("O time mandante venceu!");
        } else if (golsMandante < golsVisitante) {
            System.out.println("O time visitante venceu!");
        } else {
            System.out.println("A partida terminou em empate.");
        }

        scanner.close();
    }
}
