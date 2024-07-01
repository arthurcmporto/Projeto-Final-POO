import java.util.ArrayList;

public class Agencia {
    private String codigo;
    private String cidade;
    private ArrayList<Conta> contas;

    public Agencia(String codigo, String cidade) {
        this.codigo = codigo;
        this.cidade = cidade;
        this.contas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public void criaConta(Conta conta) {
        contas.add(conta);
    }

    public boolean removeConta(int numeroConta) {
        return contas.removeIf(conta -> conta.getNumeroConta() == numeroConta);
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }
}
