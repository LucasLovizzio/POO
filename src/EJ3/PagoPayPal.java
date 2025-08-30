package EJ3;

import java.math.BigDecimal;

public class PagoPayPal implements FormaDePago {

    private String usuario;

    public PagoPayPal() {
    }

    public PagoPayPal(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public void pagar(BigDecimal importe) {
        PayPal.cobrar(usuario, importe); // PayPal es una API externa.
    }

    public String getUsuario() {
        return usuario;
    }
}
