import 'package:examen_final_montoya/models/result.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

//Clase que permite ver los detalles de un lanzamiento, de tipo stateful porque cambia la interfaz
class LanzamientoDetailsScreen extends StatefulWidget {
  final Result lanzamiento;

  const LanzamientoDetailsScreen({required this.lanzamiento});

  @override
  State<LanzamientoDetailsScreen> createState() =>
      _LanzamientoDetailsScreenState();
}

class _LanzamientoDetailsScreenState extends State<LanzamientoDetailsScreen> {
  bool isFavorite = false;

   @override
  void initState() {
    super.initState();
    _loadFavoriteStatus(); 
  }

  @override
  Widget build(BuildContext context) {
    final lanzamiento = widget.lanzamiento;
    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles del Lanzamiento', style: TextStyle(color: Colors.white),),
        backgroundColor: Colors.black,
        iconTheme: IconThemeData(color: Colors.white),
        actions: [
          IconButton(
            icon: Icon(
              isFavorite ? Icons.star : Icons.star_border,
              color: Colors.white,
            ),
            onPressed: _toggleFavorite,
          ),
        ],
      ),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            _buildImagePreview(lanzamiento.image!),
            SizedBox(height: 20),
            _buildInfoRow('Nombre:', lanzamiento.name),
            SizedBox(height: 10),
            _buildInfoRow('Descripcion:', lanzamiento.mission),
            SizedBox(height: 10),
            _buildInfoRow('Agencia:', lanzamiento.lspName),
            SizedBox(height: 10),
            _buildInfoRow('Fecha:', lanzamiento.net.toLocal().toString()),
            SizedBox(height: 10),
            _buildInfoRow('Estado:', lanzamiento.status.description),
            SizedBox(height: 10),
          ],
        ),
      ),
    );
  }

  //Campo de Imagen
  Widget _buildImagePreview(String url) {
    if (url.isNotEmpty && url.startsWith('http')) {
      return Image.network(url);
    } else {
      return Image.asset('assets/no-image.jpg');
    }
  }

  //Campo de Informacion
  Widget _buildInfoRow(String label, String value) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text('$label', style: TextStyle(fontWeight: FontWeight.bold)),
        Expanded(child: Text(value ?? 'No disponible')),
      ],
    );
  }

  //Carga los marcados en favorito
  Future<void> _loadFavoriteStatus() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      isFavorite = prefs.getBool('favorite_${widget.lanzamiento.id}') ?? false;
    });
  }

  //Permite guardar favoritos
  Future<void> _toggleFavorite() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      isFavorite = !isFavorite;
      prefs.setBool('favorite_${widget.lanzamiento.id}', isFavorite);
    });
  }
}
