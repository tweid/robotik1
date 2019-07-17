package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

public class Go extends Behavior {

	Go(Arbitrator arbitrator, int priority) {
		super(arbitrator, priority, Reading.Button);
	}

	@Override
	void onAccept(final int buttonValue) {
		if (buttonValue != Button.ID_ENTER) {
			sendWish(Wish.FORWARD);
			sendWish(Wish.NOTHING);
		}
	}
}