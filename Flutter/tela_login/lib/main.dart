import 'package:flutter/material.dart';
import 'package:tela_login/screens/login_screen.dart';
import 'package:tela_login/screens/list_screen.dart';

void main() {
  runApp(
    MaterialApp(
      title: 'Tela de Login',
      theme: ThemeData(
        
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const LoginScreen(),
        '/lista': (context) => const ListScreen(),
      },
    ),
  );
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  @override
    void setState(VoidCallback fn) {
      // TODO: implement setState
      super.setState(fn);
    }

  @override
  Widget build(BuildContext context) {
      
    return Scaffold(
      appBar: AppBar(
        
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,

        title: Text(widget.title),
      ),
      body: Center(
        
        child: Column(
         
          mainAxisAlignment: MainAxisAlignment.center,
        ),
      ),
    );
  }
}
