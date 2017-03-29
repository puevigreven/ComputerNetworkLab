package tcpProtocolLab;

public class Packet {

	int id = 0;
	int start = 1;
	int destId = 0;
	
	public int getDestId() {
		return destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

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


	public Packet(int i, int start , int destId) {
		this.id = i;
		this.start = start;
		this.destId = destId;
	}

}
