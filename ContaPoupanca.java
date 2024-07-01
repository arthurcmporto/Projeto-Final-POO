import java.util.Date;

public class ContaPoupanca extends Conta {
    private double saldoMinimo;
    private Date dataAbertura;

    public ContaPoupanca(int numeroConta, double saldo, double saldoMinimo, Date dataAbertura) {
        super(numeroConta, saldo);
        this.saldoMinimo = saldoMinimo;
        this.dataAbertura = dataAbertura;
    }

    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "numeroConta=" + getNumeroConta() +
                ", saldo=" + getSaldo() +
                ", saldoMinimo=" + saldoMinimo +
                ", dataAbertura=" + dataAbertura +
                '}';
    }
}