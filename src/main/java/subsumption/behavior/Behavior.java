package subsumption.behavior;

import subsumption.arbitrator.Arbitrator;
import subsumption.common.Reading;
import subsumption.utility.Wish;

/* Basisklasse für alle Verhalten. */
public abstract class Behavior extends Thread {

	private final Arbitrator arbitrator;
	private final int priority;
	private final Reading type;
	private int readingValue;

	public Behavior(Arbitrator arbitrator, int priority, Reading type) {
		this.arbitrator = arbitrator;
		this.priority = priority;
		this.type = type;
		setDaemon(true);
	}

	@Override
	public abstract void run();

	public synchronized void accept(Reading type, int readingValue) {
		if (this.type == type) {
			this.readingValue = readingValue;
			notifyAll();
		}

	}

	synchronized int getReadingValue() {
		//Verhalten wartet, bis ein neuer Wert eintrifft.
		try {
			wait();
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
		return readingValue;

	}

	void sendWish(Wish wish) {
		System.out.println("sending wish: " + wish);
		arbitrator.accept(wish, priority);
	}

	//statische Factory Methode für Verhalten
	public static Behavior make(String typeName, Arbitrator arbitrator, int priority) {
		switch (typeName) {
			case "Go":
				return new Go(arbitrator, priority);
			case "Stop":
				return new Stop(arbitrator, priority);

			case "Blink":
				return new Blink(arbitrator, priority);

			case "Killer":
				return new Killer(arbitrator, priority);

			default:
				throw new RuntimeException("Unbekanntes Verhalten");
		}

	}
}