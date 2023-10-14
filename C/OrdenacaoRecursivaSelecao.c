#include <stdio.h>
#include <string.h>

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

void selectionSort(struct Jogador arr[], int n, int index) {
    if (index >= n - 1) {
        return;
    }

    int minIndex = index;
    for (int i = index + 1; i < n; i++) {
        if (strcmp(arr[i].name, arr[minIndex].name) < 0) {
            minIndex = i;
        }
    }

    if (minIndex != index) {
        struct Jogador temp = arr[index];
        arr[index] = arr[minIndex];
        arr[minIndex] = temp;
    }

    selectionSort(arr, n, index + 1);
}

int main() {
    struct Jogador listaJogadores[] = {
        {1, "Maria", 65, 170, "Escola A", 1990, "SP", "Sao Paulo"},
        {2, "Joao", 75, 180, "Escola B", 1995, "RJ", "Rio de Janeiro"},
        {3, "Ana", 60, 160, "Escola C", 1985, "RS", "Porto Alegre"},
        // Adicione mais jogadores aqui
    };

    int n = sizeof(listaJogadores) / sizeof(listaJogadores[0]);

    selectionSort(listaJogadores, n, 0);

    printf("Jogadores ordenados por nome:\n");
    for (int i = 0; i < n; i++) {
        printf("[%d] ## %s ## %d ## %d ## %s ## %d ## %s ## %s\n",
               listaJogadores[i].id, listaJogadores[i].name, listaJogadores[i].weight,
               listaJogadores[i].height, listaJogadores[i].ondeEstudou,
               listaJogadores[i].anoNasc, listaJogadores[i].estadoNasc,
               listaJogadores[i].cidadeNasc);
    }

    return 0;
}
