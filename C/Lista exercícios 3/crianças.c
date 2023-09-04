/*  data: 12/06/2023
    Lista de exercícios, Assunto: Registros
    Exercício: 2.4 - crianças
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <stdbool.h>

#define MAX_CRICANCAS 100

// Registro do tipo crianca
typedef struct {
    char nome[81];
    int idade;
    double altura;
    bool escola;
} Crianca;

// Função para ler dados de um vetor de crianças
void lerVetorCrianca(Crianca v[], int n) {
    for (int i = 0; i < n; i++) {
        printf("\n--- Crianca %d ---\n", i+1);
        printf("Nome: ");
        scanf(" %[^\n]", v[i].nome);
        printf("Idade: ");
        scanf("%d", &v[i].idade);
        printf("Altura: ");
        scanf("%lf", &v[i].altura);
        printf("Frequenta a escola? (0 - Não, 1 - Sim): ");
        scanf("%d", &v[i].escola);
    }
}

// Função para imprimir os dados de um vetor de crianças
void imprimeVetorCrianca(Crianca v[], int n) {
    for (int i = 0; i < n; i++) {
        printf("\n--- Crianca %d ---\n", i+1);
        printf("Nome: %s\n", v[i].nome);
        printf("Idade: %d\n", v[i].idade);
        printf("Altura: %.2lf\n", v[i].altura);
        printf("Frequenta a escola? %s\n", v[i].escola ? "Sim" : "Nao");
    }
}

// Função para imprimir os dados de uma criança
void imprimeCrianca(Crianca c) {
    printf("\n--- Dados da Crianca ---\n");
    printf("Nome: %s\n", c.nome);
    printf("Idade: %d\n", c.idade);
    printf("Altura: %.2lf\n", c.altura);
    printf("Frequenta a escola? %s\n", c.escola ? "Sim" : "Nao");
}

// Função para calcular a média de altura das crianças
double mediaAlturaCrianca(Crianca v[], int n) {
    double soma_altura = 0;
    
    for (int i = 0; i < n; i++) {
        soma_altura += v[i].altura;
    }
    
    return soma_altura / n;
}

// Função para pesquisar uma criança pelo nome
int pesquisaCrianca(Crianca v[], int n, char nome[81]) {
    for (int i = 0; i < n; i++) {
        if (strcmp(v[i].nome, nome) == 0) {
            return i;
        }
    }
    
    return -1;
}

// Função para imprimir as crianças dentro de um intervalo de idade
void intervaloIdadeCrianca(Crianca v[], int n, int menor, int maior) {
    printf("\n--- Crianças no intervalo de idade [%d, %d] ---\n", menor, maior);
    
    for (int i = 0; i < n; i++) {
        if (v[i].idade >= menor && v[i].idade <= maior) {
            printf("Nome: %s\n", v[i].nome);
        }
    }
}

// Função para contar o número de crianças que frequentam a escola
int escolaCrianca(Crianca v[], int n) {
    int count = 0;
    
    for (int i = 0; i < n; i++) {
        if (v[i].escola) {
            count++;
        }
    }
    
    return count;
}

int main() {
    Crianca criancas[MAX_CRICANCAS];
    int num_criancas = 0;

    printf("Quantidade de criancas a serem cadastradas: ");
    scanf("%d", &num_criancas);

    lerVetorCrianca(criancas, num_criancas);

    double media_altura = mediaAlturaCrianca(criancas, num_criancas);
    printf("\nMedia de altura das criancas: %.2lf\n", media_altura);

    printf("\nCriancas com idade entre 5 e 10 anos:\n");
    intervaloIdadeCrianca(criancas, num_criancas, 5, 10);

    char nome_pesquisa[81];
    printf("\nDigite o nome a ser pesquisado: ");
    scanf(" %[^\n]", nome_pesquisa);

    int indice_crianca = pesquisaCrianca(criancas, num_criancas, nome_pesquisa);
    if (indice_crianca != -1) {
        imprimeCrianca(criancas[indice_crianca]);
    } else {
        printf("\nCrianca nao cadastrada.\n");
    }

    int num_escola = escolaCrianca(criancas, num_criancas);
    double percentual_escola = (double) num_escola / num_criancas * 100;
    printf("\nNumero de criancas que frequentam a escola: %d (%.2lf%%)\n", num_escola, percentual_escola);

    printf("\n--- Vetor completo de criancas ---\n");
    imprimeVetorCrianca(criancas, num_criancas);

    return 0;
}
