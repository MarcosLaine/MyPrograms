public class CalculoIndices {

    static int tamanhoBloco = 2048; // tamanho de cada bloco em bytes (2KB)
    static double ocupacaoArvoreB = 0.69; // 69% de ocupação na árvore B+
    static int ponteiroBloco = 16; // tamanho do ponteiro para blocos de disco em bytes
    static int ponteiroNo = 12; // tamanho do ponteiro de nó da árvore B+ em bytes

    public static void main(String[] args) {
        //Calculando para a tabela Clientes
        int numRegistrosClientes = 100000;
        int tamanhoRegistroCliente = 11 + 160 + 200 + 16 + 12 + 1; // Somatório dos tamanhos de cada campo
        calculaBlocagemEIndices("Clientes", numRegistrosClientes, 11, tamanhoRegistroCliente); // CPF como chave primária

        // Repetir para outras tabelas conforme necessário
    }

    // Método para calcular blocagem e índices
    public static void calculaBlocagemEIndices(String nomeTabela, int numRegistros, int tamanhoChave, int tamanhoRegistro) {
        // Fator de bloco para a tabela
        int elementosPorBloco = (int) Math.floor(tamanhoBloco / tamanhoRegistro);
        int numBlocos = (int) Math.ceil(numRegistros / (double) elementosPorBloco);
        int espacoTotal = numBlocos * tamanhoBloco;

        // Cálculo para índice B+ (dinâmico)
        int tamChavePonteiro = tamanhoChave + ponteiroNo;
        int elementosPorBlocoIndice = (int) Math.floor((tamanhoBloco * ocupacaoArvoreB) / tamChavePonteiro);
        int numBlocosIndice = (int) Math.ceil(numRegistros / (double) elementosPorBlocoIndice);
        int espacoTotalIndice = numBlocosIndice * tamanhoBloco;
        
        // Acessos de bloco para recuperação usando árvore B+
        double alturaArvore = Math.ceil(Math.log(numRegistros) / Math.log(elementosPorBlocoIndice));
        int acessosBloco = (int) alturaArvore + 1; // Considerando acesso ao nó folha

        System.out.println("Tabela: " + nomeTabela);
        System.out.println("Fator de bloco: " + elementosPorBloco);
        System.out.println("Número de blocos (dados): " + numBlocos);
        System.out.println("Espaço total (dados): " + espacoTotal + " bytes");
        System.out.println("Número de blocos (índice): " + numBlocosIndice);
        System.out.println("Espaço total (índice): " + espacoTotalIndice + " bytes");
        System.out.println("Acessos a blocos para recuperação: " + acessosBloco);
        System.out.println();
    }
}
