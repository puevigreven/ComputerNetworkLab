package tcpProtocol;

public class Simulation {

	int fileSize = 10;
	int b = 2;
	int Qin = 5;
	double pd = 0.5;

	public static void main(String args[]) {

		System.out.println("Filesize vs Bandwidth");
		for (int i = 10; i <= 100; i = i + 10) {
			Source s = new Source(100, i);
			s.init();
			s.sim();
		}

		System.out.println("Filesize vs Window Size");
		for (int i = 1; i <= 10; i = i + 1) {
			Source s = new Source(100, 10, i);
			s.init();
			s.sim();
		}

		System.out.println("Filesize vs Probablity");
		for (double i = 0.1; i <= 1.0; i = i + 0.1) {
			Source s = new Source(100, i);
			s.init();
			s.sim();
		}
		
		System.out.println("Link Utilisation vs Qin");
		for (int i = 1; i <= 10; i = i + 1) {
			Source s = new Source(100, 10, i);
			s.init();
			s.sim();
		}
		
		System.out.println("Link Utilisation vs Bandwidth");
		for (int i = 10; i <= 100; i = i + 10) {
			Source s = new Source(100, i);
			s.init();
			s.sim();
		}


	}

}
