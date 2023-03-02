package view;
import javax.swing.JOptionPane;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		
		KillController kill = new KillController();
		
		String nomep = "";
		int pid = 0;
		
		int opc = 0;
		
		do {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Qual serviço gostaria de executar?\n 1 - Escrever no console toda a lista de tarefas\n 2 - Matar um processo por PID\n 3 - Matar um processo pelo nome\n 9 - Finaliza Programa"));
			
			switch(opc) {
			case 1: kill.listaProcessos();
				break;
			case 2: pid = Integer.parseInt(JOptionPane.showInputDialog("Digite o PID do processo"));
					kill.mataPID(pid);
				break;
			case 3: nomep = JOptionPane.showInputDialog("Digite o no me do processo");
					kill.mataNome(nomep);
				break;
			case 9: JOptionPane.showMessageDialog(null, "Programa Finalizado");
				break;
			default: JOptionPane.showMessageDialog(null, "Opção Invalida");
				break;
			}
		} while(opc != 9);

	}

}
