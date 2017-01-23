package ComputerNetworkLab.src;


public class Packet {
	int sourceId;		
	int startTime;
	int endTime;
	int size;
	int ptime;
	int status = 1;

	
	public Packet(int a) {
		this.sourceId = a;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	public int getPtime() {
		return ptime;
	}

	public void setPtime(int ptime) {
		this.ptime = ptime;
	}


	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

