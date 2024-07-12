package edu.ar.model;

public class Director extends Persona {

  private int cantPelDirigidas;

  public Director(String nombre, String apellido, int anioNacimiento, int cantPelDirigidas) {
    super(nombre, apellido, anioNacimiento);
    this.cantPelDirigidas = cantPelDirigidas;
  }

  public int getCantPelDirigidas() {
    return cantPelDirigidas;
  }

  public void setCantPelDirigidas(int cantPelDirigidas) {
    this.cantPelDirigidas = cantPelDirigidas;
  }

}
