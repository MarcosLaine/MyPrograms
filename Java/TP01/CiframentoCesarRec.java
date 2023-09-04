public class CiframentoCesarRec {
    public static void main(String[] args) {
        while (true) {
            String frase = MyIO.readLine();

            if (frase.equals("FIM")) {
                break;
            }

            String mensagemCifrada = cifrarCesar(frase, 3);
            MyIO.println(mensagemCifrada);
        }
    }

    public static String cifrarCesar(String mensagem, int chave) {
        if (mensagem.isEmpty()) {
            return "";
        }

        char c = mensagem.charAt(0);
        switch (c) {
            case '.':
                return '1' + cifrarCesar(mensagem.substring(1), chave);
            case '-':
                return '0' + cifrarCesar(mensagem.substring(1), chave);
            case 'z':
                return '}' + cifrarCesar(mensagem.substring(1), chave);
            case '/':
                return '2' + cifrarCesar(mensagem.substring(1), chave);
            case '1':
                return '4' + cifrarCesar(mensagem.substring(1), chave);
            case '?':
                return 'B' + cifrarCesar(mensagem.substring(1), chave);
            case '(':
                return '+' + cifrarCesar(mensagem.substring(1), chave);
            case ')':
                return ',' + cifrarCesar(mensagem.substring(1), chave);
            case '!':
                return '$' + cifrarCesar(mensagem.substring(1), chave);
            case ',':
                return '/' + cifrarCesar(mensagem.substring(1), chave);
            case 'Z':
                return ']' + cifrarCesar(mensagem.substring(1), chave);
            case ':':
                return '=' + cifrarCesar(mensagem.substring(1), chave);
            case '\'':
                return '*' + cifrarCesar(mensagem.substring(1), chave);
            case 'y':
                return '|' + cifrarCesar(mensagem.substring(1), chave);
            case ';':
                return '>' + cifrarCesar(mensagem.substring(1), chave);
            case '*':
                return '-' + cifrarCesar(mensagem.substring(1), chave);
            case 'x':
                return '{' + cifrarCesar(mensagem.substring(1), chave);
            case '&':
                return ')' + cifrarCesar(mensagem.substring(1), chave);
            case '7':
                return ':' + cifrarCesar(mensagem.substring(1), chave);
            case '8':
                return ';' + cifrarCesar(mensagem.substring(1), chave);
            case '9':
                return '<' + cifrarCesar(mensagem.substring(1), chave);
            case '_':
                return 'b' + cifrarCesar(mensagem.substring(1), chave);
            case '[':
                return '^' + cifrarCesar(mensagem.substring(1), chave);
            case ']':
                return '`' + cifrarCesar(mensagem.substring(1), chave);
            case '\uFFFD':
                return '\uFFFD' + cifrarCesar(mensagem.substring(1), chave);
            default:
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    char cifrado = (char) ((c - base + chave) % 26 + base);
                    return cifrado + cifrarCesar(mensagem.substring(1), chave);
                } else if (Character.isDigit(c)) {
                    char cifrado = (char) ((c - '0' + chave) % 10 + '0');
                    return cifrado + cifrarCesar(mensagem.substring(1), chave);
                } else if (c == ' ') {
                    return '#' + cifrarCesar(mensagem.substring(1), chave);
                } else {
                    return c + cifrarCesar(mensagem.substring(1), chave);
                }
        }
    }
}
