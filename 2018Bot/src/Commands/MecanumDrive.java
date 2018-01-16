package Commands;

import org.usfirst.frc.team3694.robot.OI;

import Subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class MecanumDrive {
	
	static Joystick driveStick;
	static Victor frontLeft;
	static Victor frontRight;
	static Victor backLeft;
	static Victor backRight;
	static double xSpeed;
	static double ySpeed;
	
	public MecanumDrive(Joystick _driveStick, Victor _frontLeft, Victor _backLeft, Victor _frontRight, Victor _backRight) {
		_driveStick = OI.driveStick;
		_frontLeft = DriveTrain.frontLeft;
		_backLeft = DriveTrain.rearLeft;
		_frontRight = DriveTrain.frontRight;
		_backRight = DriveTrain.rearRight;
		driveStick = _driveStick;
		frontLeft = _frontLeft;
		frontRight = _frontRight;
		backLeft = _backLeft;
		backRight = _backRight;
	}
	
	public static void setSpeeds(double fL, double bL, double fR, double bR){
		frontLeft.set(fL);
		backLeft.set(bL);
		frontRight.set(fR);
		backRight.set(bR);
	}
	
	public static void drive(){
		
		//Get axis values from joystick to avoid calling methods to many times.
		xSpeed = driveStick.getX();
		ySpeed = driveStick.getY();
		
		//If trigger button is pressed down, strafe, else function normally.
		while(driveStick.getTrigger() == true){

			//Strafe left if joystick is left, else strafe right. Side that you want to go towards spins inwards while other spins outwards.
			if(xSpeed < 0){
				setSpeeds(xSpeed, -xSpeed, -xSpeed, xSpeed);
			}else if(xSpeed > 0){
				setSpeeds(-xSpeed, xSpeed, xSpeed, -xSpeed);
			}
			
		}
		while (driveStick.getTrigger() == false){
			//Basically arcadeDrive
			if(xSpeed == 0){
				setSpeeds(ySpeed, ySpeed, ySpeed, ySpeed);
			}else{
				setSpeeds(xSpeed, xSpeed, -xSpeed, -xSpeed);
			}
		}
	}
}
