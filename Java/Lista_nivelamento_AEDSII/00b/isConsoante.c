boolean isConsoante(String s, int n) {
    if (n >= s.length()) {
        return true; // A string foi percorrida sem encontrar vogais, portanto, é composta apenas por consoantes.
    }
    
    char c = s.charAt(n);

    if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) { // Verifica se o caractere é uma letra
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return false; // Encontrou uma vogal, portanto não é uma consoante
        } else {
            return isConsoante(s, n + 1); // Verifica o próximo caractere recursivamente
        }
    } else {
        return false; // Caractere não é uma letra, portanto não é uma consoante
    }
}
