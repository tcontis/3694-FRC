package Commands;

import Subsystems.DriveTrain;

public class autoDrive {
	public static void straightDrive () {
		DriveTrain.frontLeft.set(0.65);
		DriveTrain.rearLeft.set(0.65);
		DriveTrain.frontRight.set(0.65);
		DriveTrain.rearRight.set(0.65);
	}
	public static void strafeRight () {
		DriveTrain.frontLeft.set(-0.65);
		DriveTrain.rearLeft.set(0.65);
		DriveTrain.frontRight.set(0.65);
		DriveTrain.rearRight.set(-0.65);
	}
	public static void strafeLeft () {
		DriveTrain.frontLeft.set(0.65);
		DriveTrain.rearLeft.set(-0.65);
		DriveTrain.frontRight.set(-0.65);
		DriveTrain.rearRight.set(0.65);
	}
	public static void turnRight () {
		DriveTrain.frontLeft.set(1.0);
		DriveTrain.rearLeft.set(1.0);
		DriveTrain.frontRight.set(.35);
		DriveTrain.rearRight.set(.35);
	}
	public static void turnleft () {
		DriveTrain.frontRight.set(1.0);
		DriveTrain.rearRight.set(1.0);
		DriveTrain.frontLeft.set(.35);
		DriveTrain.rearLeft.set(.35);
	}
}
