import 'package:flutter/material.dart';

class UiProvider extends ChangeNotifier{

  int _selectedMenuOpt = 1;

  int get getSelectedMenuOpt{
    return this._selectedMenuOpt;
  }

  set setSelectedMenuOpt(int index){
    this._selectedMenuOpt = index;
    notifyListeners();
  }

}