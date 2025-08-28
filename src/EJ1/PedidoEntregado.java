package EJ1;

public class PedidoEntregado implements EstadoPedido {

    public PedidoEntregado() {
    }

    @Override
    public boolean puedoAgregarRemito() {
        return false;
    }

    @Override
    public boolean puedoAgregarArticulo() {
        return false;
    }

    @Override
    public boolean puedoCerrarPedido() {
        return false;
    }
}
