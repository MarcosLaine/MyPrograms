import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class pilha {
    public static class Pilha<T> implements Serializable {
        private ArrayList<T> elementos = new ArrayList<>();

        public void push(T elemento) {
            elementos.add(elemento);
        }

        public T pop() {
            if (!elementos.isEmpty()) {
                return elementos.remove(elementos.size() - 1);
            }
            return null;
        }

        public void listar() {
            for (T elemento : elementos) {
                System.out.println(elemento);
            }
        }

        public void salvarEmArquivo(String nomeArquivo) {
            try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
                writer.writeObject(elementos);
                System.out.println("Pilha salva com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao salvar a pilha no arquivo.");
                e.printStackTrace();
            }
        }

        @SuppressWarnings("unchecked")
        public void carregarDeArquivo(String nomeArquivo) {
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
                elementos = (ArrayList<T>) reader.readObject();
                System.out.println("Pilha carregada com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar a pilha do arquivo.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pilha<String> pilha = new Pilha<>();
        pilha.carregarDeArquivo("pilha.dat");

        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Inserir");
            System.out.println("2. Remover");
            System.out.println("3. Listar");
            System.out.println("4. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o elemento a ser inserido: ");
                    String elemento = scanner.nextLine();
                    pilha.push(elemento);
                    break;
                case 2:
                    String removido = pilha.pop();
                    if (removido != null) {
                        System.out.println("Elemento removido: " + removido);
                    } else {
                        System.out.println("A pilha está vazia.");
                    }
                    break;
                case 3:
                    System.out.println("Elementos da pilha:");
                    pilha.listar();
                    break;
                case 4:
                    pilha.salvarEmArquivo("pilha.dat");
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}
