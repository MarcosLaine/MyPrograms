public class AlgebraBooleana {
    public static String resolveAlgebra(int index, String operador) {
        String booleanReaolvido = "";

        for (int i = 0; i < operador.length(); i++) {// loop para ler a string
            if (i < index || i > index) {// preenche a string e resolve a expressão da posição "index"
                booleanReaolvido += operador.charAt(i);
            } else if (i == index) {
                if (operador.charAt(i) == '!') {// resolve expressão NOT
                    if (operador.charAt(i + 2) == '0')
                        booleanReaolvido += '1';
                    else
                        booleanReaolvido += '0';
                    i += 3;// pula o parentese "!(0)"
                } else if (operador.charAt(i) == '^') {// resolve expressão AND
                    int contador = 0;
                    int valor = 0;
                    while (operador.charAt(i + contador) != ')') {
                        contador++;// contador para saber quantas posições precisam ser ignoradas para a proxima
                                   // operação

                        if (operador.charAt(i + contador) == '0')
                            valor++;
                    }
                    if (valor > 0)
                        booleanReaolvido += '0';
                    else
                        booleanReaolvido += '1';
                    i = i + contador;// faz com que o "i" ignore o parentese
                } else if (operador.charAt(i) == '.') {// resolve expressão OR

                    int contador = 0;
                    int valor = 0;
                    while (operador.charAt(i + contador) != ')') {
                        contador++;// contador para saber quantas posições precisam ser ignoradas para a proxima
                                   // operação
                        if (operador.charAt(i + contador) == '1')
                            valor++;
                    }
                    if (valor > 0)
                        booleanReaolvido += '1';
                    else
                        booleanReaolvido += '0';
                    i = i + contador;// faz com que o "i" ignore o parentese
                }
            }
        }
        return booleanReaolvido;
    }

    public static void main(String[] args) throws Exception {
        String entrada = "";
        entrada = MyIO.readLine();
        while (entrada.charAt(0) != '0') {

            String operador = "";
            int contador = 0;
            char A = entrada.charAt(2);
            char B = entrada.charAt(4);
            char C = entrada.charAt(6);

            for (int i = 0; i < entrada.length(); i++) { // string sem usar .equals para as entradas
                if (entrada.charAt(i) == 'a' && entrada.charAt(i + 1) == 'n')
                    operador += '^';// conversão de AND para ^
                if (entrada.charAt(i) == 'o' && entrada.charAt(i + 1) == 'r')
                    operador += '.';// conversão de OR para .
                if (entrada.charAt(i) == 'n' && entrada.charAt(i + 1) == 'o')
                    operador += '!';// conversão de NOt para !
                if (entrada.charAt(i) == 'A')
                    operador += A;
                if (entrada.charAt(i) == 'B')
                    operador += B;
                if (entrada.charAt(i) == 'C')
                    operador += C;
                if (entrada.charAt(i) == '(')// pula o parentese (
                    operador += entrada.charAt(i);
                if (entrada.charAt(i) == ')')// pula o parentese )
                    operador += entrada.charAt(i);
            }
            for (int i = 0; i < operador.length(); i++) {// conta as operações feitas
                if (operador.charAt(i) == '.' || operador.charAt(i) == '^' || operador.charAt(i) == '!')
                    contador++;
            }
            int[] posicoes = new int[contador];
            int contador2 = 0;// salva a posição no index de cada operação realizada

            for (int i = 0; i < operador.length(); i++) {// preenche todo o vetor com as operações realizadas
                if (operador.charAt(i) == '.' || operador.charAt(i) == '^' || operador.charAt(i) == '!') {
                    posicoes[contador2] = i;
                    contador2++;
                }
            }

            for (int i = contador - 1; i >= 0; i--) {
                operador = resolveAlgebra(posicoes[i], operador);// chamada da função para resolver de fato a expressão
                                                                 // booleana
            }
            MyIO.println(operador);
            entrada = MyIO.readLine();
        }
    }
}