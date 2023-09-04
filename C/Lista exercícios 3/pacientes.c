/*  data: 12/06/2023
    Lista de exercícios, Assunto: Registros
    Exercício: 2.6 - Pacientes 
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>
#include <string.h>

#define MAX_PACIENTES 10
#define TAM_NOME 50
#define TAM_MEDICO 50

// Registro do tipo Paciente
typedef struct {
    char nome[TAM_NOME];
    char medico[TAM_MEDICO];
    char data_nascimento[11];
    char sexo;
} Paciente;

// Variável global para armazenar a lista de pacientes
Paciente pacientes[MAX_PACIENTES];
int num_pacientes = 0;

// Função para cadastrar pacientes
void cadastrarPaciente() {
    if (num_pacientes == MAX_PACIENTES) {
        printf("A lista de pacientes esta cheia.\n");
        return;
    }

    Paciente novo_paciente;

    printf("Nome: ");
    scanf(" %[^\n]", novo_paciente.nome);

    printf("Nome do medico: ");
    scanf(" %[^\n]", novo_paciente.medico);

    printf("Data de nascimento (formato DD/MM/AAAA): ");
    scanf(" %[^\n]", novo_paciente.data_nascimento);

    printf("Sexo (M/F): ");
    scanf(" %c", &novo_paciente.sexo);

    pacientes[num_pacientes] = novo_paciente;
    num_pacientes++;

    printf("Paciente cadastrado com sucesso.\n");
}

// Função auxiliar para comparar nomes de pacientes (usada na ordenação)
int compararNomes(const void *a, const void *b) {
    const Paciente *pacienteA = (const Paciente *)a;
    const Paciente *pacienteB = (const Paciente *)b;

    return strcmp(pacienteA->nome, pacienteB->nome);
}

// Função para mostrar pacientes em ordem de cadastramento
void mostrarPacientesCadastrados() {
    printf("--- Pacientes Cadastrados ---\n");

    for (int i = 0; i < num_pacientes; i++) {
        printf("Paciente %d:\n", i+1);
        printf("Nome: %s\n", pacientes[i].nome);
        printf("Medico: %s\n", pacientes[i].medico);
        printf("Data de Nascimento: %s\n", pacientes[i].data_nascimento);
        printf("Sexo: %c\n", pacientes[i].sexo);
        printf("\n");
    }
}

// Função para mostrar pacientes em ordem crescente (ordenados pelo nome)
void mostrarPacientesOrdemCrescente() {
    Paciente pacientes_ordenados[MAX_PACIENTES];
    memcpy(pacientes_ordenados, pacientes, sizeof(pacientes));

    qsort(pacientes_ordenados, num_pacientes, sizeof(Paciente), compararNomes);

    printf("--- Pacientes em Ordem Crescente ---\n");

    for (int i = 0; i < num_pacientes; i++) {
        printf("Paciente %d:\n", i+1);
        printf("Nome: %s\n", pacientes_ordenados[i].nome);
        printf("Medico: %s\n", pacientes_ordenados[i].medico);
        printf("Data de Nascimento: %s\n", pacientes_ordenados[i].data_nascimento);
        printf("Sexo: %c\n", pacientes_ordenados[i].sexo);
        printf("\n");
    }
}

// Função para mostrar pacientes em ordem decrescente (ordenados pelo nome)
void mostrarPacientesOrdemDecrescente() {
    Paciente pacientes_ordenados[MAX_PACIENTES];
    memcpy(pacientes_ordenados, pacientes, sizeof(pacientes));

    qsort(pacientes_ordenados, num_pacientes, sizeof(Paciente), compararNomes);
    // Inverter a ordem dos pacientes
    for (int i = 0, j = num_pacientes-1; i < j; i++, j--) {
        Paciente temp = pacientes_ordenados[i];
        pacientes_ordenados[i] = pacientes_ordenados[j];
        pacientes_ordenados[j] = temp;
    }

    printf("--- Pacientes em Ordem Decrescente ---\n");

    for (int i = 0; i < num_pacientes; i++) {
        printf("Paciente %d:\n", i+1);
        printf("Nome: %s\n", pacientes_ordenados[i].nome);
        printf("Medico: %s\n", pacientes_ordenados[i].medico);
        printf("Data de Nascimento: %s\n", pacientes_ordenados[i].data_nascimento);
        printf("Sexo: %c\n", pacientes_ordenados[i].sexo);
        printf("\n");
    }
}

// Função para excluir paciente individualmente
void excluirPaciente() {
    if (num_pacientes == 0) {
        printf("A lista de pacientes está vazia.\n");
        return;
    }

    char nome_excluir[TAM_NOME];
    printf("Digite o nome do paciente que deseja excluir: ");
    scanf(" %[^\n]", nome_excluir);

    int indice_excluir = -1;

    for (int i = 0; i < num_pacientes; i++) {
        if (strcmp(pacientes[i].nome, nome_excluir) == 0) {
            indice_excluir = i;
            break;
        }
    }

    if (indice_excluir == -1) {
        printf("Paciente nao encontrado.\n");
        return;
    }

    for (int i = indice_excluir; i < num_pacientes - 1; i++) {
        pacientes[i] = pacientes[i + 1];
    }

    num_pacientes--;

    printf("Paciente excluído com sucesso.\n");
}

// Função para excluir pacientes por médico
void excluirPacientesPorMedico() {
    if (num_pacientes == 0) {
        printf("A lista de pacientes está vazia.\n");
        return;
    }

    char nome_medico[TAM_MEDICO];
    printf("Digite o nome do medico para excluir seus pacientes: ");
    scanf(" %[^\n]", nome_medico);

    int num_excluidos = 0;

    for (int i = 0; i < num_pacientes; i++) {
        if (strcmp(pacientes[i].medico, nome_medico) == 0) {
            num_excluidos++;
        } else if (num_excluidos > 0) {
            pacientes[i - num_excluidos] = pacientes[i];
        }
    }

    num_pacientes -= num_excluidos;

    if (num_excluidos > 0) {
        printf("Pacientes do medico %s excluidos com sucesso.\n", nome_medico);
    } else {
        printf("Nenhum paciente encontrado para o medico %s.\n", nome_medico);
    }
}

int main() {
    int opcao;

    do {
        printf("--- Menu ---\n");
        printf("1. Cadastrar paciente\n");
        printf("2. Mostrar pacientes em ordem de cadastramento\n");
        printf("3. Mostrar pacientes em ordem crescente (por nome)\n");
        printf("4. Mostrar pacientes em ordem decrescente (por nome)\n");
        printf("5. Excluir paciente individualmente\n");
        printf("6. Excluir pacientes por médico\n");
        printf("0. Sair\n");
        printf("Escolha uma opção: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                cadastrarPaciente();
                break;
            case 2:
                mostrarPacientesCadastrados();
                break;
            case 3:
                mostrarPacientesOrdemCrescente();
                break;
            case 4:
                mostrarPacientesOrdemDecrescente();
                break;
            case 5:
                excluirPaciente();
                break;
            case 6:
                excluirPacientesPorMedico();
                break;
            case 0:
                printf("Encerrando o programa.\n");
                break;
            default:
                printf("Opção inválida. Tente novamente.\n");
                break;
        }

        printf("\n");
    } while (opcao != 0);

    return 0;
}
