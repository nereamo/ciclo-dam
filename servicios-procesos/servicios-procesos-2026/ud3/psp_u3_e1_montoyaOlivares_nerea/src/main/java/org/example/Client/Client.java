package org.example.Client;

/**
 * Clase que permite crear un cliente
 */
public class Client {

    private int id;
    private String name;
    private String lastName;

    public Client(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    @Override
    public String toString() {
        return "[" + id + "] "  + name + " " + lastName;
    }
}