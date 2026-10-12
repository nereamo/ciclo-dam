import 'package:flutter/material.dart';
import 'package:games_app/providers/games_provider.dart';
import 'package:games_app/screens/details_screen_populars.dart';
import '../models/popularGame.dart';

//Clase que muestra una coleccion de 'juegos' de manera ordenada pudiendo deslizar a ambos lados para mostrar el siguiente

class GameSlider extends StatelessWidget {
  
  final List<PopularGames> popularGames;  //Lista de objeto Populars
  const GameSlider({Key? key, required this.popularGames}) : super(key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 270,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 20),
            child: Text(
              'Populars',
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, color: Colors.white)),
            ),
          const SizedBox(height: 5),
          Expanded(
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: popularGames.length  ,
              itemBuilder: (_, int index) {
                final gameItem = popularGames[index]; //Variable que almacena la posición de populars
                return _GamePoster(popularGames: gameItem);
              }
            )
          )
        ],
      ),
    );
  }
}

class _GamePoster extends StatelessWidget {

  final PopularGames popularGames;  //Objeto Populars
  const _GamePoster({Key? key, required this.popularGames}) : super(key: key);  //Constructor

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 130,
      height: 190,
      margin: const EdgeInsets.symmetric(horizontal: 10),
      child: Column(
        children: [
          GestureDetector(
            onTap: () async { //Acciones al pulsar el objeto
              GamesProvider provider = GamesProvider(); //Instancia de la clase GamesProvider
              final gameDetails = await provider.getDetailsGame(popularGames.id); //LLama al metodo y almacena el id en la variable
              Navigator.push( //Navega hasta DetailsScreenPopulars donde mostrará la info del objeto
                context,
                MaterialPageRoute(
                  builder: (context) => DetailsScreenPopulars(gameDetails: gameDetails),
                ), 
              );
            },
            child: ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: FadeInImage(
                placeholder: AssetImage('assets/no-image.jpg'),
                image: NetworkImage(popularGames.fullbackgroundImage),  //imagen que se muestra inicialmente
                width: 130,
                height: 190,
                fit: BoxFit.cover,
              ),
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Text(
            popularGames.name,
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
            style: TextStyle(color: Colors.white),
          )
        ],
      ),
    );
  }
}
