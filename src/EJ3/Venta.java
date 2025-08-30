package EJ3;

import java.math.BigDecimal;
import java.util.Map;

public class Venta {

    private Map<Producto, Integer> productos;
    private FormaDePago formaDePago;

    public Venta() {
    }

    public Venta(Map<Producto, Integer> productos, FormaDePago formaDePago) {
        this.productos = productos;
        this.formaDePago = formaDePago;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void agregarProducto(Producto producto, Integer cantidad) {
        this.productos.put(producto, cantidad);
    }

    public void pagar() {
        BigDecimal total = productos.entrySet().stream()
                .map(entry -> entry.getKey().getPrecioUnitario().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        formaDePago.pagar(total);
    }
}

