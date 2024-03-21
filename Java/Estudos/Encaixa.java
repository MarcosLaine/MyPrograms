import java.util.Scanner;

public class Encaixa {
    static boolean verifica(String strOriginal, String strVerificar){
        if (strOriginal.length() < strVerificar.length()) {
            return false;
        }
        return strOriginal.substring(strOriginal.length() - strVerificar.length()).equals(strVerificar);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int teste = sc.nextInt();
        sc.nextLine();

        while (teste != 0) {
            String linha = sc.nextLine();
            String[] partes = linha.split(" ");
            String original = partes[0];
            String verificar = partes[1];

            if (verifica(original, verificar)) {
                System.out.println("encaixa");
            } else {
                System.out.println("nao encaixa");
            }

            teste--;
        }

        sc.close();
    }
}
