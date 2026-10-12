import 'package:flutter/material.dart';
import 'package:productes_app/providers/register_provider.dart';
import 'package:provider/provider.dart';

class RegisterScreen extends StatefulWidget {
  @override
  _RegisterScreenState createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {

  //Permite validar y gyuardar los datos de un formulario (contiene _key.currentState!.validate(); y _key.currentState!.save();)
  final GlobalKey<FormState> _key = GlobalKey();  

  //final FirebaseAuth _auth = FirebaseAuth.instance; //Autenticar usuarios con Firebase

  RegExp emailRegExp =
      new RegExp(r'^\w+[\w-\.]*\@\w+((-\w+)|(\w*))\.[a-z]{2,3}$');
  RegExp contRegExp = new RegExp(r'^([1-zA-Z0-1@.\s]{1,255})$');
  String? _correu;
  String? _passwd;

  late var registerProvider;

  //Interfaz donde se muestra un formulario de registro
  @override
  Widget build(BuildContext context) {

    registerProvider = Provider.of<RegisterProvider>(context);

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
                    Icon(Icons.person_add,size: 80, color: Colors.deepPurpleAccent),
                    SizedBox(height: 20),
                    Text(
                      "Registro",
                      style: TextStyle(
                        fontSize: 22,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                    SizedBox(height: 20),
                    _registerForm(),
                    _registerButton(),
                    _loginButton(),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  //Widget del formulario para registrar un nuevo usuario
  Widget _registerForm() {
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
            obscureText: true,
            decoration: InputDecoration(
              labelText: "Password",
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
              ),
            ),
          ),
          SizedBox(height: 20),
        ],
      ),
    );
  }

  //Widget del botón 'Register'
  Widget _registerButton() {
    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        backgroundColor: Colors.deepPurpleAccent,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        padding: EdgeInsets.symmetric(vertical: 15, horizontal: 50),
      ),
      onPressed: register,
      child:
          Text("Register", style: TextStyle(fontSize: 16, color: Colors.black)),
    );
  }

  //Widget del botón 'Login' permite volver a la pantalla de login
  Widget _loginButton() {
    return TextButton(
      onPressed: () => Navigator.pushNamed(context, 'login'),
      child: Text("Login", style: TextStyle(color: Colors.black)),
    );
  }

  //Método que registra un usuario en Firebase Authentication utilizando correo electrónico y contraseña.
  void register() async {
    if (_key.currentState!.validate()) {
      _key.currentState!.save();
      try {
        await registerProvider.loginOrRegister(_correu!, _passwd!);
        _showMessage("Se ha registrado correctamente. Inicia sesión", isSuccess: true);
      } catch (e) {
        _showMessage("Error: $e");
      }
    }
  }

  //Muestra un mensaje de éxito si se ha regitrado un uevo usuario correctamente
  void _showMessage(String message, {bool isSuccess = false}) {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text(isSuccess ? "Éxito" : "Error"),
          content: Text(message),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
                if (isSuccess)
                  Navigator.pop(context); //Volver a la pantalla de login si fue exitoso
              },
              child: Text("OK"),
            ),
          ],
        );
      },
    );
  }
}
