using System;

public class CalculoIndices
{
    static int tamanhoBloco = 2048; // tamanho de cada bloco em bytes (2KB)
    static double ocupacaoArvoreB = 0.69; // 69% de ocupação na árvore B+
    static int ponteiroBloco = 16; // tamanho do ponteiro para blocos de disco em bytes
    static int ponteiroNo = 12; // tamanho do ponteiro de nó da árvore B+ em bytes

    public static void Main(string[] args)
    {
        // Calculando para todas as tabelas
        CalculaBlocagemEIndices("Atores", 10000, 16, 176); // Codigo (16B) + Nome (160B)
        CalculaBlocagemEIndices("Clientes", 100000, 11, 400); // CPF (11B) + Nome (160B) + Endereco (200B) + Telefone (16B) + DataNascimento (12B) + Sexo (1B)
        CalculaBlocagemEIndices("Filmes", 2000000, 16, 256); // Codigo (16B) + Nome (160B) + Genero (80B)
        CalculaBlocagemEIndices("Funcionarios", 3500, 11, 171); // CPF (11B) + Nome (160B)
        CalculaBlocagemEIndices("Midias", 10000000, 24, 56); // Identificador (24B) + Tipo (8B) + PrecoDiaria (24B)
        CalculaBlocagemEIndices("Aluguel", 20000000, 12, 46); // DataLocacao (12B) + DataDevolucao (10B) + ValorPagar (24B)
        CalculaBlocagemEIndices("Pagamentos", 50000000, 48, 84); // Codigo (48B) + Data (12B) + Valor (24B)
        CalculaBlocagemEIndices("AtoresEmFilmes", 1000000, 32, 32); // CodFilme (16B) + CodAtor (16B), assumindo tamanho de chave agregada
    }

    // Método para calcular blocagem e índices
    public static void CalculaBlocagemEIndices(string nomeTabela, int numRegistros, int tamanhoChave, int tamanhoRegistro)
    {
        // Fator de bloco para a tabela
        int elementosPorBloco = (int)Math.Floor((double)tamanhoBloco / tamanhoRegistro);
        int numBlocos = (int)Math.Ceiling((double)numRegistros / elementosPorBloco);
        int espacoTotal = numBlocos * tamanhoBloco;

        // Cálculo para índice B+ (dinâmico)
        int tamChavePonteiro = tamanhoChave + ponteiroNo;
        int elementosPorBlocoIndice = (int)Math.Floor(tamanhoBloco * ocupacaoArvoreB / tamChavePonteiro);
        int numBlocosIndice = (int)Math.Ceiling((double)numRegistros / elementosPorBlocoIndice);
        int espacoTotalIndice = numBlocosIndice * tamanhoBloco;

        // Acessos de bloco para recuperação usando árvore B+
        double alturaArvore = Math.Ceiling(Math.Log(numRegistros) / Math.Log(elementosPorBlocoIndice));
        int acessosBloco = (int)alturaArvore + 1; // Considerando acesso ao nó folha

        Console.WriteLine("Tabela: " + nomeTabela);
        Console.WriteLine("Fator de bloco: " + (elementosPorBloco+16));
        Console.WriteLine("Número de blocos (dados): " + numBlocos);
        Console.WriteLine("Espaço total (dados): " + FormatarTamanho(espacoTotal));
        Console.WriteLine("Número de blocos (índice): " + numBlocosIndice);
        Console.WriteLine("Espaço total (índice): " + FormatarTamanho(espacoTotalIndice));
        Console.WriteLine("Acessos a blocos para recuperação: " + acessosBloco);
        Console.WriteLine();
    }

    // Função para formatar bytes em MB ou GB conforme necessário
    public static string FormatarTamanho(int bytes)
    {
        if (bytes < 1024 * 1024)
            return $"{bytes / 1024.0:F2} KB"; // Convertendo para KB se menos que 1MB
        else if (bytes < 1024 * 1024 * 1024)
            return $"{bytes / (1024.0 * 1024):F2} MB"; // Convertendo para MB se menos que 1GB
        else
            return $"{bytes / (1024.0 * 1024 * 1024):F2} GB"; // Convertendo para GB se mais que 1GB
    }
}
