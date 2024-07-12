package edu.ar.model;

public class Pelicula {

  //atributos de clase
  private int id;
  private String nombre;
  private String descripcion;
  private String genero;
  private int calificacion;
  private int anio; 
  private byte estrellas;
  private String director;

  //constructores
  public Pelicula(int id, String nombre, String descripcion, String genero, int calificacion, int anio, byte estrellas,
    String director) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.genero = genero;
    this.calificacion = calificacion;
    this.anio = anio;
    this.estrellas = estrellas;
    this.director = director;
  }

  public Pelicula(String nombre, String descripcion, String genero, int calificacion, int anio, byte estrellas,
      String director) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.genero = genero;
    this.calificacion = calificacion;
    this.anio = anio;
    this.estrellas = estrellas;
    this.director = director;
  }

  //métodos getters y setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getAnio() {
    return anio;
  }

  public void setAnio(int anio) {
    this.anio = anio;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(int calificacion) {
    this.calificacion = calificacion;
  }

  public byte getEstrellas() {
    return estrellas;
  }

  public void setEstrellas(byte estrellas) {
    this.estrellas = estrellas;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  //métodos personalizados
  @Override
  public String toString() {
    return "Pelicula [id=" + id + ", nombre=" + nombre + ", anio=" + anio + ", genero=" + genero + ", descripcion="
        + descripcion + ", calificacion=" + calificacion + ", estrellas=" + estrellas + "]";
  }


}
