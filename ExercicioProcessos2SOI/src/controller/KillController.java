package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	
	public KillController() {
		super();
	}
	
	private String os() {
		String osname = System.getProperty("os.name");
		return osname;
	}
	public void mataPid(int pid) {
		String os = os();
		if(os.contains("Windows")) {
			mataProcesso("TASKKILL /PID " + pid);
		}
		else if(os.contains("Linux")) {
			mataProcesso("kill -9 " + pid);
		}
	}
	
	public void mataNome(String nome) {
		String os = os();
		if(os.contains("Windows")) {
			mataProcesso("TASKKILL /IM " + nome);
		}
		else if(os.contains("Linux")) {
			mataProcesso("pkill -f " + nome);
		}
	}
	
	public void mataProcesso(String proc) {
		String[] procArr = proc.split(" ");
		try {
			Runtime.getRuntime().exec(procArr);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void listaProcessos() {
		String os = os();
		String[] procArr = null;
		if(os.contains("Windows")) {
			procArr = "TASKLIST /FO TABLE".split(" ");
		}
		else if(os.contains("Linux")) {
			procArr = "ps -ef".split(" ");
		}
		try {
			Process p = Runtime.getRuntime().exec(procArr);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine().toString();
			while(linha!=null) {
				System.out.println(linha);
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
