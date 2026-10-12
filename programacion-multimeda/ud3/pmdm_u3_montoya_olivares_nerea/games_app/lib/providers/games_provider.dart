import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../models/models.dart';

//Clase que establece la conexión con API RAWG.io mediante consultas

class GamesProvider extends ChangeNotifier {  //ChangeNotifier notifica a los widgets si hay cambios

  final String _baseUrl = 'api.rawg.io';
  final String _apiKey = 'aa493fd088e34f8cba01d0b979f3a68c';
  final String _language = 'es-ES';
  final String _page = '1';

  List<ReleaseGame> onDisplayGames = [];
  List<PopularGames> popularGames = [];
  Map<int, String> details = {};
  Map<int, List<ShortScreenshots>> screenshots = {};

  GamesProvider() {
    this.getReleaseGames();
    this.getPopularGames();
    
  }

  //Método que obtiene los juegos que seran lanzados en 2025
  getReleaseGames() async {
    var url = Uri.https(_baseUrl, '/api/games', {
      'dates': '2025-01-01,2026-01-01', //Rango de fechas para obtener los nuevos lanzamientos de 2025
      'ordering': '-added', //Ordenar por fecha de mas reciente
      'key': _apiKey, 
      'language': _language, 
      'page': _page, 
    });

    final result = await http.get(url);
    final releasedGames = ReleaseGamesResponse.fromJson(result.body);

    onDisplayGames = releasedGames.release;
    notifyListeners();
  }

  //Método que obtiene los juegos mas populares
  getPopularGames() async {
    var url = Uri.https(_baseUrl, '/api/games', {
      'key': _apiKey, 
      'ordering': '-added', 
      'language': _language, 
      'page': _page, 
    });

    final result = await http.get(url);
    final popularGamesData = PopularGamesResponse.fromJson(result.body); 

    popularGames = popularGamesData.populars; 
    notifyListeners();
  }

  //Método que obtiene los detalles de los juegos a través de su ID
  Future<DetailGamesResponse> getDetailsGame(int id) async {
    var url = Uri.https(_baseUrl, '/api/games/$id', {
      'key': _apiKey, 
    });

    final result = await http.get(url);
    final decodedBody = utf8.decode(result.bodyBytes);  //Permite mpstrar texto con acentos
    final detailsGame = DetailGamesResponse.fromJson(decodedBody);
    
    details[id] = detailsGame.descriptionRaw;
    return detailsGame;
  }

  //Método que obtiene las screenshots de los juegos a través de su ID
  Future<List<ShortScreenshots>> getScreenshot(int gameId) async {
    var url = Uri.https(_baseUrl, '/api/games/$gameId/screenshots', {
      'key': _apiKey,
    });

    final result = await http.get(url);
    final decodedBody = json.decode(result.body);

    List<dynamic> screenshotsJson = decodedBody['results'];
    List<ShortScreenshots> screenshots = screenshotsJson
        .map((json) => ShortScreenshots.fromMap(json))
        .toList();

    this.screenshots[gameId] = screenshots;
    notifyListeners();
    return screenshots;
  }
}                                   