package SocketProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlainServerProgram {
	static Charset charset = Charset.forName("UTF-8");
	static int Number_Of_Clients = 0;
	static String defaultpath = null;

	public static void main(String[] args) throws IOException {
	//	Plainserver p = new Plainserver();
	//	p.run();
		readConfigFile();
		int clientNumber = 0;
		ServerSocket listener = new ServerSocket(9091);
		try {
			while (true) {
				log("inside loop" + clientNumber);
				if (clientNumber < 10) {
					new MultithreadingServer(listener.accept(), clientNumber++, defaultpath).run();
				}else{
					log("Server numbers maxed out");
					break;
				}
			}
		} finally {
			listener.close();
		}
	}

	static void readConfigFile() throws IOException {
		// log("Reading the config file \n");
		String path = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/config";
		String configData = readFile(path, charset);
		String noc = null;
		String[] dic = configData.split("\\s+");

		int tst = 0;
		for (String a1 : dic) {
			String[] dic1 = a1.split("=");
			for (String a : dic1) {

				if (tst == 1) {
					noc = a;
					tst = 0;
				}
				if (tst == 2) {
					defaultpath = a;
				}
				if (a.equals("Number_Of_Clients")) {
					tst = 1;
				}
				if (a.equals("Default_HTML_File")) {
					tst = 2;
				}
			}
		}

		Number_Of_Clients = Integer.parseInt(noc);

		// log("Number_Of_Clients:" + Number_Of_Clients);
		// log("defaultpath:" + defaultpath);

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	private static void log(String message) {
		System.out.println(message);
	}
}
