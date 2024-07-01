import java.util.Date;

public class ContaCorrenteFactory implements ContaFactory {
    private double taxaJuros;
    private Date dataAbertura;

    public ContaCorrenteFactory(double taxaJuros) {
        this.taxaJuros = taxaJuros;
        this.dataAbertura = new Date();
    }

    @Override
    public Conta criarConta(int numeroConta, double saldo) {
        return new ContaCorrente(numeroConta, saldo, taxaJuros, dataAbertura);
    }
}
