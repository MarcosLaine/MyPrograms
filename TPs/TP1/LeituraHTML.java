import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class LeituraHTML {
    public static void main(String[] args) throws Exception {
        boolean ver = true;
        while (ver) {
            String nome = MyIO.readLine();
            if (nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M')
                ver = false;
            if (ver) {
                String url = MyIO.readLine();
                resposta(url, nome);
            }
        }
    }

    static String carregar(String strURL) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
        try {
            url = new URL(strURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
            br.close();
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return resp;
    }

    static void resposta(String url, String nomedapagina) {
        String dados = carregar(url);
        String[] nomesDasFuncoes = { "\u0061", "\u0065", "\u0069", "\u006F", "\u0075", "\u00E1", "\u00E9", "\u00ED",
                "\u00F3", "\u00FA", "\u00E0", "\u00E8", "\u00EC", "\u00F2", "\u00F9", "\u00E3", "\u00F5", "\u00E2",
                "\u00EA", "\u00EE", "\u00F4", "\u00FB" };
        for (int i = 0; i < nomesDasFuncoes.length; i++) {
            int resultado = contarCaracteres(dados, nomesDasFuncoes[i]);
            System.out.print(nomesDasFuncoes[i] + "(" + resultado + ") ");
        }
        System.out.print("consoantes(" + Consoante(dados) + ") " + "<br>(" + Br(dados) + ") " + "<table>("
                + Table(dados) + ") " + nomedapagina + "\n");
    }

    static int contarCaracteres(String frase, String caractere) {
        int cont = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == caractere.charAt(0))
                cont++;
        }
        return cont;
    }

    static int Consoante(String frase) {
        int cont = 0;
        for (int i = 0; i < frase.length(); i++) {
            if ((frase.charAt(i) > 96 && frase.charAt(i) < 123))
                if (!(frase.charAt(i) == 'A' || frase.charAt(i) == 'a' || frase.charAt(i) == 'O'
                        || frase.charAt(i) == 'o' || frase.charAt(i) == 'i' || frase.charAt(i) == 'I'
                        || frase.charAt(i) == 'E' || frase.charAt(i) == 'e' || frase.charAt(i) == 'u'
                        || frase.charAt(i) == 'U'))
                    cont++;
        }
        return cont;
    }

    static int Br(String frase) {
        int cont = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == '<')
                if (frase.charAt(i + 1) == 'b' && frase.charAt(i + 2) == 'r' && frase.charAt(i + 3) == '>')
                    cont++;
        }
        return cont;
    }

    static int Table(String frase) {
        int cont = 0;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == '<')
                if (frase.charAt(i + 1) == 't' && frase.charAt(i + 2) == 'a' && frase.charAt(i + 3) == 'b'
                        && frase.charAt(i + 4) == 'l' && frase.charAt(i + 5) == 'e' && frase.charAt(i + 6) == '>')
                    cont++;
        }
        return cont;
    }
}