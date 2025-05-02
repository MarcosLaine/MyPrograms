import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

// App principal
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'App com duas telas',
      theme: ThemeData(
        useMaterial3: true,
      ),
      home: const PrimeiraTela(),
    );
  }
}

// Primeira tela
class PrimeiraTela extends StatelessWidget {
  const PrimeiraTela({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.green,
        foregroundColor: Colors.white,
        title: const Text('Primeira Tela'),
      ),
      body: Center(
        child: FilledButton(
          child: const Text('Ir para segunda tela'),
          onPressed: () {
            Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => const SegundaTela(),
              ),
            );
          },
        ),
      ),
    );
  }
}

// Segunda tela
class SegundaTela extends StatelessWidget {
  const SegundaTela({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.orange,
        foregroundColor: Colors.white,
        title: const Text('Segunda Tela'),
      ),
      body: const Center(
        child: Text('Esta é a segunda tela'),
      ),
    );
  }
}