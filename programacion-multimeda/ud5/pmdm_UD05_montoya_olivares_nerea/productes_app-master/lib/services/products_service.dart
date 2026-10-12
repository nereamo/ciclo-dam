import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import '../models/models.dart';
import 'package:http/http.dart' as http;

class ProductsService extends ChangeNotifier {
  //Variables
  final String _baseUrl =
      "flutter-app-productos-d61c2-default-rtdb.europe-west1.firebasedatabase.app";
  final List<Product> products = [];
  late Product selectedProduct; //Objeto producto
  File? newPicture;

  bool isLoading = true;
  bool isSaving = false;

  //Constructor
  ProductsService() {
    this.loadProducts();
  }

  //Método de petición de los productos de la api
  Future loadProducts() async {
    isLoading = true; //Si está 'cargando' mostrará el spinner
    notifyListeners();

    final url = Uri.https(_baseUrl, 'products.json');
    final resp = await http.get(url);

    final Map<String, dynamic> productsMap = json.decode(resp.body);

    //ForEach (key = id del producto, value = producto)
    productsMap.forEach((key, value) {
      final tempProduct = Product.fromMap(value);
      tempProduct.id = key;
      products.add(tempProduct);
    });

    isLoading = false; //Cuando la petición termine, no estará 'cargando'
    notifyListeners();
  }

  //Método para crear o actualizar un producto
  Future saveOrCreateProduct(Product product) async {
    isSaving = true;
    notifyListeners();

    if (product.id == null) {
      await createProduct(product);
    } else {
      await updateProduct(product);
    }

    isSaving = false;
    notifyListeners();
  }

  //Actualizar producto por su id
  Future<String> updateProduct(Product product) async {
    final url = Uri.https(_baseUrl,'products/${product.id}.json'); //${product.id} -> Acceder al id.
    final resp = await http.put(url, body: product.toJson());
    final decodedData = resp.body;
    print(decodedData);

    //Compara la lista con el producto
    final index = this.products.indexWhere((element) => element.id == product.id);
    this.products[index] = product;

    return product.id!;
  }

  //Crear producto
  Future<String> createProduct(Product product) async {
    final url = Uri.https(_baseUrl,'products.json');
    final resp = await http.post(url, body: product.toJson());
    final decodedData = json.decode(resp.body);
    product.id = decodedData['name'];
    this.products.add(product);

    return product.id!;
  }

  //Método para actualizar una imagen seleccionada
  void updateSelectedImage(String path){
    this.newPicture = File.fromUri(Uri(path: path));
    this.selectedProduct.picture = path;

    notifyListeners();
  }

  //Método encargado de subir una imagen a Cloudinary
  Future<String?> uploadImage() async{
    if(newPicture == null) return null;

    this.isSaving = true;
    notifyListeners();

    final url = Uri.parse('https://api.cloudinary.com/v1_1/nmo-pau-casesnoves/image/upload?upload_preset=nmopcases');

    final imageUploadRequest = http.MultipartRequest('POST', url);

    final file = await http.MultipartFile.fromPath('file', newPicture!.path);

    imageUploadRequest.files.add(file);

    final streamResponse = await imageUploadRequest.send();
    final resp = await http.Response.fromStream(streamResponse);

    if(resp.statusCode != 200 && resp.statusCode != 201){
      print('Hay un error!');
      print(resp.body);
      return null;
    }

    this.newPicture = null;

    final decodedData = json.decode(resp.body);
    return decodedData['secure_url'];
  }
}
