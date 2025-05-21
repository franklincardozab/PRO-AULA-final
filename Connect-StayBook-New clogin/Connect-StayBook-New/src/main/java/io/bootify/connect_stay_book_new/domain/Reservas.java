package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "reservas")
public class Reservas {

    @Id
    private String id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Integer numerosPersonas;
    private String estado;

    // Relaciones con otras entidades
    private Usuario usuario;
    private Pagos pago;

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getNumerosPersonas() {
        return numerosPersonas;
    }

    public void setNumerosPersonas(Integer numerosPersonas) {
        this.numerosPersonas = numerosPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }
}