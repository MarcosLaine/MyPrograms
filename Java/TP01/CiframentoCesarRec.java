public class CiframentoCesarRec {
    public static void main(String[] args) {
        while (true) {
            String frase = MyIO.readLine();

            if (frase.equals("FIM")) {//condição de parada do programa
                break;
            }

            String mensagemCifrada = cifrarCesar(frase, 0, 3);//chama a função
            MyIO.println(mensagemCifrada);
        }
    }

    public static String cifrarCesar(String mensagem, int index, int chave) {
        if (index == mensagem.length()) {
            return "";
        }

        char c = mensagem.charAt(index);
        char cifrado = (char)(c + chave);//altera a letra com a chave
        return cifrado + cifrarCesar(mensagem, index + 1, chave);//chamada recursiva passando para a proxima letra
    }
}
