public class palindromo {
    public static void main(String[] args) {
        String linha;
        int tamanho, i, j;
        boolean palindromo;

        while (true){
            linha = MyIO.readLine();

            // Verifica se a linha é "FIM" para sair do programa
            if (linha.equals("FIM")) {
                break;
            }
            tamanho = linha.length();
            palindromo = true;

            // Verifica se a palavra é um palíndromo por iteração
            for (i = 0, j = tamanho - i - 1; i < j; i++, j--) {
                if (linha.charAt(i) != linha.charAt(j)) {//verifica se a letra na posição i é igual à ela mesma na posição oposta dela
                    palindromo = false;
                    break;
                }
            }

            if (palindromo) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
    }
}
