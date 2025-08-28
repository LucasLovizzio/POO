package EJ1;

import java.util.*;

public class Pedido {
    private long numero;
    private String cliente;
    private Date fechaDeEntrega;
    private EstadoPedido estado;
    private List<Remito> remitos;
    private List<LineaPedido> lineas;
    private Map<Articulo, Integer> entregados;

    public Pedido(long numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.fechaDeEntrega = null;
        this.estado = new PedidoAbierto();
        this.remitos = new ArrayList<>();
        this.lineas = new ArrayList<>();
        this.entregados = new HashMap<>();
    }

    public void agregarLineaPedido(Articulo articulo, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        Optional<LineaPedido> lineaExistente = lineas.stream()
                .filter(lp -> lp.getArticulo().equals(articulo))
                .findFirst();

        if (lineaExistente.isPresent()) {
            LineaPedido linea = lineaExistente.get();
            linea.setCantidad(linea.getCantidad() + cantidad);
        } else {
            lineas.add(new LineaPedido(cantidad, articulo));
        }
    }

    public void agregarRemito(Remito remito) {
        try {
            RemitoValidator.validarRemito(remito, this);

            // Si pasa todas las validaciones, agregar el remito y actualizar entregados
            actualizarEntregados(remito);
            this.remitos.add(remito);

        } catch (Exception e) {
            System.out.println("Error al agregar remito: " + e.getMessage());
        }
    }

    private void actualizarEntregados(Remito remito) {
        for (LineaRemito lr : remito.getLineas()) {
            Articulo articulo = lr.getArticulo();
            int cantidadRemito = lr.getCantidad();
            int actual = entregados.getOrDefault(articulo, 0);
            entregados.put(articulo, actual + cantidadRemito);
        }
    }

    public boolean noHayMasProductosParaRemitos() {
        return lineas.stream()
                .allMatch(lp -> getPendiente(lp.getArticulo()) == 0);
    }

    public void cerrarPedido() {
        if (!estado.puedoCerrarPedido()) {
            throw new IllegalStateException("No se puede cerrar el pedido en el estado actual");
        }

        if (!noHayMasProductosParaRemitos()) {
            throw new IllegalStateException("Aún hay productos pendientes que pueden ser agregados a remitos");
        }

        this.estado = new PedidoCerrado();
    }

    public void entregarPedidoSinMasProductos() {
        if (!estado.puedoCerrarPedido()) {
            throw new IllegalStateException("No se puede entregar el pedido en el estado actual");
        }

        if (!noHayMasProductosParaRemitos()) {
            throw new IllegalStateException("Aún hay productos disponibles para agregar a remitos");
        }

        this.estado = new PedidoEntregado();
        this.fechaDeEntrega = new Date();
    }

    public void entregarPedido() {
        if (!estado.puedoCerrarPedido()) {
            throw new IllegalStateException("No se puede entregar el pedido en el estado actual");
        }

        if (!(estado instanceof PedidoCerrado)) {
            throw new IllegalStateException("El pedido debe estar cerrado antes de ser entregado");
        }

        this.estado = new PedidoEntregado();
        this.fechaDeEntrega = new Date();
    }

    public boolean estaCompletamenteEntregado() {
        return lineas.stream()
                .allMatch(lp -> getPendiente(lp.getArticulo()) == 0);
    }

    public String obtenerResumenEntregas() {
        return PedidoReportGenerator.generarResumenCompleto(this);
    }

    public int buscarCantidadPedido(Articulo articulo) {
        return lineas.stream()
                .filter(lp -> lp.getArticulo().equals(articulo))
                .mapToInt(LineaPedido::getCantidad)
                .sum();
    }

    public int getPendiente(Articulo articulo) {
        int pedido = buscarCantidadPedido(articulo);
        int entregado = entregados.getOrDefault(articulo, 0);
        return pedido - entregado;
    }

    public void cambiarAPedidoCerrado() {
        if (!(estado instanceof PedidoAbierto)) {
            throw new IllegalStateException("Solo se puede cambiar a cerrado desde el estado abierto");
        }

        if (lineas.isEmpty()) {
            throw new IllegalStateException("No se puede cerrar un pedido sin líneas");
        }

        this.estado = new PedidoCerrado();
    }

    // Getters
    public long getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public Date getFechaDeEntrega() { return fechaDeEntrega; }
    public EstadoPedido getEstado() { return estado; }
    public List<Remito> getRemitos() { return remitos; }
    public List<LineaPedido> getLineas() { return lineas; }
    public Map<Articulo, Integer> getEntregados() { return entregados; }
}
