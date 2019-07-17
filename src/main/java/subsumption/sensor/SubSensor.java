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

	public SubSensor(final @NotNull Behavior[] behaviors, final @NotNull Reading type) {
		this.behaviors = behaviors;
		this.type = type;
		setDaemon(true);
	}

	void send(final int value) {
		for (final Behavior behavior : behaviors) {
			behavior.accept(type, value);
		}
	}

	public static SubSensor make(final String typeName, final @NotNull Behavior[] behaviors) {
		switch (typeName) {
			case BUTTON_SENSOR:
				return new ButtonSensor(behaviors);
			case COLLISION_SENSOR:
				return new CollisionSensor(behaviors);
			case ULTRASOUND_SENSOR:
				return new UltrasoundSensor(behaviors);
			default:
				new RuntimeException("unbekannter Sensortyp: " + typeName);
				return null;
		}
	}
}