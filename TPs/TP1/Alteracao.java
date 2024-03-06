import java.util.Random;

public class Alteracao{
    public static String subtituir(String frase){
        Random gerador = new Random();
        gerador.setSeed(4);
        char letra1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        String fraseTrocada = frase.replace(letra1, letra2);

        return fraseTrocada;
    }

    public static void main(String[] args) {
        while(true){
            String entrada = MyIO.readLine();

            if(entrada.equals("FIM")){
                break;
            }

            String trocada = subtituir(entrada);

            MyIO.println(trocada);


        }
    }

}