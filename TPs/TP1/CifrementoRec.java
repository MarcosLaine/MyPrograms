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
        if(i == str.length())
            return "";
        else
            return cifrar(str,chave,i+1)+(char)(str.charAt(i)  + chave);
    }
    
    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        while( !entrada.equalsIgnoreCase("FIM") ){
            entrada = cifrar(entrada, 3, entrada.length()-1);
            MyIO.println(entrada);
            entrada = MyIO.readLine();
        }
    }
}
