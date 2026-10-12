import 'package:flutter/material.dart';
import 'package:productes_app/providers/login_provider.dart';
import 'package:provider/provider.dart';

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  //Permite validar y gyuardar los datos de un formulario (contiene _key.currentState!.validate(); y _key.currentState!.save();)
  GlobalKey<FormState> _key = GlobalKey();

  RegExp emailRegExp = new RegExp(r'^\w+[\w-\.]*\@\w+((-\w+)|(\w*))\.[a-z]{2,3}$');
  RegExp contRegExp = new RegExp(r'^([1-zA-Z0-1@.\s]{1,255})$');
  String? _correu;
  String? _passwd;
  String missatge = '';
  String errorCode = '';
  bool _isChecked = false;

  late var loginProvider;

  //Interfaz que muestra un formulario para iniciar sesión en la aplicación
  @override
  Widget build(BuildContext context) {
    loginProvider = Provider.of<LoginProvider>(context);

    return Scaffold(
      body: Container(
        decoration: BoxDecoration(color: Colors.black),
        child: Center(
          child: Padding(
            padding: EdgeInsets.all(20),
            child: Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20),
              ),
              elevation: 8,
              child: Padding(
                padding: EdgeInsets.all(70),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Icon(Icons.key, size: 80, color: Colors.deepPurpleAccent),
                    SizedBox(height: 20),
                    Text(
                      "Iniciar sesión",
                      style: TextStyle(
                        fontSize: 22,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                    SizedBox(height: 20),
                    _loginForm(), //Llamada al método que valida el formulario
                    loginProvider.isLogin
                        ? CheckboxListTile(
                            value: _isChecked,
                            onChanged: (value) {
                              _isChecked = value!;
                              setState(() {});
                            },
                            title: Text('Recordar'),
                            controlAffinity: ListTileControlAffinity.leading,
                          )
                        : SizedBox(height: 40),
                    _LoginButton(), //Llamada al botón del iniciar sesión
                    _registerButton(), //Llamada al botón d eregistrar
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  //Widget del formulario para iniciar sesión
  Widget _loginForm() {
    return Form(
      key: _key,
      child: Column(
        children: [
          TextFormField(
            onSaved: (text) => _correu = text, //Guardar valor email
            initialValue: '',
            validator: (text) {
              if (text!.length == 0) {
                return "Debe introducir el email";
              } else if (!emailRegExp.hasMatch(text)) {
                return "Formato de email incorrecto";
              }
              return null;
            },
            keyboardType: TextInputType.emailAddress,
            decoration: InputDecoration(
              labelText: "Email",
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
              ),
            ),
          ),
          SizedBox(height: 10),
          TextFormField(
            onSaved: (text) => _passwd = text, //Guardar valor password
            initialValue: '',
            validator: (text) {
              if (text!.length == 0) {
                return "Debe introducir una contraseña";
              } else if (text.length <= 5) {
                return "La contraseña debe tener más de 5 carácteres";
              } else if (!contRegExp.hasMatch(text)) {
                return "Contraseña incorrecta";
              }
              return null;
            },
            keyboardType: TextInputType.text,
            decoration: InputDecoration(
              labelText: "Password",
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
              ),
            ),
            obscureText: true,
          ),
          SizedBox(height: 20),
        ],
      ),
    );
  }

  //Widget del botton de iniciar sesion
  Widget _LoginButton() {
    return Consumer<LoginProvider>(
      builder: (context, loginProvider, child) => ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: Colors.deepPurpleAccent,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          padding: EdgeInsets.symmetric(vertical: 15, horizontal: 50),
        ),
        onPressed: () => _loginRequest(),
        child: loginProvider.isLoading
            ? CircularProgressIndicator(color: Colors.white)
            : Text("Login",
                style: TextStyle(fontSize: 16, color: Colors.black)),
      ),
    );
  }

  //Valida el formulario de inicio de sesión, si no son correctos los datos introducidos mostrará un mensaje de error en un snckBar
  _loginRequest() async {
    final loginProvider = Provider.of<LoginProvider>(context, listen: false);

    if (_key.currentState!.validate()) {
      _key.currentState!.save();
      // Aquí se realiza la petición de login a la API o similar
      await loginProvider.loginOrRegister(_correu!, _passwd!);
      missatge = 'Gracias \n $_correu \n $_passwd';
      if (loginProvider.accesGranted) {
        Navigator.of(context).pushReplacementNamed('home', arguments: missatge);
      } else {
        String errorMsg = loginProvider.errorMessage.isNotEmpty
            ? loginProvider.errorMessage
            : 'Error desconocido';
        // Mostrar mensaje de error con SnackBar
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
          content: Text(errorMsg),
        ));
      }
    }
  }

  //Widget del botón 'Register', no dirige a la interfaz para registrar un nuevo usuario
  Widget _registerButton() {
    return TextButton(
      onPressed: () => Navigator.pushNamed(context, 'register'),
      child: Text("Register", style: TextStyle(color: Colors.black)),
    );
  }
}
