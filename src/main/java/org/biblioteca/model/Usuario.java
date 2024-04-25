package org.biblioteca.model;


import jdk.jshell.Snippet;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Usuario {
    private Integer id;
    private String nombre;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
