package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;
import java.util.List;

public class DetallesResrvaDTO {

    private String id;

    @Size(max = 255)
    private String tipoHabitacion;

    private Double precioTotal;

    private List<@Size(max = 255) String> serviciosIncluidos;

    private String habitacion;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(final String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(final Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<String> getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public void setServiciosIncluidos(final List<String> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(final String habitacion) {
        this.habitacion = habitacion;
    }

}