package tcpProtocolLab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Source {

	int fileSize = 100;
	int b = 30;
	int b1 = 30;
	int b2 = 20;
	int win1 = 1;
	int win2 = 1;
	int packetSize = 2; // MSS
	int qL = 10;
	LinkedList<Packet> file1 = new LinkedList<Packet>();
	LinkedList<Packet> file2 = new LinkedList<Packet>();
	LinkedList<Packet> q1 = new LinkedList<Packet>();
	LinkedList<Packet> q2 = new LinkedList<Packet>();

	public Source(int fs, int b) {
		this.b = b;
		this.fileSize = fs;
	}

	public Source(int fs, int b, int Qin) {
		this.fileSize = fs;
		// this.b = b;
		// this.win = Qin;
	}

	void init() {
		for (int i = 1; i <= (fileSize / packetSize); i++) {
			file1.add(new Packet(i, 0, 1));
			file2.add(new Packet(i, 0, 2));
		}

	}

	void log(String s) {
		System.out.println(s);
	}

	void sim() {

		// Router rout = new Router();
		// LinkedList<Packet> q = rout.getRouter();
		int ct = 0;
		int temp1 = 0;
		int temp2 = 0;
		while (!file1.isEmpty() && !file2.isEmpty()) {
			int d1 = 1;
			int d2 = 1;

			ct = ct + 1;

			if (!file1.isEmpty()) {
				int qs = q1.size();
				log("qs = " + qs);
				if (qs <= qL && (qL - qs) >= win1) {
					log("1 loop");
					for (int s = 0; s < win1 && d1 <= (b / packetSize); s++, d1++) {
						q1.add(file1.removeFirst());
					}
					temp1 = win1;
					win1 = win1 + 1;

				} else if (qs <= qL && (qL - qs) < win1) {
					log("2 loop");
					for (int s = 0; s < (qL - qs); s++) {
						q1.add(file1.removeFirst());
					}
					log("=>" + q1.size());
				}
				if (qs == qL) {
					log("3 loop");
					win1 = win1 / 2;
				}
			}

			log("Before POP: Inside while at time: " + ct + " ===win1 = " + temp1 + "===win2 = " + temp2);
			for (Packet p : q1) {
				log("File1 packet number: " + p.id);
			}

			int qsize = q1.size();
			for (int l = 1; l <= qsize; l++) {
				q1.remove();
			}

		}

	}
}
