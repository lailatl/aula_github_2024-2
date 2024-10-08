import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;

public class Banco {
    Scanner s = new Scanner(System.in);
    String format = "\n********************************\n"; // usar para mensagens de concluído
    String warning = "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n"; // usar para mensgens de erro
    String espaco = "\n-----------------------------------------------\n"; // usar antes dos nomes das funções principais
    ArrayList<Cliente> Clientes;
    ArrayList<Conta> Contas;

    public Banco() {
        Clientes = new ArrayList<>();
        Contas = new ArrayList<>();
        Cliente cliente1 = new Cliente("ygor", "123" , "rua tal","2222-3333", "yfcm@mail");
        Cliente cliente2 = new Cliente("mancini", "456" , "rua tal tal","4444-5555", "mancini@mail");
        Conta conta1 = new Conta(14365367, 567, "Corrente");
        Conta conta2 = new Conta(43256754, 327, "Corrente");
        cliente2.setConta(conta1);
        cliente1.setConta(conta2);
        //this.fazerDeposito();
        //fazerDeposito();
        //LocalDateTime data = LocalDateTime.now();
        //cliente2.getConta().setTransacao(new Transacao(LocalDateTime.now(), "Depósito", 150));
        //cliente2.getConta().setTransacao(new Transacao(LocalDateTime.now().plusDays(5), "Saque", 150));

        Clientes.add(cliente1);
        Clientes.add(cliente2);
    }

    public void cadastrarCliente(){
        System.out.println(espaco+"Cadastro de Cliente\n");
        String nomeCompleto, cpf, endereco, telefone, email;

        //Já é pedido o CPF de primeira. Caso já exista, o processo de cadastro se encerra na hora.
        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        //Se NÃO exister cliente com o cpf dado anteriormente, o cadastro se inicia de fato.
        if(this.findCliente(cpf)==null){
            System.out.println("\nInforme o seu nome completo: ");
            nomeCompleto = s.nextLine();

            System.out.println("\nInforme o seu endereço: ");
            endereco = s.nextLine();

            System.out.println("\nInforme o seu telefone: ");
            telefone = s.nextLine();

            System.out.println("\nInforme o seu melhor email: ");
            email = s.nextLine();

            this.Clientes.add(new Cliente(nomeCompleto, cpf, endereco, telefone, email));

            System.out.println(format+"Cliente Cadastrado Com Sucesso"+format);
            return;
        }
        System.out.println(warning+"Cliente Já Possui Cadastro");
        return;
    }

    public void cadastrarConta(){
        String tipo, cpf;
        
        System.out.println(espaco+"Cadastro de conta\n");
        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        } else {
            System.out.println("\nInforme o tipo da conta(Corrente ou Poupança): ");
            tipo = s.nextLine();
            int numero = gerarNumeroConta();
            int agencia = gerarNumeroAgencia();
            Conta conta = new Conta(numero, agencia, tipo);
            cliente.setConta(conta);
            System.out.println(format+"Conta cadastrada com sucesso!\n");
            System.out.println("numero:"+ numero +"\nagencia:"+ agencia + format);

        }
        
    }

    public void consultarSaldo(){
        String cpf;

        System.out.println(espaco+"Consultar Saldo da Conta\n");
        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        }
        Conta conta = cliente.getConta();
        if (conta == null) {
            System.out.println(warning + "Cliente não possui uma conta cadastrada\n");
            return;
        }
        System.out.println(format + "O saldo da sua conta é: " + conta.getSaldo() + format);
    }

     public void visualizarCliente(){
        String cpf;
        System.out.println(espaco+"Visualizar Dados do Cliente\n");

        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        } else {
            System.out.println(format+"DADOS CADASTRADOS\n");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Email: " + cliente.getEmail()+format);
        }
        
    }

    public void visualizarConta(){
        String cpf;
        System.out.println(espaco+"Visualizar Dados de Conta\n");

        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        }
        Conta conta = cliente.getConta();
        if (conta == null){
            System.out.println(warning + "Cliente não possui uma conta cadastrada\n");
            return;
        }else{
            System.out.println(format+"DADOS DA CONTA\n");
            System.out.println("Número: " + conta.getNumero());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Tipo: " + conta.getTipo());
            System.out.println("Saldo: " + conta.getSaldo()+format);
        }
    }

    public void fazerDeposito(){
        String cpf;
        System.out.println(espaco+"Realizar Depósito na Conta\n");

        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;

        } else if(cliente.getConta() == null){
            System.out.println(warning+"Conta não cadastrada\n");
            return;

        } else {
            double valor;

            System.out.println("\nInforme o valor a ser depositado: ");
            valor = Double.parseDouble(s.nextLine());

            if(valor <=0){
                System.out.println(warning+"Valor inválido\n");
                return;
            } else {
                Transacao transacao = new Transacao(LocalDateTime.now(), "Depósito", valor, cliente.getConta().getSaldo(), cliente.getConta().getSaldo() + valor);
                cliente.getConta().setTransacao(transacao);

                cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
                
                System.out.println(format+"Depósito realizado com sucesso!\n");
                System.out.println("Novo saldo: " + cliente.getConta().getSaldo()+format);
            }
        }
        
    }

    public void fazerSaque() {
        System.out.println(espaco + "Realizar Saque na Conta\n");

        System.out.print("Informe o número do seu CPF: ");
        String cpf = s.nextLine();

        Cliente cliente = findCliente(cpf);
        if (cliente == null) {
            System.out.println(warning + "Cliente não cadastrado\n");
            return;
        }

        Conta conta = cliente.getConta();
        if (conta == null) {
            System.out.println(warning + "Conta não encontrada\n");
            return;
        }

        System.out.print("Informe o valor do saque: ");
        double valor = s.nextDouble();
        s.nextLine(); // Consumir a nova linha

        if (valor <= 0) {
            System.out.println(warning + "Valor de saque inválido\n");
            return;
        }

        if (conta.getSaldo() >= valor) {
            String tipo = "Saque";
            Transacao transacao = new Transacao(LocalDateTime.now(), tipo, valor, conta.getSaldo(), conta.getSaldo() - valor);
            conta.setTransacao(transacao);
            conta.setSaldo(conta.getSaldo() - valor);


            System.out.println(format+"Saque de valor " + valor + " realizado com sucesso. Novo saldo: " + conta.getSaldo() + format);
        } else {
            System.out.println(warning + "Saldo insuficiente para realizar o saque\n");
        }
    }

    public void pagarFatura() {
        System.out.println("Realizar Pagamento de Fatura\n");
        
        System.out.print("Informe o número do seu CPF: ");
        String cpf = s.nextLine();
        
        System.out.print("Digite a identificação da fatura (código de barras ou número de referência): ");
        String identificacaoFatura = s.next();

        System.out.print("Digite o valor da fatura: ");
        double valorFatura = s.nextDouble();

        Cliente cliente = findCliente(cpf);
        if (cliente == null) {
            System.out.println(warning + "Cliente não cadastrado\n");
            return;
        }

        Conta conta = cliente.getConta();
        if (conta == null) {
            System.out.println(warning + "Conta não encontrada\n");
            return;
        }
    
        if (conta.getSaldo() >= valorFatura) {
            String tipo = "Pagamento de Fatura" + identificacaoFatura;
            Transacao transacao = new Transacao(LocalDateTime.now(), tipo, valorFatura, conta.getSaldo(), conta.getSaldo() - valorFatura);
            conta.setTransacao(transacao);
            conta.setSaldo(conta.getSaldo() - valorFatura);
    
            System.out.println(format+"Pagamento de fatura no valor de " + valorFatura + " realizado com sucesso. Novo saldo: " + conta.getSaldo());
    
            // Enviar confirmação de pagamento ao cliente (simulação)
            System.out.println("Confirmação de pagamento enviada ao cliente.");
        } else {
            System.out.println("Saldo insuficiente para realizar o pagamento da fatura.");
        }
    }

    public void fazerTransferencia(){
        String cpfRemetente;
        System.out.println(espaco+"Realizar Transferencia entre contas\n");

        System.out.println("Informe o número do seu CPF: ");
        cpfRemetente = s.nextLine();

        Cliente clienteRemetente = findCliente(cpfRemetente);
        if (clienteRemetente == null){ 
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        } 
        
        String cpfDestinatario;
        System.out.println("Informe o número do CPF do destinatário: ");
        cpfDestinatario = s.nextLine();

        Cliente clienteDestinatario = findCliente(cpfDestinatario);
        if(clienteDestinatario == null){
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        } 

        double valor;
        System.out.println("\nInforme o valor a ser transferido: ");
        valor = Double.parseDouble(s.nextLine());

        if(valor <= 0) {
            System.out.println(warning+"Valor inválido\n");
            return;

        }else if(clienteRemetente.getConta().getSaldo() < valor){
            System.out.println(warning+"Saldo insuficiente\n");
            return;

        }else{
            LocalDateTime data = LocalDateTime.now();
            Transacao transacaoRemetente = new Transacao(data, "Transferência", valor, clienteRemetente.getConta().getSaldo(), clienteRemetente.getConta().getSaldo() - valor);
            Transacao transacaoDestinatario = new Transacao(data, "Transferência", valor, clienteDestinatario.getConta().getSaldo(), clienteDestinatario.getConta().getSaldo() + valor);

            transacaoRemetente.setTransacaoCliente(clienteRemetente, clienteDestinatario);
            transacaoDestinatario.setTransacaoCliente(clienteRemetente, clienteDestinatario);
            //transacaoRemetente.setTransacaoCliente(clienteRemetente, clienteDestinatario);

            clienteRemetente.getConta().setSaldo(clienteRemetente.getConta().getSaldo() - valor);
            clienteDestinatario.getConta().setSaldo(clienteDestinatario.getConta().getSaldo() + valor);


            transacaoRemetente.setDescricao("Transferência para " + clienteDestinatario.getNome());
            transacaoDestinatario.setDescricao("Transferência recebida de " + clienteRemetente.getNome());

            clienteRemetente.getConta().setTransacao(transacaoRemetente);
            clienteDestinatario.getConta().setTransacao(transacaoDestinatario);

            System.out.println(format+"Transferência realizada com sucesso!\n");
            System.out.println("Novo saldo: " + clienteRemetente.getConta().getSaldo()+format);
        }
        
    }

    
    public void visualizarHistorico(){
        System.out.println(espaco+"Visualizar Histórico de transações da Conta\n");
        String cpf;

        System.out.println("Informe o número do seu CPF: ");
        cpf = s.nextLine();

        Cliente cliente = this.findCliente(cpf);

        if(cliente == null){
            System.out.println(warning+"Cliente não cadastrado\n");
            return;
        }else if(cliente.getConta() == null){
            System.out.println(warning+"Conta não cadastrada\n");
            return;
        }

        System.out.printf("\nTipo Da Conta: %s\n", cliente.getConta().getTipo());
        System.out.printf("Saldo Atual Da Conta: %.2f\n\n", cliente.getConta().getSaldo());

        for (int i = cliente.getConta().getTransacoes().size() - 1; i >= 0; i--) {

            Transacao transacao = cliente.getConta().getTransacoes().get(i);
            String data = transacao.getDataFormatada();
            double valorTransacao = transacao.getValor();
            double valorAntes = transacao.getSaldoAntesTransacao();
            double valorDepois = transacao.getSaldoPosTransacao();

            if(transacao.getTipo().equals("Depósito")){
                System.out.printf("\nData: %s \n   Tipo: Depósito | Valor Da Transação: %.2f | Saldo Anterior: %.2f | Saldo: %.2f\n", data, valorTransacao, valorAntes,valorDepois);
            }else if(transacao.getTipo().equals("Saque")){
                System.out.printf("\nData: %s \n   Tipo: Saque | Valor Da Transação: %.2f | Saldo Anterior: %.2f | Saldo: %.2f\n", data, -valorTransacao, valorAntes, valorDepois);
            }else if(transacao.getTipo().startsWith("Pagamento de Fatura")){
                System.out.printf("\nData: %s \n   Tipo: %s | Valor Da Transação: %.2f | Saldo Anterior: %.2f | Saldo: %.2f\n", data, transacao.getTipo(), -valorTransacao, valorAntes, valorDepois);
            }else if (transacao.getTipo().equals("Transferência")) {
                if(valorAntes>valorDepois){
                    System.out.printf("\nData: %s \n   Tipo: %s | Enviada para: %s | Valor Da Transação: %.2f | Saldo Anterior: %.2f | Saldo: %.2f\n", data, transacao.getTipo(), transacao.getClienteDestinatario().getNome(), -valorTransacao, valorAntes, valorDepois);
                }else{
                    System.out.printf("\nData: %s \n   Tipo: %s | Recebida por: %s | Valor Da Transação: %.2f | Saldo Anterior: %.2f | Saldo: %.2f\n", data, transacao.getTipo(), transacao.getClienteRemetente().getNome(),valorTransacao, valorAntes, valorDepois);
                }
            }
        }
    }


    ///Métodos de apoio//
    public Cliente findCliente(String cpf){
        for (Cliente cliente : Clientes) {
           if (cliente.getCpf().equals(cpf)){
            return cliente;
           }  
        }
        return null;
    }

    public Conta findConta(int numeroConta) {
        for (Conta conta : Contas) {
            if (conta.getNumero() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public int gerarNumeroConta() {
        Random random = new Random();
        int numero = 10000000 + random.nextInt(90000000); // Gera um número de 8 dígitos
        return numero;
    }

    public int gerarNumeroAgencia() {
        Random random = new Random();
        int numero = 100 + random.nextInt(900); // Gera um número de 3 dígitos
        return numero;
    }





}
