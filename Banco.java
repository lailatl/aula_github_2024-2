import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;

public class Banco {
    Scanner s = new Scanner(System.in);
    String format = "\n********************************\n"; // usar para mensagens de concluído
    String warning = "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n"; // usar para mensgens de erro
    String espaco = "\n-----------------------------------------------\n"; // usar antes dos nomes das funções principais
    ArrayList<Cliente> Clientes = new ArrayList<>();
    ArrayList<Conta> Contas = new ArrayList<>();


    public Banco() {
        ArrayList<Conta> Contas = new ArrayList<>();
        Cliente cliente1 = new Cliente("ygor", "123" , "rua tal","2222-3333", "yfcm@mail");
        Clientes.add(cliente1);
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

            System.out.println(format+"Cliente Cadastrado Com Sucesso");
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
        } else {
            System.out.println(format+"O saldo da sua conta é: " + cliente.getConta().getSaldo()+format);
        }

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
        } else {
            Conta conta = cliente.getConta();
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
        } else {
            double valor;

            System.out.println("\nInforme o valor a ser depositado: ");
            valor = Double.parseDouble(s.nextLine());

            if(valor <=0){
                System.out.println(warning+"Valor inválido\n");
                return;
            } else {
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
                Transacao transacao = new Transacao(LocalDateTime.now(), "Depósito", valor);
                cliente.getConta().setTransacao(transacao);
                
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
            conta.setSaldo(conta.getSaldo() - valor);

            String tipo = "Saque";
            Transacao transacao = new Transacao(LocalDateTime.now(), tipo, valor);
            conta.setTransacao(transacao);

            System.out.println("Saque de valor " + valor + " realizado com sucesso. Novo saldo: " + conta.getSaldo() + format);
        } else {
            System.out.println(warning + "Saldo insuficiente para realizar o saque\n");
        }
    }

    ////////////falta implemetar//////////////
    public void pagarFatura(){
        System.out.println(espaco+"Realizar Pagamento de Fatura\n");
        
    }

    ////////////falta implemetar//////////////
    public void fazerTranferencia(){
        System.out.println(espaco+"Realizar Transferencia entre contas\n");
        
    }

    ////////////falta implemetar//////////////
    public void visualizarHistorico(){
        System.out.println(espaco+"Visualizar Histórico de transações da Conta\n");
        //acessar o arraylist de transações dentro de conta
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
