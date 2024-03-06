/*class Ciframento{
    static String cifrar(String str, int chave) {
        String cifrada = "";
        for (int i = 0; i < str.length(); i++) {
            cifrada += (char)(str.charAt(i) + chave);
        }
        return cifrada;
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        while( !entrada.equalsIgnoreCase("FIM") ){
            entrada = cifrar(entrada, 3);
            MyIO.println(entrada);
            entrada = MyIO.readLine();
        }
    }
} */

public class CifrementoRec {

    static String cifrar(String str, int chave, int i){
        if(i >= str.length()) // Se i for maior ou igual ao comprimento da string, terminamos a recursão.
            return "";
        else
            // Primeiro fazemos a chamada recursiva para o próximo caractere e depois adicionamos o caractere cifrado.
            return (char)(str.charAt(i) + chave) + cifrar(str, chave, i + 1);
    }
    
    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        while(!entrada.equalsIgnoreCase("FIM")){
            // A chamada para a cifragem começa com i = 0.
            entrada = cifrar(entrada, 3, 0);
            MyIO.println(entrada);
            entrada = MyIO.readLine();
        }
    }
}
