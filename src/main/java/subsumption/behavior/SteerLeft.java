package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.sensor.UltrasoundSensor;
import subsumption.utility.Wish;

public class SteerLeft extends Behavior {

    public SteerLeft(Arbitrator arbitrator, int priority) {
        super(arbitrator, priority, Reading.Distance);
    }

    @Override
    public void run() {
        while (true) {
            int distance = getReadingValue();
            while (UltrasoundSensor.TOO_CLOSE != distance) {
//                System.out.println("SteerLeft got:" + distance);
                distance = getReadingValue();
            }

//            System.out.println("Sending wishes SteerLeft");
            sendWish(Wish.STEER_LEFT);
        }
    }

}