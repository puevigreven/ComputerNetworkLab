package tcpProtocol;

public class Packet {

	int id = 0;
	int start = 1;
	int end = 0;

	public Packet(int i) {
		this.id = i;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Packet(int i, int start) {
		this.id = i;
		this.start = start;
	}

}
