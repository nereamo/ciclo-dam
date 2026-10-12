import 'dart:convert';

//Clase producto
class Product {
    bool available;
    String name;
    String? picture;
    double price;

    String? id;

   Product({
        required this.available,
        required this.name,
        this.picture,
        required this.price,
        this.id
    });

    //Conversión de JSON a un objeto de tipo 'Product'
    factory Product.fromJson(String str) => Product.fromMap(json.decode(str));

    //Conversión de objeto de tipo 'Product' a JSON
    String toJson() => json.encode(toMap());

    //Crea objeto 'Product' desde un Map
    factory Product.fromMap(Map<String, dynamic> json) => Product(
        available: json["available"],
        name: json["name"],
        picture: json["picture"],
        price: json["price"].toDouble(),
    );

    //Convierte el objeto de tipo 'Product' en un Map
    Map<String, dynamic> toMap() => {
        "available": available,
        "name": name,
        "picture": picture,
        "price": price,
    };

  /*Copia del producto para utilzarlo en caso de necesitar modificar alguna de las propiedades
  Devolvera todos los valores del producto.
  */
  Product copy() => Product(
    available: this.available, 
    name: this.name, 
    picture: this.picture,
    price: this.price,
    id: this.id,
  );
}