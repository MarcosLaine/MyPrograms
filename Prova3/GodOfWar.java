import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GodOfWar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine(); // Consumir a linha restante

        Set<String> dictionary = new HashSet<>();
        for (int i = 0; i < q; i++) {
            dictionary.add(sc.nextLine());
        }

        int p = sc.nextInt();
        sc.nextLine(); // Consumir a linha restante

        List<String> words = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            words.add(sc.nextLine());
        }

        String message = sc.nextLine();

        String[] messageWords = message.split(" ");
        StringBuilder correctedMessage = new StringBuilder();

        for (String word : messageWords) {
            if (dictionary.contains(word)) {
                correctedMessage.append(word).append(" ");
            } else {
                String bestMatch = null;
                int minDistance = Integer.MAX_VALUE;

                for (String dictWord : dictionary) {
                    int distance = levenshteinDistance(word, dictWord);
                    if (distance < minDistance || (distance == minDistance && dictWord.compareTo(bestMatch) < 0)) {
                        minDistance = distance;
                        bestMatch = dictWord;
                    }
                }

                correctedMessage.append(bestMatch).append(" ");
            }
        }

        System.out.println(correctedMessage.toString().trim());
        sc.close();
    }

    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] 
                                 + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1), 
                                 Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
