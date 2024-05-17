file:///C:/Users/kino1/Desktop/Programacao/MyPrograms/Java/Estudos/ArvoreRodar.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.3\scala3-library_3-3.3.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 744
uri: file:///C:/Users/kino1/Desktop/Programacao/MyPrograms/Java/Estudos/ArvoreRodar.java
text:
```scala
class NoArvore {
    char letra;   // Caracter que o nó representa
    NoArvore esq, dir;  // Ponteiros para os nós filhos esquerdo e direito
    No2 raiz;     // Raiz da sub-árvore de palavras

    // Construtor para inicializar o nó com uma letra
    NoArvore(char letra) {
        this.letra = letra;
        this.esq = null;
        this.dir = null;
        this.raiz = null;
    }
}

class No2 {
    String palavra;  // Palavra armazenada no nó
    No2 esq, dir;    // Ponteiros para os nós filhos esquerdo e direito

    // Construtor para inicializar o nó com uma palavra
    No2(String palavra) {
        this.palavra = palavra;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreArvore {
    NoArvore raiz;  // Raiz@@ da árvore principal

    // Método principal que conta as palavras que correspondem ao padrão
    public int contarPalavras(String padrao) {
        if (padrao == null || padrao.isEmpty()) {
            return 0;
        }
        return contarPalavrasNaArvore(raiz, padrao);
    }

    // Método recursivo para percorrer a árvore de 'NoArvore'
    private int contarPalavrasNaArvore(NoArvore no, String padrao) {
        if (no == null) {
            return 0;
        }

        int count = 0;

        if (no.letra == padrao.charAt(0)) {
            count += contarPalavrasNaSubArvore(no.raiz, padrao);
        }

        count += contarPalavrasNaArvore(no.esq, padrao);
        count += contarPalavrasNaArvore(no.dir, padrao);

        return count;
    }

    // Método recursivo para percorrer a sub-árvore de 'No2' e contar as palavras que correspondem ao padrão
    private int contarPalavrasNaSubArvore(No2 no2, String padrao) {
        if (no2 == null) {
            return 0;
        }

        int count = 0;

        if (no2.palavra.length() == padrao.length()) {
            count++;
        }

        count += contarPalavrasNaSubArvore(no2.esq, padrao);
        count += contarPalavrasNaSubArvore(no2.dir, padrao);

        return count;
    }

    // Método para inserir uma palavra na árvore
    public void inserirPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        raiz = inserirPalavraNaArvore(raiz, palavra, 0);
    }

    private NoArvore inserirPalavraNaArvore(NoArvore no, String palavra, int indice) {
        if (indice >= palavra.length()) {
            return no;
        }

        char letra = palavra.charAt(indice);
        if (no == null) {
            no = new NoArvore(letra);
        }

        if (letra < no.letra) {
            no.esq = inserirPalavraNaArvore(no.esq, palavra, indice);
        } else if (letra > no.letra) {
            no.dir = inserirPalavraNaArvore(no.dir, palavra, indice);
        } else {
            if (indice == palavra.length() - 1) {
                if (no.raiz == null) {
                    no.raiz = new No2(palavra);
                } else {
                    inserirPalavraNaSubArvore(no.raiz, palavra);
                }
            } else {
                no.dir = inserirPalavraNaArvore(no.dir, palavra, indice + 1);
            }
        }

        return no;
    }

    private void inserirPalavraNaSubArvore(No2 no2, String palavra) {
        if (no2 == null) {
            no2 = new No2(palavra);
            return;
        }

        if (palavra.compareTo(no2.palavra) < 0) {
            if (no2.esq == null) {
                no2.esq = new No2(palavra);
            } else {
                inserirPalavraNaSubArvore(no2.esq, palavra);
            }
        } else {
            if (no2.dir == null) {
                no2.dir = new No2(palavra);
            } else {
                inserirPalavraNaSubArvore(no2.dir, palavra);
            }
        }
    }
}

public class ArvoreRodar {
    public static void main(String[] args) {
        // Cria uma nova instância da árvore
        ArvoreArvore arvore = new ArvoreArvore();

        // Insere algumas palavras na árvore
        arvore.inserirPalavra("PROVA");
        arvore.inserirPalavra("PROVAS");
        arvore.inserirPalavra("TESTE");
        arvore.inserirPalavra("EXAME");
        arvore.inserirPalavra("PROFESSOR");
        arvore.inserirPalavra("PRODUTO");
        arvore.inserirPalavra("PROJETOS");
        arvore.inserirPalavra("PROJETO");
        arvore.inserirPalavra("PROJETOS");
        arvore.inserirPalavra("PRIMO");
        arvore.inserirPalavra("PRIMA");

        // Define o padrão a ser procurado
        String padrao = "PROVA";

        // Conta as palavras na árvore que correspondem ao padrão
        int count = arvore.contarPalavras(padrao);

        // Imprime o resultado
        System.out.println("Número de palavras que começam com " + padrao.charAt(0) + " e têm " + padrao.length() + " caracteres: " + count);
    }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:368)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator