import 'package:card_swiper/card_swiper.dart';
import 'package:flutter/material.dart';
import 'package:games_app/models/releaseGame.dart';
import 'package:games_app/providers/games_provider.dart';
import 'package:games_app/screens/details_screen_release.dart';

//Clase que muestra una coleccion de 'juegos' de manera superpuesta pudiendo deslizar a ambos lados para mostrar el siguiente

class GameSwiper extends StatelessWidget {

  final List<ReleaseGame> release;  //Lista de objeto Release
  const GameSwiper({Key? key, required this.release}) : super (key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    if(this.release.length == 0){ 
      return Container(
        width: double.infinity,
        height: size.height*0.5,
        child: const Center(
          child: CircularProgressIndicator(),
        ),
      );
    }

    return Container(
      width: double.infinity,
      height: size.height * 0.5,
      child: Swiper(
        itemCount: release.length,
        layout: SwiperLayout.STACK,
        itemWidth: size.width * 0.6,
        itemHeight: size.height * 0.4,
        itemBuilder: (BuildContext context, int index) {
          final game = release[index];  //Variable que almacena la posición de release
          return GestureDetector(
            onTap: () async { //Acciones al pulsar el objeto
              GamesProvider provider = GamesProvider(); //Instancia de la clase GamesProvider
              final gameDetails = await provider.getDetailsGame(game.id); //LLama al metodo y almacena el id en la variable
              Navigator.push( //Navega hasta DetailsScreenRelease donde mostrará la info del objeto
                context,
                MaterialPageRoute(
                  builder: (context) => DetailsScreenRelease(gameDetails: gameDetails),
                ), 
              );
            },
            child: ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: FadeInImage(
                placeholder: AssetImage('assets/no-image.jpg'),
                  image: NetworkImage(game.fullbackgroundImage),  //imagen que se muestra inicialmente
                  fit: BoxFit.cover),
            )
          );
        },
      ),
    );
  }
}
