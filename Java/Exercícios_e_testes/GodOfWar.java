import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GodOfWar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = Integer.parseInt(scanner.nextLine().trim()); // número de palavras no conjunto

        List<String> conjuntoPalavras = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            conjuntoPalavras.add(scanner.nextLine().trim());
        }

        int c = Integer.parseInt(scanner.nextLine().trim()); // número de palavras na mensagem 

        List<String> mensagem = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            mensagem.add(scanner.nextLine().trim());
        }

        List<String> mensagemCorrigida = corrigirMensagem(conjuntoPalavras, mensagem);

        for (String palavra : mensagemCorrigida) {
            System.out.print(palavra + " ");
        }

        scanner.close();
    }

    private static List<String> corrigirMensagem(List<String> conjuntoPalavras, List<String> mensagem) {
        List<String> mensagemCorrigida = new ArrayList<>();
        for (String palavra : mensagem) {
            String palavraMaisSemelhante = encontrarPalavraMaisSemelhante(conjuntoPalavras, palavra);
            mensagemCorrigida.add(palavraMaisSemelhante);
        }
        return mensagemCorrigida;
    }

    private static String encontrarPalavraMaisSemelhante(List<String> conjuntoPalavras, String palavra) {
        String palavraMaisSemelhante = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (String palavraConjunto : conjuntoPalavras) {
            int distancia = calcularDistanciaLevenshtein(palavra, palavraConjunto);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                palavraMaisSemelhante = palavraConjunto;
            } else if (distancia == menorDistancia && palavraConjunto.compareTo(palavraMaisSemelhante) < 0) {
                palavraMaisSemelhante = palavraConjunto;
            }
        }
        return palavraMaisSemelhante;
    }

    private static int calcularDistanciaLevenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1), 
                                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
