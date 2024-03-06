public class Palindromo {
    public static void main(String[] args) {

        while (true) {
            String frase = MyIO.readLine();
            boolean ispalind = true;
            if (frase.equals("FIM")) {
                break;
            } else {

                for (int i = 0, j = frase.length() - 1; i < j; i++, j--) {
                    if (frase.charAt(i) != frase.charAt(j)) {
                        ispalind = false;
                    }
                }
                if (ispalind)
                    System.out.println("SIM");
                else
                    System.out.println("NAO");
            }
        }
    }
}