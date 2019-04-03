import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class HelloEV3
{
    public static void main(String... args) {
        LCD.clearDisplay();
        LCD.drawString("Hello EV3", 0, 0);
        Button.waitForAnyPress();
    }
}

