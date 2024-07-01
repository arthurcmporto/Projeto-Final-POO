import java.util.Date;

public class ContaCorrente extends Conta {
    private double taxaJuros;
    private Date dataAbertura;

    public ContaCorrente(int numeroConta, double saldo, double taxaJuros, Date dataAbertura) {
        super(numeroConta, saldo);
        this.taxaJuros = taxaJuros;
        this.dataAbertura = dataAbertura;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numeroConta=" + getNumeroConta() +
                ", saldo=" + getSaldo() +
                ", taxaJuros=" + taxaJuros +
                ", dataAbertura=" + dataAbertura +
                '}';
    }
}