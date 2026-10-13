import 'package:examen_final_montoya/providers/theme_provider.dart';
import 'package:examen_final_montoya/screens/home_screen.dart';
import 'package:examen_final_montoya/screens/lanzamiento_details_screen.dart';
import 'package:examen_final_montoya/services/lanzamiento_Service.dart';
import 'package:flutter/material.dart';

import 'package:provider/provider.dart';

void main() {
  runApp( //Arrana aplicación
    MultiProvider( //permite varios providers
      providers: [
        ChangeNotifierProvider(create: (_) => LanzamientoService()),
        ChangeNotifierProvider(create: (_) => ThemeProvider()), //permite que ThemeProvider sea accesible desde cualquier widget
      ],
      child: MyApp(),
    ),
  );
}

//Configuración de la navegación entre pantallas.  
//Widget principal de la app, todo es fijo (StatelessWidget)
class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final themeProvider = Provider.of<ThemeProvider>(context); //Ecucha ThemeProvider 

    return MaterialApp(
      debugShowCheckedModeBanner: false, //Oculta cartelito DEBUG
      title: 'Lanzamiento App',
      theme: ThemeData.light(), //Define tema claro por defecto
      darkTheme: ThemeData.dark(),
      themeMode: themeProvider.isDarkTheme ? ThemeMode.dark : ThemeMode.light, //Usa Themeprovider para saber que theme aplicar
      //Cambiar el valor de initialroute para cambiar entre login y home.
      initialRoute: 'home', //Primera pantalla en mostrarse
      routes: {
        'home': (_) => HomeScreen(),
      },
    );
  }
}
