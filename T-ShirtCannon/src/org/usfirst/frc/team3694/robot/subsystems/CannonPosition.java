package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.commands.cannonDrive;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CannonPosition extends Subsystem {
	public static SpeedController XAxis = new Victor(3);
	public static SpeedController YAxis = new Victor(4);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new cannonDrive());
    }
}

