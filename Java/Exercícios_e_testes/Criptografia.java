import java.io.IOException;
import java.util.Scanner;

public class Criptografia {

    public static String criptografa(String entrada) {
        String novaString = "";
        for (int i = 0; i < entrada.length(); i++) {
            if (Character.isLetter(entrada.charAt(i))) {
                char novoChar = (char) (entrada.charAt(i) + 3);
                novaString += novoChar;
            } else if (Character.isDigit(entrada.charAt(i))) {
                novaString += entrada.charAt(i);
            } else {
                novaString += entrada.charAt(i);
            }
        }
        return novaString;
    }

    public static String inverte(String entrada) {
        String novaString = "";
        for (int i = entrada.length() - 1; i > -1; i--) {
            char novoChar = entrada.charAt(i);
            novaString += novoChar;
        }
        return novaString;
    }

    public static String deslocaMetade(String entrada) {
        int meio = entrada.length() / 2;
        String primeiraMetade = entrada.substring(0, meio);
        String segundaMetade = entrada.substring(meio);
        String novaSegunda = "";
        for (int i = 0; i < segundaMetade.length(); i++) {
            char novoChar = segundaMetade.charAt(i);
            novaSegunda += (char) (novoChar-1);

        }
        return primeiraMetade + novaSegunda;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int tentativas = scanner.nextInt();
        scanner.nextLine();
        while (tentativas > 0) {
            String entrada = scanner.nextLine();
            String criptogradada = criptografa(entrada);
            String invertida = inverte(criptogradada);
            //System.err.println(criptogradada);
            //System.out.println(invertida);
            System.out.println(deslocaMetade(invertida));
            tentativas--;
        }
        scanner.close();
    }
}