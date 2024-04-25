package org.biblioteca;

import org.biblioteca.model.Biblioteca;
import org.biblioteca.model.Libro;
import org.biblioteca.model.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();


    public static void main(String[] args) {
        Integer option = 0;
        System.out.println("Bienvenido al Sistema de Gestion de Biblioteca\n1. Agregar Nuevo Libro\n2. Prestar Libro\n" +
                "3. Devolver Libro\n4. Mostrar Catalogo\n5. Mostrar Libros Prestados\n6. Salir");


        while (option != 6) {
            try {
                System.out.print("Por favor, seleccione una opción:");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        agregarLibro();
                        break;
                    case 2:
                        prestarLibro();
                        break;
                    case 3:
                        devolverLibro();
                        break;
                    case 4:
                        System.out.println(biblioteca.getCatalogo());
                        break;
                    case 5:
                        System.out.println(biblioteca.obtenerLibroPrestado());
                        break;

                    default:
                        break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Debe ingresar el año, vuelva a intentar");
                scanner.next();
            }
        }


    }


    private static void agregarLibro() throws InputMismatchException{
        Libro libro = new Libro();
        System.out.print("Ingrese el titulo del libro: ");
        libro.setTitulo(scanner.next());

        System.out.print("Ingrese el autor del libro: ");
        libro.setAutor(scanner.next());

        System.out.print("Ingrese el año de publicacion del libro: ");
        libro.setAnioPublicacion(scanner.nextInt());

        System.out.println(biblioteca.agregarLibroCatalogo(libro));


    }


    private static void prestarLibro()  throws InputMismatchException {
        Usuario usuario = new Usuario();
        System.out.print("Ingrese el nombre del usuario: ");
        usuario.setNombre(scanner.next());

        System.out.print("Ingrese el identificador del usuario: ");
        usuario.setId(scanner.nextInt());

        System.out.print("Ingrese el titulo del libro a prestar: ");
        String libro = scanner.next();

        System.out.println(biblioteca.prestarLibro(libro, usuario));


    }


    private static void devolverLibro()  throws InputMismatchException{


        System.out.print("Ingrese el identificador del usuario: ");
        Integer userId = (scanner.nextInt());

        System.out.print("Ingrese el titulo del libro a devolver: ");
        String libro = scanner.next();

        System.out.println(biblioteca.devolverLibro(libro, userId));


    }


}