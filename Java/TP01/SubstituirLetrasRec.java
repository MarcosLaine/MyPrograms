import java.util.Random;

public class SubstituirLetrasRec {
    public static String substituirRecursivo(String frase, char letra1, char letra2, int index) {
        if (index < frase.length()) {
            char caractereAtual = frase.charAt(index);
            if (caractereAtual == letra1) {
                caractereAtual = letra2;
            }
            return caractereAtual + substituirRecursivo(frase, letra1, letra2, index + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        while (true) {
            String entrada = MyIO.readLine();

            if (entrada.equals("FIM")) {
                break;
            }

            Random gerador = new Random();
            gerador.setSeed(4);
            char letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

            String trocada = substituirRecursivo(entrada, letra1, letra2, 0);

            MyIO.println(trocada);
        }

    }
}
