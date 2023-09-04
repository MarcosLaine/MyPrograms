public class AlgebraBooleanaRec {
    public static boolean avaliarExpressao(String expressao, int[] valores, int indice) {
        if (expressao.equals("and")) {
            boolean operando1 = avaliarExpressao("and", valores, indice - 1);
            boolean operando2 = avaliarExpressao("and", valores, indice - 2);
            return operando1 && operando2;
        } else if (expressao.equals("or")) {
            boolean operando1 = avaliarExpressao("or", valores, indice - 1);
            boolean operando2 = avaliarExpressao("or", valores, indice - 2);
            return operando1 || operando2;
        } else if (expressao.equals("not")) {
            boolean operando = avaliarExpressao("not", valores, indice - 1);
            return !operando;
        } else {
            int valor = Integer.parseInt(expressao);
            return valores[valor] == 1;
        }
        
    }

    public static void main(String[] args) {

        while (true) {
            int n = MyIO.readInt();
            if (n == 0) {
                break;
            }

            int[] valores = new int[n];
            for (int i = 0; i < n; i++) {
                valores[i] = MyIO.readInt();
            }

            String entrada = MyIO.readLine();
            boolean resultado = avaliarExpressao(entrada, valores, n - 1);

            if (resultado) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
        }
    }
}
