package subsumption.sensor;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import subsumption.behavior.Behavior;
import static subsumption.common.Reading.Distance;

public class UltrasoundSensor extends SubSensor
{
    private final float[] floats;
    public static final int TOO_CLOSE = 0;
    public static final int PERFECT = 1;
    public static final int TOO_FAR = 2;
    public static final int NEAR_BORDER = 5;
    public static final int DISTANT_BORDER = 8;
    public static final int SCALE_TO_CM = 100;


    private SensorModes sensor3 = new EV3UltrasonicSensor(SensorPort.S3);
    private SampleProvider ultrasonic = sensor3.getMode("Distance");

    public UltrasoundSensor(Behavior[] behaviors) {
        super(behaviors, Distance);
        floats = new float[1];
    }

    public void run(){
        float distance = 10;
        float sample[] = new float[ultrasonic.sampleSize()];
        while (true){
            ultrasonic.fetchSample(sample, 0);
            distance = sample[0] * SCALE_TO_CM;

            if (distance <= NEAR_BORDER) {
                send(TOO_CLOSE);
//                System.out.println("Too Close");
            } else if (distance <= DISTANT_BORDER) {
                send(PERFECT);
//                System.out.println("Perfect");
            } else {
                send(TOO_FAR);
//                System.out.println("Too Far");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}