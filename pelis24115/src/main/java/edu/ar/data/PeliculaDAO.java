package edu.ar.data;

import static edu.ar.data.Conexion.close;
import static edu.ar.data.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import edu.ar.model.Pelicula;

public class PeliculaDAO {

  private static final String SQL_SELECT = "SELECT * FROM movie";
  private static final String SQL_SELECT_BY_ID = "SELECT * FROM movie WHERE id = ?";
  private static final String SQL_INSERT = 
  "INSERT INTO movie (nombre, descripcion, genero, calificacion, anio, estrellas, director) VALUES (?,?,?,?,?,?,?)";
  private static final String SQL_UPDATE = 
  "UPDATE movie SET nombre = ?, descripcion = ?, genero = ?, calificacion = ?, anio = ?, estrellas = ?, director = ? WHERE id = ?";
  private static final String SQL_DELETE = "DELETE FROM movie WHERE id = ?";

  public static List<Pelicula> obtener() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Pelicula pelicula = null;
    List<Pelicula> peliculas = new ArrayList<>();

    //bloque try catch finally
    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_SELECT);
      rs = ps.executeQuery();
      
      //Itero los resultados devueltos por el select
      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        String genero = rs.getString("genero");
        int calificacion = rs.getInt("calificacion");
        int anio = rs.getInt("anio");
        byte estrellas = rs.getByte("estrellas");
        String director = rs.getString("director");

        //utilizo la instancia de persona y la inicializo
        pelicula = new Pelicula(id,nombre,descripcion,genero,calificacion,anio,estrellas,director);
        peliculas.add(pelicula);
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        close(rs);
        close(ps);
        close(conn);
      } catch (Exception e) {
        e.printStackTrace(System.out);
      }
    }
    return peliculas;
  }

  //Método para insertar una pelicula
  public static int insertar(Pelicula peli) {
    Connection conn = null;
    PreparedStatement ps = null;
    int registros = 0;
    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_INSERT);
      ps.setString(1, peli.getNombre());
      ps.setString(2, peli.getDescripcion());
      ps.setString(3, peli.getGenero());
      ps.setInt(4, peli.getCalificacion());
      ps.setInt(5, peli.getAnio());
      ps.setByte(6, peli.getEstrellas());
      ps.setString(7, peli.getDirector());
      registros = ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
    finally {
      try {
        close(ps);
        close(conn);
      } catch (SQLException e) {
        e.printStackTrace(System.out);
      }
    }
    return registros;
  }

  //Seleccionar por ID
  public static Pelicula seleccionarPorId(int id) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Pelicula pelicula = null;

    try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_SELECT_BY_ID);
      ps.setInt(1, id);
      rs = ps.executeQuery();

      while (rs.next()) {
        int idPeli = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        String genero = rs.getString("genero");
        int calificacion = rs.getInt("calificacion");
        int anio = rs.getInt("anio");
        byte estrellas = rs.getByte("estrellas");
        String director = rs.getString("director");

        pelicula = new Pelicula(nombre, descripcion, genero, calificacion, anio, estrellas, director);
      }
    } catch(SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        close(rs);
        close(ps);
        close(conn);
      } catch(SQLException e) {
        e.printStackTrace(System.out);
      }
    }
    return pelicula;
  }



  //Pruebas

  private static final String SQL_SELECT_ORDER_BY_NOMBRE = "SELECT * FROM movie ORDER BY nombre ASC";

public static List<Pelicula> obtenerOrdenadasPorNombre() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Pelicula pelicula = null;
    List<Pelicula> peliculas = new ArrayList<>();

    try {
        conn = getConexion();
        ps = conn.prepareStatement(SQL_SELECT_ORDER_BY_NOMBRE);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String genero = rs.getString("genero");
            int calificacion = rs.getInt("calificacion");
            int anio = rs.getInt("anio");
            byte estrellas = rs.getByte("estrellas");
            String director = rs.getString("director");

            pelicula = new Pelicula(id, nombre, descripcion, genero, calificacion, anio, estrellas, director);
            peliculas.add(pelicula);
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    } finally {
        try {
            close(rs);
            close(ps);
            close(conn);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    return peliculas;
}

public static int eliminar(int id) {
  Connection conn = null;
  PreparedStatement ps = null;
  int registros = 0;
  try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_DELETE);
      ps.setInt(1, id);
      registros = ps.executeUpdate();
  } catch (SQLException e) {
      e.printStackTrace(System.out);
  } finally {
      try {
          close(ps);
          close(conn);
      } catch (SQLException e) {
          e.printStackTrace(System.out);
      }
  }
  return registros;
}

public static int actualizar(Pelicula peli) {
  Connection conn = null;
  PreparedStatement ps = null;
  int registros = 0;
  try {
      conn = getConexion();
      ps = conn.prepareStatement(SQL_UPDATE);
      ps.setString(1, peli.getNombre());
      ps.setString(2, peli.getDescripcion());
      ps.setString(3, peli.getGenero());
      ps.setInt(4, peli.getCalificacion());
      ps.setInt(5, peli.getAnio());
      ps.setByte(6, peli.getEstrellas());
      ps.setString(7, peli.getDirector());
      ps.setInt(8, peli.getId());
      registros = ps.executeUpdate();
  } catch (SQLException e) {
      e.printStackTrace(System.out);
  } finally {
      try {
          close(ps);
          close(conn);
      } catch (SQLException e) {
          e.printStackTrace(System.out);
      }
  }
  return registros;
}



}
