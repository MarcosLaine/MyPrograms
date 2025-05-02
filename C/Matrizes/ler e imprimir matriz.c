/*  data: 18/05/2023
    Lista de exercícios, Assunto: Matrizes
    Exercício: 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.10 - Matrizes - Lista completa exceto 2.9
    Aluno: Marcos Paulo da Silva Laine  RA:1430851
*/
#include <stdio.h>

const int n = 4;

void leMatriz(float mat[][n], int m);
void imprimeMatriz(float mat[][n], int m);
void matrizTransposta(float mat[][n], float matt[][n], int m);
void inverteDiagonaisMat(float mat[][n], int m);
int somaDiagonalPrincipal(float M[][n], int m);
void identidade(float M[][n], int MI[][n], int m);
void inverteColunasMat(float mat[][n], int m);
void minMax(float M[][n], int m, int *linha, int *coluna);
float* maiorLinha(float M[][n], int m, float *maiorl);
float* menorColuna(float M[][n], int m, float *menorc);

int main() {
  int m;
  printf("Digite o número de linhas da matriz: ");
  scanf("%d", &m);
  float mat[m][n];
  leMatriz(mat, m);
  printf("\nMatriz:\n");
  imprimeMatriz(mat, m);
  printf("\nMatriz transposta:\n");
  float matt[n][m];
  matrizTransposta(mat, matt, m);
  imprimeMatriz(matt, n);
  printf("\nMatriz com diagonais invertidas:\n");
  inverteDiagonaisMat(mat, m);
  imprimeMatriz(mat, m);
  printf("\nSoma dos valores da diagonal principal: %d\n", somaDiagonalPrincipal(mat, m));
  printf("\nMatriz Identidade:\n");
  float matI[m][n];
  identidade(mat, matI, m);
  imprimeMatriz(matI, m);
  printf("\nMatriz com colunas invertidas:\n");
  inverteColunasMat(mat, m);
  imprimeMatriz(mat, m);
  //int linha, coluna;
  //minMax(mat, m, &linha, &coluna);
  //printf("\nElemento MINMAX: %.2f\n", mat[linha][coluna]);
  //printf("Índices: linha = %d, coluna = %d\n", linha, coluna);
  //float maiorl[m];
  //maiorLinha(mat, m, maiorl);
  //printf("\nMaiores elementos de cada linha:\n");
  //for (int i = 0; i < m; i++) {
//    printf("Linha %d: %.2f\n", i + 1, maiorl[i]);
  //}
  //float menorc[n];
  //menorColuna(mat, m, menorc);
  //printf("\nMenores elementos de cada coluna:\n");
  //for (int j = 0; j < n; j++) {
//    printf("Coluna %d: %.2f\n", j + 1, menorc[j]);
//  }
  return 0;
}
void leMatriz(float mat[][n], int m) {
  for (int i = 0; i < m; i++) {
    printf("Digite os valores da linha %d:\n", i + 1);
    for (int j = 0; j < n; j++) {
      printf("valor %d: ", j + 1);
      scanf("%f", &mat[i][j]);
    }
  }
}

void imprimeMatriz(float mat[][n], int m) {
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      printf("%.2f ", mat[i][j]);
    }
    printf("\n");
  }
}

void matrizTransposta(float mat[][n], float matt[][n], int m) {
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      matt[j][i] = mat[i][j];
    }
  }
}

void inverteDiagonaisMat(float mat[][n], int m) {
  for (int i = 0; i < m; i++) {
    float temp = mat[i][i];
    mat[i][i] = mat[i][n - 1 - i];
    mat[i][n - 1 - i] = temp;
  }
}

int somaDiagonalPrincipal(float M[][n], int m) {
  int soma = 0;
  for (int i = 0; i < m; i++) {
    soma += M[i][i];
  }
  return soma;
}

void identidade(float M[][n], int MI[][n], int m) {
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i == j) {
        MI[i][j] = 1;
      } else {
        MI[i][j] = 0;
      }
    }
  }
}

void inverteColunasMat(float mat[][n], int m) {
  for (int j = 0; j < n / 2; j++) {
    for (int i = 0; i < m; i++) {
      float temp = mat[i][j];
      mat[i][j] = mat[i][n - 1 - j];
      mat[i][n - 1 - j] = temp;
    }
  }
}

void minMax(float M[][n], int m, int *linha, int *coluna) {
  float min = M[0][0];
  float max = M[0][0];
  *linha = 0;
  *coluna = 0;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (M[i][j] < min) {
        min = M[i][j];
        *linha = i;
        *coluna = j;
      }
      if (M[i][j] > max) {
        max = M[i][j];
      }
    }
  }
}

float* maiorLinha(float M[][n], int m, float *maiorl) {
  for (int i = 0; i < m; i++) {
    float maior = M[i][0];
    for (int j = 1; j < n; j++) {
      if (M[i][j] > maior) {
        maior = M[i][j];
      }
    }
    maiorl[i] = maior;
  }
  return maiorl;
}

float* menorColuna(float M[][n], int m, float *menorc) {
  for (int j = 0; j < n; j++) {
    float menor = M[0][j];
    for (int i = 1; i < m; i++) {
      if (M[i][j] < menor) {
        menor = M[i][j];
      }
    }
    menorc[j] = menor;
  }
  return menorc;
}