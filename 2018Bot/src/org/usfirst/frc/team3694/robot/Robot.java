
package org.usfirst.frc.team3694.robot;


import Commands.MecanumDrive;
import Commands.lineDrive;
import Subsystems.DriveTrain;
import Commands.manDrive;
import Subsystems.Sensors;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	//Smart Dashboard
	private static final String crossLine = "Cross the Line";
	private static final String switchLeft = "far Left putting cube into switch";
	private static final String switchMiddle = "middle putting cube into switch";
	private static final String switchRight = "far Right putting cube into switch";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	//Subsystems n' stuff
	OI Interface = new OI();
	DriveTrain drivetrain = new DriveTrain();
	Sensors sensors = new Sensors();
	
	//FMS
	String gameData;
	
	
	@Override
	public void robotInit() {
		//Smart Dashboard
		m_chooser.addDefault("Cross the Line", crossLine);
		m_chooser.addObject("far Left putting cube into switch", switchLeft);
		m_chooser.addObject("Middle putting cube into switch", switchMiddle);
		m_chooser.addObject("far Right putting cube into switch", switchRight);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		//FMS
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);
	}
	
	//This function is called periodically during autonomous.
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case crossLine:
				default:
					lineDrive.LineDrive();	
					Timer.delay(3.00);
					break;
			case switchLeft:
				if(gameData.charAt(0) == 'L')
				{
					Commands.switchLeft.SwitchLeftLeft();
				} 
				else {
					Commands.switchLeft.SwitchLeftRight();
				}
				break;
			case switchMiddle:
				if(gameData.charAt(0) == 'L')
				{
					//Put left auto code here
				} 
				else {
					//Put right auto code here
				}
				break;
			case switchRight:
				if(gameData.charAt(0) == 'L')
				{
					//Put left auto code here
				} 
				else {
					//Put right auto code here
				}
				break;	
		}
	}

	//This function is called periodically during operator control.
	@Override
	public void teleopPeriodic() {
		while (isOperatorControl() && isEnabled()) {
			MecanumDrive.drive();
			manDrive.Manipulator();
		}
	}

	
    //This function is called periodically during test mode.
	@Override
	public void testPeriodic() {
	}
}
