package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem {
    
	public static Victor leftDrive;
	public static Victor rightDrive;
	
	public Chassis() {
		leftDrive = new Victor(RobotMap.leftDrive);
		rightDrive = new Victor(RobotMap.rightDrive);
	}
    public void initDefaultCommand() {
       
    }
}

