/*  data: 12/06/2023
    Lista de exercícios, Assunto: Registros
    Exercício: 2.5 - Professor x Títulos
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <stdbool.h>

#define MAX_TITULOS 5
#define MAX_PROFESSORES 10
#define TAM_NOME 100

typedef struct {
    int cod_titulo;
    char descricao[TAM_NOME];
    float valor_hora_aula;
} Titulo;

typedef struct {
    int num_registro;
    char nome[TAM_NOME];
    int cod_titulo;
    int total_horas_aula_semanal;
} Professor;

Titulo titulos[MAX_TITULOS];
Professor professores[MAX_PROFESSORES];

void cadastrarTitulos() {
    printf("--- Cadastro de Titulos ---\n");

    for (int i = 0; i < MAX_TITULOS; i++) {
        Titulo titulo;
        titulo.cod_titulo = i + 1;

        printf("Titulo %d:\n", i + 1);
        printf("Descricao: ");
        scanf(" %[^\n]", titulo.descricao);
        printf("Valor Hora/Aula: ");
        scanf("%f", &titulo.valor_hora_aula);

        titulos[i] = titulo;
    }
}

void cadastrarProfessores() {
    printf("--- Cadastro de Professores ---\n");

    for (int i = 0; i < MAX_PROFESSORES; i++) {
        Professor professor;
        professor.num_registro = i + 1;

        printf("Professor %d:\n", i + 1);
        printf("Nome: ");
        scanf(" %[^\n]", professor.nome);
        printf("Código do Titulo (1 a 5): ");
        scanf("%d", &professor.cod_titulo);
        printf("Total de Horas/Aula Semanal: ");
        scanf("%d", &professor.total_horas_aula_semanal);

        professores[i] = professor;
    }
}

void mostrarRelacaoProfessores() {
    printf("--- Relação de Professores ---\n");

    for (int i = 0; i < MAX_PROFESSORES; i++) {
        Professor professor = professores[i];

        printf("Professor %d:\n", professor.num_registro);
        printf("Nome: %s\n", professor.nome);
        printf("Código do Titulo: %d\n", professor.cod_titulo);
        printf("Descrição do Titulo: %s\n", titulos[professor.cod_titulo - 1].descricao);
        printf("Total de Horas/Aula Semanal: %d\n", professor.total_horas_aula_semanal);
        printf("\n");
    }
}

int main() {
    cadastrarTitulos();
    cadastrarProfessores();
    mostrarRelacaoProfessores();

    return 0;
}
