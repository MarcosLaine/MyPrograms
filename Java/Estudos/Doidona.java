class HashTable {
    Integer[] table;

    public HashTable(int size) {
        table = new Integer[size];
    }

    public boolean insert(int value) {
        int index = hash(value);
        if (table[index] == null) {
            table[index] = value;
            return true;
        }
        return false;
    }

    public boolean search(int value) {
        int index = hash(value);
        return table[index] != null && table[index] == value;
    }

    public boolean remove(int value) {
        int index = hash(value);
        if (table[index] != null && table[index] == value) {
            table[index] = null;
            return true;
        }
        return false;
    }

    public void show() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                System.out.println("Tabela[" + i + "] = " + table[i]);
            }
        }
    }

    private int hash(int value) {
        // Implementação simples de função hash
        return value % table.length;
    }

    // Método de rehashing para a tabela T3
    public int rehash(int value) {
        // Implementação simplificada de rehashing
        return (value * 7) % table.length;
    }
}

class BinaryTree {
    private class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value)
            root.left = insertRec(root.left, value);
        else if (value > root.value)
            root.right = insertRec(root.right, value);
        return root;
    }

    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node root, int value) {
        if (root == null)
            return false;
        if (root.value == value)
            return true;
        return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    public void remove(int value) {
        root = removeRec(root, value);
    }

    private Node removeRec(Node root, int value) {
        if (root == null)
            return root;
        if (value < root.value)
            root.left = removeRec(root.left, value);
        else if (value > root.value)
            root.right = removeRec(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.value = minValue(root.right);
            root.right = removeRec(root.right, root.value);
        }
        return root;
    }

    private int minValue(Node root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public void show() {
        showRec(root);
    }

    private void showRec(Node root) {
        if (root != null) {
            showRec(root.left);
            System.out.println(root.value);
            showRec(root.right);
        }
    }
}

public class Doidona {
    private final HashTable T1;
    private final HashTable T3;
    private final BinaryTree reservaT3;

    public Doidona(int tamanhoT1, int tamanhoT3) {
        T1 = new HashTable(tamanhoT1);
        T3 = new HashTable(tamanhoT3);
        reservaT3 = new BinaryTree();
    }

    public void inserir(int elemento) {
        if (!T1.insert(elemento)) {
            // Se falhar em inserir em T1, tenta inserir em T3
            if (!T3.insert(elemento)) {
                // Se falhar em T3, tenta rehash
                int rehashIndex = T3.rehash(elemento);
                if (T3.table[rehashIndex] != null) {
                    // Se rehash falhar, insere na árvore binária
                    reservaT3.insert(elemento);
                } else {
                    T3.table[rehashIndex] = elemento;
                }
            }
        }
    }

    public boolean pesquisar(int elemento) {
        if (T1.search(elemento)) return true;
        if (T3.search(elemento)) return true;
        return reservaT3.search(elemento);
    }

    public void mostrar() {
        System.out.println("Tabela T1:");
        T1.show();
        System.out.println("Tabela T3:");
        T3.show();
        System.out.println("Árvore binária:");
        reservaT3.show();
    }

    public void remover(int elemento) {
        if (!T1.remove(elemento)) {
            if (!T3.remove(elemento)) {
                reservaT3.remove(elemento);
            }
        }
    }

    public static void main(String[] args) {
        Doidona doidona = new Doidona(10, 10);

        // Testando a inserção
        doidona.inserir(15);
        doidona.inserir(25);
        doidona.inserir(35);

        // Mostrando os elementos
        System.out.println("Elementos após inserção:");
        doidona.mostrar();

        // Testando a pesquisa
        boolean encontrado15 = doidona.pesquisar(15);
        boolean encontrado50 = doidona.pesquisar(50);
        System.out.println("15 encontrado? " + encontrado15);
        System.out.println("50 encontrado? " + encontrado50);

        // Testando a remoção
        doidona.remover(25);

        // Mostrando os elementos após remoção
        System.out.println("\nElementos após remoção do 25:");
        doidona.mostrar();
    }
}
