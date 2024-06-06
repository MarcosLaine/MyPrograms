/* Depois de um belo dia de aula é função das vans levarem os estudantes para suas respectivas casas. Mas o que muitos não sabem é que além dos gastos e manutenção da van o motorista precisa ter uma rota para entregar os passageiros em suas casas. Como você é o menino(a) da informática, ele pediu sua ajuda para desenvolver essa rota ordenando os alunos pela distância (da menor para a maior), pela região (em ordem alfabética) e por último pelo nome.


Entrada
Ele te dá a quantidade Q de alunos que não faltaram, o nome do aluno A e uma sigla para a região onde ele mora S ("L" Leste, "N" Norte, "O" Oeste, "S" Sul), e C que representa o custo da entrada da cidade até sua casa. A saída dos casos será (EOF).

Saída
A saída será uma lista das pessoas na ordem em que devem ser entregues. */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
    char nome[50];
    char regiao[2];
    int custo;
} Aluno;

int comparar_alunos(const void *a, const void *b)
{
    Aluno *aluno1 = (Aluno *)a;
    Aluno *aluno2 = (Aluno *)b;

    if (aluno1->custo != aluno2->custo)
        return aluno1->custo - aluno2->custo;
    else if (strcmp(aluno1->regiao, aluno2->regiao) != 0)
        return strcmp(aluno1->regiao, aluno2->regiao);
    else
        return strcmp(aluno1->nome, aluno2->nome);
}

int main()
{
    char input[10000000];
    while (scanf("%s", input) != EOF)
    {
        int q;
        scanf("%d", &q);

        Aluno *alunos = (Aluno *)malloc(q * sizeof(Aluno));

        for (int i = 0; i < q; i++)
        {
            scanf("%49s %1s %d", alunos[i].nome, alunos[i].regiao, &alunos[i].custo);
        }

        qsort(alunos, q, sizeof(Aluno), comparar_alunos);

        for (int i = 0; i < q; i++)
        {
            printf("%s %s %d\n", alunos[i].nome, alunos[i].regiao, alunos[i].custo);
        }

        free(alunos);
    }

    return 0;
}