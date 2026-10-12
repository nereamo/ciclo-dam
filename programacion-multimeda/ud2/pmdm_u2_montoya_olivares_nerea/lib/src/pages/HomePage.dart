import 'package:flutter/material.dart';
import 'package:pmdm_u2_montoya_olivares_nerea/src/pages/PersonalPage.dart';
import 'package:pmdm_u2_montoya_olivares_nerea/src/pages/WidgetPage.dart';
import 'package:pmdm_u2_montoya_olivares_nerea/Util/Persona.dart';

class HomePage extends StatefulWidget { //StatefulWidget ->  el contenido acambia
  
  @override
  HomePageState createState() => HomePageState(); //Instancia de estado
}


class HomePageState extends State<HomePage>{

  Persona? persona;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar( //Barra superior con estilo y texto
        backgroundColor: Colors.indigoAccent,
        title: Text(
          'APPMMD', style: TextStyle(color: Colors.white, fontSize: 24),
        ),
        centerTitle: true,
      ),
      body: ListView(
        children: [ //Inicializa los métodos
          ..._listElements(context),
          if (persona != null) //Si el objeto persona no es nulo, muestra el resultado
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                'Persona: ${persona?.name} ${persona?.lastName}', //Concatena Persona, nombre y apellido
                style: TextStyle(fontSize: 20, color: Colors.indigo),
              ),
            ),
        ],
      ),
    );
  }


  //Devuleve lista de Widgets que se muestran en HomePage
  List <Widget> _listElements(BuildContext context){
    return [
      ListTile( //Widget para acceder a PersonalPage
        leading: Icon(Icons.account_box , color: Colors.indigoAccent),
        title: Text('Personal Page'),
        onTap: () async { //Al hacer click, accede a WidgetPage
          Persona updatedPersona = await Navigator.push( //Accede a PersonalPage, espera a que se que los datos esten completos
            context,
            MaterialPageRoute(
              builder: (context) => PersonalPage(persona: persona ?? Persona(name: '', lastName: '', bornDate: '', email: '', password: '')), //Devuelve objeto persona
            ),
          );
          setState(() { //Actualiza info
            persona = updatedPersona;
          });
        },
      ),

      Divider(), //Muestra una linea de separtación entre elementos

      ListTile( //Widget para acceder a WidgetPage
        leading: Icon(Icons.widgets_outlined, color: Colors.indigoAccent),
        title: Text('Widget Page'),
        onTap: () { //Al hacer click, accede a WidgetPage
          Navigator.push( //Accede a WidgetPage
            context, 
            MaterialPageRoute(
              builder: (context) => WidgetPage()
            ),
          );
        },
      ),

      Divider(),
    ];
  }
}