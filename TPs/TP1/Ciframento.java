class Ciframento{
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
}