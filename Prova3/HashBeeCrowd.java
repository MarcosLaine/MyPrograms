import java.util.Scanner;

class Celula {
    int elemento;
    Celula prox;

    Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class ListaEncadeada {
    Celula inicio;
    Celula fim;

    ListaEncadeada() {
        this.inicio = null;
        this.fim = inicio;
    }

    void inserir(int elemento) {
        Celula nova = new Celula(elemento);
        nova.prox = null;
        if (inicio == null) {
            inicio = nova;
            fim = nova;
        } else {
            fim.prox = nova;
            fim = nova;
        }
    }
}

public class HashBeeCrowd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int entradas;

        while ((entradas = sc.nextInt()) != 0) {
            int M = sc.nextInt(); // tamanho da tabela
            int C = sc.nextInt(); // numero de entradas

            ListaEncadeada[] lista = new ListaEncadeada[M];

            for (int i = 0; i < M; i++) {
                lista[i] = new ListaEncadeada();
            }

            for (int i = 0; i < C; i++) {
                int X = sc.nextInt();
                lista[hash(X)].inserir(X);
            }

            Celula atual = inicio;
            while (atual != null) {
                System.out.print(atual.elemento);
                if (atual.prox != null) {
                    System.out.print(" -> ");
                }
                atual = atual.prox;
            }
            System.out.print(" \\");
            System.out.println();
            System.out.println();
            entradas--;
        }
        sc.close();

    }

    public static int hash(X){
        return X % M;
    }
}

