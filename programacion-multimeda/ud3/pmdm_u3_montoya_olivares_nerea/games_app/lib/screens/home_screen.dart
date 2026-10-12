import 'package:flutter/material.dart';
import 'package:games_app/providers/games_provider.dart';
import 'package:games_app/widgets/game_slider.dart';
import 'package:games_app/widgets/game_swiper.dart';
import 'package:provider/provider.dart';

//Clase inicial, donde se muestra el GameSwiper y el GameSlider

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final gamesProvider = Provider.of<GamesProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('New Releases', style: TextStyle(color: Colors.white)),
        elevation: 0,
        backgroundColor: Colors.black,
        actions: [
          IconButton(onPressed: () {}, icon: const Icon(Icons.search_outlined, color: Colors.white))
        ],
      ),
      body: SingleChildScrollView(
        child: Container(
          color:Colors.black,
          child: Column(
            children: [
              //Llama a los widgets que se mostrarán en la pantalla inicial
              GameSwiper(release: gamesProvider.onDisplayGames),  
              GameSlider(popularGames: gamesProvider.popularGames), 
            ],
          ),
        ),
      ),
    );
  }
}
