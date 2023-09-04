
var notaAluno = [30,20,15];
var avaliacoes = ["prova 1", "prova 2", "trabalho final"];
function soma(x,y) {
    var total = x+y;
    return total;
}



function avaliaAluno() {
    
    var somaAluno = 0;
    for (let x = 0; x < avaliacoes.length; x++) {
        console.log(`a nota para ${avaliacoes[x]} foi ${notaAluno[x]}`);
        somaAluno = somaAluno + notaAluno[x];
    }
    if(somaAluno >= 60){
        console.log('aluno aprovado');
    } else{
        console.log('aluno reprovado');
    }
}