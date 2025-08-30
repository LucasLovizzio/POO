package EJ3;

import java.math.BigDecimal;

public class Producto {

    private String nombre;
    private BigDecimal precioUnitario;

    public Producto() {
    }

    public Producto(String nombre, BigDecimal precioUnitario) {
        this.nombre = nombre;
        setPrecioUnitario(precioUnitario);
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    private void setPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser nulo o negativo");
        }
        this.precioUnitario = precioUnitario;
    }
}
