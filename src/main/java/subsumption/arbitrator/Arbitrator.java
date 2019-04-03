package subsumption.arbitrator;

import subsumption.effector.Effector;
import subsumption.utility.Wish;

public class Arbitrator
{
	private final int MAX_PRIO = 10;
	private final Wish[] wishes = new Wish[MAX_PRIO];

	private final Effector[] effectors;

	public Arbitrator(Effector... effectors)
	{
		this.effectors = effectors;
	}

	public synchronized void accept(Wish wish, int priority)
	{
		if (wish == Wish.NOTHING)
			wishes[priority] = null;
		else
		{
			// Wunsch in Tabelle eintragen.
			wishes[priority] = wish;
			// im Array alle Wünsche durchlaufen ab der Priorität dieses
			// aktuellen wunsches + 1
			// gibt es irgendeine höhere?
			boolean wishHasTopPriority = true;
			for (int p = priority + 1; p < MAX_PRIO; p++)
				if (wishes[p] != null)
				{
					wishHasTopPriority = false;
					break;
				}
			//falls dieser Wunsch die höchste Prio hatte
			//diesen Wunsch an alle Effektoren weitergeben
			if (wishHasTopPriority)
				for (Effector effector : effectors)
					effector.accept(wish);
		}

	}
}