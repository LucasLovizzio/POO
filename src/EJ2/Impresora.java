package EJ2;

public class Impresora {

    private String nombre;
    private boolean enUso;

    public Impresora() {
    }

    public Impresora(String nombre) {
        this.nombre = nombre;
        this.enUso = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnUso() {
        return enUso;
    }
    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public void imprimir(String documento) {
        System.out.println("Imprimiendo en " + getNombre() + ": " + documento);
    }
}
