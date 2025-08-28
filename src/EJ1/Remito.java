package EJ1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Remito {

    private long numero;
    private Date fecha;
    private List<LineaRemito> lineas;

    // Constructor

    public Remito(long numero, Date fecha, List<LineaRemito> lineas) {
        this.numero = numero;
        this.fecha = fecha;
        this.lineas = lineas;
    }

    public Remito(long numero, Date fecha) {
        this.numero = numero;
        this.fecha = fecha;
        this.lineas = new ArrayList<>();
    }

    // Methods

    public void agregarLinea(LineaRemito linea) {
        this.lineas.add(linea);
    }

    public void eliminarLinea(LineaRemito linea) {
        try {
            this.lineas.remove(linea);
        } catch (Exception e) {
            System.out.println("Error al eliminar la linea: " + e.getMessage());
        }
    }

    public List<Articulo> getArticulos() {
        return this.lineas.stream()
                .map(LineaRemito::getArticulo)
                .toList();
    }

    // Getters and Setters

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<LineaRemito> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaRemito> lineas) {
        this.lineas = lineas;
    }

}
