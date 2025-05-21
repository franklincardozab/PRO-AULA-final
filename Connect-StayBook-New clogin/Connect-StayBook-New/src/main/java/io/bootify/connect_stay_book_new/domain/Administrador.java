package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administrador")
public class Administrador {

    @Id
    private String id;

    private String permisos;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(final String permisos) {
        this.permisos = permisos;
    }

}