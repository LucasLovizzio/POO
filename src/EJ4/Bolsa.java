package EJ4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bolsa implements Producto {

    private static final double DESCUENTO = 10; // 10%
    private static final BigDecimal DESCUENTO_FINAL = BigDecimal.valueOf((100 - DESCUENTO) / 100.0);

    private final List<Producto> productos;

    public Bolsa() {
        this.productos = new ArrayList<>();
    }

    public Bolsa(List<Producto> productos) {
        this.productos = new ArrayList<>(productos);        // crea una copia interna y no usa la referencia externa.
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        getProductos().add(producto);
    }

    private BigDecimal precioSinDescuento() {
        return getProductos().stream()
                .map(Producto::precio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal precio() {
        return precioSinDescuento().multiply(DESCUENTO_FINAL);
    }
}
