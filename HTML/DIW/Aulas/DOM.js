var elem = document.getElementById('btnHello');
elem.addEventListener('click', function() {
    var divTela = document.getElementById("tela");
    divTela.innerHTML = "Olá mundo!";
}, false);