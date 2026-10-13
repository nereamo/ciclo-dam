import 'dart:convert';
import 'dart:io';
import 'package:examen_final_montoya/models/result.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

//Class encargada de gestionar los lanzamientos, extiende de ChangeNotifier para poder notificar a los widgets los cambios.
class LanzamientoService extends ChangeNotifier {
  //Variables
  final String _baseUrl = 'https://ll.thespacedevs.com/2.2.0/launch/upcoming/?mode=list';

  final List<Result> lanzamientoList = []; //Lista de lanzamientos
  Result? selectedLanzamiento; //Objeto Plats
  File? newPicture;

  bool isLoading = true;
  bool isSaving = false;

  //Constructor con llamada a loadProduct() para cargar los lanzamientos desde la API
  LanzamientoService() {
    this.loadProduct();
  }

  //Carga lanzamientos de la API
  Future loadProduct() async {
    isLoading = true; //Si está 'cargando' mostrará el spinner
    notifyListeners();

    final url = Uri.parse('https://ll.thespacedevs.com/2.2.0/launch/upcoming/?mode=list');
    final resp = await http.get(url);

    if (resp.statusCode == 200) {
    final Map<String, dynamic> productsMap = json.decode(resp.body);
    final List<dynamic> results = productsMap['results'];

    lanzamientoList.clear();

    for (var lanzamientoJson in results) {
      final tempProduct = Result.fromMap(lanzamientoJson);
      lanzamientoList.add(tempProduct);
    }
  }else {
    print('Error al cargar lanzamientos: ${resp.statusCode}');
  }
    isLoading = false; //Cuando la petición termine, no estará 'cargando'
    notifyListeners();
  }


  //Actualiza la imagen seleccionada
  void updateSelectedImage(String path) {
    this.newPicture = File.fromUri(Uri(path: path));

    if (this.selectedLanzamiento != null) {
      this.selectedLanzamiento!.image = path;
    }

    notifyListeners();
  }

  //Método encargado de subir una imagen a Cloudinary
  Future<String?> uploadImage() async {
    if (newPicture == null) return null;

    this.isSaving = true;
    notifyListeners();

    final url = Uri.parse(
      'https://api.cloudinary.com/v1_1/nmo-pau-casesnoves/image/upload?upload_preset=nmopcases',
    );

    final imageUploadRequest = http.MultipartRequest('POST', url);
    final file = await http.MultipartFile.fromPath('file', newPicture!.path);
    imageUploadRequest.files.add(file);

    final streamResponse = await imageUploadRequest.send();
    final resp = await http.Response.fromStream(streamResponse);

    if (resp.statusCode != 200 && resp.statusCode != 201) {
      print('Hay un error!');
      print(resp.body);
      return null;
    }

    this.newPicture = null;

    final decodedData = json.decode(resp.body);
    return decodedData['secure_url'];
  }
}
