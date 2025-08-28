package EJ1;

public class PedidoAbierto implements EstadoPedido {

    public PedidoAbierto() {

    }

    @Override
    public boolean puedoAgregarRemito() {
        return false;
    }

    @Override
    public boolean puedoAgregarArticulo() {
        return true;
    }

    @Override
    public boolean puedoCerrarPedido() {
        return false;
    }
}
