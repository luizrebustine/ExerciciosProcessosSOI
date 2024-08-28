package view;
import javax.swing.JOptionPane;

import controller.KillController;
public class Main {

	public static void main(String[] args) {
		KillController kc = new KillController();
		int opc = 0;
		while(opc!=9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Listar Processos\n2"
					+ " - Matar por PID\n3 - Matar por NOME\n9 - Sair"));
			switch(opc) {
				case 1:
					kc.listaProcessos();
					break;
				case 2:
					int pid = Integer.parseInt(JOptionPane.showInputDialog("Informe o PID"));
					kc.mataPid(pid);
				case 3:
					String nome = JOptionPane.showInputDialog("Informe o NOME");
					kc.mataNome(nome);
			}
				
		}
	}

}
