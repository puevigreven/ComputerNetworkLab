package SocketProgramming;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultithreadingServer {
	static int PortNumber = 9091;
	static Charset charset = Charset.forName("UTF-8");
	Socket socket;
	int user = 0;
	String directory = null;

	MultithreadingServer(Socket socket, int users, String path) {
		this.user = users;
		this.directory = path;
		this.socket = socket;
	}

	void run() throws IOException {

		System.out.println("hello from the server");
		try {
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			String line = reader.readLine();

			String url = null;
			int sd = 0;
			// log("===============START=============");
			while (line != null && !line.isEmpty()) {
				//log(line);

				String[] dic = line.split(" ");
				for (String a : dic) {
					if (sd == 1) {
						url = a;
						sd = 0;
					}
					if (a.equals("GET")) {
						// System.out.println("=====> FOUND GET");
						sd = 1;
					}
				}
				line = reader.readLine();
			}
			log("url is: " + url);
			// log("===============END=============");

			String fileName = null;
			if (url != null) {
				String[] sl = url.split("/");
				int i = 1;
				for (String a : sl) {
					//System.out.println(i + " ==== > " + a);
					if (i == 2) {
						fileName = a;
					}
					i++;
				}
				directory = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/";
				System.out.println("file name is: " + fileName);
				int found = findFile(fileName, new File(directory));
				if (found == 0) {
					String httpResponse_fileNotFound = "HTTP/1.1 200 OK\r\n\r\n" + "404 File Not found";
					socket.getOutputStream().write(httpResponse_fileNotFound.getBytes("UTF-8"));
				} else {
					String path = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/"
							+ fileName;

					String content = readFile(path, charset);
					//log(content);
					String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + content;
					socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				}

			}
		} finally

		{
			log("Closing");
		}
	}

	static void readConfigFile() throws IOException {
		log("Reading the config file");
		String path = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/config";
		String configData = readFile(path, charset);
		log(configData);

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static int findFile(String name, File file) {
		int found = 0;
		File[] list = file.listFiles();
		if (list != null)
			for (File fil : list) {
				if (fil != null) {
					if (fil.isDirectory()) {
						findFile(name, fil);
					} else if (name.equalsIgnoreCase(fil.getName())) {
						found = 1;
					}
				}
			}
		return found;
	}

	private static void log(String message) {
		System.out.println(message);
	}
}
