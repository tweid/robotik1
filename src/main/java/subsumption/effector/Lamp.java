package subsumption.effector;

import subsumption.utility.Wish;
import lejos.hardware.Button;

class Lamp extends Effector
{
	@Override
	public void accept(Wish command)
	{
		switch (command){
			case LEDGREEN:
				Button.LEDPattern(1);
				break;
			case LEDOFF:
				Button.LEDPattern(0);
				break;
			default: //not my command
				break;
		}
	}

}
