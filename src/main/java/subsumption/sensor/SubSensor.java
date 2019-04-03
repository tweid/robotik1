package subsumption.sensor;

import org.jetbrains.annotations.NotNull;
import subsumption.behavior.Behavior;
import subsumption.common.Reading;


public abstract class SubSensor extends Thread {
	private final @NotNull Behavior[] behaviors;

	private final @NotNull Reading type;

	public SubSensor(@NotNull Behavior[] behaviors, @NotNull Reading type) {
		this.behaviors = behaviors;
		this.type = type;
		setDaemon(true);
	}

	void send(int value) {
		System.out.println("sending type " + type + ", value=  " + value);
		for (Behavior behavior : behaviors)
			behavior.accept(type, value);
	}

	public static SubSensor make(String typeName, @NotNull Behavior[] behaviors) {
		switch (typeName) {
			case "ButtonSensor":
				return new ButtonSensor(behaviors);
			default:
				new RuntimeException("unbekannter Sensortyp: " + typeName);
				return null;
		}
	}
}