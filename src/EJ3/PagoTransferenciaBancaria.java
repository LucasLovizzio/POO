package EJ3;

import java.math.BigDecimal;

public class PagoTransferenciaBancaria implements FormaDePago {

    private String cbuOrigen;

    public PagoTransferenciaBancaria() {
    }

    public PagoTransferenciaBancaria(String cbuOrigen) {
        this.cbuOrigen = cbuOrigen;
    }

    @Override
    public void pagar(BigDecimal importe) {
        Banco.cobrar(cbuOrigen, importe); // TransferenciaBancaria es una API externa.
    }

    public String getCbuOrigen() {
        return cbuOrigen;
    }
}
