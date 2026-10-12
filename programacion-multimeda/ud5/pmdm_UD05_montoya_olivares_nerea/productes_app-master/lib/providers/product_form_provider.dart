import 'package:flutter/material.dart';
import 'package:productes_app/models/products.dart';

//Clase que notifica los cambios en el formulario
class ProductFormProvider extends ChangeNotifier {

  GlobalKey<FormState> formKey = new GlobalKey<FormState>();

  Product tempProduct;  //Producto temporal, debe ser una copia

  //Constructor
  ProductFormProvider(
    this.tempProduct
  );

  //Devuelve bool si el formulario es correcto o no.
  bool isValidForm(){
    
    print(tempProduct.name);
    print(tempProduct.price);
    print(tempProduct.available);
    return formKey.currentState?.validate() ?? false;
  }

  //Método para notificar a los listeners que el valor del bool Available ha cambiado.
  updateAvailability(bool value){
    print(value);

    this.tempProduct.available = value;
    notifyListeners();
  }
}