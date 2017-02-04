package ComputerNetworkLab.src;

import java.util.LinkedList;
import java.util.Queue;

public class Source {
	int id;
	int packetSendingRate;
	int packetGenrationRate;
	int runningTime;
	int sumOfdelayOfAllPackets = 0;
	Queue<Packet> q = new LinkedList<>();

	public Source(int id, int packetSendingRate, int packetGenrationRate) {
		this.id = id;
		this.packetSendingRate = packetSendingRate;
		this.packetGenrationRate = packetGenrationRate;
	}

	public Source(int id) {
		this.id = id;
	}

	public int getSumOfdelayOfAllPackets() {
		return sumOfdelayOfAllPackets;
	}

	public void addToSumOfdelayOfAllPackets(int s) {
		this.sumOfdelayOfAllPackets = this.sumOfdelayOfAllPackets + s;
	}

	public Queue<Packet> getQ() {
		return q;
	}

	public void setQ(Queue<Packet> q) {
		this.q = q;
	}

	public void generatePacket(int a) {
		int i = 0;
		while (i < this.packetGenrationRate) {
			Packet p = new Packet(this.id);
			p.setStartTime(a);
			p.setPtime(a);
			// System.out.println("START OF PACEKT " + p.getStartTime());
			this.q.add(p);
			i++;
		}
	}

	public void sendPacket(int a) {
		Packet p;
		int r = 0;
		while (r < packetSendingRate) {
			if (!q.isEmpty()) {
				p = q.remove();
				p.setEndTime(a);
				sumOfdelayOfAllPackets = sumOfdelayOfAllPackets + (a - p.getStartTime());
				System.out.println("The packet with Source id " + p.getSourceId() + " is sent");
			}
			r++;
		}

	}

	public int getPacketGenrationRate() {
		return packetGenrationRate;
	}

	public void setPacketGenrationRate(int packetGenrationRate) {
		this.packetGenrationRate = packetGenrationRate;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPacketSendingRate() {
		return packetSendingRate;
	}

	public void setPacketSendingRate(int packetSendingRate) {
		this.packetSendingRate = packetSendingRate;
	}

}
