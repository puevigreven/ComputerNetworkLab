import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import ComputerNetworkLab.src.Source;
import ComputerNetworkLab.src.Packet;
import ComputerNetworkLab.src.quicksort;

public class Switching {
	private static Scanner in;
	public static Comparator<Packet> comparator = new quicksort();
	static int maxQueueSize = 100;
	public static Queue<Packet> queue = new PriorityQueue<Packet>(maxQueueSize, comparator);
	public static Queue<Packet> sq = new PriorityQueue<Packet>(100, comparator);

	public static int length = 20;
	public static int bandwidth = 10;
	int totalPacketsDrop = 0;

	public static void main(String[] args) {
		// new Switching().circuitSwitching();
		new Switching().packetSwitching();
	}

	public void packetSwitching() {
		in = new Scanner(System.in);
		System.out.println("Enter number of sources: ");
		int numberOfSources = in.nextInt();
		Source[] sourceArray = new Source[numberOfSources];

		// for (Packet p : queue){
		// System.out.println("elements in queue are : " + p.getPtime() +
		// "source id : " + p.getSourceId());
		// }

	//	System.out.println("How long should the simulation run(in secs) ");
		int totalTime = 200;
		int currentTime = 1;

		while (currentTime <= totalTime) {
			//System.out.println("current time: " + currentTime );
			
			for (int i = 0; i < numberOfSources; i++) {
				sourceArray[i] = new Source(i, 1, 1);
				sourceArray[i].generatePacket(currentTime);
			}
			
			//assuming L/B is less than 1 sec
			
			for (Source s : sourceArray) {
				for (Packet p : s.getQ()) {
					if (queue.size() < maxQueueSize) {
						queue.add(p);
						s.getQ().poll();
					} else {
						totalPacketsDrop += 1;
					}
				}
			}
			
			//assuming L'/B' is less than 1 sec
			int k = 0;
			while(k < 5){
			Packet q = queue.poll();
			q.setEndTime(currentTime);
			sourceArray[q.getSourceId()].addToSumOfdelayOfAllPackets(q.getEndTime() - q.getStartTime());
			//System.out.println("elements: " + q.getPtime());
			k++;
			}
			currentTime += 1;
		}
		int sumofAllDelay = 0 ;
		
		for (Source s : sourceArray) {

			//System.out.println("The Sum Of Delays of all packets in " + s.getId() + " is " + s.getSumOfdelayOfAllPackets());
			sumofAllDelay = sumofAllDelay + s.getSumOfdelayOfAllPackets();
		}
		System.out.println("total packet drop - " + totalPacketsDrop  + " total delay: " + sumofAllDelay);

	}

	public void circuitSwitching() {
		in = new Scanner(System.in);
		System.out.println("Enter number of sources: ");
		int numberOfSources = in.nextInt();
		Source[] sourceArray = new Source[numberOfSources];
		for (int i = 0; i < numberOfSources; i++) {
			System.out.println("Enter the packet Sending Rate for " + i + " node");
			int packerSendingRate = in.nextInt();
			System.out.println("Enter the packet Gentation Rate for " + i + " node");
			int packetGenerationRate = in.nextInt();

			sourceArray[i] = new Source(i, packerSendingRate, packetGenerationRate);
		}
		System.out.println("How long should the simulation run(in secs) ");
		int totalTime = in.nextInt();
		System.out.println("Enter the packet Time Division for each node");
		int packetTime = in.nextInt();
		int currentTime = 0;
		while (currentTime < totalTime) {

			for (Source s : sourceArray) {
				s.generatePacket(currentTime);
			}

			int quo = currentTime / packetTime;
			int run = quo % numberOfSources;
			sourceArray[run].sendPacket(currentTime);

			currentTime++;

		}
		int average_delay = 0;
		for (Source s : sourceArray) {
			Queue<Packet> p = s.getQ();
			for (Packet d : p) {
				d.setEndTime(totalTime);
				int delay = d.getEndTime() - d.getStartTime();
				// s.addToSumOfdelayOfAllPackets(delay);
			}
			average_delay += s.getSumOfdelayOfAllPackets();
			System.out.println(
					"The Sum Of Delays of all packets in " + s.getId() + "is " + s.getSumOfdelayOfAllPackets());
		}

		System.out.println(" ==== > average delay = " + average_delay);
	}

}
