package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

public class Stop extends Behavior {

	public Stop(Arbitrator arbitrator, int priority) {
		super(arbitrator, priority, Reading.Button);
	}

	@Override
	public void run() {
		while (getReadingValue() != Button.ID_DOWN);
		sendWish(Wish.STOP);
		sendWish(Wish.LEDOFF);
	}
}