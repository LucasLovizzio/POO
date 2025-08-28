package EJ1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Articulo {

    private String nombre;
    private BigDecimal precio;

    public Articulo(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = new BigDecimal(precio).setScale(2, RoundingMode.HALF_UP);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String mostrarArticulo() {
        return "Articulo: " + this.getNombre() + " - Precio: " + this.getPrecio() + " €";
    }
}
