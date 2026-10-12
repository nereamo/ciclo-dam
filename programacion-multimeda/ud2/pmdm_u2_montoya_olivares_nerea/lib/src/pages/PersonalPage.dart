import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:pmdm_u2_montoya_olivares_nerea/Util/Persona.dart';

class PersonalPage extends StatefulWidget { //StatefulWidget ->  el contenido acambia

  final Persona persona;  //Objeto Persona
  PersonalPage({required this.persona}); //Contructor

  @override
  PersonalPageState createState() => PersonalPageState(); //Instancia de estado
}

class PersonalPageState extends State<PersonalPage>{

  String _nombre =''; //Variables
  String _apellido ='';
  String _nacimiento ='';
  String _email ='';
  String _password ='';

  @override
  void initState() {  //Inicia las variables a partir de objeto persona
    super.initState();
    _nombre = widget.persona.name;
    _apellido = widget.persona.lastName;
    _nacimiento = widget.persona.bornDate;
    _email = widget.persona.email;
    _password = widget.persona.password;
  }

  final TextEditingController _inputFieldDataController = TextEditingController();  //Controla la fecha del calendario

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar( //Barra superior con estilo y texto
        backgroundColor: Colors.indigoAccent,
        title: Text('Montoya' , style: TextStyle(color: Colors.white, fontSize: 24)),
        centerTitle: true,
        leading: Container(),
      ),
      
      body: ListView( 
        padding: EdgeInsets.symmetric(horizontal: 10.0, vertical: 20.0),
        children: [ //Inicializa los métodos 
          _crearNombre(),
          Divider(),
          _crearApellido(),
          Divider(),
          _crearFecha(context),
          Divider(),
          _crearEmail(),
          Divider(),
          _crearPassword()
        ],
      ),
      
      floatingActionButton: _buttons(context),  //Botones flotantes
    );
  }


  //Método botones, el botón 'save' devuleve el objeto Persona con los datos asignados
  //El botón return, vuelve a HomePage
  Widget _buttons(BuildContext context){ 
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: <Widget>[
        SizedBox(width: 30.0),
        FloatingActionButton( //Botón save
          backgroundColor: Colors.indigoAccent,
          child: Text('Save', style: TextStyle(color: Colors.white,fontSize: 16)),
          onPressed: (){  //Al pulsar guarad los datos y vuleve a HomePage
            Navigator.pop(context, Persona(
              name: _nombre,
              lastName: _apellido,
              bornDate: _nacimiento,
              email: _email,
              password: _password,
            ));
          },
        ),
        Expanded(child: SizedBox()),

        FloatingActionButton( //Botón return
          backgroundColor: Colors.indigoAccent,
          child: Text('Return', style: TextStyle(color: Colors.white,fontSize: 16)),
          onPressed: (){
            Navigator.pop(context);
          },
        ),
        SizedBox(width: 5.0),
      ],
    );
  }

  //Método para asignar nombre
  Widget _crearNombre(){
    return TextField(
      textCapitalization: TextCapitalization.sentences, //La 1a letra se convierte a mayúscula
      decoration: InputDecoration(  //Estilo visual del campo de txt
        counter: Text('Letras: ${_nombre.length}'),
        hintText: 'Nombre',
        labelText: 'Nombre',
        helperText: 'Introduce el nombre',
        suffixIcon: Icon(Icons.accessibility, color: Colors.indigoAccent), 
        icon: Icon(Icons.account_circle, color: Colors.indigoAccent), 
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: Colors.indigoAccent, width: 1.0), 
          borderRadius: BorderRadius.circular(20.0), 
        )
      ),
      onChanged: (valor){ //Cada vez que al valor cambia, es actualizado
        _nombre = valor;
      },
    );
  }

  //Método para asignar apellido
  Widget _crearApellido(){
    return TextField(
      textCapitalization: TextCapitalization.sentences, //La 1a letra se convierte a mayúscula
      decoration: InputDecoration(  //Estilo visual del campo de txt
        counter: Text('Letras: ${_apellido.length}'),
        hintText: 'Apellido',
        labelText: 'Apellido',
        helperText: 'Introduce el apellido',
        suffixIcon: Icon(Icons.accessibility, color: Colors.indigoAccent),
        icon: Icon(Icons.account_circle, color: Colors.indigoAccent,),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: Colors.indigoAccent, width: 1.0), // Borde cuando no está en foco
          borderRadius: BorderRadius.circular(20.0),
        )
      ),
      onChanged: (valor){ //Cada vez que al valor cambia, es actualizado
        _apellido = valor;
      },
    );
  }
  
  //Método para asignar fecha de nacimiento
  Widget _crearFecha(BuildContext context){
    return TextField(
      enableInteractiveSelection: false, //No permite seleccionar el txt
      controller: _inputFieldDataController, //El campo se actualiza con la fecha seleccionada
      decoration: InputDecoration(  //Personalización
        hintText: 'Fecha de nacimiento',
        labelText: 'Fecha de nacimiento',
        icon: Icon(Icons.calendar_month, color: Colors.indigoAccent),
        suffixIcon: Icon(Icons.perm_contact_calendar, color: Colors.indigoAccent),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: Colors.indigoAccent, width: 1.0), // Borde cuando no está en foco
          borderRadius: BorderRadius.circular(20.0),  //Forma del borde
        ),
      ),
      onTap: (){ //Al hacer click muestra el calendario
        FocusScope.of(context).requestFocus( FocusNode()); //Desactiva la función txt
        _seleccionarFecha (context); //Muestra el calendario
      },
    );
  }

  void _seleccionarFecha(BuildContext context) async{
    DateTime? picked = await showDatePicker(  //Muestra calendario esperando a la elección
      context: context,
      locale: Locale('es', 'ES'), //Idioma
      initialDate: DateTime.now(), //Fecha actual
      firstDate: DateTime(1600, 1, 1),
      lastDate: DateTime.now() //Fecha máxima seleccionable
    );
    if(picked != null){ 
      setState((){
        _nacimiento = DateFormat('dd/MM/yyyy').format(picked); //Loa fecha seleccionada es convertida a txt
        _inputFieldDataController.text = _nacimiento;
      });
    }
  }

  //Método para asignar email
  Widget _crearEmail(){
    return TextField(
      textCapitalization: TextCapitalization.sentences, //La 1a letra se convierte a mayúscula
      decoration: InputDecoration(  //Estilo visual del campo de txt
        hintText: 'Email',
        labelText: 'Email',
        helperText: 'Introduce el email',
        icon: Icon(Icons.email, color: Colors.indigoAccent),  //Tipo de Icon
        suffixIcon: Icon(Icons.alternate_email, color: Colors.indigoAccent),//Color Icon
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: Colors.indigoAccent, width: 1.0), //Borde cuando no está en foco
          borderRadius: BorderRadius.circular(20.0),  //Forma del borde
        )
      ),
      onChanged: (valor) => setState(() { //Cada vez que al valor cambia, es actualizado
        _email = valor;
      })
    );
  }

  Widget _crearPassword(){
    return TextField(
      obscureText: true, //Oculta la contraseña
      decoration: InputDecoration(  //Estilo visual del campo de txt
        hintText: 'Password', 
        labelText: 'Password',  
        helperText: 'Introduce la contraseña',
        suffixIcon: Icon(Icons.lock_open, color: Colors.indigoAccent), //Tipo de Icon
        icon: Icon(Icons.lock, color: Colors.indigoAccent), //Color Icon
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: Colors.indigoAccent, width: 1.0), //Borde cuando no está en foco
          borderRadius: BorderRadius.circular(20.0), //Forma del borde
        )
      ),
      onChanged: (valor) => setState(() { //Cada vez que al valor cambia, es actualizado
        _password = valor;
      })
    );
  }
}

