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
    public void run() {
        while (true) {
            int distance = getReadingValue();
            while (UltrasoundSensor.TOO_FAR != distance) {
//                System.out.println("SteerRight got:" + distance);
                distance = getReadingValue();
            }

//            System.out.println("Sending wishes SteerRight");
            sendWish(Wish.STEER_RIGHT);
        }
    }

}