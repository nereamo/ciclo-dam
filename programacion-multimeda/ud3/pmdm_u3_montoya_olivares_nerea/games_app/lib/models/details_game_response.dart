import 'models.dart';

//Clase obtenida de la consulta API, de esta clase se ha obtenido la descripción 

class DetailGamesResponse {
  int id;
  String slug;
  String name;
  String description;
  String backgroundImage;
  List<Genre> genres;
  String descriptionRaw;

  DetailGamesResponse
({
    required this.id,
    required this.slug,
    required this.name,
    required this.description,
    required this.backgroundImage,
    required this.genres,
    required this.descriptionRaw,
  });

  get fullbackgroundImage {
    return this.backgroundImage.isNotEmpty? this.backgroundImage: 'https://i.sstatic.net/GNhxO.png';
  }

  //Método que crea una instancia de DetailGamesResponse
  //  a partir de una cadena JSON.
  factory DetailGamesResponse
.fromJson(String str) => DetailGamesResponse
.fromMap(json.decode(str));

  //Método que crea una instancia de DetailGamesResponse
  //  a partir de un mapa JSON y lo convierte en una lista de objetos DetailGamesResponse
  // .
  factory DetailGamesResponse
.fromMap(Map<String, dynamic> json) => DetailGamesResponse
(
    id: json["id"],
    slug: json["slug"],
    name: json["name"],
    description: json["description"],
    backgroundImage: json["background_image"],
    genres: List<Genre>.from(json["genres"].map((x) => Genre.fromMap(x))),
    descriptionRaw: json["description_raw"],
  );
}