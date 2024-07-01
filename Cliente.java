public class Cliente {
    private int idCliente;
    private String nome;
    private String telefone;
    private ContaPoupanca contaPoupanca;
    private ContaCorrente contaCorrente;

    private Cliente(ClienteBuilder builder) {
        this.idCliente = builder.idCliente;
        this.nome = builder.nome;
        this.telefone = builder.telefone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public boolean temContaPoupanca() {
        return contaPoupanca != null;
    }

    public boolean temContaCorrente() {
        return contaCorrente != null;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public static class ClienteBuilder {
        private int idCliente;
        private String nome;
        private String telefone;

        public ClienteBuilder setIdCliente(int idCliente) {
            this.idCliente = idCliente;
            return this;
        }

        public ClienteBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClienteBuilder setTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }
}