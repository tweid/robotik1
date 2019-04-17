package subsumption.sensor;

import org.jetbrains.annotations.NotNull;
import subsumption.behavior.Behavior;
import subsumption.common.Reading;


public abstract class SubSensor extends Thread {
	public static final String BUTTON_SENSOR = "ButtonSensor";
	public static final String COLLISION_SENSOR = "CollisionSensor";
	public static final String ULTRASOUND_SENSOR = "UltrasoundSensor";
	private final @NotNull Behavior[] behaviors;

	private final @NotNull Reading type;

	public SubSensor(@NotNull Behavior[] behaviors, @NotNull Reading type) {
		this.behaviors = behaviors;
		this.type = type;
		setDaemon(true);
	}

	void send(int value) {
		//System.out.println("sending type " + type + ", value=  " + value);
		for (Behavior behavior : behaviors)
			behavior.accept(type, value);
	}

	public static SubSensor make(String typeName, @NotNull Behavior[] behaviors) {
		switch (typeName) {
			case BUTTON_SENSOR:
				System.out.println("Button-Sensor");
				return new ButtonSensor(behaviors);
			case COLLISION_SENSOR:
				System.out.println("Collision-Sensor");
				return new CollisionSensor(behaviors);
			case ULTRASOUND_SENSOR:
				System.out.println("Ultrasound-Sensor");
				return new UltrasoundSensor(behaviors);
			default:
				new RuntimeException("unbekannter Sensortyp: " + typeName);
				return null;
		}
	}
}