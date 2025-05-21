package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cliente")
public class Cliente {

    @Id
    private String id;

    private List<String> historialReservas;

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