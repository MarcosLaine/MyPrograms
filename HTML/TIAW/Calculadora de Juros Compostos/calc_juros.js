// Seleciona os elementos do HTML
const inputValorInicial = document.getElementById("valor");
const inputTempo = document.getElementById("tempo");
const inputTempoAnos = document.getElementById("tempoAnos");
const inputTempoMeses = document.getElementById("tempoMeses");
const inputTaxa = document.getElementById("taxa");
const inputTaxaAnos = document.getElementById("taxaAnos");
const inputTaxaMeses = document.getElementById("taxaMeses");
const inputAportes = document.getElementById("aportes");
const spanValorFinal = document.getElementById("valorFinalSpan");
const spanJurosFinal = document.getElementById("jurosFinalSpan");
const btnEnviar = document.getElementById("enviar");

// Define os valores padrão
let tempoSelecionado = "anos";
let taxaSelecionada = "anos";

// Adiciona o evento de clique no botão "enviar"
btnEnviar.addEventListener("click", calcular);

// Adiciona o evento de mudança no radio button "tempo"
inputTempoAnos.addEventListener("change", () => {
    tempoSelecionado = "anos";
});

inputTempoMeses.addEventListener("change", () => {
    tempoSelecionado = "meses";
});

// Adiciona o evento de mudança no radio button "taxa"
inputTaxaAnos.addEventListener("change", () => {
    taxaSelecionada = "anos";
});

inputTaxaMeses.addEventListener("change", () => {
    taxaSelecionada = "meses";
});

// Define a função que calcula os juros compostos
function calcular() {
    let valorInicial = Number(inputValorInicial.value);
    let taxa = Number(inputTaxa.value);
    let tempo = Number(inputTempo.value);
    let aportes = Number(inputAportes.value);
    let valorFinal = 0;
    let jurosAcumulado = 0;

    if (tempoSelecionado === "anos") {
        taxa = taxa / 100;
        tempo = tempo * 12;
    } else {
        taxa = taxa / 100 / 12;
    }

    for (let i = 0; i < tempo; i++) {
        jurosAcumulado += (valorInicial + aportes * i) * taxa;
        valorFinal = valorInicial + jurosAcumulado;
    }

    spanValorFinal.textContent = valorFinal.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' });
    spanJurosFinal.textContent = jurosAcumulado.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' });
}