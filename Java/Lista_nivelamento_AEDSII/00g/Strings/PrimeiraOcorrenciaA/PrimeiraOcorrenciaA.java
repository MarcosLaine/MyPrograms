import java.util.Scanner;

public class PrimeiraOcorrenciaA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma palavra: ");
        String palavra = scanner.nextLine();

        int posicaoA = -1; // Inicializamos com -1 para indicar que não encontramos a letra 'A' ainda

        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == 'A' || palavra.charAt(i) == 'a') {
                posicaoA = i;
                break; // Encontrou a primeira ocorrência, então podemos sair do loop
            }
        }

        if (posicaoA != -1) {
            System.out.println("A primeira ocorrência da letra 'A' está na posição: " + (posicaoA + 1));
        } else {
            System.out.println("A letra 'A' não foi encontrada na palavra.");
        }

        scanner.close();
    }
}
