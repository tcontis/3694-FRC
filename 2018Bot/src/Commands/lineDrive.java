package Commands;

import Subsystems.DriveTrain;
import Subsystems.Sensors;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class lineDrive {
	public static DifferentialDrive autoDrive = new DifferentialDrive(DriveTrain.frontRight, DriveTrain.frontLeft);
	static double turningValue = (0.0 - Sensors.gyro.getAngle()) * Sensors.Kp;
	public static void LineDrive() {
		autoDrive.tankDrive(1.0, turningValue);
		DriveTrain.rearLeft.set(DriveTrain.frontLeft.getSpeed());
		DriveTrain.rearRight.set(DriveTrain.frontRight.getSpeed());
	}
}
