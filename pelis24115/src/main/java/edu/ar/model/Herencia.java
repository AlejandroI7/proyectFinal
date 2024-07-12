package edu.ar.model;

public class Herencia {


  public static void main(String[] args) {
    Persona persona = new Persona("Alvarez", "Julian", 2001);
    
    Actor actor = new Actor("John", "Doe", 2000, 7);
    actor.setCantPeliculas(8);
    System.out.println(actor);

  }

}
