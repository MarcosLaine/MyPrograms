import java.util.Scanner;

public class ContagemCaracteres {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma palavra: ");
        String palavra = scanner.nextLine();

        int totalCaracteres = palavra.length();
        int maiusculos = 0;

        for (int i = 0; i < totalCaracteres; i++) {
            char caracter = palavra.charAt(i);
            if (Character.isUpperCase(caracter)) {
                maiusculos++;
            }
        }

        System.out.println("Número total de caracteres: " + totalCaracteres);
        System.out.println("Número de caracteres maiúsculos: " + maiusculos);

        scanner.close();
    }
}
