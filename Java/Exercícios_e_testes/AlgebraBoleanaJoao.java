public class AlgebraBoleanaJoao {

    public static String Algebra(int posicao, String funcoes) 
    {
        // INICIANDO AS VARAIVES 
        String retorno ="";

        //CRIANDO UM LOOP FOR QUE IRA PERCORRER TODA A STRING 
        for(int i = 0; i < funcoes.length() ; i++)
        {
            //AQUI IREMOS PREENCHER A NOVA STRING retorno COM OS VALORES DA STRING ORIGINAL
            //POREM, VAMOS RESOLVER UM DOS OPERADORES QUANDO CHEGAR NA POSICAO posicao E
            //DEPOIS VOLTAR A PREENCHER A STRING PARA NAO PERDER DADOS
            if (i < posicao || i > posicao)
            {
                //SE NAO CHEGOU NA POSICAO AINDA OU SE JA PASSOU DELA SO VAMOS COPIAR A STRING
                retorno += funcoes.charAt(i); 
            }
            //CHEGAMOS NA POSICAO QUE ESTA ACONTECENDO A PRIMEIRA OPERACAO DA DIREITA PRA ESQUERDA
            else if (i == posicao)
            {
                if(funcoes.charAt(i) == '!')
                {
                    if (funcoes.charAt(i+2) == '0') retorno += '1';
                    else retorno += '0';
                    //OBSERVA QUE ESTAMOS CRIANDO A NOVA STRING DO 0 LOGO TODOS VALORES ADICIONADOS SEMPRE SERA NA``PROXIMA CASA ``
                    i += 3;
                    // PULAMOS 3 CASAS PORQUE NOSSO NOT ESTA COMO !(0) ELE CONTA 3 CASAS QUE VAI ATE O VALOR DE DENTRO '0'
                    // E A POSICAO FICA EM CIMA DO ')' POREM TEM ++ DO FOR QUE VAI PULAR ESSA POSICAO, SE FIZER NO WHILE AKI PRECISA 
                    // SER 4
                }
                else if( funcoes.charAt(i) == '.')
                {
                    int contador = 0;
                    int valor=0;
                    while (funcoes.charAt(i + contador) != ')')
                    {
                        //AQUI TEM UNS B.O.S AS FUNCOES AND E OR PODEM TER VARIOS TERMOS DENTRO DELA LOGO NOS PRECISAMOS SABER
                        //QUANTAS POSICOES VAMOS PULAR, POR ISSO CRIAMOS UM CONTADOR
                        contador++ ;
                        //A FUNCAO OR SE TIVER ALGUM VALOR TRUE (1) ELA RETORNA TRUE
                        //LOGO ESTAMOS CONTANDO QUANTOS TRUES EXISTEM, SE EXISTIR PELO MENOS UM ELA VAI RETORNA TRUE
                        if(funcoes.charAt(i + contador) == '1')valor ++;
                    }
                    //COMO DITO ANTERIORMENTE
                    if (valor > 0) retorno += '1';
                    else retorno += '0';
                    //NOVAMENTE TEMOS QUE MOVER A POSICAO DE i POREM AGORA ANDAMOS UM VALOR QUE PODE VARIAR
                    //NOVAMENTE VAMOS DEIXAR O i EM CIMA DO ')' PORQUE O ++ DO FOR VAI PULAR ELE
                    i = i + contador;
                }
                else if( funcoes.charAt(i) == '^')
                {
                    //SAME DO OR
                    int contador = 0;
                    int valor=0;
                    while (funcoes.charAt(i + contador) != ')')
                    {
                        contador++ ;
                        if(funcoes.charAt(i + contador) == '0')valor ++;
                    }
                    if (valor > 0) retorno += '0';
                    else retorno += '1';
                    i = i + contador;
                }
            }
        }
        //RETORNAMOS A STRING COM O ULTIMO TERMO MAIS A DIREITA RESOLVIDO
        return retorno;
    }
    public static void main(String[] args) throws Exception {
        String leitura = "";
        leitura = MyIO.readLine();
        while( leitura.charAt(0) != '0')
        {
            // INICIANDO AS VARIAVEIS
            String func = "";
            int contador = 0;
            char A = leitura.charAt(2);
            char B = leitura.charAt(4);
            char C = leitura.charAt(6);

            // CRIANDO UMA STRING QUE CONTENHA APENAS OS VALORES DE A,B,C E A EXPRESSOES
            for (int i = 0; i < leitura.length(); i++) 
            {
                if (leitura.charAt(i) == 'a' && leitura.charAt(i + 1) == 'n')func += '^';
                if (leitura.charAt(i) == 'o' && leitura.charAt(i + 1) == 'r')func += '.';
                if (leitura.charAt(i) == 'n' && leitura.charAt(i + 1) == 'o')func += '!';
                if (leitura.charAt(i) == 'A')func += A;
                if (leitura.charAt(i) == 'B')func += B;
                if (leitura.charAt(i) == 'C')func += C;
                if (leitura.charAt(i) == '(')func += leitura.charAt(i);
                if (leitura.charAt(i) == ')')func += leitura.charAt(i);
            }
            
            // CONTANDO QUANTAS OPERACOES ESTAO SENDO FEITAS NA STRING
            for (int i = 0; i < func.length(); i++)
            {
                if (func.charAt(i) == '.' || func.charAt(i) == '^' || func.charAt(i) == '!')
                    contador++;
            }

            // INICIANDO UM ARRAY DE INT QUE TEM SALVO EM CADA POSICAO A POSICAO DE CADA OPERACAO Ex: [O,5,7,9] A SEGUNDA OPERACAO OCORRE NA POSICAO 5
            int[] posicoes = new int[contador];
            int contador2 = 0;

            //PREENCHENDO O VETOR, TEM QUE SER FEITA DEPOIS POIS NAO SEI CRIAR UM ARRAY DE TAMANHO DINAMICO EM JAVA
            for (int i = 0; i < func.length(); i++) {
                if (func.charAt(i) == '.' || func.charAt(i) == '^' || func.charAt(i) == '!') 
                {
                    posicoes[contador2] = i;
                    contador2++;
                }
            }

            // CHAMADA DA FUNCAO ALGEBRA QUE VAI RESOLVER AS OPERACOES DA ULTIMA PARA A PRIMEIRA        
            for (int i = contador - 1; i >= 0; i--) 
            {
                func = Algebra(posicoes[i], func);
            }

            //RESULTADO
            MyIO.println(func);

            //PREGUICA DE FAZER O WHILE DIREITO 
            leitura = MyIO.readLine();
        }
        
    }
}


