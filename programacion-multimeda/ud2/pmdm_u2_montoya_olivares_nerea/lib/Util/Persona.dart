

class Persona {
  final String name;
  final String lastName;
  final String bornDate;
  final String email;
  final String password;

  Persona({ //Constructor, todos los parámetros son obligatorios
    required this.name,
    required this.lastName,
    required this.bornDate,
    required this.email,
    required this.password,
  });
}