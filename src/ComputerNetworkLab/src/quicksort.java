
package ComputerNetworkLab.src;

import java.util.Comparator;

public class quicksort implements Comparator<Packet> {

	public int compare(Packet one, Packet two) {
		return Integer.compare(one.getPtime(), two.getPtime());
	}
}