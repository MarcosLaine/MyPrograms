import java.util.Scanner;

public class BeeCrowdOlimpieda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quadro de Medalhas");

        while (scanner.hasNext()) {
            String descricao = scanner.nextLine(); // Lê a descrição da modalidade (não usaremos esta informação)
            String ouro = scanner.nextLine(); // Lê o país vencedor de ouro
            String prata = scanner.nextLine(); // Lê o país vencedor de prata
            String bronze = scanner.nextLine(); // Lê o país vencedor de bronze

            // Chame a função para atualizar a tabela de medalhas
            medalhas(ouro, prata, bronze);
        }

        // Chame a função para ordenar e imprimir a tabela de medalhas
        ordenarEImprimirQuadroMedalhas();
    }

    // Declaramos as variáveis de medalhas usando nomes em português
    private static int totalOuros = 0;
    private static int totalPratas = 0;
    private static int totalBronzes = 0;

    // Declaramos um array para manter o controle das medalhas por país
    private static String[] paises = new String[300]; // Supomos até 300 países
    private static int[] ouros = new int[300];
    private static int[] pratas = new int[300];
    private static int[] bronzes = new int[300];

    public static void medalhas(String ouro, String prata, String bronze) {
        for (int i = 0; i < paises.length; i++) {
            if (paises[i] == null) {
                paises[i] = ouro;
                ouros[i]++;
                pratas[i] = 0;
                bronzes[i] = 0;
                return;
            } else if (paises[i].equals(ouro)) {
                ouros[i]++;
                return;
            } else if (paises[i].equals(prata)) {
                pratas[i]++;
                return;
            } else if (paises[i].equals(bronze)) {
                bronzes[i]++;
                return;
            }
        }
    }

    public static void ordenarEImprimirQuadroMedalhas() {
        int n = paises.length;

        // Implementamos uma ordenação manual com base nos critérios especificados
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Comparamos os países com base nos critérios (ouro, prata, bronze)
                if (ouros[j] < ouros[j + 1] ||
                    (ouros[j] == ouros[j + 1] && pratas[j] < pratas[j + 1]) ||
                    (ouros[j] == ouros[j + 1] && pratas[j] == pratas[j + 1] && bronzes[j] < bronzes[j + 1])) {
                    // Trocamos os países de posição se necessário
                    int tempOuro = ouros[j];
                    int tempPrata = pratas[j];
                    int tempBronze = bronzes[j];
                    String tempPais = paises[j];

                    ouros[j] = ouros[j + 1];
                    pratas[j] = pratas[j + 1];
                    bronzes[j] = bronzes[j + 1];
                    paises[j] = paises[j + 1];

                    ouros[j + 1] = tempOuro;
                    pratas[j + 1] = tempPrata;
                    bronzes[j + 1] = tempBronze;
                    paises[j + 1] = tempPais;
                }
            }
        }

        // Imprimimos a tabela de medalhas ordenada
        System.out.println("Quadro de Medalhas");
        for (int i = 0; i < n; i++) {
            if (paises[i] != null) {
                System.out.println(paises[i] + " " + ouros[i] + " " + pratas[i] + " " + bronzes[i]);
            }
        }
    }
}
