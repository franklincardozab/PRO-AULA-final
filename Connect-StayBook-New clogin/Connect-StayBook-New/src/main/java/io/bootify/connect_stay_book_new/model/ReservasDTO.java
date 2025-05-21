package io.bootify.connect_stay_book_new.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservasDTO {

    private String id;

    // Fecha sin hora
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    // Fecha con hora
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaInicioConHora;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaFinConHora;

    private Integer numerosPersonas;
    private String estado;
    private String usuario;
    private String pago;

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

    public LocalDateTime getFechaInicioConHora() {
        return fechaInicioConHora;
    }

    public void setFechaInicioConHora(LocalDateTime fechaInicioConHora) {
        this.fechaInicioConHora = fechaInicioConHora;
    }

    public LocalDateTime getFechaFinConHora() {
        return fechaFinConHora;
    }

    public void setFechaFinConHora(LocalDateTime fechaFinConHora) {
        this.fechaFinConHora = fechaFinConHora;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }
}