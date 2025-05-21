package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;

public class HabitacionDTO {

    private String id;

    private Integer numero;

    @Size(max = 255)
    private String tipo;

    private Double precioPorNoche;

    private Boolean disponible;

    private String hotel;

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

    public String getHotel() {
        return hotel;
    }

    public void setHotel(final String hotel) {
        this.hotel = hotel;
    }

}

