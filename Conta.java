public abstract class Conta {
    private int numeroConta;
    private double saldo;

    public Conta(int numeroConta, double saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean debitarValor(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean creditarValor(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Conta nº: " + numeroConta + ", Saldo: " + saldo;
    }
}