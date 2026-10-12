import 'models.dart';

//Clase extraída de 'popular_games_response' con las propiedades que es usada para la clase 'GameSlider'

class PopularGames {
  int id;
  String slug;
  String name;
  String backgroundImage;
  List<Genre> genres;
  List<ShortScreenshots> shortScreenshots;

  PopularGames({
    required this.id,
    required this.slug,
    required this.name,
    required this.backgroundImage,
    required this.genres,
    required this.shortScreenshots,
  });

  get fullbackgroundImage { //Si la imagen esta vacía, devuelve una imagen 'no imagen'
    return this.backgroundImage.isNotEmpty? this.backgroundImage: 'https://i.sstatic.net/GNhxO.png';
  }

  //Método que crea una instancia de PopularGames a partir de una cadena JSON.
  factory PopularGames.fromJson(String str) => PopularGames.fromMap(json.decode(str));  

  //Método que crea una instancia de PopularGames a partir de un mapa JSON y lo convierte en una lista de objetos Popular.
  factory PopularGames.fromMap(Map<String, dynamic> json) => PopularGames(
    id: json["id"],
    slug: json["slug"],
    name: json["name"],
    backgroundImage: json["background_image"],
    genres: List<Genre>.from(json["genres"].map((x) => Genre.fromMap(x))),
    shortScreenshots: List<ShortScreenshots>.from(json["short_screenshots"].map((x) => ShortScreenshots.fromMap(x))),
  );
}
