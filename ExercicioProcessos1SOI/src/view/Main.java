package view;
import javax.swing.JOptionPane;

import controller.RedesController;
public class Main {
	public static void main(String[] args) {
		RedesController co = new RedesController();
		int opc = 0;
		while(opc!=9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Verficiar IP\n2"
					+ " - Verificar PING\n9 - Sair"));
			switch(opc) {
				case 1:
					co.verifyIp();
					break;
				case 2:
					co.verifyPing();
			}
				
		}
	}
}
