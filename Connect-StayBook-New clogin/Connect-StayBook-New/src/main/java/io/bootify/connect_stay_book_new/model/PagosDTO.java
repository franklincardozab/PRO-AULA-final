package io.bootify.connect_stay_book_new.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class PagosDTO {

    private String id;

    private Double monto;

    @Size(max = 255)
    private String metodoPago;

    private LocalDateTime fechaPago;

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

}
