import 'models.dart';

//Clase obtenida de la consulta API, de esta clase se ha obtenido la clase 'Populars'

class PopularGamesResponse {
  List<PopularGames> populars;

  PopularGamesResponse({
    required this.populars,
  });

  factory PopularGamesResponse.fromJson(String str) => PopularGamesResponse.fromMap(json.decode(str));  //Si la imagen esta vacía, devuelve una imagen 'no imagen'

  //Método que crea una instancia de PopularGames a partir de un mapa JSON y lo convierte en una lista de objetos Popular.
  factory PopularGamesResponse.fromMap(Map<String, dynamic> json) => PopularGamesResponse(
    populars: List<PopularGames>.from(json["results"].map((x) => PopularGames.fromMap(x))),
  );
}