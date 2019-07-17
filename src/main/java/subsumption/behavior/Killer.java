package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

public class Killer extends Behavior {

	public Killer(final Arbitrator arbitrator, final int priority) {
		super(arbitrator, priority, Reading.Button);
	}

	@Override
	void onAccept(final int readingValue) {
		if (readingValue == Button.ID_ESCAPE) {
			sendWish(Wish.DIE);
		}
	}
}
