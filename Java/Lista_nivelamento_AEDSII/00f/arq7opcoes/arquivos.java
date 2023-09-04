import java.io.*;
import java.util.Scanner;

public class arquivos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Salvar uma frase em um arquivo");
        System.out.println("2. Ler e mostrar o conteúdo de um arquivo");
        System.out.println("3. Ler e mostrar o conteúdo em letras maiúsculas");
        System.out.println("4. Copiar conteúdo de um arquivo para outro");
        System.out.println("5. Converter e salvar em letras maiúsculas");
        System.out.println("6. Copiar e inverter conteúdo");
        System.out.println("7. Criptografar usando Ciframento de César");
        System.out.println("8. Descriptografar Ciframento de César");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                System.out.print("Digite o nome do arquivo para salvar: ");
                String nomeArquivo1 = scanner.nextLine();
                System.out.print("Digite a frase a ser salva: ");
                String frase = scanner.nextLine();
                salvarFraseEmArquivo(nomeArquivo1, frase);
                break;
            case 2:
                System.out.print("Digite o nome do arquivo para ler: ");
                String nomeArquivo2 = scanner.nextLine();
                lerEExibirConteudo(nomeArquivo2);
                break;
            case 3:
                System.out.print("Digite o nome do arquivo para ler em maiúsculas: ");
                String nomeArquivo3 = scanner.nextLine();
                lerEExibirMaiusculas(nomeArquivo3);
                break;
            case 4:
                System.out.print("Digite o nome do arquivo de origem: ");
                String arquivoOrigem = scanner.nextLine();
                System.out.print("Digite o nome do arquivo de destino: ");
                String arquivoDestino = scanner.nextLine();
                copiarConteudo(arquivoOrigem, arquivoDestino);
                break;
            case 5:
                System.out.print("Digite o nome do arquivo para converter e salvar: ");
                String arquivoEntrada = scanner.nextLine();
                System.out.print("Digite o nome do arquivo de destino: ");
                String arquivoSaida = scanner.nextLine();
                converterESalvarMaiusculas(arquivoEntrada, arquivoSaida);
                break;
            case 6:
                System.out.print("Digite o nome do arquivo de origem: ");
                String arquivoOrigem2 = scanner.nextLine();
                System.out.print("Digite o nome do arquivo de destino: ");
                String arquivoDestino2 = scanner.nextLine();
                copiarEInverterConteudo(arquivoOrigem2, arquivoDestino2);
                break;
            case 7:
                System.out.print("Digite o nome do arquivo para criptografar: ");
                String arquivoEntradaCifrar = scanner.nextLine();
                System.out.print("Digite o nome do arquivo de destino: ");
                String arquivoSaidaCifrar = scanner.nextLine();
                ciframentoCesar(arquivoEntradaCifrar, arquivoSaidaCifrar, 3);
                break;
            case 8:
                System.out.print("Digite o nome do arquivo para descriptografar: ");
                String arquivoEntradaDescript = scanner.nextLine();
                System.out.print("Digite o nome do arquivo de destino: ");
                String arquivoSaidaDescript = scanner.nextLine();
                deciframentoCesar(arquivoEntradaDescript, arquivoSaidaDescript, 3);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }

    public static void salvarFraseEmArquivo(String nomeArquivo, String frase) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println(frase);
            System.out.println("Frase salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a frase no arquivo.");
            e.printStackTrace();
        }
    }

    public static void lerEExibirConteudo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    public static void lerEExibirMaiusculas(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha.toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    public static void copiarConteudo(String arquivoOrigem, String arquivoDestino) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoOrigem));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Cópia concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao copiar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void converterESalvarMaiusculas(String arquivoEntrada, String arquivoSaida) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                writer.write(linha.toUpperCase());
                writer.newLine();
            }
            System.out.println("Conversão concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao converter e salvar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void copiarEInverterConteudo(String arquivoOrigem, String arquivoDestino) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoOrigem));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                StringBuilder reversed = new StringBuilder(linha).reverse();
                writer.write(reversed.toString());
                writer.newLine();
            }
            System.out.println("Cópia invertida concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao copiar e inverter o arquivo.");
            e.printStackTrace();
        }
    }

    public static void ciframentoCesar(String arquivoEntrada, String arquivoSaida, int chave) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                StringBuilder cifrado = new StringBuilder();
                for (char c : linha.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        int deslocamento = (c - base + chave) % 26;
                        cifrado.append((char) (base + deslocamento));
                    } else {
                        cifrado.append(c);
                    }
                }
                writer.write(cifrado.toString());
                writer.newLine();
            }
            System.out.println("Criptografia concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao cifrar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void deciframentoCesar(String arquivoEntrada, String arquivoSaida, int chave) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                StringBuilder decifrado = new StringBuilder();
                for (char c : linha.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        int deslocamento = (c - base - chave + 26) % 26;
                        decifrado.append((char) (base + deslocamento));
                    } else {
                        decifrado.append(c);
                    }
                }
                writer.write(decifrado.toString());
                writer.newLine();
            }
            System.out.println("Descriptografia concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao decifrar o arquivo.");
            e.printStackTrace();
        }
    }
}
