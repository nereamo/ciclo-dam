import 'package:flutter/material.dart';

class WidgetPage extends StatefulWidget{  //StatefulWidget ->  el contenido acambia

  @override
  WidgetPageState createState() => WidgetPageState(); //Instancia de estado
}

class WidgetPageState extends State<WidgetPage>{

  bool isVolumeOn = false;  //Variables para Widget _segmentedButton
  bool isVolumeOff = false;
  bool isVibrationOn = false;

  Color _themeColor = Colors.white;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar( //Barra superior con estilo y texto
        backgroundColor: Colors.indigoAccent,
        title: Text('All Widgets', style: TextStyle(color: Colors.white, fontSize: 24)),
        centerTitle: true,
        leading: Container(),
      ),
      body: Container(
        color: _themeColor,
        padding: const EdgeInsets.all(8.0), //Espacio al rededor de los elementos
        child: Column(
          children: [ //Contiene los Widgets
            _segmentedButton(),
            SizedBox(height: 20),
            _dropDownMenu()
          ],
        ),
      ),

      floatingActionButton: FloatingActionButton.small( //Botón que devuelve a la pagina HomePage
        backgroundColor: Colors.indigoAccent,
        child: Icon(Icons.arrow_forward, color: Colors.white),
        onPressed:(){
          Navigator.pop(context);
        },
      ),
    );
  }

  //Widget que permite cambiar el color de fondo
  Widget _dropDownMenu(){
    return DropdownMenu(
      enableFilter: true, //Al ecribir, filtra las palabras
      label: Text('Select Theme Color'),
      helperText: 'Change de color of your app',
      width: 500.0,
      dropdownMenuEntries: <DropdownMenuEntry<Color>>[ //Entradas de color asignables
        DropdownMenuEntry(value: Colors.red, label: 'Red'),
        DropdownMenuEntry(value: Colors.lightGreenAccent, label: 'Light Green'),
        DropdownMenuEntry(value: Colors.yellow, label: 'Yellow'),
        DropdownMenuEntry(value: Colors.purpleAccent, label: 'Purple'),
        DropdownMenuEntry(value: Colors.orangeAccent, label: 'Orange'),
        DropdownMenuEntry(value: Colors.lightBlueAccent, label: 'Light Blue')
      ],
      onSelected: (color){  //Al seleccionar un color, cambia le valor de la variable _themeColor
        if(color != null){
          setState(() {
            _themeColor = color;
          });
        }
      },
    );
  }

  //Widget que permite seleccionar mas de un botón
  Widget _segmentedButton(){
    return Row(  
      mainAxisAlignment: MainAxisAlignment.center,  //Alineación central
      children: [
        ElevatedButton( //Botón ON
          onPressed: (){
            setState(() { //Cambia el estado de ON en on/off
              isVolumeOn = !isVolumeOn;
              if (isVolumeOn) {
                isVolumeOff = false;  //Desactiva volumeOff si se activa volumeOn
              }
            });
          }, 
          style: ElevatedButton.styleFrom(  //Estilo del botón
            backgroundColor:isVolumeOn? Colors.indigo: Colors.blueGrey,
            foregroundColor: Colors.white,
          ),
          child: Row(
            children: [
              Icon(Icons.volume_up, size: 16, color: Colors.white), //Icono de volume On 
            ],
          )
        ),
        
        SizedBox(width: 8), //Tamaño del boton
        
        ElevatedButton( //Botón OFF
          onPressed: (){
            setState(() { //Cambia el estado de OFF en on/off
              isVolumeOff = !isVolumeOff;
              if(isVolumeOff){
                isVolumeOn = false; //Desactiva volumeOn si se activa volumeOff
              }
            });
          },
          style: ElevatedButton.styleFrom(  //Estilo del botón
            backgroundColor: isVolumeOff?  Colors.indigo: Colors.blueGrey,
            foregroundColor: Colors.white
          ), 
          child: Row(
            children: [
              Icon(Icons.volume_off, size: 16, color: Colors.white), //Icono de volume On 
            ],
          )
        ),
          
        SizedBox(width: 8), //Tamaño del boton
          
        ElevatedButton( //Botón vibración
          onPressed: (){
            setState(() { //Cambia el estado vibración On/Off
              isVibrationOn = !isVibrationOn;
            });
          }, 
          style: ElevatedButton.styleFrom(  //Estilo del botón
            backgroundColor:isVibrationOn? Colors.indigo: Colors.blueGrey,
            foregroundColor: Colors.white
          ),
          child: Row(
            children: [
              Icon(Icons.vibration, size: 16, color: Colors.white),  //Icono de volume On 
            ],
          )
        ),
      ],
    );
   }
}

