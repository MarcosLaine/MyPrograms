import java.util.Scanner;

public class Espelho {
    public static void inverter(int input) {
        int i = 0;
        while (input > 0) {
            i *= 10;
            i += (input % 10);
            input /= 10;
        }
        System.out.print(i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int B = MyIO.readInt(), E = MyIO.readInt(), F = B;

            for (int i = B; i <= E; i++) {
                MyIO.print(B);

                if (B == E)
                    for (int j = B; j > F; j--)
                        inverter(j);
                B++;
            }
            MyIO.print(F);
        }
    }
}
