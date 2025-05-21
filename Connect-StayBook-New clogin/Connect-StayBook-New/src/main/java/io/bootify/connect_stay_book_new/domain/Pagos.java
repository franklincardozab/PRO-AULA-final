package io.bootify.connect_stay_book_new.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pagos")
public class Pagos {

    @Id
    private String id;

    private Double monto;
    private String metodoPago;
    private LocalDateTime fechaPago;
    private String reservaId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(final Double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(final String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(final LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReservaId() {
        return reservaId;
    }

    public void setReservaId(final String reservaId) {
        this.reservaId = reservaId;
    }
}