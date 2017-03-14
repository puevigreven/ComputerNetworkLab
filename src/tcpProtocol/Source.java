package tcpProtocol;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Source {

	int fileSize = 100;
	int b = 10;
	int win = 5;
	double pd = 0.3;
	int packetSize = 2;

	LinkedList<Packet> q = new LinkedList<Packet>();
	LinkedList<Packet> al = new LinkedList<Packet>();

	public Source(int fs, int b) {
		this.fileSize = fs;
		this.b = b;
	}

	public Source(int fs, int b, int Qin) {
		this.fileSize = fs;
		this.b = b;
		this.win = Qin;
	}

	public Source(int fs, double pd) {
		this.fileSize = fs;
		this.pd = pd;
	}

	void init() {
		for (int i = 1; i <= (fileSize / packetSize); i++) {
			al.add(new Packet(i));
		}
		for (int j = 0; j < win; j++) {
			q.add(al.poll());
		}

	}

	void sim() {

		int ct = 0;
		while (!al.isEmpty()) {
			ct = ct + 1;
			for (int s = 0; s < win; s++) {
				Packet p = q.get(s);
				Random rand = new Random();
				double m = (rand.nextInt(10) + 1);
				double n = m / 10.0;

				if (p != null) {

					if (p.start > ct) {
						continue;
					}
					if (pd <= n && p.start <= ct) {
						q.remove(s);
						q.add(al.poll());
					} else if (pd > n && p.start <= ct) {
						p.setStart(p.getStart() + win);
					}
				}else{
					break;
				}

			}

		}
		double avgDel = ct - (fileSize / (packetSize * b));
		System.out.println("Bandwidth Size is " + b + " Delay is " + avgDel);
		System.out.println("Qin = " + win +"- Link Utilisation is =" + (double)fileSize/( (double)b * (double)ct) );
		 
	}

}
