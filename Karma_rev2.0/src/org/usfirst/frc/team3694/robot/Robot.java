package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//SmartDashboard Instantiations
		final String defaultAuto = "Default";
		final String customAuto = "My Auto";
		String autoSelected;
		SendableChooser<String> chooser = new SendableChooser<>();
	
	//Speed Controllers
		public Victor frontLeft = new Victor(0);
		public Victor frontRight = new Victor(1);
		public Victor rearLeft = new Victor(2);
		public Victor rearRight = new Victor(3);
		public Spark climb = new Spark(5);
		
	//Joysticks & Buttons
		public Joystick leftStick = new Joystick(0);
		public Joystick rightStick = new Joystick(1);
		public Joystick manStick = new Joystick(2);
		public JoystickButton reverse = new JoystickButton(leftStick, 1);
		
	//Gyroscope
		public static Gyro gyro = new AnalogGyro(0);
		public double kp;
		
	//timer
		public Timer timer = new Timer();
		public double timeOff = timer.get();
	
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		kp = gyro.getAngle();
		switch (autoSelected) {
		case customAuto:
			while (isEnabled() && isAutonomous()) {
				frontLeft.set(.65);
				rearLeft.set(.65);
				frontRight.set(.65);
				rearRight.set(.65);
				if (kp >= 0) {
					timer.reset();
					timer.start();
					rearLeft.set(.45);
					frontLeft.set(.45);
						timer.delay(timeOff);
				}
				if (kp <= 0) {
					timer.reset();
					timer.start();
					rearRight.set(.45);
					frontRight.set(.45);
						timer.delay(timeOff);
				}
				timer.stop();
			}
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	@Override
	public void teleopPeriodic() {
		
		//while enabled and teleopmode
			while (isEnabled() && isOperatorControl()) {
				
				//set left motors to left joystick squared
					frontLeft.set(Math.pow(leftStick.getY(), 2));
					rearLeft.set(Math.pow(leftStick.getY(), 2));
				
				//set right motors to right joystick squared
					frontRight.set(Math.pow(rightStick.getY(), 2));
					rearRight.set(Math.pow(rightStick.getY(), 2));
					
				//set the climber Spark to manStick y-axis
					climb.set(manStick.getY());
					
				//while the reverse button is being pressed
					while (reverse.get() == true) {
						
						//set the left motors to the inverse of the y axis of left stick  squared
							frontLeft.set(Math.pow(leftStick.getY(), 2) * -1);
							rearLeft.set(Math.pow(leftStick.getY(), 2) * -1);
						
						//set the right motors to the inverse of the y axis of the right stick squared
							frontRight.set(Math.pow(rightStick.getY(), 2) * -1);
							rearRight.set(Math.pow(rightStick.getY(), 2) * -1);
				}
			}
	}

	@Override
	public void testPeriodic() {
	}
}

