package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "habitacion")
public class Habitacion {

    @Id
    private String id;

    private Integer numero;
    private String tipo;
    private Double precioPorNoche;
    private Boolean disponible;

    @DBRef
    private Hotel hotel;  // Referencia a un hotel

    @DBRef
    private Set<DetallesResrva> detallesReservas;  // Referencia a reservas relacionadas

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(final Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(final Double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(final Boolean disponible) {
        this.disponible = disponible;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(final Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<DetallesResrva> getDetallesReservas() {
        return detallesReservas;
    }

    public void setDetallesReservas(final Set<DetallesResrva> detallesReservas) {
        this.detallesReservas = detallesReservas;
    }
}