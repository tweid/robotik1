package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

class Stop extends Behavior {

    Stop(final Arbitrator arbitrator, final int priority) {
        super(arbitrator, priority, Reading.Button);
    }

    @Override
    void onAccept(final int buttonValue) {
        if (buttonValue == Button.ID_DOWN) {
            sendWish(Wish.STOP);
            sendWish(Wish.LEDOFF);
            sendWish(Wish.NOTHING);
        }
    }
}