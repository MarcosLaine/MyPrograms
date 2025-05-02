class Lista {
    CelulaLista inicio; // Ponteiro para o início da lista
    CelulaLista fim;    // Ponteiro para o fim da lista

    // Método para adicionar uma nova célula de pilha à lista
    void adicionaPilha(CelulaPilha topo) {
        CelulaLista novaCelula = new CelulaLista(topo); // Cria uma nova célula da lista com o topo da pilha
        if (inicio == null) { // Se a lista estiver vazia
            inicio = fim = novaCelula; // A nova célula é o início e o fim da lista
        } else {
            fim.prox = novaCelula; // Adiciona a nova célula ao fim da lista
            fim = novaCelula; // Atualiza o ponteiro fim para a nova célula
        }
    }

    // Método para encontrar a célula da lista que aponta para a maior pilha
    CelulaLista maiorPilha() {
        if (inicio == null) { // Se a lista estiver vazia
            return null;  // Retorna nulo
        }

        CelulaLista maior = inicio; // Começa assumindo que a primeira célula tem a maior pilha
        int maiorTamanho = tamanhoPilha(inicio.topo); // Calcula o tamanho da pilha da primeira célula

        // Percorre a lista a partir da segunda célula
        for (CelulaLista atual = inicio.prox; atual != null; atual = atual.prox) {
            int tamanhoAtual = tamanhoPilha(atual.topo); // Calcula o tamanho da pilha da célula atual
            if (tamanhoAtual > maiorTamanho) { // Se a pilha atual for maior que a maior conhecida
                maiorTamanho = tamanhoAtual; // Atualiza o tamanho da maior pilha
                maior = atual; // Atualiza a célula com a maior pilha
            }
        }

        return maior; // Retorna a célula da lista que contém a maior pilha
    }

    // Método auxiliar para contar elementos de uma pilha
    int tamanhoPilha(CelulaPilha topo) {
        int contador = 0; // Inicializa o contador
        for (CelulaPilha atual = topo; atual != null; atual = atual.prox) {
            contador++; // Incrementa o contador para cada elemento na pilha
        }
        return contador; // Retorna o número total de elementos na pilha
    }
}

class CelulaLista {
    CelulaPilha topo; // Referência para o topo da pilha
    CelulaLista prox; // Referência para a próxima célula na lista

    // Construtor que inicializa a célula da lista com o topo da pilha
    CelulaLista(CelulaPilha topo) {
        this.topo = topo;
        this.prox = null; // Inicialmente, a próxima célula é nula
    }
}

class CelulaPilha {
    int elemento; // Elemento armazenado na célula da pilha
    CelulaPilha prox; // Referência para a próxima célula na pilha

    // Construtor que inicializa a célula da pilha com o elemento
    CelulaPilha(int elemento) {
        this.elemento = elemento;
        this.prox = null; // Inicialmente, a próxima célula é nula
    }
}

class PrincipalListas {
    public static void main(String[] args) {
        Lista listaDePilhas = new Lista(); // Cria uma nova lista de pilhas

        // Criando algumas pilhas e adicionando à lista

        // Pilha 1 com 3 elementos
        CelulaPilha pilha1 = new CelulaPilha(1);
        pilha1.prox = new CelulaPilha(2);
        pilha1.prox.prox = new CelulaPilha(3);
        listaDePilhas.adicionaPilha(pilha1);

        // Pilha 2 com 2 elementos
        CelulaPilha pilha2 = new CelulaPilha(4);
        pilha2.prox = new CelulaPilha(5);
        listaDePilhas.adicionaPilha(pilha2);

        // Pilha 3 com 4 elementos
        CelulaPilha pilha3 = new CelulaPilha(6);
        pilha3.prox = new CelulaPilha(7);
        pilha3.prox.prox = new CelulaPilha(8);
        pilha3.prox.prox.prox = new CelulaPilha(9);
        listaDePilhas.adicionaPilha(pilha3);

        // Encontrar a célula da lista que aponta para a maior pilha
        CelulaLista maior = listaDePilhas.maiorPilha();

        // Imprimir o resultado
        if (maior != null) {
            System.out.println("A célula da lista que aponta para a maior pilha contém o topo com elemento: " + maior.topo.elemento);
            System.out.println("Tamanho da maior pilha: " + listaDePilhas.tamanhoPilha(maior.topo));
        } else {
            System.out.println("A lista de pilhas está vazia.");
        }
    }
}
