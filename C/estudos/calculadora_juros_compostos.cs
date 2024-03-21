using System;

class Program {

    static float Calculate(float montante, float tempo, float capital, float taxa) {
        taxa /= 100;
        montante = capital * (float)Math.Pow(1 + taxa, tempo);
        return montante;
    }

    static void Entry(out float montante, out float tempo, out float capital, out float taxa) {
        char resTempo;

        Console.WriteLine("Informe o capital"); // capital inicial
        capital = float.Parse(Console.ReadLine());

        Console.WriteLine("Informe a taxa anual de juros pretendida"); // taxa de juros
        taxa = float.Parse(Console.ReadLine());

        Console.WriteLine("voce quer fazer o calculo em anos ou meses? M para meses ou A para anos"); // pergunta de que modo quer fazer o cálculo
        resTempo = char.Parse(Console.ReadLine());

        do {
            if (resTempo == 'M' || resTempo == 'm') {
                Console.WriteLine("Digite o tempo em meses");
                tempo = float.Parse(Console.ReadLine());
                tempo /= 12; // transforma meses em anos para facilitar o calculo
            } else if (resTempo == 'A' || resTempo == 'a') {
                Console.WriteLine("Digite o tempo em anos");
                tempo = float.Parse(Console.ReadLine());
            }

            if (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm') {
                Console.WriteLine("formato invalido. Informe M ou A");
                resTempo = char.Parse(Console.ReadLine());
            }
        } while (resTempo != 'A' && resTempo != 'a' && resTempo != 'M' && resTempo != 'm');
    }

    static void Main(string[] args) {
        char repetir;
        float montante, tempo, capital, taxa;

        do {
            Entry(out montante, out tempo, out capital, out taxa);
            montante = Calculate(montante, tempo, capital, taxa);
            Console.WriteLine($"O valor do montante é: {montante:F3}");
            Console.WriteLine("Quer repetir a operacao? (S/N)");
            repetir = char.Parse(Console.ReadLine());
        } while (repetir == 's' || repetir == 'S');
    }
}
