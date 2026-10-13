import 'package:examen_final_montoya/screens/lanzamiento_details_screen.dart';
import 'package:examen_final_montoya/services/lanzamiento_Service.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

//Class incial donde muestra una lista de lanzamientos de tipo StatelessWidget porque no tendra ningun cambio
class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final lanzService = Provider.of<LanzamientoService>(context);

    if (lanzService.isLoading) {
      return Scaffold(
        appBar: AppBar(title: Text('Lanzamientos'), backgroundColor: Colors.black), //Barra superior
        body: Center(child: CircularProgressIndicator()),
      );
    }

    final lanzamientos = lanzService.lanzamientoList;

    return Scaffold(
      appBar: AppBar(
        title: Text('Lanzamientos'),
        titleTextStyle: TextStyle(color: Colors.white),
        backgroundColor: Colors.black,
        actions: [
          IconButton(
            icon: Icon(Icons.logout),
            tooltip: 'Cerrar sesion',
            color: Colors.white,
            onPressed: () {
              Navigator.pushReplacementNamed(context, 'login');
            },
          ),
        ],
      ),
      body: ListView.builder(
        itemCount: lanzamientos.length,
        itemBuilder: (context, index) {
          final lanzamiento = lanzamientos[index];
          return ListTile(
            title: Text(lanzamiento.name),
            subtitle: Text(lanzamiento.net.toLocal().toString()),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => LanzamientoDetailsScreen(lanzamiento: lanzamiento)),
              );
            },
          );
        },
      ),
    );
  }
}
