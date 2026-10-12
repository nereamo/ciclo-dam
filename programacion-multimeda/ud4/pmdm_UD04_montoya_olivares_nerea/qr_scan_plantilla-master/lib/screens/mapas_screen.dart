import 'package:flutter/material.dart';
import 'package:qr_scan/widgets/scan_titles.dart';

class MapasScreen extends StatelessWidget {
  const MapasScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ScanTitles(tipus: 'geo');
  }
}
