package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.utility.Wish;

public class Blink extends Behavior {

	public Blink(Arbitrator arbitrator, int priority) {
		//Kein Event, kein Readingtype
		super(arbitrator, priority, null);
	}

	@Override
	public void run() {
		try {
			while (true) {
				sendWish(Wish.LEDGREEN);
				Thread.sleep(500);
				sendWish(Wish.LEDOFF);
				Thread.sleep(500);
			}
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
