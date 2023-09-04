
// DB

function leDados (){
    let strDados = localStorage.getItem('db');
    let objDados = {};
  
    if (strDados) {
      objDados = JSON.parse (strDados);
    }
    else {
      objDados = { cadastros: [
        {nome: "GIGABYTE B450M", sobrenome: "1 tb",    email: "rtx 1050ti", senha: "Ryzen 5 5600G" },
        {nome: "DDR3 AM3",  sobrenome: "2 tb",   email: "rtx 4090",    senha: "INTEL CORE I9-13900K" },
        {nome: "GIGABYTE B550M", sobrenome: "1 tb", email: "rtx 3060",          senha: "Ryzen 5 5600X" }
                               ]}
    }
  
    return objDados;
  }
  
  function salvaDados (dados) {
    localStorage.setItem ('db', JSON.stringify (dados));
  }
  
  function incluirCadastro (){
    // Ler dados do localStorage
    let objDados = leDados();
    
    // Incluir um novo Cadastro
    let strName = document.getElementById ('Placa-Mãe').value;
    let strLastname = document.getElementById ('Armazenamento').value;
    let strEmail = document.getElementById('Placa-de-Vídeo').value;
    let strPassword = document.getElementById('Processador').value;
    
    let novoCadastro = {
        nome: strName,
        sobrenome: strLastname,
        email: strEmail,
        senha: strPassword
    };
    objDados.cadastros.push (novoCadastro);
  
    // Salvar os dados no localStorage Novamente
    //if(passwordtwoValue === passwordValue) {
      salvaDados(objDados);
  
    // Atualiza os dados da tela
    imprimeDados();
  }
  
  function imprimeDados () {
    let tela = document.getElementById('tela');
    let strHtml = '';
    let objDados = leDados ();
  
    for (i = 0; i < objDados.cadastros.length; i++) {
      strHtml += `<p>${objDados.cadastros[i].nome} - ${objDados.cadastros[i].sobrenome} - ${objDados.cadastros[i].email} - ${objDados.cadastros[i].senha}</p>`
    }
  
    tela.innerHTML = strHtml;
  }
  
  // Config Botões
  document.getElementById ('btnCarregaDados').addEventListener ('click', imprimeDados);
  document.getElementById ('btn-submit').addEventListener ('click', incluirCadastro);