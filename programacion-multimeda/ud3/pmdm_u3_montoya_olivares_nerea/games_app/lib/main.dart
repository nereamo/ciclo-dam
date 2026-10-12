import 'package:flutter/material.dart';
import 'package:games_app/screens/screens.dart';
import 'package:provider/provider.dart';
import 'package:games_app/providers/games_provider.dart';

//Clase que inicializa la aplicación

void main() => runApp(AppState());

class AppState extends StatelessWidget{ //widget que configura los proveedores de estado para la aplicación
  
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: ( _ ) => GamesProvider(), 
          lazy: false
        )
      ],
      child: MyApp(),
    );
  }
}

class MyApp extends StatelessWidget { //widget principal

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Games',
      initialRoute: 'home',
      routes: {
        'home': (BuildContext context) => HomeScreen(), //Ruta inicial
      },
      theme: ThemeData.light()
          .copyWith(appBarTheme: const AppBarTheme(color: Colors.black)),
    );
  }
}
