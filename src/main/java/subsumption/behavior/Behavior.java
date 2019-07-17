package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

/* Basisklasse für alle Verhalten. */
public abstract class Behavior {

	public static final String TURN_LEFT = "TurnLeft";
	public static final String KILLER = "Killer";
	public static final String BLINK = "Blink";
	public static final String STOP = "Stop";
	public static final String GO = "Go";
	public static final String STEER_LEFT = "SteerLeft";
	public static final String STEER_RIGHT = "SteerRight";
	private final Arbitrator arbitrator;
	private final int priority;
	private final Reading type;
	private int readingValue;

	public Behavior(final Arbitrator arbitrator, final int priority, final Reading type) {
		this.arbitrator = arbitrator;
		this.priority = priority;
		this.type = type;
	}

	public synchronized void accept(final Reading type, final int readingValue) {
		if (this.type == type) {
			this.readingValue = readingValue;
			onAccept(readingValue);
		}
	}

	abstract void onAccept(final int readingValue);

	void sendWish(final Wish wish) {
		//System.out.println("sending wish: " + wish);
		arbitrator.accept(wish, priority);
	}

	//statische Factory Methode für Verhalten
	public static Behavior make(final String typeName, final Arbitrator arbitrator, final int priority) {
		switch (typeName) {
			case GO:
				return new Go(arbitrator, priority);
			case STOP:
				return new Stop(arbitrator, priority);
			case KILLER:
				return new Killer(arbitrator, priority);
			case TURN_LEFT:
				return new TurnLeft(arbitrator, priority);
			case STEER_LEFT:
				return new SteerLeft(arbitrator, priority);
			case STEER_RIGHT:
				return new SteerRight(arbitrator, priority);
			default:
				throw new RuntimeException("Unbekanntes Verhalten");
		}

	}
}