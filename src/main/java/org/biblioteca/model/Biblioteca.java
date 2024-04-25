package org.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Biblioteca {

    private List<Libro> catalogo = new ArrayList<>();

    private List<Libro> catalogoDisponible = new ArrayList<>();

    private HashMap<Usuario, List<Libro>> libroPrestado = new HashMap<>();

    public String agregarLibroCatalogo(Libro guardarLibro) {
        String response = "Libro agregado al catálogo";
        Optional<Libro> buscarLibro = findByName(catalogo, guardarLibro.getTitulo());
        if (buscarLibro.isEmpty()) {
            catalogo.add(guardarLibro);
            catalogoDisponible.add(guardarLibro);
        } else
            response = "Libro ya existe en el catálogo";

        return response;

    }


    public String prestarLibro(String libroPrestar, Usuario usuario) {
        String response = "Libro prestaado correctamente a " + usuario.getNombre();
        Optional<Libro> bookFind = findByName(catalogoDisponible, libroPrestar);
        if (bookFind.isPresent()) {
            libroPrestado.computeIfAbsent(usuario, k -> new ArrayList<>()).add(bookFind.get());
            catalogoDisponible.remove(bookFind.get());
        } else {
            response = "El libro no esta disponible";
        }

        return response;

    }

    public String devolverLibro(String libroaPrestar, Integer userId) {
        String response = "Libro devuelto correctamente  ";
        Usuario usuario = Usuario.builder().id(userId).build();
        if (libroPrestado.containsKey(usuario)) {
            Optional<Libro> libro = findByName(libroPrestado.get(usuario), libroaPrestar);
            if (libro.isPresent()) {
                catalogoDisponible.add(libro.get());
                libroPrestado.get(usuario).remove(libro.get());
            } else {
                response = "El usuario no posee este libro";
            }

        } else {
            response = "El usuario no existe";
        }

        return response;

    }

    public List<Libro> obtenerLibroPrestado() {
        List<Libro> todosLosLibros = new ArrayList<>();
        libroPrestado.values().forEach(todosLosLibros::addAll);
        if (todosLosLibros.isEmpty()) {
            System.out.println("No hay libros prestados en este momento.");
        }
        return todosLosLibros;
    }

    private Optional<Libro> findByName(List<Libro> list, String name) {
        return list.stream().filter(book -> book.getTitulo().equals(name)).findFirst();
    }
}
