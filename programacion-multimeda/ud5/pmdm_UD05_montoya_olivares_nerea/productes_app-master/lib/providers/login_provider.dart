import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

//Clase que maneja la autenticación de usuarios usando Firebase Authentication
class LoginProvider extends ChangeNotifier {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  UserCredential? userLogged;
  User? user;

  bool isLogin = true;
  bool isRegister = false;
  bool isLoading = false;

  bool accesGranted = false;
  String errorMessage = '';

  static var loginProvider;

  LoginProvider();

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
      errorMessage = getMessageFromErrorCode(error);
      notifyListeners();
    }
    isLoading = false;
    accesGranted = user != null;
    notifyListeners();
  }

  //Método que se usa al cerrar sesión en 'HomeScreen', cierra la sesión de fireBase
  Future<void> logout() async {
    await _auth.signOut();
    accesGranted = false;  
    user = null;  
    notifyListeners(); 
  }

  bool get isLoginOrRegister {
    return isLogin || isRegister;
  }
  
}

//Convierte códigos de error de Firebase en mensajes legibles para el usuario.
String getMessageFromErrorCode(error) {
  switch (error.code) {
    case "ERROR_EMAIL_ALREADY_IN_USE":
    case "account-exists-with-different-credential":
    case "email-already-in-use":
      return "Email already used. Go to login page.";
    case "ERROR_WRONG_PASSWORD":
    case "wrong-password":
      return "Wrong email/password combination.";
    case "ERROR_USER_NOT_FOUND":
    case "user-not-found":
      return "No user found with this email.";
    case "ERROR_USER_DISABLED":
    case "user-disabled":
      return "User disabled.";
    case "ERROR_TOO_MANY_REQUESTS":
    case "operation-not-allowed":
      return "Too many requests to log into this account.";
    case "ERROR_OPERATION_NOT_ALLOWED":
    case "operation-not-allowed":
      return "Server error, please try again later.";
    case "ERROR_INVALID_EMAIL":
    case "invalid-email":
      return "Email address is invalid.";
    case "INVALID_LOGIN_CREDENTIALS":
      return "Invalid credentials.";
    default:
      return "Login failed. Please try again.";
  }
}
