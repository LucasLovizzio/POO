package EJ1;

import java.util.Date;
import java.util.List;

public class PedidoReportGenerator {

    public static String generarResumenCompleto(Pedido pedido) {
        StringBuilder sb = new StringBuilder();

        // Información del pedido
        sb.append(String.format("=== RESUMEN DEL PEDIDO %d ===%n", pedido.getNumero()));
        sb.append(String.format("Cliente: %s%n", pedido.getCliente()));
        sb.append(String.format("Estado: %s%n", pedido.getEstado().getClass().getSimpleName()));

        // Verificar disponibilidad de productos para remitos
        boolean hayProductosDisponibles = !pedido.noHayMasProductosParaRemitos();

        if (hayProductosDisponibles) {
            sb.append("%nAún hay productos disponibles para remitos.%n");
            sb.append(generarListaProductosDisponibles(pedido));
        } else {
            sb.append("%nNo hay más productos disponibles para remitos.%n");
            sb.append(generarMensajeEstado(pedido));
        }

        return sb.toString();
    }

    private static String generarListaProductosDisponibles(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("Productos disponibles para remito:%n");

        for (LineaPedido lp : pedido.getLineas()) {
            Articulo articulo = lp.getArticulo();
            int pendiente = pedido.getPendiente(articulo);

            if (pendiente > 0) {
                sb.append(String.format("- %s: %d unidades disponibles%n",
                        articulo.getNombre(), pendiente));
            }
        }

        return sb.toString();
    }

    private static String generarMensajeEstado(Pedido pedido) {
        StringBuilder sb = new StringBuilder();

        if (pedido.getEstado() instanceof PedidoCerrado) {
            sb.append("El pedido puede ser entregado.%n");
        } else if (pedido.getEstado() instanceof PedidoEntregado) {
            sb.append("El pedido ha sido entregado exitosamente.%n");
            if (pedido.getFechaDeEntrega() != null) {
                sb.append(String.format("Fecha de entrega: %s%n", pedido.getFechaDeEntrega()));
            }
        }

        return sb.toString();
    }
}
