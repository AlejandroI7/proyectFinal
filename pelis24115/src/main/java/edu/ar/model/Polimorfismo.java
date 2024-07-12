package edu.ar.model;

import java.util.List;
import java.util.ArrayList;

public class Polimorfismo {

  public static void main(String[] args) {

    //raw type
    List<String> palabras = new ArrayList<>();
    List<Persona> personas = new ArrayList<>();

    Integer enteroWrapper = 2;
    String pal = "Texto";
    palabras.add("Hola");
    palabras.add(pal);
    //palabras.add(enteroWrapper);


    Persona person = new Persona();
    Actor actor = new Actor("Johnny", "Cage", 1980, 4);
    Director director = new Director("Johnny", "Cage", 1980, 4);

    personas.add(person);
    personas.add(actor);
    personas.add(director);

    person = actor;

    System.out.println(person);

   //actor = person;



  }

}
