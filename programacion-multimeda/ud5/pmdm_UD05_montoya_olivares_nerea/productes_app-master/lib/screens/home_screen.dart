import 'package:flutter/material.dart';
import 'package:productes_app/models/products.dart';
import 'package:productes_app/providers/login_provider.dart';
import 'package:productes_app/screens/loading_screen.dart';
import 'package:productes_app/services/services.dart';
import 'package:productes_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

//Clase que muestra la interfaz con todos los productos
class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final productsService = Provider.of<ProductsService>(context);

    if (productsService.isLoading)
      return LoadingScreen(); //Si isLoading de ProductsService es true, devuelve LoadingScreen
    return Scaffold(
      appBar: AppBar(
        title: Text('Productos', style: TextStyle(color: Colors.white)),
        backgroundColor: Colors.black,
        actions: [
          IconButton(
            icon: Icon(Icons.logout, color: Colors.white),
            onPressed: () {
              //Llamamos al método logout del LoginProvider
              final loginProvider = Provider.of<LoginProvider>(context, listen: false);
              loginProvider.logout();
              //Volvemos a la pantalla de 'Login'
              Navigator.of(context).pushReplacementNamed('login');
            },
          ),
        ],
      ),
      body: ListView.builder(
        itemCount:
            productsService.products.length, //Cargará todos los productos
        itemBuilder: (BuildContext context, int index) => GestureDetector(
          child: ProductCard(
            product: productsService.products[index],
          ),
          onTap: () {
            productsService.newPicture = null;
            productsService.selectedProduct =
                productsService.products[index].copy();
            Navigator.of(context).pushNamed('product');
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add, color: Colors.white),
        backgroundColor: Colors.black,
        onPressed: () {
          productsService.newPicture = null;
          //Inicializa un producto nuevo sin datos
          productsService.selectedProduct = Product(
            available: false,
            name: '',
            price: 0,
          );
          Navigator.of(context).pushNamed('product');
        },
      ),
    );
  }
}
