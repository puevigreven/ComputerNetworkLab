package SocketProgramming;

import java.io.IOException;

public class PlainServerProgram {
	public static void main(String[] args) throws IOException {
		Plainserver s = new Plainserver();
		s.run();
	}
}
