import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

//Clase que maneja la autenticación de usuarios usando Firebase Authentication
class RegisterProvider extends ChangeNotifier {
  final FirebaseAuth _auth = FirebaseAuth.instance;

  String email = '';
  String password = '';
  UserCredential? userLogged;
  User? user;

  bool isLogin = false;
  bool isRegister = true;
  bool isLoading = false; //Indicador de carga

  bool accesGranted = false;
  String errorMessage = '';

  RegisterProvider();

  bool get isLoginOrRegister {
    return isLogin || isRegister;
  }

  //Determina si es login o registro
  loginOrRegister(String email, String password) async {
    isLoading = true;
    notifyListeners();
    try {
      if (isRegister) {
        userLogged = await _auth.createUserWithEmailAndPassword(
            email: email, password: password);
      } else if (isLogin) {
        userLogged = await _auth.signInWithEmailAndPassword(
            email: email, password: password);
      }
      user = userLogged!.user;
    } catch (error) {
      //print(error.toString());
      errorMessage = getMessageFromErrorCodeRegister(error);
      notifyListeners();
    }
    isLoading = false;
    accesGranted = user != null;
    notifyListeners();
  }
}

//Convierte códigos de error de Firebase en mensajes legibles para el usuario.
String getMessageFromErrorCodeRegister(error) {
  switch (error.code) {
    case "ERROR_EMAIL_ALREADY_IN_USE":
    case "account-exists-with-different-credential":
    case "email-already-in-use":
      return "El email ya está en uso.";
    case "ERROR_WEAK_PASSWORD":
    case "weak-password":
      return "La contraseña es demasiado débil.";
    case "ERROR_INVALID_EMAIL":
    case "invalid-email":
      return "El email ingresado no es válido.";
    default:
      return "Error desconocido, por favor intente de nuevo.";
  }
}
