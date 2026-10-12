import 'package:flutter/material.dart';
import 'package:pmdm_u2_montoya_olivares_nerea/src/pages/HomePage.dart';
import 'package:flutter_localizations/flutter_localizations.dart';


void main() { //Ejecuta app
  runApp(MyApp());
}

class MyApp extends StatelessWidget{ //StatelessWidget -> contenido que no cambia

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate, //Localización componentes de Material
        GlobalWidgetsLocalizations.delegate,  //Localización componentes de Widget
        GlobalCupertinoLocalizations.delegate,  //Localización componentes de Widget iOS
      ],

      supportedLocales: [ //Idiomas soportados por app
        const Locale('en', 'US'), // Inglés (Estados Unidos)
        const Locale('es', 'ES'), // Español (España)
        const Locale('ca', 'ES'), // Catalán (España)
      ],

      locale: const Locale('es', 'ES'), //Idioma predeterminado
      debugShowCheckedModeBanner: false,  //Quita el banner
      
      home: Center( 
        child: HomePage(), //El HomePage es la pagina principal
      ),
    );
  }

}