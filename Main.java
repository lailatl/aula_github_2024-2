import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		Banco banco = new Banco();

		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		Menu contaMenu = new Menu("Menu Conta", Arrays.asList("Cadastrar", "Consultar Saldo", "Consultar Histórico", "Visualizar"));
		Menu clientMenu = new Menu("Menu Cliente", Arrays.asList("Cadastrar", "Visualizar"));
		Menu operaMenu = new Menu("Menu Operações", Arrays.asList("Saque", "Depósito", "Tranferência", "Pagar fatura"));
		int opMainMenu = mainMenu.getSelection();

		while(opMainMenu != 4) {
			switch(opMainMenu) {
				case 1:
					int opContaMenu = contaMenu.getSelection();
					switch(opContaMenu) {
							case 1:
								banco.cadastrarConta();
								break;
							default:
							case 2:
								
								break;
							
						}
					break;
				case 2:
					System.out.println("Cliente");
					break;
				case 3:
					System.out.println("Operacoes");
					break;
				default:
					break;
			}
			opMainMenu = mainMenu.getSelection();
		}

		s.close();
		System.out.println("Fim");
	}

}