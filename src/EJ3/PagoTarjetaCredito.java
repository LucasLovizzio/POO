package EJ3;

import java.math.BigDecimal;

public class PagoTarjetaCredito implements FormaDePago {

    private String nombreTarjeta;
    private String numero;
    private int CVV;

    public PagoTarjetaCredito() {
    }

    public PagoTarjetaCredito(String nombreTarjeta, String numero, int CVV) {
        this.nombreTarjeta = nombreTarjeta;
        this.numero = numero;
        this.CVV = CVV;
    }

    @Override
    public void pagar(BigDecimal importe) {
        Tarjeta.cobrar(nombreTarjeta, numero, CVV, importe); // TarjetaCredito es una API externa.
    }

    private String getNombreTarjeta() {
        return nombreTarjeta;
    }

    private String getNumero() {
        return numero;
    }

    private int getCVV() {
        return CVV;
    }

}
