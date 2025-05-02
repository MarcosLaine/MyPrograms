import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Atividade Cadastro',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: MyHomePage(title: 'Cadastro'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({super.key, required this.title});
  final String title;
  final String nome = "", senha = "", email = "", telefone = "";
  final DateTime data = DateTime.now();
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _selecionado = "";
  bool _telefone = false;
  bool _Email = false;
  double _fontSize = 16.0;
  bool _showPassword = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),  
      ),
      drawer: Drawer(),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
            backgroundColor: Colors.green
          ),
          BottomNavigationBarItem(icon: Icon(Icons.person),
            label: "Profile",
            backgroundColor: Colors.green
          ),
        ]
      ),
      body: Center(
        child: SingleChildScrollView(
          child: Container(
            width: 600, // Increased width for better layout
            child: Padding(padding: const EdgeInsets.all(20), // Reduced padding
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
                  const SizedBox(
                    height: 20,
                  ),
                  TextField(
                    maxLength: 50,
                    keyboardType: TextInputType.emailAddress,
                    decoration: InputDecoration(
                      labelText: "email@email.com",
                      labelStyle: TextStyle(fontSize: _fontSize),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  TextField(
                    maxLength: 11,
                    keyboardType: TextInputType.numberWithOptions(decimal: false, signed: true),
                    decoration: InputDecoration(
                      labelText: "(XX) XXXXX-XXXX",
                      labelStyle: TextStyle(fontSize: _fontSize),
                    ),
                    style: TextStyle(fontSize: _fontSize),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  TextField(
                    maxLength: 216,
                    keyboardType: TextInputType.visiblePassword,
                    obscureText: !_showPassword,
                    decoration: InputDecoration(
                      labelText: "Senha",
                      labelStyle: TextStyle(fontSize: _fontSize),
                      suffixIcon: IconButton(
                        icon: Icon(
                          _showPassword ? Icons.visibility : Icons.visibility_off,
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
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly, // Better spacing for radio buttons
                    children: [
                      Text("Masculino", style: TextStyle(fontSize: _fontSize)),
                      Radio(value: "Masculino", groupValue: _selecionado, onChanged: (String? e) {print(e); setState(() {_selecionado = e ?? "";}); }),
                      Text("Feminino", style: TextStyle(fontSize: _fontSize)),
                      Radio(value: "Feminino", groupValue: _selecionado, onChanged: (String? e) {print(e); setState(() {_selecionado = e ?? "";});}),
                    ],
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  Text("Notificações?", style: TextStyle(fontSize: _fontSize)),
                  const SizedBox(
                    height: 20,
                  ),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Telefone", style: TextStyle(fontSize: _fontSize)),
                          Switch(value: _telefone, onChanged: (bool e) {setState(() {_telefone = e;});}),
                        ],
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Email", style: TextStyle(fontSize: _fontSize)),
                          Switch(value: _Email, onChanged: (bool e) {setState(() {_Email = e;});}),
                        ],
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      Text("Tamanho da Fonte: ${_fontSize.toStringAsFixed(1)}", style: TextStyle(fontSize: _fontSize)),
                      Container(
                        width: 300, // Constrain slider width
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
                      const SizedBox(
                        height: 20,
                      ),
                      ElevatedButton(
                        onPressed: () {
                          print("Botão pressionado");
                        },
                        child: Padding(
                          padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                          child: Text(
                            "Cadastrar",
                            style: TextStyle(fontSize: _fontSize),
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              )
            ),
          ),
        ) 
      ),
    );
  }
}
