package EJ1;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainEJ1 {
    public static void main(String[] args) {

        // Crear artículos
        Articulo art1 = new Articulo("Laptop", "1200.00");
        Articulo art2 = new Articulo("Mouse", "25.50");

        // Crear pedido
        Pedido pedido = new Pedido(1, "Cliente Ejemplo");

        // Agregar líneas al pedido
        pedido.agregarLineaPedido(art1, 2);
        pedido.agregarLineaPedido(art2, 5);

        try {
            pedido.cambiarAPedidoCerrado();
            System.out.println("Pedido cambiado a estado cerrado exitosamente.");
        } catch (IllegalStateException e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }

        // Crear remito válido
        Remito remito1 = new Remito(1, new Date());
        remito1.agregarLinea(new LineaRemito(art1, 2));
        remito1.agregarLinea(new LineaRemito(art2, 5));

        try {
            RemitoValidator.validarRemito(remito1, pedido);
            pedido.agregarRemito(remito1);
            System.out.println("Remito validado y agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }

        // Intentar crear remito inválido (artículo no existente)
        Articulo art3 = new Articulo("Teclado", "45.00");
        Remito remitoInvalido = new Remito(2, new Date());
        remitoInvalido.agregarLinea(new LineaRemito(art3, 1));

        try {
            RemitoValidator.validarRemito(remitoInvalido, pedido);
            pedido.agregarRemito(remitoInvalido);
            System.out.println("Remito inválido agregado (esto no debería aparecer).");
        } catch (IllegalArgumentException e) {
            System.out.println("Validación falló como se esperaba: " + e.getMessage());
        }

        System.out.println("\n=== Probando funcionalidad de entrega ===");

        // Verificar si no hay más productos para remitos
        if (pedido.noHayMasProductosParaRemitos()) {
            System.out.println("No hay más productos disponibles para remitos.");

            // Entregar el pedido usando el nuevo metodo
            try {
                pedido.entregarPedidoSinMasProductos();
                System.out.println("Pedido entregado exitosamente.");
            } catch (IllegalStateException e) {
                System.out.println("Error al entregar: " + e.getMessage());
            }
        } else {
            System.out.println("Aún hay productos disponibles para remitos.");
        }

        System.out.println(PedidoReportGenerator.generarResumenCompleto(pedido));

        // Mostrar estado final del pedido
        System.out.println("Estado final del pedido: " + pedido.getEstado().getClass().getSimpleName());
    }
}
