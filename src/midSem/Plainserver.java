package midSem;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class Plainserver {

	static int PortNumber = 9092;

	void run() throws IOException, InterruptedException {
		String httpResponse;
		ServerSocket listener = new ServerSocket(PortNumber);
		System.out.println("hello from the server");
		try {
			while (true) {
				try (Socket socket = listener.accept()) {

					InputStreamReader isr = new InputStreamReader(socket.getInputStream());
					BufferedReader reader = new BufferedReader(isr);
					String line = reader.readLine();
					System.out.println("input: " + line);

					System.out.println(line);

					String[] dic = line.split(" ");
					String file = dic[1];
					String path = dic[2];

					System.out.println("file name: " + file);
					System.out.println("path : " + path);
					File source = new File(file);
					File dest = new File(path);
					int s = copyfile(source, dest);

					if (s == 0) {
						httpResponse = "File copy failed";

					} else {
						httpResponse = "OK";
					}
					log(httpResponse);
					
					socket.getOutputStream().write(httpResponse.getBytes());
				}
			}
		} finally

		{
			System.out.println("Closing");
			listener.close();
		}
	}

	public static int copyfile(File source, File dest) {
		try {
			FileUtils.copyFileToDirectory(source, dest);
		} catch (IOException e) {
			return 0;
		}
		return 1;
	}

	private static void log(String message) {
		System.out.println(message);
	}

}
