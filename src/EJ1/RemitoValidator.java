package EJ1;

import java.util.List;

public class RemitoValidator {

    public static void validarRemito(Remito remito, Pedido pedido) {
        if (!pedido.getEstado().puedoAgregarRemito()) {
            throw new IllegalStateException("No se puede agregar un remito en el estado actual del pedido.");
        }

        if (remito.getLineas().isEmpty()) {
            throw new IllegalArgumentException("El remito no puede estar vacío");
        }

        for (LineaRemito lr : remito.getLineas()) {
            validarLineaRemito(lr, pedido);
        }
    }

    private static void validarLineaRemito(LineaRemito lineaRemito, Pedido pedido) {
        Articulo articulo = lineaRemito.getArticulo();
        int cantidadRemito = lineaRemito.getCantidad();

        // Validar que el artículo exista en el pedido
        int cantidadPedido = pedido.buscarCantidadPedido(articulo);
        if (cantidadPedido == 0) {
            throw new IllegalArgumentException("El artículo '" + articulo.getNombre() +
                    "' no existe en el pedido");
        }

        // Validar que no supere lo pendiente
        int pendiente = pedido.getPendiente(articulo);
        if (cantidadRemito > pendiente) {
            throw new IllegalArgumentException("Cantidad excede lo pendiente para '" +
                    articulo.getNombre() + "'. Pendiente: " + pendiente +
                    ", Remito: " + cantidadRemito);
        }

        if (cantidadRemito <= 0) {
            throw new IllegalArgumentException("Cantidad debe ser mayor a cero para '" +
                    articulo.getNombre() + "'");
        }
    }
}
