/*  data: 12/06/2023
    Lista de exercícios, Assunto: Registros
    Exercício: 2.1, 2.2, 2.3 - Conta Bancária
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

#define MAX_CONTAS 100

// Definição da estrutura para a conta bancária
typedef struct {
    int agencia;
    int numero_conta;
    char nome_cliente[50];
    float saldo;
} ContaBancaria;

// Função para ler os dados da conta bancária
void lerContaBancaria(ContaBancaria *conta) {
    printf("Digite a agencia: ");
    scanf("%d", &conta->agencia);
    
    printf("Digite o numero da conta: ");
    scanf("%d", &conta->numero_conta);
    
    printf("Digite o nome do cliente: ");
    scanf(" %[^\n]s", conta->nome_cliente);
    
    printf("Digite o saldo: ");
    scanf("%f", &conta->saldo);
}

// Função para exibir os dados da conta bancária
void exibirContaBancaria(ContaBancaria conta) {
    printf("\nDados da conta bancaria:\n");
    printf("Agencia: %d\n", conta.agencia);
    printf("Numero da conta: %d\n", conta.numero_conta);
    printf("Nome do cliente: %s\n", conta.nome_cliente);
    printf("Saldo: R$ %.2f\n", conta.saldo);
}

// Função para cadastrar uma conta bancária
void cadastrarContaBancaria(ContaBancaria contas[], int *num_contas) {
    if (*num_contas >= MAX_CONTAS) {
        printf("Não é possível cadastrar mais contas. Limite atingido.\n");
        return;
    }
    
    ContaBancaria nova_conta;
    
    lerContaBancaria(&nova_conta);
    
    // Verifica se já existe uma conta com a mesma agencia e nuemro de conta
    for (int i = 0; i < *num_contas; i++) {
        if (contas[i].agencia == nova_conta.agencia && contas[i].numero_conta == nova_conta.numero_conta) {
            printf("Já existe uma conta com a mesma agencia e nuemro de conta.\n");
            return;
        }
    }
    
    contas[*num_contas] = nova_conta;
    (*num_contas)++;
    
    printf("Conta cadastrada com sucesso!\n");
}

// Função para exibir todas as contas cadastradas
void exibirTodasContas(ContaBancaria contas[], int num_contas) {
    if (num_contas == 0) {
        printf("Nenhuma conta cadastrada.\n");
    } else {
        printf("\n*** CONTAS CADASTRADAS ***\n");
        for (int i = 0; i < num_contas; i++) {
            exibirContaBancaria(contas[i]);
        }
    }
}

// Função para encontrar a conta com o maior saldo
ContaBancaria encontrarMaiorSaldo(ContaBancaria contas[], int num_contas) {
    ContaBancaria maior_saldo = contas[0];
    
    for (int i = 1; i < num_contas; i++) {
        if (contas[i].saldo > maior_saldo.saldo) {
            maior_saldo = contas[i];
        }
    }
    
    return maior_saldo;
}

// Função para encontrar a conta com o menor saldo
ContaBancaria encontrarMenorSaldo(ContaBancaria contas[], int num_contas) {
    ContaBancaria menor_saldo = contas[0];
    
    for (int i = 1; i < num_contas; i++) {
        if (contas[i].saldo < menor_saldo.saldo) {
            menor_saldo = contas[i];
        }
    }
    
    return menor_saldo;
}

// Função para calcular a media dos saldos dos correntistas
float calcularMediaSaldos(ContaBancaria contas[], int num_contas) {
    float soma_saldos = 0;
    
    for (int i = 0; i < num_contas; i++) {
        soma_saldos += contas[i].saldo;
    }
    
    return soma_saldos / num_contas;
}

// Função para excluir uma conta bancária
void excluirContaBancaria(ContaBancaria contas[], int *num_contas, int agencia, int numero_conta) {
    int indice_conta = -1;
    
    for (int i = 0; i < *num_contas; i++) {
        if (contas[i].agencia == agencia && contas[i].numero_conta == numero_conta) {
            indice_conta = i;
            break;
        }
    }
    
    if (indice_conta == -1) {
        printf("Conta não encontrada.\n");
    } else {
        // Desloca as contas para preencher o espaço da conta excluída
        for (int i = indice_conta; i < *num_contas - 1; i++) {
            contas[i] = contas[i + 1];
        }
        
        (*num_contas)--;
        printf("Conta excluida com sucesso!\n");
    }
}

int main() {
    ContaBancaria contas[MAX_CONTAS];
    int num_contas = 0;
    
    char opcao;
    
    do {
        printf("\n*** MENU ***\n");
        printf("a. Sair\n");
        printf("b. Cadastrar uma conta\n");
        printf("c. Visualizar todas as contas\n");
        printf("d. Mostrar informacoes da conta com maior saldo\n");
        printf("e. Mostrar informacoes da conta com menor saldo\n");
        printf("f. Calcular a media dos saldos dos correntistas\n");
        printf("g. Excluir uma conta\n");
        printf("Escolha uma opcao: ");
        scanf(" %c", &opcao);
        
        switch (opcao) {
            case 'a':
                printf("Encerrando o programa.\n");
                break;
            case 'b':
                cadastrarContaBancaria(contas, &num_contas);
                break;
            case 'c':
                exibirTodasContas(contas, num_contas);
                break;
            case 'd': {
                if (num_contas == 0) {
                    printf("Nenhuma conta cadastrada.\n");
                } else {
                    ContaBancaria conta_maior_saldo = encontrarMaiorSaldo(contas, num_contas);
                    exibirContaBancaria(conta_maior_saldo);
                }
                break;
            }
            case 'e': {
                if (num_contas == 0) {
                    printf("Nenhuma conta cadastrada.\n");
                } else {
                    ContaBancaria conta_menor_saldo = encontrarMenorSaldo(contas, num_contas);
                    exibirContaBancaria(conta_menor_saldo);
                }
                break;
            }
            case 'f': {
                if (num_contas == 0) {
                    printf("Nenhuma conta cadastrada.\n");
                } else {
                    float media_saldos = calcularMediaSaldos(contas, num_contas);
                    printf("media dos saldos dos correntistas: R$ %.2f\n", media_saldos);
                }
                break;
            }
            case 'g': {
                int agencia, numero_conta;
                printf("Digite a agencia da conta a ser excluída: ");
                scanf("%d", &agencia);
                printf("Digite o nuemro da conta a ser excluída: ");
                scanf("%d", &numero_conta);
                excluirContaBancaria(contas, &num_contas, agencia, numero_conta);
                break;
            }
            default:
                printf("Opção inválida.\n");
        }
    } while (opcao != 'a');
    
    return 0;
}
