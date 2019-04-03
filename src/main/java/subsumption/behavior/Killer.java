package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;

public class Killer extends Behavior {

	public Killer(Arbitrator arbitrator, int priority) {
		super(arbitrator, priority, Reading.Button);
		setDaemon(false);
	}

	@Override
	public void run() {
		while (getReadingValue() != Button.ID_ESCAPE);
	}
}
