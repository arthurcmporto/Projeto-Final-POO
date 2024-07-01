import java.util.Date;

public class ContaPoupancaFactory implements ContaFactory {
    private double saldoMinimo;
    private Date dataAbertura;

    public ContaPoupancaFactory(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
        this.dataAbertura = new Date();
    }

    @Override
    public Conta criarConta(int numeroConta, double saldo) {
        return new ContaPoupanca(numeroConta, saldo, saldoMinimo, dataAbertura);
    }
}
