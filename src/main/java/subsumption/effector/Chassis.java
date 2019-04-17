package subsumption.effector;

import subsumption.utility.Wish;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Chassis extends Effector {
    public static final double STEERING_FACTOR = 0.5;
    public static final int STEERING_TIME = 40;
    final RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
    final RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
    final float friction = 1.5f;

    @Override
    public void accept(Wish command) {
        if (command == Wish.FORWARD) {
            forward();
        } else if (command == Wish.STOP) {
            stop();
        } else if (command == Wish.TURN_LEFT) {
            turnLeft();
        } else if (command == Wish.STEER_LEFT) {
            steerLeft();
        } else if (command == Wish.STEER_RIGHT) {
            steerRight();
        }

    }

    private void steerRight() {
        System.out.println("steerRight");
        int originSpeed = motorA.getSpeed();
        motorA.setSpeed((int) (originSpeed * STEERING_FACTOR));
        try {
            Thread.sleep(STEERING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorA.setSpeed(originSpeed);
    }

    private void steerLeft() {
        System.out.println("steerLeft");
        int originSpeed = motorD.getSpeed();
        motorD.setSpeed((int) (originSpeed * STEERING_FACTOR));
        try {
            Thread.sleep(STEERING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorD.setSpeed(originSpeed);
    }

    private void stop() {
        motorA.stop();
        motorD.stop();
    }

    private void forward() {
        motorA.forward();
        motorD.forward();
    }

    private void turnLeft() {
        motorA.backward();
        motorD.backward();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motorA.rotate((int) (360 * friction));
        motorD.stop();

        forward();
    }

}