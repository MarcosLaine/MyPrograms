public class AlgebraBooleanaRec {

    public static int[] resolveExpressao(String[] partes, int index) {
        int[] resultado = new int[2];

        if (partes[index].equals("0")) {
            resultado[0] = 0;
            resultado[1] = 1;
        } else if (partes[index].equals("1")) {
            resultado[0] = 1;
            resultado[1] = 0;
        } else if (partes[index].equals("not")) {
            int[] subResultado = resolveExpressao(partes, index + 1);
            resultado[0] = subResultado[1];
            resultado[1] = subResultado[0];
        } else if (partes[index].equals("and")) {
            int[] subResultado1 = resolveExpressao(partes, index + 1);
            int[] subResultado2 = resolveExpressao(partes, subResultado1[1] + 1);
            resultado[0] = subResultado1[0] & subResultado2[0];
            resultado[1] = subResultado2[1];
        } else if (partes[index].equals("or")) {
            int[] subResultado1 = resolveExpressao(partes, index + 1);
            int[] subResultado2 = resolveExpressao(partes, subResultado1[1] + 1);
            resultado[0] = subResultado1[0] | subResultado2[0];
            resultado[1] = subResultado2[1];
        } else if (partes[index].equals("(")) {
            int[] subResultado = resolveExpressao(partes, index + 1);
            resultado[0] = subResultado[0];
            resultado[1] = subResultado[1];
        } else if (partes[index].equals(")")) {
            resultado[0] = 0;
            resultado[1] = 1;
        }

        return resultado;
    }

    public static void main(String[] args) {
        String entrada = "";
        entrada = MyIO.readLine();
        while (!entrada.equals("0")) {
            String[] partes = entrada.split(" ");
            int A = Integer.parseInt(partes[0]);
            int B = Integer.parseInt(partes[1]);
            int C = Integer.parseInt(partes[2]);
            String expressao = partes[3];

            int[] resultado = resolveExpressao(expressao.split(""), 0);
            
            int resultadoFinal = (A == 1 && B == 0) ? resultado[0] : resultado[1];
            
            MyIO.println(resultadoFinal);
            
            entrada = MyIO.readLine();
        }
    }
}
