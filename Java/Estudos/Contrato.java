import java.util.Scanner;

public class Contrato {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int d = scanner.nextInt();
            String n = scanner.next();

            if (d == 0 && n.equals("0")) {
                break;
            }

            String resultado = n.replace(Integer.toString(d), "");
            resultado = resultado.isEmpty() ? "0" : resultado;

            System.out.println(new java.math.BigInteger(resultado));
        }

        scanner.close();
    }
}
