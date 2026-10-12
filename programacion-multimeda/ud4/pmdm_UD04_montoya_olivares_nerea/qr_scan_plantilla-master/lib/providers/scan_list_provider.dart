import 'package:flutter/material.dart';
import 'package:qr_scan/models/scan_model.dart';
import 'package:qr_scan/providers/db_provider.dart';

//Clase intermediaria entre la DB y los widgets
class ScanListProvider extends ChangeNotifier{

  List<ScanModel> scans =  [];
  String tipusSeleccionat = 'http';

  Future<ScanModel> nouScan(String valor) async{
    final nouScan = ScanModel(valor: valor);
    final id = await DbProvider.db.insertScan(nouScan);
    nouScan.id = id;

    if(nouScan.tipus == tipusSeleccionat){
      this.scans.add(nouScan);
      notifyListeners();
    }
    return nouScan;
  }

  carregarScans() async {
    final scans = await DbProvider.db.getAllScans();
    this.scans = [...scans];
    notifyListeners();
  }

  carregarScansTipus(String tipus) async {
    final scans = await DbProvider.db.getScanByTipus(tipus);
    this.scans = [...scans];
    notifyListeners();
  }

  esborrarPerId(int id) async {
    await DbProvider.db.deleteById(id);
    this.scans.removeWhere((scan) => scan.id == id);
    notifyListeners();
  }

  esborrarTots() async {
    await DbProvider.db.deleteAllScans();
    this.scans = [];
    notifyListeners();
  }
}