public class ExemploWhile02 {
    public static void main(String[] args) {
        int numero = 1;
        while (numero <= 10) {
            double logaritmo = Math.log10(numero);
            System.out.println("Log10(" + numero + ") = " + logaritmo);
            numero++;
        }
    }
}
