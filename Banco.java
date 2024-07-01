import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Banco {
    private static Scanner scanner = new Scanner(System.in);
    private static Agencia agencia = new Agencia("001", "Cidade Exemplo");
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void iniciar() {
        while (true) {
            mostrarMenu();
            int opcao = lerOpcao();
            executarOpcao(opcao);
        }
    }

    private static void mostrarMenu() {
        System.out.println("1. Adicionar Cliente");
        System.out.println("2. Remover Cliente");
        System.out.println("3. Adicionar Conta");
        System.out.println("4. Remover Conta");
        System.out.println("5. Debitar Valor");
        System.out.println("6. Creditar Valor");
        System.out.println("7. Gerar Relatório de Contas");
        System.out.println("8. Gerar Relatório de Clientes");
        System.out.println("0. Sair");
        System.out.println("Escolha uma opção:");
    }

    private static int lerOpcao() {
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir newline
        return opcao;
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarCliente();
                break;
            case 2:
                removerCliente();
                break;
            case 3:
                adicionarConta();
                break;
            case 4:
                removerConta();
                break;
            case 5:
                debitarValor();
                break;
            case 6:
                creditarValor();
                break;
            case 7:
                gerarRelatorioContas();
                break;
            case 8:
                gerarRelatorioClientes();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void adicionarCliente() {
        int id = lerInt("ID do Cliente:");
        String nome = lerString("Nome do Cliente:");
        String telefone = lerString("Telefone do Cliente:");

        if (clienteExiste(id, nome, telefone)) {
            System.out.println("Erro: Já existe um cliente com este ID, nome ou telefone.");
            return;
        }

        Cliente cliente = new Cliente.ClienteBuilder()
                .setIdCliente(id)
                .setNome(nome)
                .setTelefone(telefone)
                .build();
        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static boolean clienteExiste(int id, String nome, String telefone) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == id ||
                    cliente.getNome().equalsIgnoreCase(nome) ||
                    cliente.getTelefone().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    private static void removerCliente() {
        int id = lerInt("ID do Cliente a ser removido:");
        Cliente clienteRemover = encontrarClientePorId(id);

        if (clienteRemover != null) {
            clientes.remove(clienteRemover);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente encontrarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        return null;
    }

    private static void adicionarConta() {
        int id = lerInt("ID do Cliente para a conta:");
        Cliente cliente = encontrarClientePorId(id);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        int tipo = lerInt("Tipo de conta (1: Corrente, 2: Poupança):");
        int numeroConta = lerInt("Número da Conta:");
        double saldo = lerDouble("Saldo inicial:");
        ContaFactory contaFactory = criarContaFactory(tipo);

        if (contaFactory == null) {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        Conta conta = contaFactory.criarConta(numeroConta, saldo);
        if (conta instanceof ContaCorrente) {
            cliente.setContaCorrente((ContaCorrente) conta);
        } else if (conta instanceof ContaPoupanca) {
            cliente.setContaPoupanca((ContaPoupanca) conta);
        }

        agencia.criaConta(conta);
        System.out.println(tipo == 1 ? "Conta Corrente adicionada com sucesso!" : "Conta Poupança adicionada com sucesso!");
    }

    private static ContaFactory criarContaFactory(int tipo) {
        if (tipo == 1) {
            double taxaJuros = lerDouble("Taxa de juros:");
            return new ContaCorrenteFactory(taxaJuros);
        } else if (tipo == 2) {
            double saldoMinimo = lerDouble("Saldo mínimo:");
            return new ContaPoupancaFactory(saldoMinimo);
        }
        return null;
    }

    private static void removerConta() {
        int numeroConta = lerInt("Número da Conta a ser removida:");
        boolean removida = agencia.removeConta(numeroConta);

        if (removida) {
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void debitarValor() {
        int numeroConta = lerInt("Número da Conta:");
        double valor = lerDouble("Valor a debitar:");
        Conta conta = encontrarConta(numeroConta);

        if (conta != null && conta.debitarValor(valor)) {
            System.out.println("Valor debitado com sucesso!");
        } else {
            System.out.println("Erro ao debitar valor: saldo insuficiente ou valor inválido.");
        }
    }

    private static void creditarValor() {
        int numeroConta = lerInt("Número da Conta:");
        double valor = lerDouble("Valor a creditar:");
        Conta conta = encontrarConta(numeroConta);

        if (conta != null && conta.creditarValor(valor)) {
            System.out.println("Valor creditado com sucesso!");
        } else {
            System.out.println("Erro ao creditar valor: valor inválido.");
        }
    }

    private static Conta encontrarConta(int numeroConta) {
        for (Conta conta : agencia.getContas()) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    private static void gerarRelatorioContas() {
        ArrayList<Conta> contas = agencia.getContas();
        contas.sort((c1, c2) -> Integer.compare(c1.getNumeroConta(), c2.getNumeroConta()));
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }

    private static void gerarRelatorioClientes() {
        clientes.sort((c1, c2) -> c1.getNome().compareTo(c2.getNome()));
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static int lerInt(String mensagem) {
        System.out.println(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consumir newline
        return valor;
    }

    private static String lerString(String mensagem) {
        System.out.println(mensagem);
        return scanner.nextLine();
    }

    private static double lerDouble(String mensagem) {
        System.out.println(mensagem);
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline
        return valor;
    }
}