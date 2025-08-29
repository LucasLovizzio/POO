package EJ2;

import java.util.ArrayList;
import java.util.List;

public class PoolImpresoras {

    private static PoolImpresoras instancia;
    private List<Impresora> impresorasDisponibles;

    private PoolImpresoras() {
        impresorasDisponibles = new ArrayList<>();
        impresorasDisponibles.add(new Impresora("HP"));
        impresorasDisponibles.add(new Impresora("Dell"));
        impresorasDisponibles.add(new Impresora("Epson"));

    }

    public static synchronized PoolImpresoras getInstancia() {
        if (instancia == null) {
            instancia = new PoolImpresoras();
        }
        return instancia;
    }

    public Impresora obtenerImpresora(String nombre) {
        for(Impresora impresora : getImpresorasDisponibles()) {
            if(impresora.getNombre().equalsIgnoreCase(nombre) && !impresora.isEnUso()) {
                return impresora;
            }
        }
        return null; // Si no hay impresora con ese nombre o no está disponible.
    }

    public void liberarImpresora(Impresora impresora) {
        if (impresora != null && impresorasDisponibles.contains(impresora)) {
            impresora.setEnUso(false);
        }
    }

    public List<Impresora> getImpresorasDisponibles() {
        return impresorasDisponibles;
    }

    public void setImpresorasDisponibles(List<Impresora> impresorasDisponibles) {
        this.impresorasDisponibles = impresorasDisponibles;
    }
}
