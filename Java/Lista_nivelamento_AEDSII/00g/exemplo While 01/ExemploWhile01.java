public class ExemploWhile01 {
    public static void main(String[] args) {
        int contador = 0; // Contador para controlar quantos números pares foram encontrados
        int numero = 0; // Número a ser verificado

        while (contador < 10) {
            if (numero % 2 == 0) { // Verifica se o número é par
                System.out.println(numero);
                contador++;
            }
            numero++;
        }
    }
}
