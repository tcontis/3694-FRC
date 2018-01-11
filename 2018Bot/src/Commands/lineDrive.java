package Commands;

import Subsystems.DriveTrain;
import Subsystems.Sensors;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class lineDrive {
	public static DifferentialDrive autoDrive = new DifferentialDrive(DriveTrain.right, DriveTrain.left);
	static double turningValue = (0.0 - Sensors.gyro.getAngle()) * Sensors.Kp;
	public static void LineDrive() {
		autoDrive.tankDrive(0.75, turningValue);
		Timer.delay(13); //time temporary
	}
}
