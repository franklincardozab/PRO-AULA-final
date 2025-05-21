package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;

public class AdministradorDTO {

    private String id;

    @Size(max = 255)
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
