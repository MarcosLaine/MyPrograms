#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Estrutura de dados para armazenar informações de um jogador
typedef struct {
    char id[10];
    char nome[150];
    char altura[10];
    char peso[10];
    char universidade[150];
    char anoNascimento[10];
    char cidadeNascimento[150];
    char estadoNascimento[150];
} Jogador;

int mov = 0;  // Variável para contar as movimentações
int comp = 0; // Variável para contar as comparações

// Função para clonar um jogador
Jogador clone(Jogador *jogador) {
    Jogador novo;
    strcpy(novo.id, jogador->id);
    strcpy(novo.nome, jogador->nome);
    strcpy(novo.altura, jogador->altura);
    strcpy(novo.peso, jogador->peso);
    strcpy(novo.universidade, jogador->universidade);
    strcpy(novo.anoNascimento, jogador->anoNascimento);
    strcpy(novo.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo.estadoNascimento, jogador->estadoNascimento);
    return novo;
}

// Função para imprimir os dados de um jogador
void imprimir(Jogador *jogador) {
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n",
           jogador->id, jogador->nome, jogador->altura, jogador->peso,
           jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento,
           jogador->estadoNascimento);
}

// Função para ler os dados de um jogador a partir de uma linha
void ler(Jogador *jogador, char linha[]) {
    int posicao[7];
    int virgulas = 0;
    for (int i = 0; i < strlen(linha); i++){
        if(linha[i] == ','){
            posicao[virgulas] = i;
            virgulas++;
        }
    }
    
    int count = 0;
    char id[100];
    char nome[100];
    char peso[100];
    char altura[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];

    if (posicao[0] - 0 != 0){
        for(int i = 0; i < posicao[0]; i++){
          id[count++] = linha[i];
        }
        id[count] = '\0';
        strcpy(jogador->id,id);
    } else{
        strcpy(jogador->id,"nao informado");  
    }
   
    count = 0;

    if (posicao[1] - (posicao[0]) != 1){
        for(int j = posicao[0] + 1; j < posicao[1]; j++){
        nome[count++] = linha[j];
    }
    nome[count] = '\0';
    strcpy(jogador->nome,nome);
    } else{
    strcpy(jogador->nome,"nao informado");   
    }
    count = 0;

    if (posicao[2] - (posicao[1]) != 1){
        for (int k = posicao[1] + 1; k < posicao[2]; k++){
            altura[count++] = linha[k];
        }
        altura[count] = '\0';
        strcpy(jogador->altura,altura);
    } else{
    strcpy(jogador->altura,"nao informado");
    }
    count = 0;

    if (posicao[3] - (posicao[2]) != 1){
        for (int l = posicao[2] + 1; l < posicao[3]; l++){
            peso[count++] = linha[l];
        }
        peso[count] = '\0';
        strcpy(jogador->peso,peso);
    } else{
    strcpy(jogador->peso,"nao informado");
    }
    
    count = 0;
    if (posicao[4] - (posicao[3]) != 1){
        for (int m = posicao[3] + 1; m < posicao[4]; m++){
            universidade[count++] = linha[m];
        }
        universidade[count] = '\0';
        strcpy(jogador->universidade,universidade);
    } else{
    strcpy(jogador->universidade,"nao informado");
    }
    
    count = 0;

    if (posicao[5] - (posicao[4]) != 1){
        for (int n = posicao[4] + 1; n < posicao[5]; n++){
            anoNascimento[count++] = linha[n];
        }
        anoNascimento[count] = '\0';
        strcpy(jogador->anoNascimento,anoNascimento);
    } else{
    strcpy(jogador->anoNascimento,"nao informado");
    }
    
    count = 0;

    if (posicao[6] - (posicao[5]) != 1){
        for(int o = posicao[5] + 1; o < posicao[6]; o++){
            cidadeNascimento[count++] = linha[o];
        }
        cidadeNascimento[count] = '\0';
       
        strcpy(jogador->cidadeNascimento,cidadeNascimento);
    } else{
    strcpy(jogador->cidadeNascimento,"nao informado");
    }
    
    count = 0;

     if ((strlen(linha) - 1) - (posicao[6]) != 1){
        for(int p = posicao[6] + 1; p < strlen(linha) - 1; p++){
            estadoNascimento[count++] = linha[p];
        }
        estadoNascimento[count] = '\0';
        strcpy(jogador->estadoNascimento,estadoNascimento);
     } else{
    strcpy(jogador->estadoNascimento,"nao informado");
    }
    count = 0;
}

// Função para trocar a posição de dois jogadores
void swap(Jogador *jogador1, Jogador *jogador2) {
    Jogador temp = *jogador1;
    *jogador1 = *jogador2;
    *jogador2 = temp;
}

// Função para obter o ano de nascimento de um jogador como inteiro
int anoInt(Jogador *jogador) {
    int ano = atoi(jogador->anoNascimento);
    return ano;
}

// Função de ordenação por bolha
void bolha(Jogador *jogadores, int n) {
    int i, j;
    for (int i = (n - 1); i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if (anoInt(&jogadores[j]) > anoInt(&jogadores[j + 1])) {
                swap(&jogadores[j], &jogadores[j + 1]);
                mov += 3;
            } else if (anoInt(&jogadores[j]) == anoInt(&jogadores[j + 1])) {
                if (strcmp(jogadores[j].nome, jogadores[j + 1].nome) > 0) {
                    swap(&jogadores[j], &jogadores[j + 1]);
                    mov += 3;
                }
                comp += 2;
            }
            comp++;
        }
    }
}

int main() {
    clock_t t;
    t = clock();

    // Abre o arquivo de entrada
    FILE *arq;
    arq = fopen("/tmp/players.csv", "r");
    char linha[1000];
    fgets(linha, 1000, arq);

    // Lê os dados dos jogadores do arquivo
    Jogador jogadores[3923];
    int i = 0;
    while (fgets(linha, 1000, arq)) {
        ler(&jogadores[i], linha);
        i++;
    }
    fclose(arq);

    // Cria um array para os jogadores escolhidos
    Jogador escolhidos[3923];
    int k = 0;
    char id[100];

    // Lê os IDs dos jogadores escolhidos e os filtra
    scanf("%s", id);
    while (strcmp(id, "FIM") != 0) {
        for (int j = 0; j < 3923; j++) {
            if (atoi(id) == atoi(jogadores[j].id)) {
                escolhidos[k++] = clone(&jogadores[j]);
            }
        }
        scanf("%s", id);
    }

    // Ordena os jogadores escolhidos
    bolha(escolhidos, k);

    // Imprime os jogadores ordenados
    for (int i = 0; i < k; i++) {
        imprimir(&escolhidos[i]);
    }

    t = clock() - t;

    // Cria e escreve no arquivo de log
    FILE *log;
    log = fopen("matricula_bolha.txt", "w");
    fprintf(log, "Matricula: 803627\t Comparações: %d\t Movimentações: %d\t Execução: %lfms", comp, mov, ((double)t) / (CLOCKS_PER_SEC / 1000));
    fclose(log);

    return 0;
}
