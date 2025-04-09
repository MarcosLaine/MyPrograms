import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

// app principal
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'login and register',
      theme: ThemeData(useMaterial3: true),
      home: const TelaLogin(),
    );
  }
}

// tela de login
class TelaLogin extends StatelessWidget {
  const TelaLogin({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.green,
        foregroundColor: Colors.white,
        title: const Text('login'),
      ),
      body: Center(
        child: Container(
          width: 600,
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextField(decoration: InputDecoration(labelText: "Email")),
                TextField(
                  maxLength: 216,
                  keyboardType: TextInputType.visiblePassword,
                  obscureText: true,
                  decoration: InputDecoration(
                    labelText: "Senha",
                    labelStyle: const TextStyle(fontSize: 16.0),
                    suffixIcon: IconButton(
                      icon: const Icon(Icons.visibility_off, size: 20),
                      onPressed: () {
                        // Since TelaLogin is a StatelessWidget, we need to convert it to StatefulWidget
                        // and define _showPassword as a state variable
                        // For now, we'll just make it non-functional
                        print('Password visibility toggle pressed');
                      },
                    ),
                  ),
                  style: const TextStyle(fontSize: 16.0),
                ),
                ElevatedButton(
                  onPressed: () {
                    print("Botão pressionado");
                  },
                  child: const Text('login'),
                ),
                const SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('Não tem uma conta? '),
                    GestureDetector(
                      onTap: () {
                        Navigator.of(context).push(
                          MaterialPageRoute(
                            builder: (context) => const TelaCadastro(),
                          ),
                        );
                      },
                      child: const Text(
                        'Clique aqui',
                        style: TextStyle(
                          color: Colors.blue,
                          decoration: TextDecoration.underline,
                        ),
                      ),
                    ),
                    const Text(' para se cadastrar'),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

// tela de cadastro
class TelaCadastro extends StatefulWidget {
  const TelaCadastro({super.key});

  @override
  State<TelaCadastro> createState() => _TelaCadastroState();
}

class _TelaCadastroState extends State<TelaCadastro> {
  String _selecionado = "";
  bool _telefone = false;
  bool _Email = false;
  double _fontSize = 16.0;
  bool _showPassword = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.orange,
        foregroundColor: Colors.white,
        title: const Text('cadastro'),
      ),
      body: Center(
        child: SingleChildScrollView(
          child: Container(
            width: 600,
            child: Padding(
              padding: const EdgeInsets.all(20),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  TextField(
                    maxLength: 20,
                    keyboardType: TextInputType.name,
                    decoration: InputDecoration(
                      labelText: "Nome",
                      labelStyle: TextStyle(fontSize: _fontSize),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  const SizedBox(height: 20),
                  TextField(
                    maxLength: 50,
                    keyboardType: TextInputType.emailAddress,
                    decoration: InputDecoration(
                      labelText: "email@email.com",
                      labelStyle: TextStyle(fontSize: _fontSize),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  const SizedBox(height: 20),
                  TextField(
                    maxLength: 11,
                    keyboardType: TextInputType.numberWithOptions(
                      decimal: false,
                      signed: true,
                    ),
                    decoration: InputDecoration(
                      labelText: "(XX) XXXXX-XXXX",
                      labelStyle: TextStyle(fontSize: _fontSize),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  const SizedBox(height: 20),
                  TextField(
                    maxLength: 216,
                    keyboardType: TextInputType.visiblePassword,
                    obscureText: !_showPassword,
                    decoration: InputDecoration(
                      labelText: "Senha",
                      labelStyle: TextStyle(fontSize: _fontSize),
                      suffixIcon: IconButton(
                        icon: Icon(
                          _showPassword
                              ? Icons.visibility
                              : Icons.visibility_off,
                          size: _fontSize + 4,
                        ),
                        onPressed: () {
                          setState(() {
                            _showPassword = !_showPassword;
                          });
                        },
                      ),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Text("Masculino", style: TextStyle(fontSize: _fontSize)),
                      Radio(
                        value: "Masculino",
                        groupValue: _selecionado,
                        onChanged: (String? e) {
                          setState(() {
                            _selecionado = e ?? "";
                          });
                        },
                      ),
                      Text("Feminino", style: TextStyle(fontSize: _fontSize)),
                      Radio(
                        value: "Feminino",
                        groupValue: _selecionado,
                        onChanged: (String? e) {
                          setState(() {
                            _selecionado = e ?? "";
                          });
                        },
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  Text("Notificações?", style: TextStyle(fontSize: _fontSize)),
                  const SizedBox(height: 20),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text(
                            "Telefone",
                            style: TextStyle(fontSize: _fontSize),
                          ),
                          Switch(
                            value: _telefone,
                            onChanged: (bool e) {
                              setState(() {
                                _telefone = e;
                              });
                            },
                          ),
                        ],
                      ),
                      const SizedBox(height: 20),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Email", style: TextStyle(fontSize: _fontSize)),
                          Switch(
                            value: _Email,
                            onChanged: (bool e) {
                              setState(() {
                                _Email = e;
                              });
                            },
                          ),
                        ],
                      ),
                      const SizedBox(height: 20),
                      Text(
                        "Tamanho da Fonte: ${_fontSize.toStringAsFixed(1)}",
                        style: TextStyle(fontSize: _fontSize),
                      ),
                      Container(
                        width: 300,
                        child: Slider(
                          value: _fontSize,
                          min: 12.0,
                          max: 24.0,
                          divisions: 12,
                          label: _fontSize.toStringAsFixed(1),
                          onChanged: (double value) {
                            setState(() {
                              _fontSize = value;
                            });
                          },
                        ),
                      ),
                      const SizedBox(height: 20),
                      ElevatedButton(
                        onPressed: () {
                          print("Botão pressionado");
                        },
                        child: Padding(
                          padding: const EdgeInsets.symmetric(
                            horizontal: 20,
                            vertical: 10,
                          ),
                          child: Text(
                            "Cadastrar",
                            style: TextStyle(fontSize: _fontSize),
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
