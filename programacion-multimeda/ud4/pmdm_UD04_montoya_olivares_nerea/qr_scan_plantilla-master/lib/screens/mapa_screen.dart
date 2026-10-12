import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:qr_scan/models/scan_model.dart';

class MapaScreen extends StatefulWidget {
  const MapaScreen({Key? key}) : super(key: key);

  @override
  State<MapaScreen> createState() => _MapaScreenState();
}

class _MapaScreenState extends State<MapaScreen> {
  final Completer<GoogleMapController> _controller = Completer();
  MapType _currentMapType = MapType.normal;
  bool _showTraffic = false;

  //Metodo para alternar entre las ditintas formas de mapa
  void _toggleMapType() {
    setState(() {
      _currentMapType = _currentMapType == MapType.normal
          ? MapType.hybrid
          : MapType.normal;
    });
  }

  //Metodo para centrar el puntero
  Future<void> _centerMap() async {
    final GoogleMapController controller = await _controller.future;
    final ScanModel scan = ModalRoute.of(context)!.settings.arguments as ScanModel;
    final CameraPosition _puntiInicial = CameraPosition(
      target: scan.getLatng(),
      zoom: 10,
      tilt: 50,
    );
    controller.animateCamera(CameraUpdate.newCameraPosition(_puntiInicial));
  }

  void _toggleTraffic() {
    setState(() {
      _showTraffic = !_showTraffic;
    });
  }

  @override
  Widget build(BuildContext context) {
    final ScanModel scan =
        ModalRoute.of(context)!.settings.arguments as ScanModel;

    final CameraPosition _puntiInicial = CameraPosition(
      target: scan.getLatng(),
      zoom: 10,
      tilt: 50,
    );

    Set<Marker> markers = new Set<Marker>();
    markers.add(new Marker(
      markerId: MarkerId('id1'),
      position: scan.getLatng()
    ));

    return Scaffold(
      appBar: AppBar(
        title: Text('Google Maps'),
        centerTitle: true,
        backgroundColor: Colors.deepPurple,
        actions: [
          IconButton(
            icon: Icon(Icons.center_focus_strong),
            onPressed: _centerMap,
          ),
          IconButton(
            icon: Icon(Icons.traffic, color: _showTraffic ? Colors.green : Colors.red),
            onPressed: _toggleTraffic,
          ),
        ],
      ),
      body: GoogleMap(
        myLocationEnabled: true,
        myLocationButtonEnabled: true,
        mapType: _currentMapType,
        trafficEnabled: _showTraffic,
        markers: markers,
        initialCameraPosition: _puntiInicial,
        onMapCreated: (GoogleMapController controller) {
          _controller.complete(controller);
        },
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.only(bottom: 80.0),
        child: FloatingActionButton(
          onPressed: _toggleMapType,
          child: Icon(Icons.layers),
          backgroundColor: Colors.deepPurple,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
    );
  }
}
