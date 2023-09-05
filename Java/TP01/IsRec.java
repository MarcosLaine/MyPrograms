public class IsRec {
    public static void main(String[] args) {
        while (true) {
            String entrada = MyIO.readLine();

            if (entrada.equals("FIM")) // Condição de parada
                break;

            MyIO.println((vogal(entrada, 0) ? "SIM" : "NAO") + " " + (consoante(entrada, 0) ? "SIM" : "NAO") + " " + (inteiro(entrada, 0) ? "SIM" : "NAO") + " " + (real(entrada, 0, false) ? "SIM" : "NAO"));
        }

    }

    public static boolean vogal(String entrada, int index) {
        if (index == entrada.length()) {//condição de parada
            return true;
        }

        char c = entrada.charAt(index);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return vogal(entrada, index + 1);//chamada recursiva
        } else {
            return false;
        }
    }

    public static boolean consoante(String entrada, int index) {
        if (index == entrada.length()) {//condição de parada
            return true;
        }

        char c = entrada.charAt(index);
        if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
            return consoante(entrada, index + 1);//chamada recursiva
        } else {
            return false;
        }
    }

    public static boolean inteiro(String entrada, int index) {
        if (index == entrada.length()) {//condição de parada
            return true;
        }

        char c = entrada.charAt(index);
        if (Character.isDigit(c)) {//se o caractere for um digito, chama a recursão
            return inteiro(entrada, index + 1);//chamada recursiva
        } else {
            return false;
        }
    }

    public static boolean real(String entrada, int index, boolean foundDecimal) {
        if (index == entrada.length()) {//condição de parada
            return foundDecimal;
        }

        char c = entrada.charAt(index);
        if (c == '.' || c == ',') {
            if (foundDecimal) {
                return false; // Mais de um ponto decimal ou vírgula
            }
            return real(entrada, index + 1, true);
        } else if (Character.isDigit(c)) {//se o caractere for um digito, chama a recursão
            return real(entrada, index + 1, foundDecimal);//chamada recursiva
        } else {
            return false;
        }
    }
}
