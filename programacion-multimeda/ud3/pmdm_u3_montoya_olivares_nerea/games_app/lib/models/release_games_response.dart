import 'models.dart';

//Clase obtenida de la consulta API, de esta clase se ha obtenido la clase 'release'

class ReleaseGamesResponse {
    List<ReleaseGame> release;

  ReleaseGamesResponse({
    required this.release,
  });

  //Método que crea una instancia de PopularGames a partir de una cadena JSON.
  factory ReleaseGamesResponse.fromJson(String str) => ReleaseGamesResponse.fromMap(json.decode(str));  

  //Método que crea una instancia de PopularGames a partir de un mapa JSON y lo convierte en una lista de objetos Popular.
  factory ReleaseGamesResponse.fromMap(Map<String, dynamic> json) => ReleaseGamesResponse(
    release: List<ReleaseGame>.from(json["results"].map((x) => ReleaseGame.fromMap(x))),
  );
}