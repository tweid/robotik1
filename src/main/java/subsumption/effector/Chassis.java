package subsumption.effector;

import subsumption.utility.Wish;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Chassis extends Effector
{
	final RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	final RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);

	@Override
	public void accept(Wish command)
	{
		if (command == Wish.FORWARD) {
			motorA.forward();
			motorD.forward();
		}
		else if(command == Wish.STOP) {
			motorA.stop();
			motorD.stop();
		}
	}

}