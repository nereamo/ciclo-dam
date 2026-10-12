import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:productes_app/firebase_options.dart';
import 'package:productes_app/providers/login_provider.dart';
import 'package:productes_app/providers/register_provider.dart';
import 'package:productes_app/screens/register_screen.dart';
import 'package:productes_app/screens/screens.dart';
import 'package:productes_app/services/products_service.dart';
import 'package:provider/provider.dart';

//Inicia la aplicación con el FireBase
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
    await Firebase.initializeApp(
      name: 'ProductesApp',
      options: DefaultFirebaseOptions.currentPlatform,
    );
  runApp(AppState());
}

//Permite registrar providers
class AppState extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => LoginProvider()),
        ChangeNotifierProvider(create: (_) => ProductsService()), 
        ChangeNotifierProvider(create: (_) => RegisterProvider()),
      ],
      child: MyApp(),
    );
  }
}

//Configuración de la navegación entre pantallas.
class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Productes App',
      //Cambiar el valor de initialroute para cambiar entre login y home.
      initialRoute: 'login',
      routes: {
        'login': (_) => LoginScreen(),
        'register' : (_) => RegisterScreen(),
        'home': (_) => HomeScreen(),
        'product': (_) => ProductScreen(),
      },
      theme: ThemeData.light().copyWith(
        scaffoldBackgroundColor: Colors.grey[300],
      ),
    );
  }
}
