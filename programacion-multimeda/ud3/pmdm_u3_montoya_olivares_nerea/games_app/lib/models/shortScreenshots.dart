import 'models.dart';

//Clase extraída de 'release_games_response/popular_games_response' con las propiedades que es usada para la clase 'ScreenshortsCards'

class ShortScreenshots {
  int id;
  String image;

  ShortScreenshots({
    required this.id,
     required this.image,
  });

  get fullShortScreenshot { //Si la imagen esta vacía, devuelve una imagen 'no imagen'
    return this.image.isNotEmpty? this.image: 'https://i.sstatic.net/GNhxO.png';
  }

  //Método que crea una instancia de ShortScreenshots a partir de una cadena JSON.
  factory ShortScreenshots.fromJson(String str) => ShortScreenshots.fromMap(json.decode(str));

  //Método que crea una instancia de ShortScreenshots a partir de un mapa JSON y lo convierte en una lista de objetos ShortScreenshots.
  factory ShortScreenshots.fromMap(Map<String, dynamic> json) => ShortScreenshots(
    id: json["id"],
    image: json["image"],
  );
}
