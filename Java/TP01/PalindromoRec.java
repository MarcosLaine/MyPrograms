public class PalindromoRec {
    public static void main(String[] args) {
        String linha;
        boolean isPalind;

        while(true){
            linha = MyIO.readLine();

            if(linha.equals("FIM"))// Verifica se a linha é "FIM" para sair do programa
                break;
            
            isPalind = palindromo(linha);//atribui true or false a isPalind

            if (isPalind == true) 
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }

    public static boolean palindromo(String s){//chama a função palindromo passando como parâmetro apenas a frase
        return palindromo(s,0);
    }

    private static boolean palindromo(String s, int i){//verifica se a palavra é um palindromo por recursividade
        boolean resp = true;

        if(i<s.length()/2)
            if(s.charAt(i) != s.charAt(s.length()-i-1))//verifica se a letra na posição i é igual à ela mesma na posição oposta dela
                resp = false;
            else
                resp = palindromo(s, i+1);//faz a chamada recursiva

        return resp;
    }
}
