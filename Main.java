import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		Banco banco = new Banco();

		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		Menu contaMenu = new Menu("Menu Conta", Arrays.asList("Cadastrar", "Consultar Saldo", "Consultar Histórico", "Visualizar"));
		Menu clientMenu = new Menu("Menu Cliente", Arrays.asList("Cadastrar", "Visualizar"));
		Menu operacoesMenu = new Menu("Menu Operações", Arrays.asList("Saque", "Depósito", "Tranferência", "Pagar fatura"));
		int opMainMenu = mainMenu.getSelection();

		while(opMainMenu != 4) {
			switch(opMainMenu) {
				case 1:
					int opContaMenu = contaMenu.getSelection();
					switch(opContaMenu) {
							case 1:
								banco.cadastrarConta();
								break;
							case 2:
								banco.consultarSaldo();
								break;
							case 3:
								banco.visualizarHistorico();
								break;
							case 4:
								banco.visualizarConta();
								break;	
						}
					break;
				case 2:
					int opClientMenu = clientMenu.getSelection();
					switch (opClientMenu) {
						case 1:
							banco.cadastrarCliente();
							break;
						case 2:
							banco.visualizarCliente();
							break;
					}
					break;
				case 3:
					int opOperacoesMenu = operacoesMenu.getSelection();
					switch (opOperacoesMenu) {
						case 1:
							banco.fazerSaque();
							break;
						case 2:
							banco.fazerDeposito();
							break;
						case 3:
							banco.fazerTranferencia();
							break;
						case 4:
							banco.pagarFatura();
							break;
					}
					break;
		
			}
			opMainMenu = mainMenu.getSelection();
		}

		s.close();
		System.out.println("Fim");
	}

}
