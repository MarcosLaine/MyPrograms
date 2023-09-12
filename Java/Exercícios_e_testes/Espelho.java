import java.util.Scanner;

public class Espelho {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

            int B = sc.nextInt(); // Valor de início
            int E = sc.nextInt(); // Valor de fim

            StringBuilder sequencia = new StringBuilder();

            // Construa a sequência crescente
            for (int i = B; i <= E; i++) {
                sequencia.append(i);
            }

            // Adicione a sequência decrescente (reflexo no espelho)
            for (int i = E; i >= B; i--) {
                sequencia.append(i);
            }

            System.out.println(sequencia.toString());

        }
        sc.close();
    }
}
