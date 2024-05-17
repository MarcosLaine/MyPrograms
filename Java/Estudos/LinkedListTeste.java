import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListTeste {
    public static void main(String[] args) {
        LinkedList<String> lista = new LinkedList<String>();

        // Adicionando elementos à lista
        lista.add("joao");
        lista.add("maria");
        lista.add("marcos");

        // Imprimindo todos os elementos usando um loop for-each
        for (var elemento : lista) {
            System.out.println(elemento);
        }

        // Removendo o último elemento e adicionando novos elementos
        lista.removeLast();
        lista.add("qualquer");
        lista.add(1, "joao!");

        // Verificando se a lista contém "maria"
        System.out.println("Contém 'maria'? " + lista.contains("maria"));

        // Imprimindo a lista após modificações
        System.out.println(lista);

        // Removendo um elemento específico
        lista.remove("joao!");

        // Imprimindo a lista após remover "joao!"
        System.out.println(lista);

        // Array de nomes a ser adicionado
        String[] nomes = {"maria2", "joao2", "luciano", "mathues"};

        // Convertendo o array em uma lista e adicionando à LinkedList
        lista.addAll(Arrays.asList(nomes));

        // Imprimindo a lista final após adicionar mais nomes
        System.out.println(lista);

    }
}
