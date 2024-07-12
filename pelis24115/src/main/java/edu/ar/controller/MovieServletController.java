package edu.ar.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ar.data.PeliculaDAO;
import edu.ar.model.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/movies")
public class MovieServletController extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(MovieServletController.class);
    List<Pelicula> movieList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String route = req.getParameter("action");
        logger.info("route : " + route);
        switch (route) {
            case "getAll" -> {
                res.setContentType("application/json; charset=UTF-8");
                movieList = PeliculaDAO.obtener();
                logger.info("Dentro de getAll : " + movieList.size());
                mapper.writeValue(res.getWriter(), movieList);
            }
            case "orderByName" -> {
                res.setContentType("application/json; charset=UTF-8");
                movieList = PeliculaDAO.obtenerOrdenadasPorNombre();
                mapper.writeValue(res.getWriter(), movieList);
            }
            case "getById" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                Pelicula pelicula = PeliculaDAO.seleccionarPorId(id);
                if (pelicula != null) {
                    res.setContentType("application/json; charset=UTF-8");
                    mapper.writeValue(res.getWriter(), pelicula);
                } else {
                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Película no encontrada");
                    mapper.writeValue(res.getWriter(), response);
                }
            }
            default -> {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Acción no válida");
                mapper.writeValue(res.getWriter(), response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        sb.append(line);
    }

    // Convierte el String a un Map usando ObjectMapper
    Map<String, String> requestBody = mapper.readValue(sb.toString(), Map.class);
    String route = requestBody.get("action");
      
      /*String route = req.getParameter("action");
        logger.info("Dentro del doPost()");
        logger.info("route : " + route);*/
        switch(route) {
            case "add" -> {

              String nombre = requestBody.get("nombre");
            String descripcion = requestBody.get("descripcion");
            String genero = requestBody.get("genero");
            int calificacion = Integer.parseInt(requestBody.get("calificacion"));
            int anio = Integer.parseInt(requestBody.get("anio"));
            byte estrellas = Byte.parseByte(requestBody.get("estrellas"));
            String director = requestBody.get("director");

            Pelicula newMovie = new Pelicula(nombre, descripcion, genero, calificacion, anio, estrellas, director);
            int result = PeliculaDAO.insertar(newMovie);

            res.setContentType("application/json; charset=UTF-8");
            Map<String, String> response = new HashMap<>();
            response.put("message", result > 0 ? "Película guardada exitosamente!!!" : "Error al guardar la película");
            mapper.writeValue(res.getWriter(), response);
            
                /* String nombre = req.getParameter("nombre");
                String descripcion = req.getParameter("descripcion");
                String genero = req.getParameter("genero");
                int calificacion = Integer.parseInt(req.getParameter("calificacion"));
                int anio = Integer.parseInt(req.getParameter("anio"));
                byte estrellas = Byte.parseByte(req.getParameter("estrellas"));
                String director = req.getParameter("director");
                logger.info("Datos: {}, {}, {}, {}, {}, {}, {}", nombre, descripcion, genero, calificacion, anio, estrellas, director);

                Pelicula newMovie = new Pelicula(nombre, descripcion, genero, calificacion, anio, estrellas, director);
                int result = PeliculaDAO.insertar(newMovie);

                res.setContentType("application/json; charset=UTF-8");
                Map<String, String> response = new HashMap<>();
                response.put("message", result > 0 ? "Película guardada exitosamente!!!" : "Error al guardar la película");
                mapper.writeValue(res.getWriter(), response); */
            }
            case "update" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                String nombre = req.getParameter("nombre");
                String descripcion = req.getParameter("descripcion");
                String genero = req.getParameter("genero");
                int calificacion = Integer.parseInt(req.getParameter("calificacion"));
                int anio = Integer.parseInt(req.getParameter("anio"));
                byte estrellas = Byte.parseByte(req.getParameter("estrellas"));
                String director = req.getParameter("director");

                Pelicula updatedMovie = new Pelicula(id, nombre, descripcion, genero, calificacion, anio, estrellas, director);
                int result = PeliculaDAO.actualizar(updatedMovie);

                res.setContentType("application/json; charset=UTF-8");
                Map<String, String> response = new HashMap<>();
                response.put("message", result > 0 ? "Película actualizada exitosamente!!!" : "Error al actualizar la película");
                mapper.writeValue(res.getWriter(), response);
            }
            case "delete" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                int result = PeliculaDAO.eliminar(id);

                res.setContentType("application/json; charset=UTF-8");
                Map<String, String> response = new HashMap<>();
                response.put("message", result > 0 ? "Película eliminada exitosamente!!!" : "Error al eliminar la película");
                mapper.writeValue(res.getWriter(), response);
            }
            default -> {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Acción no válida");
                mapper.writeValue(res.getWriter(), response);
            }
        }
    }
}
