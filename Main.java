import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<Conta> contas = new ArrayList<>();

		Menu mainMenu =  new Menu("\nMenu Principal", Arrays.asList("Conta", "Cliente", "Operacoes", "Sair"));
		Menu contaMenu = new Menu("\nMenu Conta", Arrays.asList("Cadastrar"));

		int opMainMenu = mainMenu.getSelection();

		while(opMainMenu != 4) {
			switch(opMainMenu) {
				case 1:
					int opContaMenu = contaMenu.getSelection();
					switch(opContaMenu) {
							case 1:
								int numero, agencia;
								String tipo;

								System.out.println("\nCadastro de conta\n");
								System.out.println("Informe o numero da conta: ");
								numero = Integer.parseInt(s.nextLine());

								System.out.println("\nInforme a agencia: ");
								agencia = Integer.parseInt(s.nextLine());

								System.out.println("\nInforme o tipo da conta: ");
								tipo = s.nextLine();

								contas.add(new Conta(numero, agencia, tipo));
								System.out.println("\nConta cadastrada com sucesso!");
								break;
							default:
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