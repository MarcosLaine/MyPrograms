var registerButtons = document.getElementsByClassName("register-button");
var detailsButtons = document.getElementsByClassName("details-button");
var registrationForm = document.getElementById("registration-form");
var selectedChampionship = document.getElementById("selected-championship");

// Adicionar evento de clique aos botões de inscrição
for (var i = 0; i < registerButtons.length; i++) {
  registerButtons[i].addEventListener("click", function() {
    // Exibir o formulário de inscrição
    registrationForm.classList.remove("hidden");

    // Obter o nome do campeonato selecionado
    var championshipName = this.getAttribute("data-championship");

    // Atualizar o título do formulário com o nome do campeonato
    selectedChampionship.textContent = championshipName;
  });
}

// Adicionar evento de clique aos botões de detalhes
for (var i = 0; i < detailsButtons.length; i++) {
  detailsButtons[i].addEventListener("click", function() {
    // Obter o elemento de detalhes do campeonato correspondente
    var details = this.nextElementSibling;

    // Alternar a visibilidade dos detalhes
    details.classList.toggle("hidden");
  });
}

// Manipular o envio do formulário de inscrição
document.getElementById("registration-form").addEventListener("submit", function(event) {
  event.preventDefault();

  // Obter o valor do campo de entrada
  var teamName = document.getElementById("team-name").value;

  // Criar objeto JSON com os dados de inscrição
  var registrationData = {
    teamName: teamName
  };

  // Enviar os dados para o servidor (substitua a URL com a sua lógica de envio de dados)
  var url = "http://example.com/submit-registration";
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // Resposta do servidor (opcional)
      var response = JSON.parse(xhr.responseText);
      console.log(response);
    }
  };
  xhr.send(JSON.stringify(registrationData));

  // Limpar o campo de entrada após o envio dos dados
  document.getElementById("team-name").value = "";

  // Exibir uma mensagem de confirmação para o usuário (opcional)
  alert("Inscrição realizada com sucesso!");
});