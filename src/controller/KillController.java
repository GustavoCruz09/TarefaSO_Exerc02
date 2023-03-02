package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}
	
	public String os() {
		
		String os = System.getProperty("os.name");
		String[] vtSO = os.split(" ");
		
		if(os.contains("Windows")) {
			os = vtSO[0];
		} else {
			if(os.contains("Linux")) {
				os = vtSO[0];
			}
		}
		
		return os;
		
	}
	
	public void listaProcessos() {
		
		String tasklist = os();
		
		if(tasklist.contains("Windows")) {
			tasklist = "TASKLIST /FO TABLE";
		} else {
			if(tasklist.contains("Linux")) {
				tasklist = "ps -ef";
			}
		}
		
		try {
			Process p = Runtime.getRuntime().exec(tasklist);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo); 
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void mataPID(int pid) {
		String sist = os();
		StringBuffer buffer = new StringBuffer();
		
		if(sist.contains("Windows")) {
			sist = "TASKKILL /PID";
		} else {
			if(sist.contains("Linux")) {
				sist = "kill -9";
			}
		}
		
		try {
			buffer.append(sist);
			buffer.append(" ");
		    buffer.append(pid);
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) {
			String msgErro = e.getMessage();
			if(msgErro.contains("740")) {
				StringBuffer buffer2 = new StringBuffer();
				buffer2.append("cmd /c " + sist);
				try {
					Runtime.getRuntime().exec(buffer2.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.err.println(msgErro);
			}
		}
	}
	
	public void mataNome(String nomep) {
		String sist = os();
		StringBuffer buffer = new StringBuffer();
		
		if(sist.contains("Windows")) {
			sist = "TASKKILL /IM";
		} else {
			if(sist.contains("Linux")) {
				sist = "pkill -f";
			}
		}
		
		try {
			buffer.append(sist);
			buffer.append(" ");
		    buffer.append(nomep);
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) {
			String msgErro = e.getMessage();
			if(msgErro.contains("740")) {
				StringBuffer buffer2 = new StringBuffer();
				buffer2.append("cmd /c " + sist);
				try {
					Runtime.getRuntime().exec(buffer2.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.err.println(msgErro);
			}
		}
	}

}
