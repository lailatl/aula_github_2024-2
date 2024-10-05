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


    ////////////falta implemetar//////////////
    public void cadastrarCliente(){
        System.out.println(espaco+"Cadastro de Cliente\n");
        
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

     ////////////falta implemetar//////////////
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

    ////////////falta implemetar//////////////
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
                cliente.getConta().addTransacao(transacao);
                
                System.out.println(format+"Depósito realizado com sucesso!\n");
                System.out.println("Novo saldo: " + cliente.getConta().getSaldo()+format);
            }
        }
        
    }

    ////////////falta implemetar//////////////
    public void fazerSaque(){
        System.out.println(espaco+"Realizar Saque na Conta\n");
        
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
        return null; // Return null if no matching client is found
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
