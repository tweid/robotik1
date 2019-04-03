package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.CollisionSensor;
import subsumption.utility.Wish;

public class TurnLeft extends Behavior {

    public TurnLeft(Arbitrator arbitrator, int priority) {
        super(arbitrator, priority, Reading.Button);
    }

    @Override
    public void run() {
        int collision = getReadingValue();
        while (CollisionSensor.COLLISION != collision) {
            System.out.println("Turn Left got:" + collision);
            collision = getReadingValue();
        }

        // TODO: 03.04.19 Doesnt Reach this Point
        sendWish(Wish.TURN_LEFT);
    }

}
