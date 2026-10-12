import 'package:flutter/material.dart';
import 'package:games_app/models/models.dart';

//Clase que muestra un 'slider' de las screenshorts al seleccionar un juego.

class ScreenshortsCards extends StatelessWidget {

  final List<ShortScreenshots> screen;  //Lista de objetos ShortScreenshots
  const ScreenshortsCards({Key? key, required this.screen}) : super(key: key);  //Constructor

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(bottom: 30),
      width: double.infinity,
      height: 180,
      child: ListView.builder(
        itemCount: screen.length,
        scrollDirection: Axis.horizontal,
        itemBuilder: (BuildContext context, int index) {  //Itembuilder se encarga de devolver los screen
          return _CastCard(screenshot: screen[index]);
        }
      ),
    ); 
  }
}

class _CastCard extends StatelessWidget {

  final ShortScreenshots screenshot;  //Objeto ShortScreenshots
  const _CastCard({required this.screenshot});  //Constructor

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 110,
      height: 140,
      margin: const EdgeInsets.symmetric(horizontal: 10),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: FadeInImage(
          placeholder: const AssetImage('assets/no-image.jpg'),
          image: NetworkImage(screenshot.fullShortScreenshot),  //Imagen que se mostrará en cada 'card'
          height: 140,
          width: 100,
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}

