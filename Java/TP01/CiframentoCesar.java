public class CiframentoCesar {
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
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            switch (c) {
                case '.':
                    result.append('1');
                    break;
                case '-':
                    result.append('0');
                    break;
                case 'z':
                    result.append('}');
                    break;
                case '/':
                    result.append('2');
                    break;
                case '1': 
                    result.append('4');
                    break;
                case '?':
                    result.append('B');
                    break;
                case '(':
                    result.append('+');
                    break;
                case ')':
                    result.append(',');
                    break;
                case '!':
                    result.append('$');
                    break;
                case ',':
                    result.append('/');
                    break;
                case 'Z':
                    result.append(']');
                    break;
                case ':':
                    result.append('=');
                    break;
                case '\'':
                    result.append('*');
                    break;
                case 'y':
                    result.append('|');
                    break;
                case ';':
                    result.append('>');
                    break;
                case '*':
                    result.append('-');
                    break;
                case 'x':
                    result.append('{');
                    break;
                case '&':
                    result.append(')');
                    break;
                case '7':
                    result.append(':');
                    break;
                case '8':
                    result.append(';');
                    break;
                case '9':
                    result.append('<');
                    break;
                case '_':
                    result.append('b');
                    break;
                case '[':
                    result.append('^');
                    break;
                case ']':
                    result.append('`');
                    break;
                case '\uFFFD':  // Unicode U+FFFD character '�'
                    result.append('\uFFFD');
                    break;
                default:
                    if (Character.isLetter(c)) {
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        char cifrado = (char) ((c - base + chave) % 26 + base);
                        result.append(cifrado);
                    } else if (Character.isDigit(c)) {
                        char cifrado = (char) ((c - '0' + chave) % 10 + '0');
                        result.append(cifrado);
                    } else if (c == ' ') {
                        result.append('#');
                    } else {
                        result.append(c);
                    }
                    break;
            }
        }

        return result.toString();
    } 
}
