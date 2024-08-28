package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {
	
	public DistroController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void exibeDistro() {
		String os = os();
		if(os.contains("Linux")) {
			String[] procArr = "cat /etc/os-release".split(" ");
			String[] linhaArr = null;
			try {
				Process p = Runtime.getRuntime().exec(procArr);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine().toString();
				while(linha!=null) {
					linhaArr = linha.split("=");
					if (linhaArr[0].equals("NAME") || linhaArr[0].equals("VERSION")) {
						System.out.println(linha);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		else {
			System.out.println("Seu Sistema Operacional não é Linux");
		}
	}
}
