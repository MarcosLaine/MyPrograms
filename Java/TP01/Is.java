public class Is {
    public static void main(String[] args) {
        while(true){
            String entrada = MyIO.readLine();

            if(entrada.equals("FIM"))//condição de parada
                break;

            MyIO.println((vogal(entrada) ? "SIM" : "NAO") + " " +(consoante(entrada) ? "SIM" : "NAO") + " " +(inteiro(entrada) ? "SIM" : "NAO") + " " +(real(entrada) ? "SIM" : "NAO"));
        }
        
    }

    public static boolean vogal(String entrada){//verifica se há apenas vogais na string
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == 'a' && c == 'e' && c == 'i' && c == 'o' && c == 'u') {
                return true;
            }
        }
        return false;
    }

    public static boolean consoante(String entrada){//verifica se há apenas consoantes na string
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                return false;
            }
        }
        return true;
    }

    public static boolean inteiro(String entrada) {//verifica se há apenas inteiros na string
        boolean inteiro = true;
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (!Character.isDigit(c)) {//se não for um digito, retorna falso
                inteiro = false;
            }
        }
        return inteiro;
    }

    public static boolean real(String entrada) {//verifica se há ponto flutuante ou virgula na string
        boolean real = false;
        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == '.' || c == ',') {
                //Encontrou um ponto decimal ou virgula
                if (real) {
                    return false; //Mais de um ponto decimal ou mais de uma virgula
                }
                real = true;
            } else if (!Character.isDigit(c)) {//se não for um digito, retorna falso
                return false;
            }
        }

        return real;
    }
}
