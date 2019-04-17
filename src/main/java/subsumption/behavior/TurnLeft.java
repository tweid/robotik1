package subsumption.behavior;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.CollisionSensor;
import subsumption.utility.Wish;

public class TurnLeft extends Behavior {

    public TurnLeft(Arbitrator arbitrator, int priority) {
        super(arbitrator, priority, Reading.Collision);
    }

    @Override
    public void run() {
        while (true) {
            int collision = getReadingValue();
            while (CollisionSensor.COLLISION != collision) {
//                System.out.println("Turn Left got:" + collision);
                collision = getReadingValue();
            }

//            System.out.println("Sending wishes Left turn");
            sendWish(Wish.TURN_LEFT);
        }
    }

}
