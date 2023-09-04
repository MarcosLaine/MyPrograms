public class SequenciaRecusiva {
    public static void main(String[] args) {
        int n = 5; // Insira o valor de n desejado
        int resultado = calcularSequencia(n);
        System.out.println("T(" + n + ") = " + resultado);
    }

    public static int calcularSequencia(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 2;
        } else {
            return calcularSequencia(n - 1) * calcularSequencia(n - 2) - calcularSequencia(n - 1);
        }
    }
}
