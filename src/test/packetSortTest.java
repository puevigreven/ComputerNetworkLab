package test;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import ComputerNetworkLab.src.Packet;
import ComputerNetworkLab.src.quicksort;


public class packetSortTest {
	public static Comparator<Packet> comparator = new quicksort();
	public static Queue<Packet> q = new PriorityQueue<Packet>(100, comparator);
	public static void main(String[] args){
	for(int i = 1; i<10 ;i++){
		Packet p1 = new Packet(i);
		p1.setPtime(i + ThreadLocalRandom.current().nextInt(1, 10));
		q.add(p1);
	}
	
	for(Packet p : q){
		System.out.println("p time: "+ p.getPtime());
	}
	while(!q.isEmpty()){
		System.out.println("removed : "+ q.remove().getPtime());

	}

	
	}
	
	
}
