package SocketProgramming;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Plainserver {
	static int PortNumber = 9091;
	
	void run() throws IOException {
		System.out.println("hello from the server");
		ServerSocket listener = new ServerSocket(PortNumber);
		Charset charset = Charset.forName("UTF-8");
		try {
			while (true) {
				try (Socket socket = listener.accept()) {
					InputStreamReader isr = new InputStreamReader(socket.getInputStream());
					BufferedReader reader = new BufferedReader(isr);
					String line = reader.readLine();
					String url = null;
					int sd = 0;
					System.out.println("===============START=============");
					while (line != null && !line.isEmpty()) {
						System.out.println(line);

						String[] dic = line.split(" ");
						for (String a : dic) {
							if (sd == 1) {
								url = a;
								sd = 0;
							}
							if (a.equals("GET")) {
								System.out.println("=====> FOUND GET");
								sd = 1;
							}
						}
						line = reader.readLine();
					}
					System.out.println("url is: " + url);
					System.out.println("===============END=============");

					String fileName = null;
					if (url != null) {
						String[] sl = url.split("/");
						int i = 1;
						for (String a : sl) {
							System.out.println(i + " ==== > " + a);
							if (i == 2) {
								fileName = a;
							}
							i++;
						}
						String directory = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/";
						System.out.println("file name is: " + fileName);
						int found = findFile(fileName, new File(directory));
						if (found == 0) {
							String httpResponse_fileNotFound = "HTTP/1.1 200 OK\r\n\r\n" + "404 File Not found";
							socket.getOutputStream().write(httpResponse_fileNotFound.getBytes("UTF-8"));
						} else {
							String path = "/Users/puevigreven/Desktop/NetworkLab/ComputerNetworkLab/src/SocketProgramming/"
									+ fileName;

							String content = readFile(path, charset);
							System.out.println(content);
							String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + content;
							socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
						}

					}

				}
			}
		} finally

		{
			System.out.println("Closing");
			listener.close();
		}
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
						System.out.println("Found the file in : " + fil.getParentFile());
					}
				}
			}
		return found;
	}

}
