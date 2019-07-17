package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.CollisionSensor;
import subsumption.utility.Wish;

class TurnLeft extends Behavior {

    TurnLeft(final Arbitrator arbitrator, final int priority) {
        super(arbitrator, priority, Reading.Collision);
    }

    @Override
    void onAccept(int collisionValue) {
        if (collisionValue == CollisionSensor.COLLISION) {
            sendWish(Wish.TURN_LEFT);
        }
    }
}
