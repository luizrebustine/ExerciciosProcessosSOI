package controller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class RedesController {
	
	public RedesController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void verifyIp() {
		String os = os();
		String adaptador = null;
		String[] procArr = null;
		if(os.contains("Windows")) {
			procArr = "IPCONFIG".split(" ");
		}
		else if(os.contains("Linux")) {
			procArr = "ifconfig".split(" ");
		}
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine().toString();
			while(linha!=null) {
				if(linha.contains("Adaptador")) {
					adaptador = linha;
				}
				if(linha.contains("IPv4")) {
					System.out.println(adaptador);
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
	
	public void verifyPing() {
		String os = os();
		String[] procArr = null;
		if(os.contains("Windows")) {
			procArr = "ping -4 -n 10 www.google.com.br".split(" ");
		}
		else if(os.contains("Linux")) {
			procArr = "ping -4 -c 10 www.google.com.br".split(" ");
		}
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine().toString();
			while(linha!=null) {
				if(os.contains("Windows")) {
					if(linha.contains("dia = ")) {
						String[] linhaArr = linha.split(" ");
						System.out.println("Média: " + linhaArr[linhaArr.length-1]);
					}
				}
				if(os.contains("Linux")) {
					if(linha.contains("avg")) {
						String[] linhaArr = linha.split("/");
						System.out.println("Média: " + linhaArr[4]);
					}
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
}