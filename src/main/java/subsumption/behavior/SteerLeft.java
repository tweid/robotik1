package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.UltrasoundSensor;
import subsumption.utility.Wish;

class SteerLeft extends Behavior {

    SteerLeft(Arbitrator arbitrator, int priority) {
        super(arbitrator, priority, Reading.Distance);
    }

    @Override
    void onAccept(final int distanceValue) {
        if (distanceValue == UltrasoundSensor.TOO_CLOSE) {
            sendWish(Wish.STEER_LEFT);
        }
    }
}