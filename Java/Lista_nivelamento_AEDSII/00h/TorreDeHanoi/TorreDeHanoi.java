package TorreDeHanoi;
import java.util.Scanner;

public class TorreDeHanoi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numDiscos;

        System.out.print("Digite o número de discos: ");
        numDiscos = scanner.nextInt();

        if (numDiscos <= 0) {
            System.out.println("Número de discos inválido. Digite um número positivo.");
        } else {
            int movimentos = resolverTorreDeHanoi(numDiscos, 'A', 'B', 'C');
            System.out.println("Número de movimentos necessários: " + movimentos);
        }

        scanner.close();
    }

    public static int resolverTorreDeHanoi(int numDiscos, char torreOrigem, char torreAuxiliar, char torreDestino) {
        if (numDiscos == 1) {
            System.out.println("Mova o disco 1 da Torre " + torreOrigem + " para a Torre " + torreDestino);
            return 1;
        } else {
            int movimentos = 0;

            movimentos += resolverTorreDeHanoi(numDiscos - 1, torreOrigem, torreDestino, torreAuxiliar);

            System.out.println("Mova o disco " + numDiscos + " da Torre " + torreOrigem + " para a Torre " + torreDestino);
            movimentos++;

            movimentos += resolverTorreDeHanoi(numDiscos - 1, torreAuxiliar, torreOrigem, torreDestino);

            return movimentos;
        }
    }
}
