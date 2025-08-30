package EJ4;

import java.math.BigDecimal;

public class Caja implements Producto {

    private BigDecimal precioUnitario;
    private Integer cantidad;

    public Caja(BigDecimal precioUnitario, Integer cantidad) {
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    @Override
    public BigDecimal precio() {
        return getPrecioUnitario().multiply(new BigDecimal(getCantidad()));
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
