#include <stdio.h>
#include <stdbool.h>
#define TOTAL_NOTAS 6
int notas[TOTAL_NOTAS] = {100, 50, 20, 10, 5, 2};
int estoque[TOTAL_NOTAS] = {0};
void calcularNotas(int valor, int quantidade[])
{
    int valor_restante = valor;
    for (int i = 0; i < TOTAL_NOTAS; i++)
    {
        quantidade[i] = valor_restante / notas[i];
        if (quantidade[i] > estoque[i])
        {
            quantidade[i] = estoque[i];
        }
        valor_restante -= quantidade[i] * notas[i];
        estoque[i] -= quantidade[i];
    }
}
void saque(int valor)
{
    int quantidade[TOTAL_NOTAS] = {0};
    int totalEstoque = 0;
    calcularNotas(valor, quantidade);
    printf("Valor do saque: R$%d\n", valor);
    printf("Notas a serem fornecidas:\n");
    for (int i = 0; i < TOTAL_NOTAS; i++)
    {
        if (quantidade[i] > 0)
        {
            printf("%d nota(s) de R$%d\n", quantidade[i], notas[i]);
        }
    }
    for (int i = 0; i < TOTAL_NOTAS; i++)
    {
    {
        totalEstoque += estoque[i] * notas[i];
    }
    if (totalEstoque < 100)
    {
        printf("Atenção, o saldo está baixo! Reabasteça o caixa\n\n");
        return;
    }
    printf("\n");
}
void carregarNotas(int notas[], int quantidade[], int tamanho)
{
    printf("Carregamento de notas no estoque\n");
    for (int i = 0; i < tamanho; i++)
    {
        printf("Informe a quantidade de notas de valor R$%d: ", notas[i]);
        scanf("%d", &quantidade[i]);
    }
    printf("Estoque carregado com sucesso!\n");
}
void mostrarNotas(int notas[], int quantidades[], int tamanho)
{
    printf("\nValores\t\tQuantidade\n");
    for (int i = 0; i < tamanho; i++)
    {
        printf("R$%d\t\t%d\n", notas[i], quantidades[i]);
    }
}
int main()
{
    char opcao;
    int valor, senha_cadastro, senha_saque;
    bool senha_correta = false;
    int tentativas = 0;
    printf("Digite uma senha para cadastrar: ");
    scanf("%d", &senha_cadastro);
    while (1)
    {
        printf("Escolha uma opção:\n");
        printf("a) Carregar notas\n");
        printf("b) Mostrar notas\n");
        printf("c) Sacar\n");
        printf("d) Sair\n");
        printf("Opção: ");
        scanf(" %c", &opcao);
        if (opcao == 'a')
        {
            carregarNotas(notas, estoque, TOTAL_NOTAS);
        }
        else if (opcao == 'b')
        {
            mostrarNotas(notas, estoque, TOTAL_NOTAS);
        }
        else if (opcao == 'c')
        {
            printf("\nInforme o valor para sacar: ");
            scanf("%d", &valor);
            while (!senha_correta && tentativas < 3)
            {
                printf("Digite a senha: ");
                scanf("%d", &senha_saque);
                if (senha_saque == senha_cadastro)
                {
                    senha_correta = true;
                }
                else
                {
                    tentativas++;
                    if (tentativas < 3)
                        printf("Senha incorreta. Digite novamente: ");
                }
            }
            if (senha_correta)
            {
                saque(valor);
                senha_correta = false; // Resetar a flag da senha correta para o próximo saque
                tentativas = 0;        // Resetar o contador de tentativas
            }
            else
            {
                printf("Número máximo de tentativas excedido.\n");
                printf("Você foi bloqueado por 5 minutos.\n");
                sleep(300); // Bloqueio por 5 minutos (300 segundos)
            }
        }
        else if (opcao == 'd')
        {
            printf("Encerrando o programa...\n");
            break;
        }
        else
        {
            printf("Opção inválida. Tente novamente.\n");
        }
        printf("\n");
    }
    return 0;
}