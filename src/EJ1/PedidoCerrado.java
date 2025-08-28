package EJ1;

public class PedidoCerrado implements EstadoPedido{

    public PedidoCerrado() {

    }

    @Override
    public boolean puedoAgregarRemito() {
        return true;
    }

    @Override
    public boolean puedoAgregarArticulo() {
        return false;
    }

    @Override
    public boolean puedoCerrarPedido() {
        return true;
    }
}
