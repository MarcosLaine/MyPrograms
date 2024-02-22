class ContadorMaiusculoRec {
    static int contador(String frase, int charAtual){

        if(charAtual == (frase.length()))
            return 0;
        if(Character.isUpperCase(frase.charAt(charAtual))){
            return 1 + contador(frase, charAtual+1);
        }
        else    
            return contador(frase, charAtual+1);

        
    }

    public static void main(String[] args) {
        String frase = "";
        while (!frase.equals("FIM")){
            frase = MyIO.readLine();
            int num = contador(frase, 0);
            System.out.println(num);
        }
    }
}
