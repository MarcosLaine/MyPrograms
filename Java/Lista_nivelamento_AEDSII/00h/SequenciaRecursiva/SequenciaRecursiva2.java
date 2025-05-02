public class SequenciaRecursiva2 {
    public static void main(String[] args) {
        int n = 5; // Insira o valor de n desejado
        int resultado = calcularSequencia(n);
        System.out.println("T(" + n + ") = " + resultado);
    }

    public static int calcularSequencia(int n) {
        if (n == 0) {
            return 1;
        } else {
            return calcularSequencia(n - 1) * calcularSequencia(n - 1);
        }
    }
}
