import 'models.dart';

//Clase extraída de 'release_games_response' con las propiedades que es usada para la clase 'GameSwiper'

class ReleaseGame {
  int id;
  String slug;
  String name;
  String backgroundImage;
  List<ShortScreenshots> short_screenshots;
  List<Genre> genres;

  ReleaseGame({
    required this.id,
    required this.slug,
    required this.name,
    required this.backgroundImage,
    required this.short_screenshots,
    required this.genres,
  });

  get fullbackgroundImage { //Si la imagen esta vacía, devuelve una imagen 'no imagen'
    return this.backgroundImage.isNotEmpty? this.backgroundImage: 'https://i.sstatic.net/GNhxO.png';
  }

   //Método que crea una instancia de ReleasedGames a partir de una cadena JSON.
  factory ReleaseGame.fromJson(String str) => ReleaseGame.fromMap(json.decode(str)); 

  //Método que crea una instancia de ReleasedGames a partir de un mapa JSON y lo convierte en una lista de objetos ReleaseGame.
  factory ReleaseGame.fromMap(Map<String, dynamic> json) => ReleaseGame(
    id: json["id"],
    slug: json["slug"],
    name: json["name"],
    backgroundImage: json["background_image"],
    short_screenshots: List<ShortScreenshots>.from(json["short_screenshots"].map((x) => ShortScreenshots.fromMap(x))),
    genres: List<Genre>.from(json["genres"].map((x) => Genre.fromMap(x))),
  );
}