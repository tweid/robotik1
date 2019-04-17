package subsumption;

import org.jetbrains.annotations.NotNull;

import lejos.hardware.Button;
import subsumption.arbitrator.Arbitrator;
import subsumption.behavior.Behavior;
import subsumption.effector.Effector;
import subsumption.sensor.SubSensor;

public class SubsumptionMain
{
	public static void main(String... args)
	{
		Effector[] effectors = {
				Effector.make("Lamp"),
				Effector.make("Chassis")};
		Arbitrator arbitrator = new Arbitrator(effectors);

		//Array aller Verhalten
		//jedes Mal, wenn man eine Priority verwendet hat, hochzählen
		//fuer das nächste Verhalten.

		int priority = 1;
				Behavior[] behaviors = {
						Behavior.make(Behavior.BLINK, arbitrator, priority++),
						Behavior.make(Behavior.STEER_RIGHT, arbitrator, priority),
						Behavior.make(Behavior.STEER_LEFT, arbitrator, priority++),
						//Behavior.make(Behavior.TURN_LEFT, arbitrator, priority++),
						Behavior.make(Behavior.GO, arbitrator, priority++),
						Behavior.make(Behavior.STOP, arbitrator, priority++),
				Behavior.make(Behavior.KILLER, arbitrator, priority++)
		};

		//Konkrete Sensoren wie hier ButtonSensor erzeugen
		SubSensor[] sensors = {
				SubSensor.make(SubSensor.BUTTON_SENSOR, behaviors),
				//SubSensor.make(SubSensor.COLLISION_SENSOR, behaviors),
				SubSensor.make(SubSensor.ULTRASOUND_SENSOR, behaviors)
		};

		//Starten aller Threads
		for (Behavior behavior: behaviors)
			behavior.start();
		for(SubSensor sensor: sensors)
			sensor.start();
	}
}