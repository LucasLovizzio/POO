package EJ1;

public class LineaRemito {

    private Articulo articulo;
    private int cantidad;

    // Constructor

    public LineaRemito(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    //Methods


    // Getters and Setters

    public int getCantidad() {
        return cantidad;
    }

    private void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    private void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

}
