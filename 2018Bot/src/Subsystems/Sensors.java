package Subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Sensors {
	public static Gyro gyro = new AnalogGyro(1);
	public static double Kp = .03;
	
}
