
import java.util.Scanner;

public class EncryptionProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int i = 0; i < testCases; i++) {
            int lines = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            for (int j = 0; j < lines; j++) {
                String line = scanner.nextLine();
                String encrypted = encrypt(line);
                System.out.println(encrypted);
            }
        }

        scanner.close();
    }

    private static String encrypt(String line) {
        // Primeira etapa: Deslocar três posições à direita na tabela ASCII
        StringBuilder shifted = new StringBuilder();
        for (char c : line.toCharArray()) {
            shifted.append((char) (c + 3));
        }

        // Segunda etapa: Inverter a string
        shifted.reverse();

        // Terceira etapa: Mover caracteres da metade para frente uma posição à esquerda na tabela ASCII
        for (int i = shifted.length() / 2; i < shifted.length(); i++) {
            shifted.setCharAt(i, (char) (shifted.charAt(i) - 1));
        }

        return shifted.toString();
    }
}
