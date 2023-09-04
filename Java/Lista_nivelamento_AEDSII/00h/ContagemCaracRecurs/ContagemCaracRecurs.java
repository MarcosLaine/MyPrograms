public class ContagemCaracRecurs {
    public static void main(String[] args) {
        String palavra = "Exemplo de String";
        int resultado = contarCaracteresNaoVogaisEConsoantesMaiusculas(palavra);
        System.out.println("Quantidade de caracteres não vogais nem consoantes maiúsculas: " + resultado);
    }

    public static int contarCaracteresNaoVogaisEConsoantesMaiusculas(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        char primeiroChar = str.charAt(0);
        if (!Character.isUpperCase(primeiroChar) && !isVogal(primeiroChar)) {
            return 1 + contarCaracteresNaoVogaisEConsoantesMaiusculas(str.substring(1));
        } else {
            return contarCaracteresNaoVogaisEConsoantesMaiusculas(str.substring(1));
        }
    }

    public static boolean isVogal(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
