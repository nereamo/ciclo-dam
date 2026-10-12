package com.mycompany.transfer;

import java.util.regex.Pattern;

/**
* Classe Matrícula, contiene los atributos y getts/setts. Esta classe será usada para la validación de la matrícula.
* @author Nerea Montoya
*/

public class Matricula {
    
    private String digitos;
    private String letras;
    
    private static final Pattern DIGITOS_PATTERN = Pattern.compile("^[0-9]{4}$");
    private static final Pattern LETRAS_PATTERN = Pattern.compile("^[A-Z]{3}$");
    
    public Matricula(){
        
    }
    
    public Matricula(String digitos, String letras){
      setDigitos(digitos);
      setLetras(letras);
    }
    
    public String getDigitos(){
        return digitos;
    }
    
    public void setDigitos(String digitos){
        if (!DIGITOS_PATTERN.matcher(digitos).matches()) {
            throw new IllegalArgumentException("Error: Formato de los dígitos incorrecto. Debe tener 4 números (Ejemplo: 1234).");
        }
        this.digitos = digitos;
    }
    
    public String getLetras(){
        return letras;
    }
    
    public void setLetras(String letras){
       if (!LETRAS_PATTERN.matcher(letras).matches()) {
            throw new IllegalArgumentException("Error: Formato de las letras incorrecto. Debe tener 3 letras mayúsculas (Ejemplo: ABC).");
        }
        this.letras = letras;
    }
    
    public boolean validarMatricula(String matricula) {
        String[] partes = matricula.split("-");

        if (partes.length != 2) {
            System.out.println("Error: La matrícula debe tener el formato 1111-XXX.");
            return false;
        }

        String digitosParte = partes[0];
        String letrasParte = partes[1];

        try {
            setDigitos(digitosParte);
            setLetras(letrasParte);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return digitos + "-" + letras;
    }
}
