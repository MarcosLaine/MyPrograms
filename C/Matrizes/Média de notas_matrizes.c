/*  data: 18/05/2023
    Lista de exercícios, Assunto: Matrizes
    Exercício: 2.9 notas e médias bimestrais
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

const int n = 4;
const int disciplinas = 8;

void lerMatriz(float M[][n], int m);
void calculaMediasBimestrais(float M[][n], int m);
void imprimeMatriz(float M[][n], int m);

int main() {
  float notas[disciplinas + 1][n]; // +1 para a linha das médias
  lerMatriz(notas, disciplinas);
  calculaMediasBimestrais(notas, disciplinas);
  imprimeMatriz(notas, disciplinas + 1); // +1 para imprimir a linha das médias
  return 0;
}

void lerMatriz(float M[][n], int m) {
  for (int i = 0; i < m; i++) {
    printf("Digite as notas da disciplina %d:\n", i + 1);
    for (int j = 0; j < n; j++) {
      do {
        printf("  Bimestre %d: ", j + 1);
        scanf("%f", &M[i][j]);
        if (M[i][j] < 0 || M[i][j] > 10) {
          printf("Nota inválida. Digite uma nota entre 0 e 10.\n");
        }
      } while (M[i][j] < 0 || M[i][j] > 10);
    }
  }
}

void calculaMediasBimestrais(float M[][n], int m) {
  for (int j = 0; j < n; j++) {
    float soma = 0;
    for (int i = 0; i < m; i++) {
      soma += M[i][j];
    }
    M[m][j] = soma / m; // Armazena a média bimestral na última linha da matriz
  }
}

void imprimeMatriz(float M[][n], int m) {
  const char* disciplinasNomes[] = {"Disciplina 1", "Disciplina 2", "Disciplina 3", "Disciplina 4",
                                    "Disciplina 5", "Disciplina 6", "Disciplina 7", "Disciplina 8",
                                    "Média Bimestral"};

  for (int i = 0; i < m; i++) {
    printf("%s:\n", disciplinasNomes[i]);
    for (int j = 0; j < n; j++) {
      printf("  Bimestre %d: %.2f\n", j + 1, M[i][j]);
    }
    printf("\n");
  }
}
