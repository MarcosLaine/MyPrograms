public class CiframentoCesar {
    public static void main(String[] args) {

        while (true) {
            String frase = MyIO.readLine();

            if (frase.equals("FIM")) {//condição de parada
                break;
            }

            String mensagemCifrada = cifrarCesar(frase, 3);//chamada da função
            MyIO.println(mensagemCifrada);
        }
    }

    public static String cifrarCesar(String mensagem, int chave) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char c = (char)(mensagem.charAt(i)+3);//altera a palavra com base na chave
            result.append(c);
            }
        
        return result.toString();
    } 
}
