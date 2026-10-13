import 'dart:convert';

class NetPrecision {
    int id;
    String name;
    String abbrev;
    String description;

    NetPrecision({
        required this.id,
        required this.name,
        required this.abbrev,
        required this.description,
    });

    factory NetPrecision.fromJson(String str) => NetPrecision.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory NetPrecision.fromMap(Map<String, dynamic> json) => NetPrecision(
        id: json["id"],
        name: json["name"],
        abbrev: json["abbrev"],
        description: json["description"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "name": name,
        "abbrev": abbrev,
        "description": description,
    };
}