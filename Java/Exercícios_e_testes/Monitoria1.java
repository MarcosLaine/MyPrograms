import java.util.Scanner;

public class Monitoria1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String s = sc.nextLine();
        String[] separada = s.split(" ");
        String separada1 = separada[separada.length-1];
        int valor = separada1.length();

        System.out.println(valor);
        sc.close();
    }
}
