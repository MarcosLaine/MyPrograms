class Is {
    static boolean verificaSoVogal(String str) {
        for (int i = 0; i < str.length(); i++) {
            char letra = str.toLowerCase().charAt(i);
            if (letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u') {
                return false;
            }
        }
        return str.length() > 0; // Retornar falso se a string for vazia
    }

    static boolean verificaSoConsoante(String str) {
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char letra = str.charAt(i);
            if (!Character.isLetter(letra) || letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o'
                    || letra == 'u') {
                return false;
            }
        }
        return str.length() > 0; // Retornar falso se a string for vazia
    }

    static boolean verificaSoInteiro(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return str.length() > 0; // Retornar falso se a string for vazia
    }

    static boolean verificaSoReal(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && !".,-".contains("" + str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String palavra1 = MyIO.readLine();
        while (!palavra1.equals("FIM")) {
            if (verificaSoVogal(palavra1))
                System.out.printf("SIM ");
            else
                System.out.printf("NAO ");

            if (verificaSoConsoante(palavra1))
                System.out.printf("SIM ");
            else
                System.out.printf("NAO ");

            if (verificaSoInteiro(palavra1))
                System.out.printf("SIM ");
            else
                System.out.printf("NAO ");

            if(verificaSoReal(palavra1))
                System.out.printf("SIM%n");
            else
                System.out.printf("NAO%n");
            palavra1 = MyIO.readLine();
        }
    }
}
