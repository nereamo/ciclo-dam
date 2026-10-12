import 'package:flutter/material.dart';

import '../models/models.dart';

//Clase que extiende de StatelessWidgte (no tiene estado y el contenido no cambia)
class ProductCard extends StatelessWidget {
  const ProductCard({Key? key, required this.product}) : super(key: key);
  final Product product;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: 20),
      child: Container(
        margin: EdgeInsets.only(top: 30, bottom: 50),
        width: double.infinity,
        height: 400,
        decoration: _cardBorders(),
        child: Stack(
          alignment: Alignment.bottomLeft,
          children: [ //Elemento mostrados en la pantalla
            _BackgroudWidget(url: product.picture), //Imagen
            _ProductDetails(name: product.name, subtitle: product.id!), //Detalles 
            Positioned(top: 0, right: 0, child: _PriceTag(price: product.price,)), //Precio
            //TODO: Mostrar de forma condicional depenent de si el producte està disponible o no
            if(!product.available)
            Positioned(top: 0, left: 0, child: _Availability()), //Estado disponible/reservado
          ],
        ),
      ),
    );
  }

  BoxDecoration _cardBorders() => BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(25),
        boxShadow: [
          BoxShadow(
            color: Colors.black12,
            offset: Offset(0, 7),
            blurRadius: 10,
          ),
        ],
      );
}

//Llamamos a la url del producto para obtener la imagen
class _BackgroudWidget extends StatelessWidget {
  final String? url;
  const _BackgroudWidget({Key? key, required this.url}) : super(key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(25),
      child: Container(
        width: double.infinity,
        height: 400,
        child: url == null //Excepción
        ? Image( //Si no hay una imagen disponible, muestra imagen por defecto
          image: AssetImage('assets/no-image.png'),
          fit: BoxFit.cover, //Rellena todo el espacio
          )
        :FadeInImage(
          placeholder: AssetImage('assets/jar-loading.gif'),
          image: NetworkImage(url!),
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}

//Llamamos al nombre del producto
class _ProductDetails extends StatelessWidget {
  final String name, subtitle;

  const _ProductDetails({Key? key, required this.name, required this.subtitle}) : super(key: key);
  

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(right: 50),
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 15, vertical: 15),
        width: double.infinity,
        height: 80,
        decoration: _buildBoxDecoration(),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              name,
              style: TextStyle(
                  fontSize: 20,
                  color: Colors.white,
                  fontWeight: FontWeight.bold),
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            ),
            Text(
              subtitle,
              style: TextStyle(fontSize: 10, color: Colors.white),
            ),
          ],
        ),
      ),
    );
  }

  BoxDecoration _buildBoxDecoration() => BoxDecoration(
        color: Colors.black,
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(25),
          topRight: Radius.circular(25),
        ),
      );
}

//Llamamos al precio del producto
class _PriceTag extends StatelessWidget {
  final double price;

  const _PriceTag({Key? key, required this.price}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: FittedBox(
        fit: BoxFit.contain,
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10),
          child: Text(
            '$price', //Al no ser de tipo String, debe ponerse $ y llamar a la variable
            style: TextStyle(fontSize: 20, color: Colors.white),
          ),
        ),
      ),
      width: 100,
      height: 70,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: Colors.black,
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(25),
          topRight: Radius.circular(25),
        ),
      ),
    );
  }
}

//Mostrar si el producto está reservado o no
class _Availability extends StatelessWidget {
  const _Availability({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: FittedBox(
        fit: BoxFit.contain,
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10),
          child: Text(
            'Reservado',
            style: TextStyle(fontSize: 20, color: Colors.white),
          ),
        ),
      ),
      width: 100,
      height: 70,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: Colors.red[300],
        borderRadius: BorderRadius.only(
          bottomRight: Radius.circular(25),
          topLeft: Radius.circular(25),
        ),
      ),
    );
  }
}
