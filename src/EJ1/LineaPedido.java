package EJ1;

import java.math.BigDecimal;

public class LineaPedido {

    private int cantidad;
    private Articulo articulo;
    private BigDecimal precioUnitario;

    // Constructor

    public LineaPedido(int cantidad, Articulo articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.precioUnitario = articulo.getPrecio();
    }

    // Methods

    public BigDecimal getSubtotal() {
        return this.precioUnitario.multiply(new BigDecimal(getCantidad()));
    }

    // Getters and Setters

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    private void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    private void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
