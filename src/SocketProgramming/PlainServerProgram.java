package SocketProgramming;

import java.io.IOException;
import java.nio.charset.Charset;

public class PlainServerProgram {
	static Charset charset = Charset.forName("UTF-8");
	static int Number_Of_Clients = 0;
	static String defaultpath = null;

	public static void main(String[] args) throws IOException {
		 Plainserver p = new Plainserver();
		 p.run();
		}
}

	