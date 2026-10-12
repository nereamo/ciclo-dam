import 'package:flutter/material.dart';
import 'package:games_app/models/models.dart';
import 'package:games_app/providers/games_provider.dart';
import 'package:games_app/widgets/screenshorts_cards.dart';

//Clase que contiene los elementos para mostrar los detalles de los juegos del Swiper

class DetailsScreenRelease extends StatelessWidget {

  final DetailGamesResponse gameDetails;  //Lista de objeto DetailsGame
  const DetailsScreenRelease({Key? key, required this.gameDetails}) : super(key: key);  //Constructor

  @override
  Widget build(BuildContext context) {

    final gamesProvider = GamesProvider();

    return Scaffold(
      backgroundColor: Colors.black,
      body: CustomScrollView(
        slivers: [
          _CustomAppBar(gameDetails: gameDetails),
          SliverList(
            delegate: SliverChildListDelegate(
              [
                _PosterAndTitle(gameDetails: gameDetails),  //Imagen
                _Overview(gameDetails: gameDetails),  //Descripcion
                FutureBuilder<List<ShortScreenshots>>(  //Widget que mostrará las screenshots de cada juego
                  future: gamesProvider.getScreenshot(gameDetails.id),
                  builder: (context, snapshot) {
                    if (!snapshot.hasData) {
                      return const Center(child: CircularProgressIndicator());
                    }
                    final screenshots = snapshot.data ?? [];
                    return ScreenshortsCards(screen: screenshots);  //Pasa la lista de capturas a CastingCards
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

//Barra personalizada con la imagen del juego
class _CustomAppBar extends StatelessWidget {

  final DetailGamesResponse gameDetails;  //Objeto DetailsGame
  const _CustomAppBar({Key? key, required this.gameDetails}) : super(key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    return SliverAppBar(
      backgroundColor: Colors.black,
      expandedHeight: 200,
      floating: false,
      pinned: true,
      foregroundColor: Colors.white,
      flexibleSpace: FlexibleSpaceBar(
        centerTitle: true,
        titlePadding: EdgeInsets.all(0),
        title: Container(
          width: double.infinity,
          alignment: Alignment.bottomCenter,
          color: Colors.black12,
          padding: const EdgeInsets.only(bottom: 10),
        ),
        background:  FadeInImage(
          placeholder: AssetImage('assets/loading.gif'),
          image: NetworkImage(gameDetails.fullbackgroundImage), //Muestra la imagen en la barra superior
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}

//Poster y titulo del juego
class _PosterAndTitle extends StatelessWidget {

  final DetailGamesResponse gameDetails;  //Objeto DetailsGame
  const _PosterAndTitle({Key? key, required this.gameDetails}) : super(key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    final TextTheme textTheme = Theme.of(context).textTheme;
    return Container(
      margin: const EdgeInsets.only(top: 20),
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: FadeInImage(
              placeholder: AssetImage('assets/loading.gif'),
              image: NetworkImage(gameDetails.fullbackgroundImage), //Muestra la imagen
              height: 60,
            ),
          ),
          const SizedBox(
            width: 20,
          ),
          Expanded(
            child: Column(
              children: [
                Text( //Titulo del juego
                  gameDetails.name,
                  style: textTheme.headlineSmall?.copyWith(color: Colors.white),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 2,
                  textAlign: TextAlign.center,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [ //Genero del juego; si no tiene, mostrara mensage 
                    const Icon(Icons.star_outline, size: 15, color: Colors.white),
                    const SizedBox(width: 5),
                    Text(gameDetails.genres.isNotEmpty ? gameDetails.genres[0].name : 'Género no disponible',
                      style: textTheme.bodySmall?.copyWith(color: Colors.white),
                    )
                  ],
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}

//Muestra la descripcion del juego
class _Overview extends StatelessWidget {

  final DetailGamesResponse gameDetails;  //Objeto DetailsGame 
  const _Overview({Key? key, required this.gameDetails}) : super(key: key); //Constructor

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
      child: Text(
        gameDetails.descriptionRaw.isNotEmpty ? gameDetails.descriptionRaw : 'Descripción no disponible', //Descripcion del juego; si no tiene, mostrara mensage 
        textAlign: TextAlign.justify,
        style: Theme.of(context).textTheme.titleMedium?.copyWith(color: Colors.white, fontFamily: 'Roboto'),
      ),
    );
  }
}

