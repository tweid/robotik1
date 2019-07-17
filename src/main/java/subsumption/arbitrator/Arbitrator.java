package subsumption.arbitrator;

import subsumption.effector.Effector;
import subsumption.utility.Wish;

public class Arbitrator {
    private final int MAX_PRIO = 10;
    private final Wish[] wishes = new Wish[MAX_PRIO];

    private final Effector[] effectors;

    public Arbitrator(final Effector... effectors) {
        this.effectors = effectors;
    }

    public synchronized void accept(final Wish wish, final int priority) {
        if (wish == Wish.DIE) {
            justDieAlready();
        }
        if (wish == Wish.NOTHING)
            wishes[priority] = null;
        else {
            wishes[priority] = wish;
            boolean wishHasTopPriority = true;
            for (int p = priority + 1; p < MAX_PRIO; p++) {
                if (wishes[p] != null) {
                    wishHasTopPriority = false;
                    break;
                }
            }

            if (wishHasTopPriority) {
                for (final Effector effector : effectors) {
                    effector.accept(wish);
                }
            }

        }

    }

    public void justDieAlready() {
        notifyAll();
    }

    public synchronized void waitUntilDie() throws InterruptedException {
        wait();
    }
}