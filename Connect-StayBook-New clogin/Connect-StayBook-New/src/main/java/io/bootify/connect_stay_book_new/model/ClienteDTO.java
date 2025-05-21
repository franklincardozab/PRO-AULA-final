package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;
import java.util.List;

public class ClienteDTO {

    private String id;
    private List<@Size(max = 255) String> historialReservas;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<String> getHistorialReservas() {
        return historialReservas;
    }

    public void setHistorialReservas(final List<String> historialReservas) {
        this.historialReservas = historialReservas;
    }

}