import 'package:image_picker_for_web/image_picker_for_web.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';

//Función encargada de registrar los plugins cuando la aplicación web se inicia.
void registerPlugins(Registrar registrar){
  ImagePickerPlugin.registerWith(registrar);
  registrar.registerMessageHandler();
}