import java.util.Scanner;

public class Monitoria2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        String t = sc.nextLine();
        String aux = t;
        System.out.print(aux + inverter(t));
        sc.close();
        
    }

    public static String inverter(String t){
        int inicio= t.indexOf("(");
        int fim= t.indexOf(")");
        String entrada = t.substring(inicio+1, fim);
        entrada= reverse(entrada);
        return "(" + entrada + ")";
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}