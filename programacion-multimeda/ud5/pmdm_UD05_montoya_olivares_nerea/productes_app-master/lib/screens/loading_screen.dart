import 'package:flutter/material.dart';

//Clase que extiende de StatelessWidgte (no tiene estado y el contenido no cambia)
class LoadingScreen extends StatelessWidget {

  //Muestra la pantalla de carga
  @override
  Widget build(BuildContext context){
    return Scaffold( //Estructura base de la pantalla
      appBar: AppBar( //Barra superior
        title: Text('Productos', style: TextStyle(color: Colors.white)),
        backgroundColor: Colors.indigo,
      ),
      body: Center( //Contenido de la pantalla
        child: CircularProgressIndicator( //Indicador carga circular 'cargando producto', utiliza el boolan isLoading de ProductsService
          color: Colors.indigo,
        ), 
        )
    );
  }
}