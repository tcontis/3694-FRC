package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3694.robot.Motors;
import org.usfirst.frc.team3694.robot.Controls;

public class Robot extends IterativeRobot {
	//SmartDashboard stuffs
	final String ArcadeDriveLeft = "Arcade Drive with left hand";
	final String ArcadeDriveRight = "Arcade Drive with right hand";
	final String TankDrive = "TankDrive";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	

	@Override
	public void robotInit() {
		//More SmartDashboard stuffs
		chooser.addObject("Arcade Drive with left hand", ArcadeDriveLeft);
		chooser.addObject("Arcade Drive with Right Hand", ArcadeDriveRight);
		chooser.addDefault("Tank Drive", TankDrive);
		SmartDashboard.putData("Auto choices", chooser);
		
		//RobotDrive
		
	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		while (isEnabled() && isAutonomous()) {
			Motors.left.set(0.7665);
			Motors.right.set(0.7665);
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		while (isEnabled() && isOperatorControl()) {
			switch (autoSelected) {
			case TankDrive:
				default:
				Motors.left.set(Controls.leftStick.getY());
				Motors.right.set(Controls.rightStick.getY());
				break;
			case ArcadeDriveLeft:
			Motors.robodrive.arcadeDrive(Controls.leftStick);
				break;
			case ArcadeDriveRight:
				Motors.robodrive.arcadeDrive(Controls.rightStick);
					break;
			}
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

