import 'dart:convert';
import 'package:examen_final_montoya/models/netPrecision.dart';

class Result {
    String id;
    String name;
    NetPrecision status;
    DateTime net;
    String lspName;
    String mission;
    String? image;

    Result({
        required this.id,
        required this.name,
        required this.status,
        required this.net,
        required this.lspName,
        required this.mission,
        this.image,
    });

    factory Result.fromJson(String str) => Result.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Result.fromMap(Map<String, dynamic> json) => Result(
        id: json["id"],
        name: json["name"],
        status: NetPrecision.fromMap(json["status"]),
        net: DateTime.parse(json["net"]),
        lspName: json["lsp_name"],
        mission: json["mission"],
        image: json["image"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "name": name,
        "status": status.toMap(),
        "net": net.toIso8601String(),
        "lsp_name": lspName,
        "mission": mission,
        "image": image,
    };
}