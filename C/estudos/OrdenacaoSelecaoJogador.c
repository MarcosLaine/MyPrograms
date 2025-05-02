#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct Jogador {
    int id;
    char name[100];
    int weight;
    int height;
    char ondeEstudou[100];
    int anoNasc;
    char estadoNasc[100];
    char cidadeNasc[100];
};

int comparacoes = 0;
int movimentacoes = 0;
const char *FILE_NAME = "tmp/playersAtualizado.csv";  // Caminho completo para o arquivo

void ordenacaoPorSelecaoRecursiva(struct Jogador vetor[], int n) {
    if (n <= 1) {
        return;
    }

    int menorIndice = 0;
    for (int i = 1; i < n; i++) {
        comparacoes++;
        if (strcmp(vetor[i].name, vetor[menorIndice].name) < 0) {
            menorIndice = i;
        }
    }

    if (menorIndice != 0) {
        struct Jogador temp = vetor[0];
        vetor[0] = vetor[menorIndice];
        vetor[menorIndice] = temp;
        movimentacoes++;
    }

    ordenacaoPorSelecaoRecursiva(vetor + 1, n - 1);
}

void imprimirJogadores(struct Jogador vetor[], int n) {
    for (int i = 0; i < n; i++) {
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", vetor[i].id, vetor[i].name,
               vetor[i].weight, vetor[i].height, vetor[i].anoNasc, vetor[i].ondeEstudou,
               vetor[i].estadoNasc, vetor[i].cidadeNasc);
    }
}

void criarArquivoLog(int matricula, int comparacoes, int movimentacoes, double tempoExecucao) {
    FILE *logFile = fopen("matricula_selecaoRecursiva.txt", "w");
    if (logFile == NULL) {
        perror("Erro ao criar o arquivo de log");
        return;
    }

    fprintf(logFile, "%d\t%d\t%d\t%.6f\n", matricula, comparacoes, movimentacoes, tempoExecucao);
    fclose(logFile);
}

int main() {
    clock_t start_time, end_time;
    int matricula = 803627;

    FILE *file = fopen(FILE_NAME, "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    struct Jogador listaJogadores[1000];
    int jogadorCount = 0;

    while (fscanf(file, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^\n]",
                  &listaJogadores[jogadorCount].id, listaJogadores[jogadorCount].name,
                  &listaJogadores[jogadorCount].weight, &listaJogadores[jogadorCount].height,
                  listaJogadores[jogadorCount].ondeEstudou, &listaJogadores[jogadorCount].anoNasc,
                  listaJogadores[jogadorCount].estadoNasc, listaJogadores[jogadorCount].cidadeNasc) != EOF) {
        jogadorCount++;
    }

    fclose(file);

    start_time = clock();  // Início da contagem do tempo

    ordenacaoPorSelecaoRecursiva(listaJogadores, jogadorCount);

    end_time = clock();  // Fim da contagem do tempo
    double tempoExecucao = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;

    imprimirJogadores(listaJogadores, jogadorCount);
    criarArquivoLog(matricula, comparacoes, movimentacoes, tempoExecucao);

    return 0;
}
