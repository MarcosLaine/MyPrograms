import java.util.Scanner;

public class AnalisePalavra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma palavra: ");
        String palavra = scanner.nextLine();

        int numCaracteres = palavra.length();
        int numLetras = 0;
        int numNaoLetras = 0;
        int numVogais = 0;
        int numConsoantes = 0;

        for (int i = 0; i < numCaracteres; i++) {
            char caracter = Character.toLowerCase(palavra.charAt(i));
            if (Character.isLetter(caracter)) {
                numLetras++;
                if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                    numVogais++;
                } else {
                    numConsoantes++;
                }
            } else {
                numNaoLetras++;
            }
        }

        System.out.println("Número de caracteres: " + numCaracteres);
        System.out.println("Número de letras: " + numLetras);
        System.out.println("Número de não letras: " + numNaoLetras);
        System.out.println("Número de vogais: " + numVogais);
        System.out.println("Número de consoantes: " + numConsoantes);

        scanner.close();
    }
}
