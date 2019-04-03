package subsumption.effector;

import subsumption.utility.Wish;
import org.jetbrains.annotations.NotNull;

public abstract class Effector {
	public abstract void accept(@NotNull Wish command);

	public static Effector make(String typeName) {
		switch (typeName) {
			case "Lamp":
				return new Lamp();
			case "Chassis":
				return new Chassis();
			default:
				throw new IllegalArgumentException("falscher Effektor");
		}
	}
}