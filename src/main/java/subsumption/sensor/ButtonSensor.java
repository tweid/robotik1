package subsumption.sensor;

import lejos.hardware.Button;
import subsumption.behavior.Behavior;
import subsumption.common.Reading;

public class ButtonSensor extends SubSensor
{
	public ButtonSensor(Behavior[] behaviors) {
		super(behaviors, Reading.Button);

	}
	public void run(){
		while (true){
			int button = Button.waitForAnyPress();
			send(button);
		}

	}
}
