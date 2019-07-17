package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.UltrasoundSensor;
import subsumption.utility.Wish;

public class SteerRight extends Behavior {

    public SteerRight(Arbitrator arbitrator, int priority) {
        super(arbitrator, priority, Reading.Distance);
    }

    @Override
    void onAccept(final int distanceValue) {
        if (distanceValue == UltrasoundSensor.TOO_FAR) {
            sendWish(Wish.STEER_RIGHT);
        }
    }
}