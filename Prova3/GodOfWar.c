#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_WORD_LENGTH 102

int min(int a, int b) {
    return (a < b) ? a : b;
}

int levenshteinDistance(char *a, char *b) {
    int len_a = strlen(a);
    int len_b = strlen(b);
    int dp[len_a + 1][len_b + 1];

    for (int i = 0; i <= len_a; i++) {
        for (int j = 0; j <= len_b; j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                dp[i][j] = min(dp[i - 1][j - 1] + (a[i - 1] != b[j - 1]), min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
    }

    return dp[len_a][len_b];
}

int main() {
    int q, p;
    scanf("%d", &q);
    char dictionary[q][MAX_WORD_LENGTH];
    for (int i = 0; i < q; i++) {
        scanf("%s", dictionary[i]);
    }

    scanf("%d", &p);
    char words[p][MAX_WORD_LENGTH];
    for (int i = 0; i < p; i++) {
        scanf("%s", words[i]);
    }

    getchar(); // Consumir a nova linha
    char message[10001];
    fgets(message, 10001, stdin);

    char *token = strtok(message, " ");
    char correctedMessage[10001] = "";

    while (token != NULL) {
        char *word = token;
        word[strcspn(word, "\n")] = '\0'; // Remover nova linha

        int found = 0;
        for (int i = 0; i < q; i++) {
            if (strcmp(word, dictionary[i]) == 0) {
                strcat(correctedMessage, dictionary[i]);
                strcat(correctedMessage, " ");
                found = 1;
                break;
            }
        }

        if (!found) {
            char *bestMatch = NULL;
            int minDistance = MAX_WORD_LENGTH;

            for (int i = 0; i < q; i++) {
                int distance = levenshteinDistance(word, dictionary[i]);
                if (distance < minDistance || (distance == minDistance && strcmp(dictionary[i], bestMatch) < 0)) {
                    minDistance = distance;
                    bestMatch = dictionary[i];
                }
            }

            strcat(correctedMessage, bestMatch);
            strcat(correctedMessage, " ");
        }

        token = strtok(NULL, " ");
    }

    correctedMessage[strlen(correctedMessage) - 1] = '\0'; // Remover espaço extra
    printf("%s\n", correctedMessage);

    return 0;
}
