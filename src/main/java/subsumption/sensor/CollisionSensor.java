package subsumption.sensor;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import subsumption.behavior.Behavior;
import subsumption.common.Reading;

public class CollisionSensor extends SubSensor
{
    public static final int COLLISION = 1;
    public static final int NO_COLLISION = 0;
    private final float[] floats;

    private SensorModes sensor1 = new EV3TouchSensor(SensorPort.S1);
    private SampleProvider touch1 = sensor1.getMode("Touch");
    private SensorModes sensor2 = new EV3TouchSensor(SensorPort.S4);
    private SampleProvider touch2 = sensor2.getMode("Touch");

    public CollisionSensor(Behavior[] behaviors) {
        super(behaviors, Reading.Collision);
        floats = new float[1];
    }

    public void run(){
        while (true){
            touch1.fetchSample(floats, 0);
            if (floats[0] == COLLISION) {
                send(COLLISION);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            touch2.fetchSample(floats, 0);
            if (floats[0] == COLLISION) {
                send(COLLISION);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
