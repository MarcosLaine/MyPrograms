import java.util.Scanner;

public class LED {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testes = sc.nextInt();

        while (testes != 0) {
            int leds = 0;
            String numero = sc.next();
            for (int i = 0; i < numero.length(); i++) {
                char digito = numero.charAt(i);
                switch (digito) {
                    case '1':
                        leds += 2;
                        break;
                    case '2':
                    case '3':
                    case '5':
                        leds += 5;
                        break;
                    case '4':
                        leds += 4;
                        break;
                    case '6':
                    case '9':
                    case '0':
                        leds += 6;
                        break;
                    case '8':
                        leds += 7;
                        break;
                    case '7':
                        leds += 3;
                        break;
                }
            }
            System.out.println(leds + " leds");
            testes--;
        }
        sc.close();
    }
}
