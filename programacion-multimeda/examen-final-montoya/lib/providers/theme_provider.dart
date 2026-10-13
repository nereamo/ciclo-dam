import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';


//Class encargada de gestionar el tema claro y oscuro.
//Extiende de ChangeNotifier para notifiacr cambios a widgets
class ThemeProvider extends ChangeNotifier {
  bool _isDarkTheme = false; //Guarda si el tema es oscuro (true) 

  bool get isDarkTheme => _isDarkTheme; //Getter de acceso 

  //Constructor
  ThemeProvider() {
    _loadThemePreference();
  }

  //Cambia el Theme
  void toggleTheme(bool isOn) {
    _isDarkTheme = isOn;
    _saveThemePreference(isOn); //Gurarda la opcion marcada
    notifyListeners(); //Notifica los cambios de color
  }

  //Obtiene la preferencia guardada, si no se ha hecho elección, usa light theme por defecto
  void _loadThemePreference() async {
    final prefs = await SharedPreferences.getInstance();
    _isDarkTheme = prefs.getBool('isDarkTheme') ?? false;
    notifyListeners();
  }

  //Guarda el valor de la variable _isDarkTheme
  void _saveThemePreference(bool value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool('isDarkTheme', value);
  }
}