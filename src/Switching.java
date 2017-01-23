import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import ComputerNetworkLab.src.Source;
import ComputerNetworkLab.src.Packet;
import ComputerNetworkLab.src.quicksort;


public class Switching {
	private static Scanner in;
	public static Comparator<Packet> comparator = new quicksort();
	public static Queue<Packet> queue = new PriorityQueue<Packet>(100, comparator);
	public static Queue<Packet> sq = new PriorityQueue<Packet>(100, comparator);

	public static int length = 20;
	public static int bandwidth = 10;
	int totalPacketsDrop = 0;
	public static void main(String[] args) {
		//new Switching().circuitSwitching();
		new Switching().packetSwitching();
	}

	public void packetSwitching() {
		in = new Scanner(System.in);
		System.out.println("Enter number of sources: ");
		int numberOfSources = in.nextInt();
		Source[] sourceArray = new Source[numberOfSources];
		for (int i = 0; i < numberOfSources; i++) {
			sourceArray[i] = new Source(i, 1, 1);
			sourceArray[i].generatePacket(ThreadLocalRandom.current().nextInt(1, 10));
		}


		for (Source s : sourceArray) {
			for(Packet p : s.getQ()){
				s.getQ().remove();
				queue.add(p);
			}
		}
		
	
		
//	for (Packet p : queue){
//			System.out.println("elements in queue are : " +  p.getPtime() + "source id : " + p.getSourceId());
//		}

		System.out.println("How long should the simulation run(in secs) ");
		int totalTime = in.nextInt();
		int currentTime = 1;

		while(currentTime < totalTime) {
			
				Packet s = queue.peek();
				if(s.getPtime() <= totalTime){
					
				Packet q = queue.poll();
				System.out.println("current time: " + currentTime);
				System.out.println("elements: "+ q.getPtime());
		
				//packet has reached the switch	
				if(q.getStatus() == 2){
					System.out.println("sending to sink: " + q.getPtime());
					continue;
				}
					if(q.getStatus()==1){
						System.out.println("start time: " + q.getPtime());
						q.setPtime(currentTime + length / bandwidth);
						q.setStatus(2);
						queue.add(q);
						
						//new packet from a source_id is getting added in the queue
						sourceArray[q.getSourceId()].generatePacket(currentTime + ThreadLocalRandom.current().nextInt(1, 10));
						Packet ww = sourceArray[q.getSourceId()].getQ().remove();
						queue.add(ww);
						System.out.println("adding next element: "+ ww.getPtime());
					}
			
				//sourceArray[q.getSourceId()].addToSumOfdelayOfAllPackets(q.getEndTime() - q.getStartTime());
				s = queue.peek();
				currentTime+=1;
				}
				
		}
		
		
		for (Source s : sourceArray) {

		System.out.println(
				"The Sum Of Delays of all packets in " + s.getId() + "is " + s.getSumOfdelayOfAllPackets());
		}
		System.out.println("total packet drop - " + totalPacketsDrop);
	
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
