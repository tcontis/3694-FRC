package Commands;

import Subsystems.DriveTrain;
import Subsystems.Sensors;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class lineDrive {
	public static RobotDrive autoDrive = new RobotDrive(DriveTrain.frontLeft, DriveTrain.rearLeft, DriveTrain.frontRight, DriveTrain.rearRight);
	static double turningValue = (0.0 - Sensors.gyro.getAngle()) * Sensors.Kp;
	public static void LineDrive() {
		autoDrive.tankDrive(0.75, turningValue);
		Timer.delay(13); //time temporary
	}
}
