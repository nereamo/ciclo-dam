import 'models.dart';

//Clase extraída de 'release_games_response/popular_games_response' con las propiedades que es usada para la clase 'Genres'

class Genre {
  int id;
  String name;
  String slug;

  Genre({
    required this.id,
    required this.name,
    required this.slug,
  });

  //Método que crea una instancia de Genre a partir de una cadena JSON.
  factory Genre.fromJson(String str) => Genre.fromMap(json.decode(str));

  //Método que crea una instancia de Genre a partir de un mapa JSON y lo convierte en una lista de objetos Genre.
  factory Genre.fromMap(Map<String, dynamic> json) => Genre(
    id: json["id"],
    name: json["name"],
    slug: json["slug"],
  );
}