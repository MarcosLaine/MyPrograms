/*
 Hmm! Aqui você foi solicitado a fazer uma simples ordenação. A você serão dado N números e um inteiro positivo M. Você terá que ordenar estes N números em ordem ascendente de seu módulo M.
 Se houver um empate entre um número ímpar e um número par (para os quais o seu módulo M dá o mesmo valor) então o número impar irá preceder o número par.
 Se houver um empate entre dois números ímpares (para os quais o seu módulo M dá o mesmo valor), então o maior número ímpar irá preceder o menor número ímpar.
 Se houve um empate entre dois números pares (para os quais o seu módulo M dá o mesmo valor),
 então o menor número par irá preceder o maior número par. Para o resto de valores negativos siga a regra de linguagem de programação C: um número negativo nunca pode ter módulo maior do que zero.
 Por exemplo, -100 MOD 3 = -1, -100 MOD 4 = 0, etc.


Entrada
A entrada contém vários casos de teste.
Cada caso de teste inicia com dois inteiros N (0 < N ≤ 10000) e M (0 < M ≤ 10000) que denotam quantos números existirão neste conjunto.
Cada uma das próximas N linhas conterá um número cada. Estes números deverão caber em um inteiro de 32 bits com sinal.
A entrada é terminada por uma linha que conterá dois valores nulos (0) e não deve ser processada.


Saída
A primeira linha de cada conjunto de saída irá contér os valores de N e M. As próximas N linhas irão contér N números, ordenados de acordo com as regras acima mencionadas. Imprima os dois últimos zeros da entrada para a saída padrão. */

public class SortSortSort {
    public static void main(String[] args) {
        int testes, mod;
        while (((testes = MyIO.readInt())!= 0) && ((mod = MyIO.readInt()) !=0)) {

            System.out.println(testes + " " + mod);

            int[] vet = new int[testes];

            for(int i = 0; i < testes; i++){
                vet[i] = MyIO.readInt();
            }

            // int[] vetOrdenado = new int[testes];

            /* for(int i = 0; i < vetOrdenado.length-1; i++){
                vetOrdenado[i] = vet[i]%mod;
            } */

            sort(vet, testes, mod);

            for(int i = 0; i < vet.length; i++){
                System.out.println(vet[i]);
            }

            System.out.println("0 0");
        }
    }

    static int[] sort(int[] vetor, int tamanho, int mod) {
        // Loop externo que itera sobre o vetor, começando do primeiro elemento
        for (int i = 0; i < vetor.length - 1; i++) {
            // Loop interno que itera sobre o vetor, começando do primeiro elemento da parte não ordenada do vetor
            for (int j = 0; j < vetor.length - i - 1; j++) {
                // Calcula o módulo do elemento atual e do próximo elemento
                int modA = vetor[j] % mod;
                int modB = vetor[j + 1] % mod;
    
                // Compara os módulos dos elementos
                if (modA > modB) {
                    // Se o módulo do elemento atual for maior que o módulo do próximo elemento, troca os elementos
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                } else if (modA == modB) {
                    // Se os módulos forem iguais, aplica regras adicionais para ordenar os elementos
                    // Ambos são ímpares
                    if (vetor[j] % 2 != 0 && vetor[j + 1] % 2 != 0) {
                        // Se ambos forem ímpares, ordena os elementos em ordem crescente
                        if (vetor[j] < vetor[j + 1]) {
                            int aux = vetor[j];
                            vetor[j] = vetor[j + 1];
                            vetor[j + 1] = aux;
                        }
                    }
                    // Ambos são pares
                    else if (vetor[j] % 2 == 0 && vetor[j + 1] % 2 == 0) {
                        // Se ambos forem pares, ordena os elementos em ordem crescente
                        if (vetor[j] > vetor[j + 1]) {
                            int aux = vetor[j];
                            vetor[j] = vetor[j + 1];
                            vetor[j + 1] = aux;
                        }
                    }
                    // Um é ímpar e o outro é par
                    else if (vetor[j] % 2 != 0 && vetor[j + 1] % 2 == 0) {
                        // Ímpares precedem pares, então não precisa trocar
                    } else {
                        // Se o vetor[j] é par e vetor[j + 1] é ímpar, troca
                        int aux = vetor[j];
                        vetor[j] = vetor[j + 1];
                        vetor[j + 1] = aux;
                    }
                }
            }
        }
    
        // Retorna o vetor ordenado
        return vetor;
    }
}